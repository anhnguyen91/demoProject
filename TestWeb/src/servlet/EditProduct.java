/**
 * 
 */
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.Common;
import utilities.DBUtils;
import beans.Category;
import beans.Product;

/**
 * @author anh
 *
 */
@WebServlet(urlPatterns = { "/editProduct" })
public class EditProduct extends HttpServlet {
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
        
    	// get category to display
    	this.getCategory(request);

        if (errString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }

        request.setAttribute("errString", errString);
        request.setAttribute("product", product);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/docs/productEditing.jsp");
        dispatcher.forward(request, response);
 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String errString = null;
    	Connection conn = (Connection)request.getAttribute("MY_CONN");

    	String code = (String) request.getParameter("code");
    	String name = (String) request.getParameter("name");
        String category = (String) request.getParameter("category");
        String strPrice = (String) request.getParameter("price");
        String strRemain = (String) request.getParameter("numOfRemain");
        Product product = null;
        Common comm = new Common();
        errString = comm.validateProduct(request, name, category, strPrice, strRemain);

    	product = new Product(code, name, category, strPrice, strRemain);
        if (errString == null) {
        	try {
                DBUtils.updateProduct(conn, product);
            } catch (SQLException e) {
                e.printStackTrace();
                errString = "Error on updating product";
            }
        }

        request.setAttribute("product", product);
        request.setAttribute("errString", errString);

        if (errString != null) {
        	// get category to display
        	this.getCategory(request);
        	
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/docs/productEditing.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }
    
    private void getCategory(HttpServletRequest request) {
		
        Connection conn = (Connection)request.getAttribute("MY_CONN");
        List<Category> lstCategory = null;
        
        try {
        	lstCategory = DBUtils.getAllCategory(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        request.setAttribute("lstCategory", lstCategory);
    }
}
