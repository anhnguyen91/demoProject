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
import utilities.Common;
import utilities.DBUtils;

/**
 * @author anh
 *
 *
 */
@WebServlet(urlPatterns = { "/editCategory" })
 public class EditCategory extends HttpServlet {
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
	                .getRequestDispatcher("/WEB-INF/docs/categoryEditing.jsp");
	        dispatcher.forward(request, response);
	 
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	    	String errString = null;
	    	Connection conn = (Connection)request.getAttribute("MY_CONN");

	    	String code = (String) request.getParameter("code");
	    	String name = (String) request.getParameter("name");
	        Category category = null;
	        Common comm = new Common();
	        errString = comm.validateCategory(name);

	        category = new Category(code, name);
	        if (errString == null) {
	        	try {
	                DBUtils.updateCategory(conn, category);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                errString = "Error on updating product";
	            }
	        }

	        request.setAttribute("category", category);
	        request.setAttribute("errString", errString);

	        if (errString != null) {
	        	
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/docs/categoryEditing.jsp");
	            dispatcher.forward(request, response);
	        } else {
	            response.sendRedirect(request.getContextPath() + "/categoryList");
	        }
	    }
}
