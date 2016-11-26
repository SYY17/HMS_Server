package dataservice.datafactoryservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dataservice.creditdataservice.CreditDataService;
import dataservice.hoteldataservice.HotelDataService;
import dataservice.orderdataservice.OrderDataService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.roomdataservice.RoomDataService;
import dataservice.userdataservice.UserDataService;

public interface DataFactoryService extends Remote{
	
	/**
	 * 
	 * @return 获得用户数据
	 */
	public UserDataService getUserData() throws RemoteException;
	
	/**
	 * 
	 * @return 获得订单数据
	 */
	public OrderDataService getOrderData() throws RemoteException;
	
	/**
	 * 
	 * @return 获得酒店数据
	 */
	public HotelDataService getHotelData() throws RemoteException;
	
	/**
	 * 
	 * @return 获得营销策略数据
	 */
	public PromotionDataService getPromotionData() throws RemoteException;
	
	/**
	 * 
	 * @return 获得信用值数据
	 */
	public CreditDataService getCreditData() throws RemoteException;
	
	/**
	 * 
	 * @return 获得房间数据
	 */
	public RoomDataService getRoomData() throws RemoteException;
}
