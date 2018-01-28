/**
 * 
 */
package beans;

/**
 * @author anh
 *
 */
public class Product {
	private String proCode;
	private String proName;
	private String ctgCode;
	private String ctgName;
	private String price;
	private String numberOfRemain;
	
	public Product() {
		this.proCode = "";
		this.proName = "";
		this.ctgCode = "";
		this.ctgName = "";
		this.price = "";
		this.numberOfRemain = "";
	}
	
	public Product(String proName, String ctgCode,
			String price, String numberOfRemain) {
		this.proCode = "";
		this.proName = proName;
		this.ctgCode = ctgCode;
		this.price = price;
		this.numberOfRemain = numberOfRemain;
	}
	
	public Product(String proCode, String proName, String ctgCode,
			String price, String numberOfRemain) {
		this.proCode = proCode;
		this.proName = proName;
		this.ctgCode = ctgCode;
		this.price = price;
		this.numberOfRemain = numberOfRemain;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getCtgName() {
		return ctgName;
	}

	public void setCtgName(String ctgName) {
		this.ctgName = ctgName;
	}

	public String getCtgCode() {
		return ctgCode;
	}

	public void setCtgCode(String ctgCode) {
		this.ctgCode = ctgCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNumberOfRemain() {
		return numberOfRemain;
	}

	public void setNumberOfRemain(String numberOfRemain) {
		this.numberOfRemain = numberOfRemain;
	}
}
