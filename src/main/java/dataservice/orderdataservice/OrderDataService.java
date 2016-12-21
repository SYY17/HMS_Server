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
	 * @param id
	 * @throws RemoteException
	 */
	public void deleteOrder(int id) throws RemoteException;

	/**
	 * 
	 * @param id,status
	 * @throws RemoteException
	 */
	public void updateOrder(int id, OrderStatus status) throws RemoteException;
	
	/**
	 * 
	 * @param id,room
	 * @throws RemoteException
	 */
	public void updateOrder(int id, String room) throws RemoteException;

	/**
	 * 
	 * @return Arraylist<OrderPO>
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findOrder() throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @return OrderPO
	 * @throws RemoteException
	 */
	public OrderPO findOrderByOrderID(int id) throws RemoteException;
	
	/**
	 * 
	 * @param userName
	 * @return Arraylist<OrderPO>
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findOrderByUserName(String userName) throws RemoteException;
	
	/**
	 * 
	 * @param hotelName
	 * @return Arraylist<OrderPO>
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findOrderByHotelName(String hotelName) throws RemoteException;

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
