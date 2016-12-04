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
