package data.orderdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.orderdataservice.OrderDataService;
import po.OrderPO;
import po.OrderStatus;
import po.RoomType;

public class OrderDataServiceMySqlImpl implements OrderDataService {

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	static final int COL_NUM = 10;

	// 类中有待修改的语句

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
		try {
			statement = connect.prepareStatement("insert into hms_order values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			statement.setInt(1, po.getOrderID());
			statement.setString(2, po.getUserName());
			statement.setString(3, po.getHotelName());
			statement.setString(4, po.getOrderStatus().toString());
			statement.setInt(5, po.getPrice());
			statement.setString(6, po.getRoomType().toString());
			statement.setInt(7, po.getRoomNumber());
			statement.setTimestamp(8, po.getSetTime());
			statement.setDate(9, po.getCheckIn());
			statement.setDate(10, po.getCheckOut());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param po
	 * @throws RemoteException
	 */
	@Override
	public void deleteOrder(int id) throws RemoteException {
		try {
			statement = connect.prepareStatement("delete from hms_order where orderid = ?");

			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param po
	 * @throws RemoteException
	 */
	@Override
	public void updateOrder(int id, OrderStatus status) throws RemoteException {
		try {
			statement = connect.prepareStatement("update hms_order set orderstatus = ? where orderid = ?");

			statement.setString(1, String.valueOf(status));
			statement.setInt(2, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param orderID,userName,hotelName,orderStatus,price,roomType,roomNumber,setTime,checkIn,checkOut
	 * @return OrderPO
	 */
	private OrderPO getOrderPO(int orderID, String userName, String hotelName, OrderStatus orderStatus, int price,
			RoomType roomType, int roomNumber, Timestamp setTime, Date checkIn, Date checkOut) {
		return new OrderPO(orderID, userName, hotelName, orderStatus, price, roomType, roomNumber, setTime, checkIn,
				checkOut);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findOrder() throws RemoteException {
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		try {
			statement = connect.prepareStatement("select * from hms_order");
			result = statement.executeQuery();
			while (result.next()) {
				for (int i = 1; i <= COL_NUM; i++) {
					// print, 待删除
					System.out.print(result.getString(i) + "\t");
				}
				// print, 待删除
				System.out.println("");

				list.add(getOrderPO(result.getInt(1), result.getString(2), result.getString(3),
						OrderStatus.valueOf(result.getString(4)), result.getInt(5),
						RoomType.valueOf(result.getString(6)), result.getInt(7), result.getTimestamp(8),
						result.getDate(9), result.getDate(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public OrderPO findOrderByOrderID(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareCall("select * from hms_order where orderid = ?");

			statement.setInt(1, id);

			result = statement.executeQuery();
			while (result.next()) {
				for (int i = 1; i <= COL_NUM; i++) {
					// print, 待删除
					System.out.print(result.getString(i) + "\t");
				}
				// print, 待删除
				System.out.println("");

				return getOrderPO(result.getInt(1), result.getString(2), result.getString(3),
						OrderStatus.valueOf(result.getString(4)), result.getInt(5),
						RoomType.valueOf(result.getString(6)), result.getInt(7), result.getTimestamp(8),
						result.getDate(9), result.getDate(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findOrderByUserName(String userName) throws RemoteException {
		return findOrderByName("select * from hms_order where username = ?", userName);
	};

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findOrderByHotelName(String hotelName) throws RemoteException {
		return findOrderByName("select * from hms_order where hotelname = ?", hotelName);
	}

	private ArrayList<OrderPO> findOrderByName(String sqlStatement, String name) {
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		try {
			statement = connect.prepareStatement(sqlStatement);

			statement.setString(1, name);

			result = statement.executeQuery();
			while (result.next()) {
				for (int i = 1; i <= COL_NUM; i++) {
					// print, 待删除
					System.out.print(result.getString(i) + "\t");
				}
				// print, 待删除
				System.out.println("");

				list.add(getOrderPO(result.getInt(1), result.getString(2), result.getString(3),
						OrderStatus.valueOf(result.getString(4)), result.getInt(5),
						RoomType.valueOf(result.getString(6)), result.getInt(7), result.getTimestamp(8),
						result.getDate(9), result.getDate(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void initOrderDataService() throws RemoteException {
		connect = configure.init();
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void finishOrderDataService() throws RemoteException {
		configure.finish(connect, statement, result);
	}

//	public static void main(String args[]) throws RemoteException {
//		OrderDataServiceMySqlImpl o = new OrderDataServiceMySqlImpl();
//		o.initOrderDataService();
//		 o.insertOrder(new OrderPO(60102931, "庄宇州", "天字一号房",
//		 OrderStatus.Abnormal, 100, RoomType.KING_SIZE_ROOM, 2,
//		 new Timestamp(System.currentTimeMillis()), new Date(0), new Date(0)));
//		o.findOrderByOrderID(60102931);
//		o.deleteOrder(60102931);
//		o.findOrderByUserName("tom");
//		ArrayList<OrderPO> opo = o.findOrderByHotelName("盘丝洞");
//		System.out.println(opo.size());
//		o.finishOrderDataService();
//	}
}
