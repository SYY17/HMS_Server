package po;

import java.io.Serializable;
import java.sql.Date;

public class UserCreditHistoryPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private Date time;
	private int change;
	private int remain;
	private CreditMovement creditMovement;

	public UserCreditHistoryPO(int userId, int change, Date time, CreditMovement creditMovement, int remain) {
		this.userId = userId;
		this.time = time;
		this.change = change;
		this.creditMovement = creditMovement;
		this.remain = remain;
	}

	/**
	 * 
	 * @return 获得id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 
	 * @return 获得信用值更改时间
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 
	 * @return 获得信用值更改细节
	 */
	public int getChange() {
		return change;
	}
	
	/**
	 * 
	 * @return 获得信用值更改细节
	 */
	public CreditMovement getCreditMovement() {
		return creditMovement;
	}
	
	/**
	 * 
	 * @return 获得信用值
	 */
	public int getRemain() {
		return remain;
	}

}
