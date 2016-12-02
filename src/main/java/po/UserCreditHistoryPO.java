package po;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class UserCreditHistoryPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int userId;
	ArrayList<Date> time;
	ArrayList<String> history;
	
	public UserCreditHistoryPO(int userId){
		this.userId=userId;
		time = new ArrayList<Date>();
		history = new ArrayList<String>();
	}
	
	public void addHistory(Date timePoint, String content){
		 if(time==null){
			 time=new ArrayList<Date>();
		 }
		 if(history==null){
			 history=new ArrayList<String>();
		 }
		 time.add(timePoint);
		 history.add(content);
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
	public ArrayList<Date> getTime(){
		return time;
	}
	
	/**
	 * 
	 * @return 获得信用值更改细节
	 */
	public ArrayList<String> getContent(){
		return history;
	}
	
}
