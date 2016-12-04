package po;

public class CustomerPO extends UserPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String realName;
	String phoneNumber;
	String email;
	
	public CustomerPO(int i,String n,String p, String r, String pN, String e) {
		// TODO Auto-generated constructor stub
		super(i, n, p);
		realName = r;
		phoneNumber = pN;
		email = e;
	}
	
	public CustomerPO(UserPO upo, String r, String pN, String e){
		this(upo.getID(), upo.getName(), upo.getPassword(), r, pN, e);
	}
	
	public String getRealName(){
		return realName;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public String getEmail(){
		return email;
	}
}
