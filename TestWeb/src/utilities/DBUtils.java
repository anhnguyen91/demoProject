/**
 * 
 */
package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Category;
import beans.Product;

/**
 * @author anh
 * 
 */
public class DBUtils {

	// query product
	private static String QUERY_PRODUCT = "SELECT P.PRO_CODE, P.PRO_NAME, P.CTG_CODE, C.CTG_NAME,"
			+ " P.PRICE, P.NUM_OF_REMAIN FROM PRODUCT P, PRO_CATEGORY C WHERE P.CTG_CODE = C.CTG_CODE"
			+ " ORDER BY C.CTG_NAME ASC, P.PRO_NAME ASC";
	
	// add product
	private static String ADD_PRODUCT = "INSERT INTO PRODUCT(PRO_CODE, PRO_NAME, CTG_CODE, PRICE, NUM_OF_REMAIN)"
			+ " VALUES (SEQ_PRO.NEXTVAL, ?, ?, ?, ?)";
	
	// get product
	private static String GET_PRODUCT = "SELECT P.PRO_CODE, P.PRO_NAME, P.CTG_CODE, C.CTG_NAME,"
			+ " P.PRICE, P.NUM_OF_REMAIN FROM PRODUCT P, PRO_CATEGORY C WHERE P.CTG_CODE = C.CTG_CODE"
			+ " AND P.PRO_CODE = ?";
	
	// update product
	private static String UPDATE_PRODUCT = "UPDATE PRODUCT SET  PRO_NAME = ?, CTG_CODE = ?, PRICE = ?, NUM_OF_REMAIN = ?"
			+ " WHERE PRO_CODE = ?";
	
	// delete product
	private static String DELETE_PRODUCT = "DELETE PRODUCT WHERE PRO_CODE = ?";
	
	// query category
	private static String QUERY_CATEGORY = "SELECT CTG_CODE, CTG_NAME"
			+ " FROM PRO_CATEGORY"
			+ " ORDER BY CTG_CODE ASC";
	
	// add category
	private static String ADD_CATEGORY = "INSERT INTO PRO_CATEGORY(CTG_CODE, CTG_NAME)"
			+ " VALUES (SEQ_CTG.NEXTVAL, ?)";

	// get category
	private static String GET_CATEGORY = "SELECT CTG_CODE, CTG_NAME FROM PRO_CATEGORY C WHERE CTG_CODE = ?"
			+ " ORDER BY CTG_CODE ASC";
	
	// update category
	private static String UPDATE_CATEGORY = "UPDATE PRO_CATEGORY SET  CTG_NAME = ?"
			+ " WHERE CTG_CODE = ?";
	
	// delete product
	private static String DELETE_CATEGORY = "DELETE PRO_CATEGORY WHERE CTG_CODE = ?";
	
	// check foreign key constrain
	private static String CHECK_FRK = "SELECT PRO_CODE FROM PRODUCT WHERE CTG_CODE = ?";

	public static List<Product> getAllProduct(Connection conn)
			throws SQLException {
		
		PreparedStatement pstm = conn.prepareStatement(QUERY_PRODUCT);
		 
        ResultSet rs = pstm.executeQuery();
        List<Product> ret = new ArrayList<Product>();
        Product product = null;
        while (rs.next()) {
            product = new Product();
            
            product.setProCode(rs.getString("PRO_CODE"));
            product.setProName(rs.getString("PRO_NAME"));
            product.setCtgCode(rs.getString("CTG_CODE"));
            product.setCtgName(rs.getString("CTG_NAME"));
            product.setPrice(rs.getString("PRICE"));
            product.setNumberOfRemain(rs.getString("NUM_OF_REMAIN"));
            
            ret.add(product);
        }
        return ret;
	}

