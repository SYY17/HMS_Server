package dataservice.fullcutpromotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.sql.Date;

import po.FullCutPromotionPO;

public interface FullCutPromotionDataService extends Remote{

	/**
	 * 
	 * @param id
	 * @param start
	 * @param content
	 * @return 根据ID, 营销策略内容和起始时间查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<FullCutPromotionPO> findsFullPromotion(int id, String content, Date start) throws RemoteException;

	/**
	 * 
	 * @return 根据ID查找并返回所有营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<FullCutPromotionPO> findsFullPromotion(int id) throws RemoteException;

	/**
	 * 
	 * @param start
	 * @return 根据ID和起始时间查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<FullCutPromotionPO> findsFullPromotion(int id, Date start) throws RemoteException;

	/**
	 * 
	 * @param id
	 * @param content
	 * @return 根据ID和内容查找并返回营销策略信息
	 * @throws RemoteException
	 */
	public ArrayList<FullCutPromotionPO> findsFullPromotion(int id, String content) throws RemoteException;
	
	/**
	 * 
	 * @param fpo
	 * @throws RemoteException
	 */
	public void insertFullCutPromotion(FullCutPromotionPO fpo) throws RemoteException;
	
	/**
	 * 
	 * @param fpo
	 * @throws RemoteException
	 */
	public void deleteFullCutPromotion(FullCutPromotionPO fpo) throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initFullCutPromotionDataService() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishFullCutPromotionDataService() throws RemoteException;
}
