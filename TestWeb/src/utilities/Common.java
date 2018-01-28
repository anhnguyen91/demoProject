/**
 * 
 */
package utilities;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import beans.Category;

/**
 * @author anh
 *
 */
public class Common {

    public String validateProduct(HttpServletRequest request, String name, String ctg, String price, String remain) {
    	String err = null;
    	// check name
    	if (name == null || "".equals(name.trim())) {
    		err = "Name is not valid.";
    		return err;
    	}
    	
    	// check category
    	if (ctg == null || "".equals(ctg.trim())) {
    		err = "Category is not valid.";
    		return err;
    	}
    	err = isValidCtg(request, ctg);
    	if (err != null) {
    		return err;
    	}
    	
    	// check price
    	if (price == null || "".equals(price.trim())) {
    		err = "Price is not valid.";
    		return err;
    	}
    	float tmpFloat;
    	try {
    		tmpFloat = Float.parseFloat(price);
    		if (tmpFloat <= 0) {
        		err = "Price is not valid.";
        		return err;	
    		}
    	} catch (Exception e) {
    		err = "Price is not valid.";
    		return err;	
    	}
    	
    	// check price
    	if (remain == null || "".equals(remain.trim())) {
    		err = "Number of remain is not valid.";
    		return err;
    	}
    	int tmpInt;
    	try {
    		tmpInt = Integer.parseInt(remain);
    		if (tmpInt < 0) {
        		err = "Number of remain is not valid.";
        		return err;	
    		}
    	} catch (Exception e) {
    		err = "Number of remain is not valid.";
    		return err;	
    	}
    	
    	return err;
    }
    
    private String isValidCtg(HttpServletRequest request, String ctg) {
    	Connection conn = (Connection)request.getAttribute("MY_CONN");
    	String returnValue = null;
    	Category category = null;
        try {
            category = DBUtils.getCategory(conn, ctg);
        } catch (SQLException e) {
            e.printStackTrace();
            returnValue = "Error on get data.";
        }
        if (category == null) {
        	returnValue = "Category is invalid";
        }
    	return returnValue;
    }

	public String validateCategory(String name) {
		
		String err = null;
	    // check name
	    if (name == null || "".equals(name.trim())) {
	    	err = "Name is not valid.";
		}
    	return err;
	}
}
