package po;
import java.io.Serializable;

public class UserPO implements Serializable{//can we add a Userole just like the book did
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String name;
	String password;
	
	public UserPO(){
		id=0;
		name="User";
		password="password";
	}
	
	public UserPO(int i,String n,String p){
		id=i;
		name=n;
		password=p;
	}

	public String getName(){
		return name;
	}
	
	public int getID(){
		return id;
	}
	
	public String getPassword(){
		return password;
	}
	
}

class CustomerPO extends UserPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String address;
	String phoneNumber;
	String defaultQuestion;
	String answerToQues;
	int credit=0;
	
	public CustomerPO(int i,String n,String p,String add,String pn,String d,String ans){
		super(i,n,p);
		address=add;
		phoneNumber=pn;
		defaultQuestion=d;
		answerToQues=ans;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public String getDefaultQuestion(){
		return defaultQuestion;
	}
	
	public String getAnsToAns(){
		return answerToQues;
	}
	
	public int getCredit(){
		return credit;
	}
}

class HotelWorkerPO extends UserPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}

class WebsitePromotePO extends UserPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}

class WebsiteManagerPO extends UserPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
