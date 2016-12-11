package po;

import java.io.Serializable;
import java.sql.Date;

import po.PromotionType;

public class PromotionPO implements Serializable, Promotion{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String promotionName;
	String content = null;
	PromotionType promotionType;
	Date start = Date.valueOf("2016-12-01");
	Date stop = Date.valueOf("2016-12-31");
	int id = 0;
	
	public PromotionPO( String pn, String ctt, Date s, Date sp, PromotionType pt, int i){
		promotionName = pn;
		content = ctt;
		start = s;
		stop = sp;
		promotionType = pt;
		id = i;
	}
	
	/**
	 * 
	 * @return 获得营销策略名字
	 */
	public String getPromotionName(){
		return promotionName;
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
	 * @return 获得营销策略结束时间
	 */
	public Date getStopTime(){
		return stop;
	}
	
	/**
	 * 
	 * @return 获得营销策略类型
	 */
	public PromotionType getPromotionType(){
		return promotionType;
	}
	
	/**
	 * 
	 * @return 获得营销策略制定者ID
	 */
	public int getID(){
		return id;
	}

	@Override
	public double calculatePayment(double sum) {
		// TODO Auto-generated method stub
		return 0;
	}
}
