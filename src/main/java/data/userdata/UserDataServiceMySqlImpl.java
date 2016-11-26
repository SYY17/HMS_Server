package data.userdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.userdataservice.UserDataService;
import po.UserPO;

public class UserDataServiceMySqlImpl implements UserDataService{

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public UserDataServiceMySqlImpl() {
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	/**
	 * 
	 * @param upo
	 * @throws RemoteException
	 */
	@Override
	public void insertUser(UserPO upo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//为防止重复插入信息应进行检查
			if(findUser(upo.getName()) != null) return;
			
			//列：id; username; password
			statement = connect.prepareStatement("insert into user values(?, ?, ?)");
			
			statement.setString(1, String.valueOf(upo.getID()));
			statement.setString(2, upo.getName());
			statement.setString(3, upo.getPassword());
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param upo
	 * @throws RemoteException
	 */
	@Override
	public void deleteUser(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id; username; password
			statement = connect.prepareStatement("delete from user where id = ?");
			statement.setString(1, String.valueOf(id));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param upo
	 * @throws RemoteException
	 */
	@Override
	public void updateUser(UserPO upo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id; username; password
			statement = connect.prepareStatement("update user set id = ?, username = ?, password = ? where id = ?");
			statement.setString(1, String.valueOf(upo.getID()));
			statement.setString(2, upo.getName());
			statement.setString(3, upo.getPassword());
			statement.setString(4, String.valueOf(upo.getID()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @return 根据ID查找并返回用户信息
	 * @throws RemoteException
	 */
	@Override
	public UserPO findUser(String username) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("select * from user where username = ?");
			
			//列：id; username; password
			statement.setString(1, username);
			
			result = statement.executeQuery();
			
			//CreditPO: id; credit
			int id = -1;
			String password = "";
			
			//遍历result
			while(result.next()){
				id = Integer.valueOf(result.getString(1));
				password = result.getString(3);
			}
			
			if(id != -1){
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
	public void initUserDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void finishUserDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}

	/**
	 * 
	 * @return 查找并返回全部用户信息
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<UserPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<UserPO> list = new ArrayList<UserPO>(); 
		try {
			statement = connect.prepareStatement("select * from user");
			
			//列：id; username; password
			result = statement.executeQuery();
			
			//CreditPO: id; credit
			int id = 0;
			String username = "";
			String password = "";
			
			//遍历result
			while(result.next()){
				id = Integer.valueOf(result.getString(1));
				username = result.getString(2);
				password = result.getString(3);
				list.add(new UserPO(id, username, password));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
