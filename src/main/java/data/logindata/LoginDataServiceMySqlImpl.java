package data.logindata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dataservice.logindataservice.LoginDataService;

public class LoginDataServiceMySqlImpl implements LoginDataService{

	Connection connect;
	PreparedStatement statement;
	ResultSet result;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param id
	 * @return 是否存在相应用户
	 * @throws RemoteException
	 */
	public boolean isValidateUser(String username, String password, int id) throws RemoteException{
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("select id from user where username = ? and password = password(?)");
			
			//列：id; username; password
			statement.setString(1, username);
			statement.setString(2, password);
			
			result = statement.executeQuery();
			
			//UserPO: id; username; password
			int userID = -1;
			
			//遍历result
			while(result.next()){
				userID = result.getInt(1);
			}
			
			if(userID / 10000000 == id){
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
