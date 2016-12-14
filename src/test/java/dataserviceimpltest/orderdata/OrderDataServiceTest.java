package dataserviceimpltest.orderdata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import data.orderdata.OrderDataServiceMySqlImpl;
import dataservice.orderdataservice.OrderDataService;
import po.OrderPO;
import po.OrderStatus;
import po.RoomType;

public class OrderDataServiceTest {
	private OrderDataService orderDataService;
	private int orderID;
	private String userName;
	private String hotelName;
	private OrderStatus orderStatus;
	private int price;
	private RoomType roomType;
	private int roomNumber;
	private Timestamp setTime;
	private Date checkIn;
	private Date checkOut;
	private OrderPO opo;

	@Before
	public void setUp() throws Exception {
		orderDataService = new OrderDataServiceMySqlImpl();
		orderID = 18;
		userName = "庄刚轻";
		hotelName = "刚轻厂";
		orderStatus = OrderStatus.Abnormal;
		price = 200;
		roomType = RoomType.KING_SIZE_ROOM;
		roomNumber = 2;
		setTime = new Timestamp(System.currentTimeMillis());
		checkIn = new Date(System.currentTimeMillis());
		checkOut = new Date(System.currentTimeMillis());
		orderDataService.initOrderDataService();
	}

	@Test
	public void testInsertOrder() throws RemoteException {
		opo = new OrderPO(orderID, userName, hotelName, orderStatus, price, roomType, roomNumber, setTime, checkIn,
				checkOut);
		orderDataService.insertOrder(opo);
		OrderPO opoTemp = orderDataService.findOrderByOrderID(orderID);
		assertEquals(userName, opoTemp.getUserName());
		assertEquals(hotelName, opoTemp.getHotelName());
		assertEquals(orderStatus.toString(), opoTemp.getOrderStatus().toString());
		assertEquals(price, opoTemp.getPrice());
		assertEquals(roomType.toString(), opoTemp.getRoomType().toString());
		assertEquals(roomNumber, opoTemp.getRoomNumber());
		assertEquals(setTime.getTime(), opoTemp.getSetTime().getTime());
		assertEquals(checkIn.toString(), opoTemp.getCheckIn().toString());
		assertEquals(checkOut.toString(), opoTemp.getCheckOut().toString());
		orderDataService.deleteOrder(orderID);
	}

	@Test
	public void testDeleteOrder() throws RemoteException {
		opo = new OrderPO(orderID, userName, hotelName, orderStatus, price, roomType, roomNumber, setTime, checkIn,
				checkOut);
		orderDataService.insertOrder(opo);
		orderDataService.deleteOrder(orderID);
		OrderPO opoTemp = orderDataService.findOrderByOrderID(orderID);
		assertEquals(opoTemp, null);
	}

	@Test
	public void testUpdateOrder() throws RemoteException {
		opo = new OrderPO(orderID, userName, hotelName, orderStatus, price, roomType, roomNumber, setTime, checkIn,
				checkOut);
		orderDataService.insertOrder(opo);
		OrderPO opoTemp = orderDataService.findOrderByOrderID(orderID);
		assertEquals(userName, opoTemp.getUserName());
		assertEquals(hotelName, opoTemp.getHotelName());
		assertEquals(orderStatus.toString(), opoTemp.getOrderStatus().toString());
		assertEquals(price, opoTemp.getPrice());
		assertEquals(roomType.toString(), opoTemp.getRoomType().toString());
		assertEquals(roomNumber, opoTemp.getRoomNumber());
		assertEquals(setTime.getTime(), opoTemp.getSetTime().getTime());
		assertEquals(checkIn.toString(), opoTemp.getCheckIn().toString());
		assertEquals(checkOut.toString(), opoTemp.getCheckOut().toString());
		
		orderDataService.updateOrder(orderID, OrderStatus.Finished);
		opoTemp = orderDataService.findOrderByOrderID(orderID);
		assertEquals(OrderStatus.Finished.toString(), opoTemp.getOrderStatus().toString());
		orderDataService.deleteOrder(orderID);
	}

	@Test
	public void testFindOrder() throws RemoteException {
		ArrayList<OrderPO> orderList = orderDataService.findOrder();
		assertNotEquals(orderList.get(0), null);
	}

	@Test
	public void testFindOrderByOrderID() throws RemoteException {
		opo = new OrderPO(orderID, userName, hotelName, orderStatus, price, roomType, roomNumber, setTime, checkIn,
				checkOut);
		orderDataService.insertOrder(opo);
		OrderPO opoTemp = orderDataService.findOrderByOrderID(orderID);
		assertEquals(userName, opoTemp.getUserName());
		assertEquals(hotelName, opoTemp.getHotelName());
		assertEquals(orderStatus.toString(), opoTemp.getOrderStatus().toString());
		assertEquals(price, opoTemp.getPrice());
		assertEquals(roomType.toString(), opoTemp.getRoomType().toString());
		assertEquals(roomNumber, opoTemp.getRoomNumber());
		assertEquals(setTime.getTime(), opoTemp.getSetTime().getTime());
		assertEquals(checkIn.toString(), opoTemp.getCheckIn().toString());
		assertEquals(checkOut.toString(), opoTemp.getCheckOut().toString());
		orderDataService.deleteOrder(orderID);
	}

	@Test
	public void testFindOrderByUserName() throws RemoteException {
		opo = new OrderPO(orderID, userName, hotelName, orderStatus, price, roomType, roomNumber, setTime, checkIn,
				checkOut);
		orderDataService.insertOrder(opo);
		OrderPO opoTemp = orderDataService.findOrderByUserName(userName).get(0);
		assertEquals(userName, opoTemp.getUserName());
		assertEquals(hotelName, opoTemp.getHotelName());
		assertEquals(orderStatus.toString(), opoTemp.getOrderStatus().toString());
		assertEquals(price, opoTemp.getPrice());
		assertEquals(roomType.toString(), opoTemp.getRoomType().toString());
		assertEquals(roomNumber, opoTemp.getRoomNumber());
		assertEquals(setTime.getTime(), opoTemp.getSetTime().getTime());
		assertEquals(checkIn.toString(), opoTemp.getCheckIn().toString());
		assertEquals(checkOut.toString(), opoTemp.getCheckOut().toString());
		orderDataService.deleteOrder(orderID);
	}

	@Test
	public void testFindOrderByHotelName() throws RemoteException {
		opo = new OrderPO(orderID, userName, hotelName, orderStatus, price, roomType, roomNumber, setTime, checkIn,
				checkOut);
		orderDataService.insertOrder(opo);
		OrderPO opoTemp = orderDataService.findOrderByHotelName(hotelName).get(0);
		assertEquals(userName, opoTemp.getUserName());
		assertEquals(hotelName, opoTemp.getHotelName());
		assertEquals(orderStatus.toString(), opoTemp.getOrderStatus().toString());
		assertEquals(price, opoTemp.getPrice());
		assertEquals(roomType.toString(), opoTemp.getRoomType().toString());
		assertEquals(roomNumber, opoTemp.getRoomNumber());
		assertEquals(setTime.getTime(), opoTemp.getSetTime().getTime());
		assertEquals(checkIn.toString(), opoTemp.getCheckIn().toString());
		assertEquals(checkOut.toString(), opoTemp.getCheckOut().toString());
		orderDataService.deleteOrder(orderID);
	}

}
