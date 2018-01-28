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

import beans.Product;
import utilities.DBUtils;

/**
 * @author anh
 *
 */
@WebServlet(urlPatterns = { "/deleteProduct" })
public class DeleteProduct extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = (Connection)request.getAttribute("MY_CONN");

        String code = (String) request.getParameter("code");
 
        Product product = null;
    	String errString = null;

        try {
            product = DBUtils.getProduct(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errString = "Error on get information from DB.";
        }

        if (errString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }

        request.setAttribute("errString", errString);
        request.setAttribute("product", product);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/docs/ProductDeleting.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String errString = null;
		Connection conn = (Connection)request.getAttribute("MY_CONN");
 
        String code = (String) request.getParameter("code");
 
        try {
            DBUtils.deleteProduct(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errString = e.getMessage();
        } 
        
        if (errString != null) {
            request.setAttribute("errString", errString);
            
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/docs/productDelete.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }
}
