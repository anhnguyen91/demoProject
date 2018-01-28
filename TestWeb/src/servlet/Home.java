/**
 * 
 */
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author anh
 *
 */
@WebServlet(urlPatterns = {"/home"})
public class Home extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   public void HomeServlet() {
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
 
       // this is to restrict user to access WEB-INF
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/docs/homePage.jsp");
        
       dispatcher.forward(request, response);
        
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}