	public static void insertProduct(Connection conn, Product product) throws SQLException {
		PreparedStatement pstm = conn.prepareStatement(ADD_PRODUCT);
		 
        pstm.setString(1, product.getProName());
        pstm.setString(2, product.getCtgCode());
        pstm.setFloat(3, Float.valueOf(product.getPrice()));
        pstm.setInt(4, Integer.valueOf(product.getNumberOfRemain()));
 
        pstm.executeUpdate();
	}

	public static List<Category> getAllCategory(Connection conn) throws SQLException {
		
		PreparedStatement pstm = conn.prepareStatement(QUERY_CATEGORY);
		 
        ResultSet rs = pstm.executeQuery();
        List<Category> ret = new ArrayList<Category>();
        Category ctg = null;
        while (rs.next()) {
        	ctg = new Category();
            
        	ctg.setctgCode(rs.getString("CTG_CODE"));
        	ctg.setctgName(rs.getString("CTG_NAME"));
            
            ret.add(ctg);
        }
        return ret;
	}

	public static Product getProduct(Connection conn, String code) throws SQLException {
		
		PreparedStatement pstm = conn.prepareStatement(GET_PRODUCT);
		pstm.setString(1, code);
		
        ResultSet rs = pstm.executeQuery();
        Product product = null;
        while (rs.next()) {
        	product = new Product();
        	
            product.setProCode(rs.getString("PRO_CODE"));
            product.setProName(rs.getString("PRO_NAME"));
            product.setCtgCode(rs.getString("CTG_CODE"));
            product.setCtgName(rs.getString("CTG_NAME"));
            product.setPrice(rs.getString("PRICE"));
            product.setNumberOfRemain(rs.getString("NUM_OF_REMAIN"));
        }
        return product;
	}

	public static void updateProduct(Connection conn, Product product)  throws SQLException {
		PreparedStatement pstm = conn.prepareStatement(UPDATE_PRODUCT);
		 
        pstm.setString(1, product.getProName());
        pstm.setString(2, product.getCtgCode());
        pstm.setFloat(3, Float.valueOf(product.getPrice()));
        pstm.setInt(4, Integer.valueOf(product.getNumberOfRemain()));
        pstm.setString(5, product.getProCode());
 
        pstm.executeUpdate();
	}

	public static void deleteProduct(Connection conn, String code)  throws SQLException {
		PreparedStatement pstm = conn.prepareStatement(DELETE_PRODUCT);
		 
        pstm.setString(1, code);
 
        pstm.executeUpdate();
	}

	public static Category getCategory(Connection conn, String code) throws SQLException {
		
		PreparedStatement pstm = conn.prepareStatement(GET_CATEGORY);
		pstm.setString(1, code);
		
        ResultSet rs = pstm.executeQuery();
        Category ctg = null;
        while (rs.next()) {
        	ctg = new Category();
        	
        	ctg.setctgCode(rs.getString("CTG_CODE"));
        	ctg.setctgName(rs.getString("CTG_NAME"));
        }
        return ctg;
	}

	public static void createCategory(Connection conn, Category category) throws SQLException {
		PreparedStatement pstm = conn.prepareStatement(ADD_CATEGORY);
		 
        pstm.setString(1, category.getctgName());
 
        pstm.executeUpdate();
	}
	
	public static boolean checkConstrain(Connection conn, String code) throws SQLException {
		
		boolean ret = true;
		PreparedStatement pstm = conn.prepareStatement(CHECK_FRK);
		pstm.setString(1, code);
		
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
        	ret = false;
        }
        return ret;
	}

	public static void deleteCategory(Connection conn, String code) throws SQLException {
		PreparedStatement pstm = conn.prepareStatement(DELETE_CATEGORY);
		 
        pstm.setString(1, code);
 
        pstm.executeUpdate();
	}

	public static void updateCategory(Connection conn, Category category) throws SQLException {
		PreparedStatement pstm = conn.prepareStatement(UPDATE_CATEGORY);
		
        pstm.setString(1, category.getctgName());
        pstm.setString(2, category.getctgCode());
 
        pstm.executeUpdate();
	}

}
