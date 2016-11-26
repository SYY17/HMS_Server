package data.datafactory;

import java.rmi.RemoteException;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import data.orderdata.OrderDataServiceMySqlImpl;
import data.promotiondata.PromotionDataServiceMySqlImpl;
import data.roomdata.RoomDataServiceMySqlImpl;
import data.userdata.UserDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import dataservice.datafactoryservice.DataFactoryService;
import dataservice.hoteldataservice.HotelDataService;
import dataservice.orderdataservice.OrderDataService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.roomdataservice.RoomDataService;
import dataservice.userdataservice.UserDataService;

public class DataFactoryServiceMySqlImpl implements DataFactoryService{
	
	/**
	 * 
	 * @return 获得用户数据
	 */
	@Override
	public UserDataService getUserData() throws RemoteException{
		// TODO Auto-generated method stub
		UserDataService userData = new UserDataServiceMySqlImpl();
		return userData;
	}
	
	/**
	 * 
	 * @return 获得订单数据
	 */
	@Override
	public OrderDataService getOrderData() throws RemoteException{
		OrderDataService orderData=new OrderDataServiceMySqlImpl();
		return orderData;
	}
	
	/**
	 * 
	 * @return 获得酒店数据
	 */
	@Override
	public HotelDataService getHotelData() throws RemoteException{
		// TODO Auto-generated method stub
		HotelDataService hotelData = new HotelDataServiceMySqlImpl();
		return hotelData;
	}
	
	/**
	 * 
	 * @return 获得营销策略数据
	 */
	@Override
	public PromotionDataService getPromotionData() throws RemoteException{
		// TODO Auto-generated method stub
		PromotionDataService promotionData = new PromotionDataServiceMySqlImpl();
		return promotionData;
	}

	/**
	 * 
	 * @return 获得信用值数据
	 */
	@Override
	public CreditDataService getCreditData() throws RemoteException{
		// TODO Auto-generated method stub
		CreditDataService creditData = new CreditDataServiceMySqlImpl();
		return creditData;
	}

	/**
	 * 
	 * @return 获得房间数据
	 */
	@Override
	public RoomDataService getRoomData() throws RemoteException{
		// TODO Auto-generated method stub
		RoomDataService roomData = new RoomDataServiceMySqlImpl();
		return roomData;
	}
}
