package dataservice.discountpromotiondataservice;

import java.rmi.RemoteException;

import po.DiscountPromotionPO;

public interface DiscountPromotionDataService {

	/**
	 * 
	 * @param dpo
	 * @throws RemoteException
	 */
	public void insertDiscountPromotion(DiscountPromotionPO dpo) throws RemoteException;
	
	/**
	 * 
	 * @param dpo
	 * @throws RemoteException
	 */
	public void deleteDiscountPromotion(DiscountPromotionPO dpo) throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initDiscountPromotionDataService() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishDiscountPromotionDataService() throws RemoteException;
}
