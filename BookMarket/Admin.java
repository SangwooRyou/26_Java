package BookMarket;

public class Admin extends Person {
	private String id = "admin";
	private String passwd = "1234";
	
	public Admin(String name, String phone) {
		super(name, phone);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPasswd() {
		return passwd;
	}
}
