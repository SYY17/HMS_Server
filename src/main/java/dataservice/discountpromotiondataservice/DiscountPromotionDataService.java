package dataservice.discountpromotiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.sql.Date;

import po.DiscountPromotionPO;

public interface DiscountPromotionDataService {

	/**
	 * 
	 * @param id
	 * @param start
	 * @param content
	 * @return 根据ID, 营销策略内容和起始时间查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<DiscountPromotionPO> findsDiscountPromotion(int id, String content, Date start) throws RemoteException;

	/**
	 * 
	 * @return 根据ID查找并返回所有营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<DiscountPromotionPO> findsDiscountPromotion(int id) throws RemoteException;

	/**
	 * 
	 * @param start
	 * @return 根据ID和起始时间查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<DiscountPromotionPO> findsDiscountPromotion(int id, Date start) throws RemoteException;

	/**
	 * 
	 * @param id
	 * @param content
	 * @return 根据ID和内容查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<DiscountPromotionPO> findsDiscountPromotion(int id, String content) throws RemoteException;
	
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
