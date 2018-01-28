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

import utilities.Common;
import utilities.DBUtils;
import beans.Category;

/**
 * @author anh
 *
 */
@WebServlet(urlPatterns = { "/createCategory" })
public class CreateCategory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/docs/categoryCreation.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String errString = null;
    	Connection conn = (Connection)request.getAttribute("MY_CONN");
    	
        String name = (String) request.getParameter("name");
        Category category = null;
        Common comm = new Common();
        errString = comm.validateCategory(name);

        category = new Category();
        category.setctgName(name);
        
        if (errString == null) {
        	try {
                DBUtils.createCategory(conn, category);
            } catch (SQLException e) {
                e.printStackTrace();
                errString = "Error on insert data";
            }
        }

        request.setAttribute("category", category);
        request.setAttribute("errString", errString);
 
        // error
        if (errString != null) {
        	
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/docs/categoryCreation.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/categoryList");
        }
    }
}
