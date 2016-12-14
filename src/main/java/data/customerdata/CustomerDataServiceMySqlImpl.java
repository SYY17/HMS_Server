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
			
			//CustomerPO: birthday; phone; email; address
			Date birthday = null;
			String phone = "";
			String email = "";
			String address = "";
			
			//遍历result
			while(result.next()){
				birthday = result.getDate(2);
				phone = result.getString(3);
				email = result.getString(4);
				address = result.getString(5);
			}
			
			if(birthday != null){
				return new CustomerPO(new UserPO(0, username, null), birthday, phone, email, address);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param cpo
	 * @return 更新顾客信息
	 * @throws RemoteException
	 */
	@Override
	public boolean updateCustomerInfo(CustomerPO cpo) throws RemoteException {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			statement = connect.prepareStatement("update customer set birthday = ?, email = ?, phone = ?, address = ? where username = ?");
			
			statement.setDate(1, cpo.getBirthday());
			statement.setString(2, cpo.getEmail());
			statement.setString(3, cpo.getPhoneNumber());
			statement.setString(4, cpo.getAddress());
			statement.setString(5, cpo.getName());
			
			result = statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @throws RemoteException
	 */
	@Override
	public void initCustomerDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	/**
	 * @throws RemoteException
	 */
	@Override
	public void finishCustomerDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}

}
