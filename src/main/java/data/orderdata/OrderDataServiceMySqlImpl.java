package data.orderdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.orderdataservice.OrderDataService;
import po.OrderPO;
import po.OrderStatus;

public class OrderDataServiceMySqlImpl implements OrderDataService{

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public OrderDataServiceMySqlImpl() {
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	/**
	 * 
	 * @param po
	 * @throws RemoteException
	 */
	@Override
	public void insertOrder(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param po
	 * @throws RemoteException
	 */
	@Override
	public void deleteOrder(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param po
	 * @throws RemoteException
	 */
	@Override
	public void updateOrder(int id, OrderStatus status) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public OrderPO findOrder(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void initOrderDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void finishOrderDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}
}
