package data.hoteldata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;
import po.RoomPO;
import po.RoomType;

public class HotelDataServiceMySqlImpl implements HotelDataService{

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public HotelDataServiceMySqlImpl() {
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	/**
	 * 
	 * @param hpo
	 * @throws RemoteException
	 */
	@Override
	public void deleteHotel(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("delete from hotel where id = ?");
			statement.setString(1, String.valueOf(id));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param hpo
	 * @throws RemoteException
	 */
	@Override
	public void insertHotel(HotelPO hpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//为防止重复插入信息应进行检查
			if(findHotel(hpo.getHotelName()) != null) return;
			
			//列：id, workername, hotelname, businessarea, address
			//star, phone, roomtype, roomnumber, description, rate
			int columns = 11;
			statement = connect.prepareStatement("insert into hotel values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ArrayList<String> list = toString(hpo);
			
			for(int i = 0; i < columns; i++){
				statement.setString(i+1, list.get(i));
			}
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param hpo
	 * @throws RemoteException
	 */
	@Override
	public void updateHotel(HotelPO hpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			/*//为防止重复插入信息应进行检查
			if(findHotel(hpo.getHotelName()) != null) return;*/
			//为防止没有原始信息应进行检查
			if(findHotel(hpo.getHotelName()) == null) return;
			
			//列：id, workername, hotelname, businessarea, address
			//star, phone, roomtype, roomnumber, description, rate
			int columns = 11;
			statement = connect.prepareStatement("update hotel set id = ?, workername = ?, hotelname = ?, businessarea = ?, "
					+ "address =  ?, star = ?, phone = ?, roomtype = ?, roomnumber = ?, description = ?, rate = ? where id = ?");
			ArrayList<String> list = toString(hpo);
			
			for(int i = 0; i < columns; i++){
				statement.setString(i+1, list.get(i));
			}
			statement.setString(columns+1, String.valueOf(hpo.getHotelID()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param name
	 * @return 按酒店姓名查找并返回酒店信息
	 * @throws RemoteException
	 */
	@Override
	public HotelPO findHotel(String name) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id, workername, hotelname, businessarea, address
			//star, phone, roomtype, roomnumber, description, rate
			
			statement = connect.prepareStatement("select * from hotel where hotelname = ?");
			statement.setString(1, name);
			result = statement.executeQuery(); 
			
			int id = -1;
			String workername = "";
			String businessarea = "";
			String address = "";
			int star = -1;
			String phone = "";
			//RoomType roomtype = null;
			int roomnumber = -1;
			String description = "";
			int rate = -1;
			
			while(result.next()){
				id = Integer.valueOf(result.getString(1));
				workername = result.getString(2);
				businessarea = result.getString(4);
				address = result.getString(5);
				star = Integer.valueOf(result.getString(6));
				phone = result.getString(7);
				//roomtype = (RoomType)(Object)(result.getString(8));//????
				roomnumber = Integer.valueOf(result.getString(9));
				description = result.getString(10);
				rate = Integer.valueOf(result.getString(11));
			}
			
			return new HotelPO(id, name, address, businessarea, description, star, roomnumber, null, rate, workername, phone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param field
	 * @param value
	 * @return 按给定字段名与值查找并返回酒店信息
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<HotelPO> findsHotel(String field, String value) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id, workername, hotelname, businessarea, address
			//star, phone, roomtype, roomnumber, description, rate
			
			statement = connect.prepareStatement("select * from hotel where "+field+" = ?");
			statement.setString(1, value);
			result = statement.executeQuery(); 
			
			int id = -1;
			String workername = "";
			String hotelname = "";
			String businessarea = "";
			String address = "";
			int star = -1;
			String phone = "";
			//RoomType roomtype = null;
			int roomnumber = -1;
			String description = "";
			int rate = -1;
			
			ArrayList<HotelPO> hpoList = new ArrayList<HotelPO>();
			
			while(result.next()){
				id = Integer.valueOf(result.getString(1));
				workername = result.getString(2);
				hotelname = result.getString(3);
				businessarea = result.getString(4);
				address = result.getString(5);
				star = Integer.valueOf(result.getString(6));
				phone = result.getString(7);
				//roomtype = (RoomType)(Object)(result.getString(8));//????
				roomnumber = Integer.valueOf(result.getString(9));
				description = result.getString(10);
				rate = Integer.valueOf(result.getString(11));
				
				HotelPO hpo = new HotelPO(id, hotelname, address, businessarea, description, star, roomnumber, null, rate, workername, phone);
				hpoList.add(hpo);
			}
			
			if(id != -1){
				return hpoList;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return 查找并返回所有酒店信息
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<HotelPO> findsHotel() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id, workername, hotelname, businessarea, address
			//star, phone, roomtype, roomnumber, description, rate
			
			statement = connect.prepareStatement("select * from hotel");
			result = statement.executeQuery(); 
			
			int id = -1;
			String workername = "";
			String hotelname = "";
			String businessarea = "";
			String address = "";
			int star = -1;
			String phone = "";
			//RoomType roomtype = null;
			int roomnumber = -1;
			String description = "";
			int rate = -1;
			
			ArrayList<HotelPO> hpoList = new ArrayList<HotelPO>();
			
			while(result.next()){
				id = Integer.valueOf(result.getString(1));
				workername = result.getString(2);
				hotelname = result.getString(3);
				businessarea = result.getString(4);
				address = result.getString(5);
				star = Integer.valueOf(result.getString(6));
				phone = result.getString(7);
				//roomtype = (RoomType)(Object)(result.getString(8));//????
				roomnumber = Integer.valueOf(result.getString(9));
				description = result.getString(10);
				rate = Integer.valueOf(result.getString(11));
				
				HotelPO hpo = new HotelPO(id, hotelname, address, businessarea, description, star, roomnumber, null, rate, workername, phone);
				hpoList.add(hpo);
			}
			
			if(id != -1){
				return hpoList;
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
	public void initHotelDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void finishHotelDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}
	
	/**
	 * 
	 * @param hpo
	 * @return 将传入的HotelPO中的每一项转成字符串并返回列表
	 */
	private ArrayList<String> toString (HotelPO hpo){
		//列：id, workername, hotelname, businessarea, address
		//star, phone, roomtype, roomnumber, description, rate
		ArrayList<String> list = new ArrayList<String>();
		
		//按列顺序依次添加
		list.add(String.valueOf(hpo.getHotelID()));
		list.add(hpo.getStaffName());
		list.add(hpo.getHotelName());
		list.add(hpo.getBusinessArea());
		list.add(hpo.getHotelAddress());
		list.add(String.valueOf(hpo.getStarLevel()));
		list.add(hpo.getPhoneNumber());
		list.add(String.valueOf(getRoomTypes(hpo)));
		list.add(String.valueOf(hpo.getRoomNumber()));
		list.add(hpo.getHotelDescription());
		list.add(String.valueOf(hpo.getRating()));
		
		return list;
	}
	
	private HashSet<RoomType> getRoomTypes(HotelPO hpo){
		HashSet<RoomType> set = new HashSet<RoomType>();
		
		//遍历HotelPO中的rooms的type
		ArrayList<RoomPO> list = hpo.getRooms();
		for(int i = 0; i < list.size(); i++){
			set.add(list.get(i).getRoomType());
		}
		
		return set;
	}
	
}
