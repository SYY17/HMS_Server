package po;

import java.io.Serializable;
import java.sql.Date;

public class UserCreditHistoryPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int userId;
	Date time;
	int change;
	
	public UserCreditHistoryPO(int userId, int change, Date time){
		this.userId=userId;
		this.time = time;
		this.change = change;
	}

	/**
	 * 
	 * @return 获得id
	 */
	public int getUserId(){
		return userId;
	}
	
	/**
	 * 
	 * @return 获得信用值更改时间
	 */
	public Date getTime(){
		return time;
	}
	
	/**
	 * 
	 * @return 获得信用值更改细节
	 */
	public int getChange(){
		return change;
	}
	
}
