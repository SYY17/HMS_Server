package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.UserPO;

public interface UserDataService extends Remote{
	
	/**
	 * 
	 * @param upo
	 * @throws RemoteException
	 */
	public void insertUser(UserPO upo) throws RemoteException;
	
	/**
	 * 
	 * @param upo
	 * @throws RemoteException
	 */
	public void deleteUser(int id) throws RemoteException;
	
	/**
	 * 
	 * @param upo
	 * @throws RemoteException
	 */
	public void updateUser(UserPO upo) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @return 根据ID查找并返回用户信息
	 * @throws RemoteException
	 */
	public UserPO findUser(int id) throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initUserDataService() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishUserDataService() throws RemoteException;
}
