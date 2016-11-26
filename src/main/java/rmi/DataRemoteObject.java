package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.datafactory.DataFactoryServiceMySqlImpl;
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
import po.CreditPO;
import po.HotelPO;
import po.OrderPO;
import po.OrderStatus;
import po.PromotionPO;
import po.RoomPO;
import po.RoomType;
import po.UserPO;

public class DataRemoteObject extends UnicastRemoteObject implements CreditDataService, HotelDataService, 
OrderDataService, PromotionDataService, RoomDataService, UserDataService, DataFactoryService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private CreditDataService creditDataService;
	private HotelDataService hotelDataService;
	private OrderDataService orderDataService;
	private PromotionDataService promotionDataService;
	private RoomDataService roomDataService;
	private UserDataService userDataService;
	private DataFactoryService dataFactoryService;
	
	protected DataRemoteObject() throws RemoteException {
		creditDataService = new CreditDataServiceMySqlImpl();
		hotelDataService = new HotelDataServiceMySqlImpl();
		orderDataService = new OrderDataServiceMySqlImpl();
		promotionDataService = new PromotionDataServiceMySqlImpl();
		roomDataService = new RoomDataServiceMySqlImpl();
		userDataService = new UserDataServiceMySqlImpl();
		dataFactoryService = new DataFactoryServiceMySqlImpl();
	}

	/**
	 * DataFactoryService
	 * 以下为数据工厂服务的通信实现
	 */
	/*
	 * (non-Javadoc)
	 * @see dataservice.datafactoryservice.DataFactoryService#getUserData()
	 */
	@Override
	public UserDataService getUserData() throws RemoteException {
		// TODO Auto-generated method stub
		return dataFactoryService.getUserData();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.datafactoryservice.DataFactoryService#getOrderData()
	 */
	@Override
	public OrderDataService getOrderData() throws RemoteException {
		// TODO Auto-generated method stub
		return dataFactoryService.getOrderData();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.datafactoryservice.DataFactoryService#getHotelData()
	 */
	@Override
	public HotelDataService getHotelData() throws RemoteException {
		// TODO Auto-generated method stub
		return dataFactoryService.getHotelData();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.datafactoryservice.DataFactoryService#getPromotionData()
	 */
	@Override
	public PromotionDataService getPromotionData() throws RemoteException {
		// TODO Auto-generated method stub
		return dataFactoryService.getPromotionData();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.datafactoryservice.DataFactoryService#getCreditData()
	 */
	@Override
	public CreditDataService getCreditData() throws RemoteException {
		// TODO Auto-generated method stub
		return dataFactoryService.getCreditData();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.datafactoryservice.DataFactoryService#getRoomData()
	 */
	@Override
	public RoomDataService getRoomData() throws RemoteException {
		// TODO Auto-generated method stub
		return dataFactoryService.getRoomData();
	}
	
	/**
	 * UserDataService
	 * 以下为用户数据信息服务的通信实现
	 */
	/*
	 * (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#insertUser(po.UserPO)
	 */
	@Override
	public void insertUser(UserPO upo) throws RemoteException {
		// TODO Auto-generated method stub
		userDataService.insertUser(upo);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#deleteUser(int)
	 */
	@Override
	public void deleteUser(int id) throws RemoteException {
		// TODO Auto-generated method stub
		userDataService.deleteUser(id);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#updateUser(po.UserPO)
	 */
	@Override
	public void updateUser(UserPO upo) throws RemoteException {
		// TODO Auto-generated method stub
		userDataService.updateUser(upo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#findUser(java.lang.String)
	 */
	@Override
	public UserPO findUser(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userDataService.findUser(username);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#findAll()
	 */
	@Override
	public ArrayList<UserPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return userDataService.findAll();
	}
	
	/*
	 * (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#initUserDataService()
	 */
	@Override
	public void initUserDataService() throws RemoteException {
		// TODO Auto-generated method stub
		userDataService.initUserDataService();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#finishUserDataService()
	 */
	@Override
	public void finishUserDataService() throws RemoteException {
		// TODO Auto-generated method stub
		userDataService.finishUserDataService();
	}

	/**
	 * RoomDataService
	 * 以下为房间数据信息服务的通信实现
	 */
	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#insertRoom(po.RoomPO)
	 */
	@Override
	public void insertRoom(RoomPO rpo) throws RemoteException {
		// TODO Auto-generated method stub
		roomDataService.insertRoom(rpo);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#deleteAllRooms(int)
	 */
	@Override
	public void deleteAllRooms(int id) throws RemoteException {
		// TODO Auto-generated method stub
		roomDataService.deleteAllRooms(id);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#deleteRoom(int, po.RoomType)
	 */
	@Override
	public void deleteRoom(int id, RoomType type) throws RemoteException {
		// TODO Auto-generated method stub
		roomDataService.deleteRoom(id, type);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#updatePrice(int, po.RoomType, int)
	 */
	@Override
	public void updatePrice(int id, RoomType type, int price) throws RemoteException {
		// TODO Auto-generated method stub
		roomDataService.updatePrice(id, type, price);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#updateRemainSum(int, po.RoomType, int)
	 */
	@Override
	public void updateRemainSum(int id, RoomType type, int remain) throws RemoteException {
		// TODO Auto-generated method stub
		roomDataService.updateRemainSum(id, type, remain);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#updateTotalSum(int, po.RoomType, int)
	 */
	@Override
	public void updateTotalSum(int id, RoomType type, int total) throws RemoteException {
		// TODO Auto-generated method stub
		roomDataService.updateTotalSum(id, type, total);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#findRoom(int, po.RoomType)
	 */
	@Override
	public RoomPO findRoom(int id, RoomType type) throws RemoteException {
		// TODO Auto-generated method stub
		return roomDataService.findRoom(id, type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#initRoomDataService()
	 */
	@Override
	public void initRoomDataService() throws RemoteException {
		// TODO Auto-generated method stub
		roomDataService.initRoomDataService();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#finishRoomDataService()
	 */
	@Override
	public void finishRoomDataService() throws RemoteException {
		// TODO Auto-generated method stub
		roomDataService.finishRoomDataService();
	}

	/**
	 * PromotionDataService
	 * 以下为营销策略数据信息服务的通信实现
	 */
	/*
	 * (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#findsPromotion(int, java.lang.String, java.util.Date)
	 */
	@Override
	public ArrayList<PromotionPO> findsPromotion(int id, String content, Date start) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findsPromotion(id, content, start);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#findsPromotion(int)
	 */
	@Override
	public ArrayList<PromotionPO> findsPromotion(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findsPromotion(id);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#findsPromotion(int, java.util.Date)
	 */
	@Override
	public ArrayList<PromotionPO> findsPromotion(int id, Date start) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findsPromotion(id, start);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#findsPromotion(int, java.lang.String)
	 */
	@Override
	public ArrayList<PromotionPO> findsPromotion(int id, String content) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionDataService.findsPromotion(id, content);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#insertPromotion(po.PromotionPO)
	 */
	@Override
	public void insertPromotion(PromotionPO ppo) throws RemoteException {
		// TODO Auto-generated method stub
		promotionDataService.insertPromotion(ppo);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#deletePromotion(po.PromotionPO)
	 */
	@Override
	public void deletePromotion(PromotionPO ppo) throws RemoteException {
		// TODO Auto-generated method stub
		promotionDataService.deletePromotion(ppo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#initPromotionDataService()
	 */
	@Override
	public void initPromotionDataService() throws RemoteException {
		// TODO Auto-generated method stub
		promotionDataService.initPromotionDataService();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#finishPromotionDataService()
	 */
	@Override
	public void finishPromotionDataService() throws RemoteException {
		// TODO Auto-generated method stub
		promotionDataService.finishPromotionDataService();
	}

	/**
	 * OrderDataService
	 * 以下为订单数据信息服务的通信实现
	 */
	/*
	 * (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#insertOrder(po.OrderPO)
	 */
	@Override
	public void insertOrder(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		orderDataService.insertOrder(po);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#deleteOrder(int)
	 */
	@Override
	public void deleteOrder(int id) throws RemoteException {
		// TODO Auto-generated method stub
		orderDataService.deleteOrder(id);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#updateOrder(int, po.OrderStatus)
	 */
	@Override
	public void updateOrder(int id, OrderStatus status) throws RemoteException {
		// TODO Auto-generated method stub
		orderDataService.updateOrder(id, status);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#findOrder(int)
	 */
	@Override
	public OrderPO findOrder(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return orderDataService.findOrder(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#findOrderByUserID(int)
	 */
	@Override
	public ArrayList<OrderPO> findOrderByUserID(int id) throws RemoteException{
		return orderDataService.findOrderByUserID(id);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#initOrderDataService()
	 */
	@Override
	public void initOrderDataService() throws RemoteException {
		// TODO Auto-generated method stub
		orderDataService.initOrderDataService();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#finishOrderDataService()
	 */
	@Override
	public void finishOrderDataService() throws RemoteException {
		// TODO Auto-generated method stub
		orderDataService.finishOrderDataService();
	}
	
	/**
	 * HotelDataService
	 * 以下为酒店数据信息服务的通信实现
	 */
	/*
	 * (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#deleteHotel(int)
	 */
	@Override
	public void deleteHotel(int id) throws RemoteException {
		// TODO Auto-generated method stub
		hotelDataService.deleteHotel(id);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#insertHotel(po.HotelPO)
	 */
	@Override
	public void insertHotel(HotelPO hpo) throws RemoteException {
		// TODO Auto-generated method stub
		hotelDataService.insertHotel(hpo);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#updateHotel(po.HotelPO)
	 */
	@Override
	public void updateHotel(HotelPO hpo) throws RemoteException {
		// TODO Auto-generated method stub
		hotelDataService.updateHotel(hpo);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#findHotel(java.lang.String)
	 */
	@Override
	public HotelPO findHotel(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDataService.findHotel(name);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#findsHotel(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<HotelPO> findsHotel(String field, String value) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDataService.findsHotel(field, value);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#findsHotel()
	 */
	@Override
	public ArrayList<HotelPO> findsHotel() throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDataService.findsHotel();
	}
	
	/*
	 * (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#initHotelDataService()
	 */
	@Override
	public void initHotelDataService() throws RemoteException {
		// TODO Auto-generated method stub
		hotelDataService.initHotelDataService();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#finishHotelDataService()
	 */
	@Override
	public void finishHotelDataService() throws RemoteException {
		// TODO Auto-generated method stub
		hotelDataService.finishHotelDataService();
	}

	/**
	 * CreditDataService
	 * 以下为信用值数据信息服务的通信实现
	 */
	/*
	 * (non-Javadoc)
	 * @see dataservice.creditdataservice.CreditDataService#insertCredit(po.CreditPO)
	 */
	@Override
	public void insertCredit(CreditPO cpo) throws RemoteException {
		// TODO Auto-generated method stub
		creditDataService.insertCredit(cpo);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.creditdataservice.CreditDataService#deleteCredit(int)
	 */
	@Override
	public void deleteCredit(int id) throws RemoteException {
		// TODO Auto-generated method stub
		creditDataService.deleteCredit(id);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.creditdataservice.CreditDataService#updateCredit(po.CreditPO)
	 */
	@Override
	public void updateCredit(CreditPO cpo) throws RemoteException {
		// TODO Auto-generated method stub
		creditDataService.updateCredit(cpo);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.creditdataservice.CreditDataService#findCredit(int)
	 */
	@Override
	public CreditPO findCredit(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return creditDataService.findCredit(id);
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.creditdataservice.CreditDataService#init()
	 */
	@Override
	public void initCreditDataService() throws RemoteException {
		// TODO Auto-generated method stub
		creditDataService.initCreditDataService();
	}

	/*
	 * (non-Javadoc)
	 * @see dataservice.creditdataservice.CreditDataService#finish()
	 */
	@Override
	public void finishCreditDataService() throws RemoteException {
		// TODO Auto-generated method stub
		creditDataService.finishCreditDataService();
	}
	
}
