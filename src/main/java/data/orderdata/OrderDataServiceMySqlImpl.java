package data.orderdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
<<<<<<< HEAD
=======

>>>>>>> origin/master
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
		String sql = "insert into order  values(?,?,?,?,?,?,?,?,?,?)";
		try {
			statement = (PreparedStatement) connect.prepareStatement(sql);
			statement.setInt(1, po.getOrderID());
			statement.setInt(2, po.getUserID());
			statement.setInt(3, po.getHotelID());
			statement.setString(4, po.getOrderStatus().toString());
			statement.setInt(5, po.getPrice());
			statement.setString(6, po.getRoomType().toString());
			statement.setInt(7, po.getRoomNumber());
			statement.setTimestamp(8, new Timestamp(po.getSetTime().getTime()));
			statement.setTimestamp(9, new Timestamp(po.getCheckIn().getTime()));
			statement.setTimestamp(10, new Timestamp(po.getCheckOut().getTime()));
			int i = statement.executeUpdate();
			System.out.println("Insert result: " + i);
			statement.close();
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
		String sql = "delete from order where orderid='" + id + "'";
		try {
			statement = (PreparedStatement) connect.prepareStatement(sql);
			int i = statement.executeUpdate();
			System.out.println("Delete result: " + i);
			statement.close();
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
		String sql = "update order set orderstatus='" + status + "' where orderid='" + id + "'";
		try {
			statement = (PreparedStatement) connect.prepareStatement(sql);
			int i = statement.executeUpdate();
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
		String sql = "select * from order where orderid='" + id + "'";
		try {
			statement = (PreparedStatement) connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				if (rs.getInt(1) == id) {
					for (int i = 1; i <= col; i++) {
						System.out.print(rs.getString(i) + "\t");
					}
					System.out.println("");
					OrderPO po = new OrderPO(rs.getInt(1), rs.getInt(2), rs.getInt(3),
							OrderStatus.valueOf(rs.getString(4)), rs.getInt(5), RoomType.valueOf(rs.getString(6)),
							rs.getInt(7), rs.getDate(8), rs.getDate(9), rs.getDate(10));
					statement.close();
					return po;
				}
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
	public ArrayList<OrderPO> findOrderByUserID(int id) throws RemoteException {
		// TODO finish the method
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		try {
			statement = connect.prepareStatement("select * from order where userid = ?");
			statement.setString(1, String.valueOf(id));
			
			ResultSet rs = statement.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				if (rs.getInt(2) == id) {
					for (int i = 1; i <= col; i++) {
						System.out.print(rs.getString(i) + "\t");
					}
					System.out.println("");
					OrderPO po = new OrderPO(rs.getInt(1), rs.getInt(2), rs.getInt(3),
							OrderStatus.valueOf(rs.getString(4)), rs.getInt(5), RoomType.valueOf(rs.getString(6)),
							rs.getInt(7), rs.getDate(8), rs.getDate(9), rs.getDate(10));
					list.add(po);
				}
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	};

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

//	public static void main(String args[]) throws RemoteException {
//		OrderDataServiceMySqlImpl o = new OrderDataServiceMySqlImpl();
//		o.initOrderDataService();
<<<<<<< HEAD
//		o.insertOrder(new OrderPO(0, 0, 0, OrderStatus.Abnormal, 0, RoomType.KING_SIZE_ROOM, 0,
//				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
//				new Date(System.currentTimeMillis())));
//		o.findOrder(0);
//		o.insertOrder(new OrderPO(2, 0, 0, OrderStatus.Abnormal, 0, RoomType.KING_SIZE_ROOM, 0,
//				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
//				new Date(System.currentTimeMillis())));
//		o.findOrderByUserID(0);
//		o.deleteOrder(0);
//		o.deleteOrder(2);
=======
//		o.findOrderByUserID(20905098);
>>>>>>> origin/master
//		o.finishOrderDataService();
//	}
}
