package dataservice.fullcutpromotiondataservice;

import java.rmi.RemoteException;

import po.FullCutPromotionPO;

public interface FullCutPromotionDataService {

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
