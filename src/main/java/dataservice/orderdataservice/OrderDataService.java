package dataservice.orderdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrderPO;
import po.OrderStatus;

public interface OrderDataService extends Remote{
	
	/**
	 * 
	 * @param po
	 * @throws RemoteException
	 */
	public void insertOrder(OrderPO po) throws RemoteException;

	/**
	 * 
	 * @param po
	 * @throws RemoteException
	 */
	public void deleteOrder(int id) throws RemoteException;

	/**
	 * 
	 * @param po
	 * @throws RemoteException
	 */
	public void updateOrder(int id, OrderStatus status) throws RemoteException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public OrderPO findOrder(int id) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findOrderByUserID(int id) throws RemoteException;

	/**
	 * 
	 * @throws RemoteException
	 */
	public void initOrderDataService() throws RemoteException;

	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishOrderDataService() throws RemoteException;
}
