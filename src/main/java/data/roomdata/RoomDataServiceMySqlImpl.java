package data.roomdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.roomdataservice.RoomDataService;
import po.RoomPO;
import po.RoomType;

public class RoomDataServiceMySqlImpl implements RoomDataService {

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;

	public RoomDataServiceMySqlImpl() {
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}

	/**
	 * 
	 * @param rpo
	 */
	@Override
	public void insertRoom(RoomPO rpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			// 为防止重复插入信息应进行检查
			// if(findRoom(rpo.getHotelID(), rpo.getRoomType()) != null) return;
			// //不知为何加上会报错

			// 列：id; roomtype; total; remain; price
			statement = connect.prepareStatement("insert into room values(?, ?, ?, ?, ?)");

			statement.setString(1, String.valueOf(rpo.getHotelID()));
			statement.setString(2, String.valueOf(rpo.getRoomType()));
			statement.setString(3, String.valueOf(rpo.getTotalSum()));
			statement.setString(4, String.valueOf(rpo.getRemainSum()));
			statement.setString(5, String.valueOf(rpo.getPrice()));

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 */
	@Override
	public void deleteAllRooms(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			// 列：id; roomtype; total; remain; price
			statement = connect.prepareStatement("delete from room where id = ?");
			statement.setString(1, String.valueOf(id));

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @param type
	 */
	@Override
	public void deleteRoom(int id, RoomType type) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			// 列：id; roomtype; total; remain; price
			statement = connect.prepareStatement("delete from room where id = ? and roomtype = ?");
			statement.setString(1, String.valueOf(id));
			statement.setString(2, String.valueOf(type));

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @param type
	 * @param price
	 */
	@Override
	public void updatePrice(int id, RoomType type, int price) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			// 列：id; roomtype; total; remain; price
			statement = connect.prepareStatement("update room set price = ? where id = ? and roomtype = ?");
			statement.setString(1, String.valueOf(price));
			statement.setString(2, String.valueOf(id));
			statement.setString(3, String.valueOf(type));

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @param type
	 * @param remain
	 */
	@Override
	public void updateRemainSum(int id, RoomType type, int remain) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			// 列：id; roomtype; total; remain; price
			statement = connect.prepareStatement("update room set remain = ? where id = ? and roomtype = ?");
			statement.setString(1, String.valueOf(remain));
			statement.setString(2, String.valueOf(id));
			statement.setString(3, String.valueOf(type));

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @param type
	 * @param total
	 */
	@Override
	public void updateTotalSum(int id, RoomType type, int total) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			// 列：id; roomtype; total; remain; price
			statement = connect.prepareStatement("update room set total = ? where id = ? and roomtype = ?");
			statement.setString(1, String.valueOf(total));
			statement.setString(2, String.valueOf(id));
			statement.setString(3, String.valueOf(type));

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @param type
	 * @return 根据酒店工作人员ID与房间类型查找并返回相应房间信息
	 */
	@Override
	public RoomPO findRoom(int id, RoomType type) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("select * from room where id = ?");

			// 列：id; roomtype; total; remain; price
			statement.setString(1, String.valueOf(id));

			result = statement.executeQuery();

			// RoomPO: id, roomtype, total, remain, price
			int total = -1;
			int remain = -1;
			int price = -1;

			// 遍历result
			while (result.next()) {
				if (result.getString(2).equals(type.toString())) {
					total = Integer.valueOf(result.getString(3));
					remain = Integer.valueOf(result.getString(4));
					price = Integer.valueOf(result.getString(5));
				}
			}

			if (total != -1) {
				return new RoomPO(id, type, total, remain, price);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public ArrayList<RoomPO> findRooms(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			/// 列：id; roomtype; total; remain; price

			statement = connect.prepareStatement("select * from room where id = ?");
			statement.setString(1, String.valueOf(id));
			result = statement.executeQuery();

			RoomType roomtype = null;
			int totalSum = -1;
			int remainSum = -1;
			int price = -1;

			ArrayList<RoomPO> rpoList = new ArrayList<RoomPO>();

			while (result.next()) {
				// id = Integer.valueOf(result.getString(1));
				roomtype = RoomType.valueOf(result.getString(2));
				totalSum = Integer.valueOf(result.getString(3));
				remainSum = Integer.valueOf(result.getString(4));
				price = Integer.valueOf(result.getString(5));

				RoomPO rpo = new RoomPO(id, roomtype, totalSum, remainSum, price);
				rpoList.add(rpo);
			}

			if (id != -1) {
				return rpoList;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void initRoomDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void finishRoomDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}

}
