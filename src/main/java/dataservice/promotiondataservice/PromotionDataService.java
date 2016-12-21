package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.sql.Date;
import po.PromotionPO;

public interface PromotionDataService extends Remote{
	
	/**
	 * 
	 * @param id
	 * @param start
	 * @param content
	 * @return 根据ID, 营销策略内容和起始时间查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList <PromotionPO> findsPromotion(int id, String content, Date start) throws RemoteException;
	
	/**
	 * 
	 * @return 根据ID查找并返回所有营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList <PromotionPO> findsPromotion(int id) throws RemoteException;
	
	/**
	 * 
	 * @param start
	 * @return 根据ID和起始时间查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList <PromotionPO> findsPromotion(int id, Date start) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @param content
	 * @return 根据ID和内容查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList <PromotionPO> findsPromotion(int id, String content) throws RemoteException;
	
	/**
	 * 
	 * @param ppo
	 * @throws RemoteException
	 */
	public void insertPromotion(PromotionPO ppo) throws RemoteException;
	
	/**
	 * 
	 * @param ppo
	 * @throws RemoteException
	 */
	public void deletePromotion(PromotionPO ppo) throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initPromotionDataService() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishPromotionDataService() throws RemoteException;
	
	//
	/**
	 * @return 查找并返回所有营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<PromotionPO> getAllPromotion() throws RemoteException;
}
