package data.logindata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dataservice.logindataservice.LoginDataService;
import po.UserPO;

public class LoginDataServiceMySqlImpl implements LoginDataService{

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param id
	 * @throws RemoteException
	 */
	@Override
	public void insertUser(String username, String password, int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//为防止重复插入信息应进行检查
			if(findUser(id) != null) return;
			
			//列：id; username; password
			statement = connect.prepareStatement("insert into user values(?, ?, ?)");
			
			statement.setString(1, String.valueOf(id));
			statement.setString(2, username);
			statement.setString(3, password);
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param id
	 * @return 按ID查找并返回用户信息
	 * @throws RemoteException
	 */
	@Override
	public UserPO findUser(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("select * from user where id = ?");
			
			//列：id; username; password
			statement.setString(1, String.valueOf(id));
			
			result = statement.executeQuery();
			
			//UserPO: id; username; password
			String username = "";
			String password = "";
			
			//遍历result
			while(result.next()){
				username = result.getString(2);
				password = result.getString(3);
			}
			
			if(username != ""){
				return new UserPO(id, username, password);
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
	public void initLoginDataService() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management_system?useSSL=false", "root", "");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void finishLoginDataService() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			if(result != null){
				result.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		LoginDataServiceMySqlImpl s = new LoginDataServiceMySqlImpl();
//		try {
//			s.initLoginDataService();
//			s.insertUser("admin", "000000", 10916231);
//			UserPO upo = s.findUser(10916231);
//			System.out.print("id = "+upo.getID());
//			System.out.print("; username = "+upo.getName());
//			System.out.println("; password = "+upo.getPassword());
//			s.finishLoginDataService();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
