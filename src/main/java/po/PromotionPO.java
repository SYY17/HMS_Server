package po;

import java.io.Serializable;
import java.util.Date;

public class PromotionPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String content = "";
	Date start = null;
	int id = 0;
	
	public PromotionPO(String ctt, Date s, int i){
		content = ctt;
		start = s;
		id = i;
	}
	
	/**
	 * 
	 * @return 获得营销策略内容
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * 
	 * @return 获得营销策略起始时间
	 */
	public Date getStartTime(){
		return start;
	}
	
	/**
	 * 
	 * @return 获得营销策略制定者ID
	 */
	public int getID(){
		return id;
	}
}
