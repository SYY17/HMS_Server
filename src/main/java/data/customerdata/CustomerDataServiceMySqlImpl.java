package data.customerdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import po.UserPO;

public class CustomerDataServiceMySqlImpl implements CustomerDataService{

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public CustomerDataServiceMySqlImpl() {
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	/**
	 * 
	 * @param username
	 * @return 根据用户名查找并返回顾客信息
	 * @throws RemoteException
	 */
	@Override
	public CustomerPO getCustomerInfo(String username) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			statement = connect.prepareStatement("select * from customer where username = ?");
			
			//列：id; username; password
			statement.setString(1, username);
			
			result = statement.executeQuery();
			
			//CustomerPO: birthday; phone; email
			Date birthday = null;
			String phone = "";
			String email = "";
			
			//遍历result
			while(result.next()){
				birthday = result.getDate(2);
				phone = result.getString(3);
				email = result.getString(4);
			}
			
			if(birthday != null){
				return new CustomerPO(new UserPO(0, username, null), birthday, phone, email);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void initCustomerDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	@Override
	public void finishCustomerDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}

}
