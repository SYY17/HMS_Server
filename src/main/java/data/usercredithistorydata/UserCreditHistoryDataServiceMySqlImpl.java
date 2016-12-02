package data.usercredithistorydata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.usercredithistoryservice.UserCreditHistoryDataService;
import po.UserCreditHistoryPO;

public class UserCreditHistoryDataServiceMySqlImpl implements UserCreditHistoryDataService {

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public UserCreditHistoryDataServiceMySqlImpl(){
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	@Override
	public void updateHistory(UserCreditHistoryPO ucpo) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			//列：id; change; date
			statement = connect.prepareStatement("insert into UserCreditHistory values(?, ?, ?)");
			
			statement.setString(1, String.valueOf(ucpo.getUserId()));
			statement.setString(2, String.valueOf(ucpo.getChange()));
			statement.setString(3, String.valueOf(ucpo.getTime()));
			
			statement.execute();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<UserCreditHistoryPO> findCreditHistory(int userId) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<UserCreditHistoryPO> list = new ArrayList<UserCreditHistoryPO>();
		 
		try{
			statement = connect.prepareStatement("select * from UserCreditHistory where id = ?");
			
			//列：id; change; date
			statement.setString(1, String.valueOf(userId));
			
			result = statement.executeQuery();
			String tempChange;
			String tempDate;
			
			//遍历result
			while(result.next()){
				tempChange = result.getString(2);
				tempDate = result.getString(3);
				list.add(new UserCreditHistoryPO(userId, Integer.parseInt(tempChange),Date.valueOf(tempDate)));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<UserCreditHistoryPO> getAllCreditHistory() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<UserCreditHistoryPO> list = new ArrayList<UserCreditHistoryPO>();
		 
		try{
			statement = connect.prepareStatement("select * from UserCreditHistory");
			
			//列：id; change; date
			result = statement.executeQuery();
			String tempId;
			String tempChange;
			String tempDate;
			
			//遍历result
			while(result.next()){
				tempId = result.getString(1);
				tempChange = result.getString(2);
				tempDate = result.getString(3);
				list.add(new UserCreditHistoryPO(Integer.parseInt(tempId), Integer.parseInt(tempChange),Date.valueOf(tempDate)));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void initUserCreditHistoryDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	@Override
	public void finishUserCreditHistoryDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}


}
