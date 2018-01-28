/**
 * 
 */
package filter;

import java.util.Collection;
import java.util.Map;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import connections.ConnectionUtils;

/**
 * @author anh
 *
 */
@WebFilter(filterName = "navFilter", urlPatterns = { "/*" })
public class NavigationFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }
 
    // check destination of request whether it is a servlet
    private boolean isDBConnection(HttpServletRequest request) {

        // path information
        String pathInfo = request.getPathInfo();
        String sPath = request.getServletPath();
 
        String pattern = sPath;
 
        if (pathInfo != null) {
        	pattern = sPath + "/*";
        }
 
        // map of ServletRegistration
        Map<String, ? extends ServletRegistration> servletReg = request.getServletContext().getServletRegistrations();
 
        // collection of all pages
        Collection<? extends ServletRegistration> pages = servletReg.values();
        for (ServletRegistration sr : pages) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(pattern)) {
                return true;
            }
        }
        return false;
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        // 
        if (this.isDBConnection(req)) {
 
            Connection con = null;
            try {
                // get connection
            	con = ConnectionUtils.getConnection();
                // not allow auto-commit
            	con.setAutoCommit(false);
            	// set connection to request
                req.setAttribute("MY_CONN", con);
                // allow request
                chain.doFilter(request, response);
                
                // commit
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                ConnectionUtils.rollbackConnection(con);
                throw new ServletException();
            } finally {
                ConnectionUtils.closeConnection(con);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
