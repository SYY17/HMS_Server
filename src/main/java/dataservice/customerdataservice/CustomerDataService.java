package dataservice.customerdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.CustomerPO;

public interface CustomerDataService extends Remote{
	
	/**
	 * 
	 * @param username
	 * @return 添加新的顾客信息
	 * @throws RemoteException
	 */
	public boolean insertCustomer(String username) throws RemoteException;
	
	/**
	 * 
	 * @param cpo
	 * @return 更新顾客信息
	 * @throws RemoteException
	 */
	public boolean updateCustomerInfo(CustomerPO cpo) throws RemoteException;
	
	/**
	 * 
	 * @param username
	 * @return 根据用户名查找并返回顾客信息
	 * @throws RemoteException
	 */
	public CustomerPO getCustomerInfo(String username) throws RemoteException;
	
	/**
	 * 
	 * @param username
	 * @return 删除用户信息
	 * @throws RemoteException
	 */
	public boolean deleteCustomer(String username) throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initCustomerDataService() throws RemoteException;

	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishCustomerDataService() throws RemoteException;
}
