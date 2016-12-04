package data.orderdata;

import java.rmi.RemoteException;
import java.sql.Connection;
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
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("insert into hms_order values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, po.getOrderID());
			statement.setString(2, po.getUserName());
			statement.setString(3, po.getHotelName());
			statement.setString(4, po.getOrderStatus().toString());
			statement.setInt(5, po.getPrice());
			statement.setString(6, po.getRoomType().toString());
			statement.setInt(7, po.getRoomNumber());
			statement.setTimestamp(8, new Timestamp(po.getSetTime().getTime()));
			statement.setTimestamp(9, new Timestamp(po.getCheckIn().getTime()));
			statement.setTimestamp(10, new Timestamp(po.getCheckOut().getTime()));
			int i = statement.executeUpdate();

			// print, 待删除
			System.out.println("Insert result: " + i);
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
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("delete from hms_order where orderid = ?");

			statement.setInt(1, id);

			int i = statement.executeUpdate();

			// print, 待删除
			System.out.println("Delete result: " + i);
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
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("update hms_order set orderstatus = ? where orderid = ?");

			statement.setString(1, String.valueOf(status));
			statement.setInt(2, id);

			int i = statement.executeUpdate();

			// print, 待删除
			System.out.println("Update result: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		try {
			statement = connect.prepareCall("select * from hms_order where orderid = ?");

			statement.setInt(1, id);

			result = statement.executeQuery();

			// 建议在代码中明确数据库中的列数以及顺序
			int col = result.getMetaData().getColumnCount();
			for (int i = 1; i <= col; i++) {
				// print, 待删除
				System.out.print(result.getString(i) + "\t");
			}
			// print, 待删除
			System.out.println("");

			// 待修改，可以抽象出一个将各参数转换为PO对象的方法
			OrderPO po = new OrderPO(result.getInt(1), result.getString(2), result.getString(3),
					OrderStatus.valueOf(result.getString(4)), result.getInt(5), RoomType.valueOf(result.getString(6)),
					result.getInt(7), result.getTimestamp(8), result.getDate(9), result.getDate(10));
			return po;
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
		return findOrderByName("select * from hms_order where username = ?" , userName);
	};

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findOrderByHotelName(String hotelName) throws RemoteException {
		return findOrderByName("select * from hms_order where hotelname = ?" , hotelName);
	}

	private ArrayList<OrderPO> findOrderByName(String sqlStatement,String name) {
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		try {
			statement = connect.prepareStatement(sqlStatement);
			statement.setString(1, name);
			result = statement.executeQuery();
			int col = result.getMetaData().getColumnCount();
			System.out.println(col);
			while (result.next()) {
				for (int i = 1; i <= col; i++) {
					// print, 待删除
					System.out.print(result.getString(i) + "\t");
				}
				// print, 待删除
				System.out.println("");

				// 待修改，可以抽象出一个将各参数转换为PO对象的方法
				OrderPO po = new OrderPO(result.getInt(1), result.getString(2), result.getString(3),
						OrderStatus.valueOf(result.getString(4)), result.getInt(5),
						RoomType.valueOf(result.getString(6)), result.getInt(7), result.getTimestamp(8),
						result.getDate(9), result.getDate(10));
				list.add(po);
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

	 public static void main(String args[]) throws RemoteException {
	 OrderDataServiceMySqlImpl o = new OrderDataServiceMySqlImpl();
	 o.initOrderDataService();
	 o.findOrderByUserName("tom");
	 o.finishOrderDataService();
	 }
}
