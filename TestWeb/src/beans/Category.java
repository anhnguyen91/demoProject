/**
 * 
 */
package beans;

/**
 * @author anh
 *
 */
public class Category {
	private String ctgCode;
	private String ctgName;
	
	public Category() {
		this.ctgCode = "";
		this.ctgName = "";
	}
	
	public Category(String cd, String nm) {
		this.ctgCode = cd;
		this.ctgName = nm;
	}

	public String getctgCode() {
		return ctgCode;
	}

	public void setctgCode(String ctgCode) {
		this.ctgCode = ctgCode;
	}

	public String getctgName() {
		return ctgName;
	}

	public void setctgName(String ctgName) {
		this.ctgName = ctgName;
	}
	
	
}
