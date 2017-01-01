package dataservice.logindataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginDataService extends Remote{

	/**
	 * 
	 * @param username
	 * @param password
	 * @param id
	 * @return 是否存在相应用户
	 * @throws RemoteException
	 */
	public boolean isValidateUser(String username, String password, int id) throws RemoteException;
	
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
