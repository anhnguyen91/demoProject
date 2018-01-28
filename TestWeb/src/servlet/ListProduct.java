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

import utilities.DBUtils;
import beans.Product;

@WebServlet(urlPatterns = { "/productList" })
public class ListProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        Connection conn = (Connection)request.getAttribute("MY_CONN");

        List<Product> lstProduct = null;
        String errString = null;
        
        try {
        	lstProduct = DBUtils.getAllProduct(conn);
        } catch (SQLException e) {
            errString = e.getMessage();
            e.printStackTrace();
        }
        
        request.setAttribute("errString", errString);
        request.setAttribute("lstProduct", lstProduct);
        
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/docs/productList.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
