package po;

import java.io.Serializable;

public class CreditPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id = 0;
	int credit = 0;
	
	public CreditPO(int i, int c){
		id = i;
		credit = c;
	}
	
	/**
	 * 
	 * @return 获得信用值对应用户ID
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * 
	 * @return 获得信用值数据
	 */
	public int getCredit(){
		return credit;
	}
	
}
