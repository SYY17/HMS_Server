package dataservice.configurationservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface ConfigurationService extends Remote{
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public Connection init() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finish(Connection connect, Statement statement, ResultSet result) throws RemoteException;
}
