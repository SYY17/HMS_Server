package dataservice.logindataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.UserPO;

public interface LoginDataService extends Remote{
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param id
	 * @throws RemoteException
	 */
	public void insertUser(String username,String password,int id) throws RemoteException;
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param id
	 * @return 按ID查找并返回用户信息
	 * @throws RemoteException
	 */
	public UserPO findUser(int id) throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initLoginDataService() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishLoginDataService() throws RemoteException;
}
