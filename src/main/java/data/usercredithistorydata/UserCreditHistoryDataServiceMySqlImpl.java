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
import po.CreditMovement;
import po.UserCreditHistoryPO;

public class UserCreditHistoryDataServiceMySqlImpl implements UserCreditHistoryDataService {

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;

	public UserCreditHistoryDataServiceMySqlImpl() {
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}

	@Override
	public void updateHistory(int userID, int change, Date date, CreditMovement creditMovement, int remain)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			// 列：id; change; date
			statement = connect.prepareStatement("insert into usercredithistory values(?, ?, ?,?,?)");

			statement.setString(1, String.valueOf(userID + ""));
			statement.setString(2, String.valueOf(change + ""));
			statement.setString(3, String.valueOf(date));
			statement.setString(4, String.valueOf(creditMovement));
			statement.setString(5, String.valueOf(remain + ""));

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<UserCreditHistoryPO> findCreditHistory(int userId) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<UserCreditHistoryPO> list = new ArrayList<UserCreditHistoryPO>();

		try {
			statement = connect.prepareStatement("select * from usercredithistory where id = ?");

			// 列：id; change; date
			statement.setString(1, String.valueOf(userId));

			result = statement.executeQuery();
			String tempChange;
			String tempDate;
			String tempCreditMovement;
			String tempRemain;

			// 遍历result
			while (result.next()) {
				tempChange = result.getString(2);
				tempDate = result.getString(3);
				tempCreditMovement = result.getString(4);
				tempRemain = result.getString(5);
				list.add(new UserCreditHistoryPO(userId, Integer.parseInt(tempChange), Date.valueOf(tempDate),
						CreditMovement.valueOf(tempCreditMovement), Integer.parseInt(tempRemain)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<UserCreditHistoryPO> getAllCreditHistory() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<UserCreditHistoryPO> list = new ArrayList<UserCreditHistoryPO>();

		try {
			statement = connect.prepareStatement("select * from usercredithistory");

			// 列：id; change; date
			result = statement.executeQuery();
			String tempUserID;
			String tempChange;
			String tempDate;
			String tempCreditMovement;
			String tempRemain;

			// 遍历result
			while (result.next()) {
				tempUserID = result.getString(1);
				tempChange = result.getString(2);
				tempDate = result.getString(3);
				tempCreditMovement = result.getString(4);
				tempRemain = result.getString(5);
				list.add(new UserCreditHistoryPO(Integer.parseInt(tempUserID), Integer.parseInt(tempChange),
						Date.valueOf(tempDate), CreditMovement.valueOf(tempCreditMovement),
						Integer.parseInt(tempRemain)));
			}
		} catch (SQLException e) {
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
	/*
	 * public static void main(String[]args) throws RemoteException{
	 * UserCreditHistoryDataServiceMySqlImpl p = new
	 * UserCreditHistoryDataServiceMySqlImpl();
	 * p.initUserCreditHistoryDataService();
	 * 
	 * p.updateHistory(new
	 * UserCreditHistoryPO(20905098,30,Date.valueOf("2016-12-31")));
	 * p.updateHistory(new
	 * UserCreditHistoryPO(20905098,-20,Date.valueOf("2017-10-20")));
	 * //p.insertPromotion(new
	 * PromotionPO("Fourth","FourthPromotion",Date.valueOf("2016-12-01"),Date.
	 * valueOf("2016-12-31"),PromotionType.FULL_CUT,20902341));
	 * 
	 * p.finishUserCreditHistoryDataService(); }
	 */
}
