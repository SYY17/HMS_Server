package data.creditdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import po.CreditPO;

public class CreditDataServiceMySqlImpl implements CreditDataService{

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public CreditDataServiceMySqlImpl() {
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	/**
	 * 
	 * @param cpo
	 * @throws RemoteException
	 */
	@Override
	public void insertCredit(CreditPO cpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//为防止重复插入信息应进行检查
			if(findCredit(cpo.getID()) != null) return;
			
			//列：id; credit
			statement = connect.prepareStatement("insert into credit values(?, ?)");
			
			statement.setString(1, String.valueOf(cpo.getID()));
			statement.setString(2, String.valueOf(cpo.getCredit()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param id
	 * @throws RemoteException
	 */
	@Override
	public void deleteCredit(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id; credit
			statement = connect.prepareStatement("delete from credit where id = ?");
			statement.setString(1, String.valueOf(id));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param cpo
	 * @throws RemoteException
	 */
	@Override
	public void updateCredit(CreditPO cpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id; credit
			statement = connect.prepareStatement("update credit set id = ?, credit = ? where id = ?");
			statement.setString(1, String.valueOf(cpo.getID()));
			statement.setString(2, String.valueOf(cpo.getCredit()));
			statement.setString(3, String.valueOf(cpo.getID()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 * @return 根据ID查找并获得信用值信息
	 * @throws RemoteException
	 */
	@Override
	public CreditPO findCredit(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("select * from credit where id = ?");
			
			//列：id, credit
			statement.setString(1, String.valueOf(id));
			
			result = statement.executeQuery();
			
			//CreditPO: id; credit
			int credit = -1;
			
			//遍历result
			while(result.next()){
				credit = Integer.valueOf(result.getString(2));
			}
			
			if(credit != -1){
				return new CreditPO(id, credit);
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
	public void initCreditDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void finishCreditDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}

}
