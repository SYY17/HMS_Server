package po;

import java.sql.Date;

import po.PromotionType;
import po.PromotionPO;

public class FullCutPromotionPO extends PromotionPO {

	double every;
	double cut;
	
	public FullCutPromotionPO(String pn, String ctt, Date s, Date sp, PromotionType pt, int i, double every, double cut) {
		super(pn, ctt, s, sp, pt, i);
		// TODO Auto-generated constructor stub
		this.every = every;
		this.cut = cut;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 * @return 获得营销策略every
	 */
	public double getEvery(){
		return every;
	}
	
	/**
	 * 
	 * @return 获得营销策略cut
	 */
	public double getCut(){
		return cut;
	}

	@Override
	public double calculatePayment( double sum) {
		// TODO Auto-generated method stub
		double payment;
		payment = sum - cut*sum/every;
		
		if(payment>0){
			return payment;
		}
		
		return -1;
	}

}
