package po;

import java.sql.Date;

public class CustomerPO extends UserPO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Date birthday;
	String phoneNumber;
	String email;

	public CustomerPO(int i, String n, String p, Date b, String pN, String e) {
		// TODO Auto-generated constructor stub
		super(i, n, p);
		birthday = b;
		phoneNumber = pN;
		email = e;
	}

	public CustomerPO(UserPO upo, Date b, String pN, String e) {
		this(upo.getID(), upo.getName(), upo.getPassword(), b, pN, e);
	}

	/**
	 * 
	 * @return 获取用户生日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 
	 * @return 获取用户电话号码
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @return 获取用户邮箱
	 */
	public String getEmail() {
		return email;
	}
}
