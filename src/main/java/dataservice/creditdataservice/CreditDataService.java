package dataservice.creditdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.CreditPO;

public interface CreditDataService extends Remote{
	
	/**
	 * 
	 * @param cpo
	 * @throws RemoteException
	 */
	public void insertCredit(CreditPO cpo) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @throws RemoteException
	 */
	public void deleteCredit(int id) throws RemoteException;
	
	/**
	 * 
	 * @param cpo
	 * @throws RemoteException
	 */
	public void updateCredit(CreditPO cpo) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @return 根据ID查找并获得信用值信息
	 * @throws RemoteException
	 */
	public CreditPO findCredit(int id) throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initCreditDataService() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishCreditDataService() throws RemoteException;
}
