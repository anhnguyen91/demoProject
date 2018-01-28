/**
 * 
 */
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import utilities.DBUtils;

/**
 * @author anh
 *
 */
@WebServlet(urlPatterns = { "/deleteCategory" })
public class DeleteCategory extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = (Connection)request.getAttribute("MY_CONN");

        String code = (String) request.getParameter("code");
 
        Category category = null;
    	String errString = null;

        try {
        	category = DBUtils.getCategory(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errString = "Error on get information from DB.";
        }

        if (errString != null && category == null) {
            response.sendRedirect(request.getServletPath() + "/categoryList");
            return;
        }

        request.setAttribute("errString", errString);
        request.setAttribute("category", category);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/docs/categoryDeleting.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String errString = null;
		Connection conn = (Connection)request.getAttribute("MY_CONN");
 
        String code = (String) request.getParameter("code");

        boolean isNotConflictConstrain = true;
        try {
        	isNotConflictConstrain = DBUtils.checkConstrain(conn, code);
        	if (isNotConflictConstrain) {
        		DBUtils.deleteCategory(conn, code);
        	} else {
        		errString = "There are some product with this category, cannot delete.";
        	}
        } catch (SQLException e) {
            e.printStackTrace();
            errString = e.getMessage();
        } 
        
        if (errString != null) {
            request.setAttribute("errString", errString);
            request.setAttribute("code", code);
            
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/docs/categoryDeleting.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/categoryList");
        }
    }
}
