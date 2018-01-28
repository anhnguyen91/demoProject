/**
 * 
 */
package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author anh
 *
 */
public class ConnectionUtils {
	
	public static Connection getConnection()
	           throws ClassNotFoundException, SQLException {
	        
	       // information to connect
	       String hostName = "localhost";
	       String sid = "demo";
	       String role = "sys as sysdba";
	       String password = "123456a-";
	  
	       return getConnection(hostName, sid, role, password);
	   }
	  
	   public static Connection getConnection(String hostName, String sid,
	           String role, String password) throws ClassNotFoundException,
	           SQLException {

	       Class.forName("oracle.jdbc.driver.OracleDriver");

	       // jdbc:oracle:thin:@//HOSTNAME:PORT/SERVICENAME
	       String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;
	  
	       Connection conn = DriverManager.getConnection(connectionURL, role, password);
	       return conn;
	   }
	   
	    public static void closeConnection(Connection conn) {
	        try {
	            conn.close();
	        } catch (Exception e) {
	        }
	    }
	 
	    public static void rollbackConnection(Connection conn) {
	        try {
	            conn.rollback();
	        } catch (Exception e) {
	        }
	    }
}
