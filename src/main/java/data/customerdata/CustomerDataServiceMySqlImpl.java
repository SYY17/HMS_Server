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
	 * @return 添加新的顾客信息
	 * @throws RemoteException
	 */
	@Override
	public boolean insertCustomer(String username) throws RemoteException {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			statement = connect.prepareStatement("insert into customer values(?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, username);
			statement.setDate(2, new Date(System.currentTimeMillis()));
			statement.setString(3, null);
			statement.setString(4, null);
			statement.setString(5, null);
			statement.setInt(6, 0);
			statement.setString(7, null);
			
			result = !statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
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
			statement = connect.prepareStatement("update customer set birthday = ?, email = ?, phone = ?, address = ?, enterprise = ? where username = ?");
			
			statement.setDate(1, cpo.getBirthday());
			statement.setString(2, cpo.getEmail());
			statement.setString(3, cpo.getPhoneNumber());
			statement.setString(4, cpo.getAddress());
			statement.setString(6, cpo.getName());
			statement.setString(5, cpo.getEnterprise());
			
			result = !statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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
			int member = -1;
			String enterprise = "";
			
			//遍历result
			while(result.next()){
				birthday = result.getDate(2);
				phone = result.getString(3);
				email = result.getString(4);
				address = result.getString(5);
				member = result.getInt(6);
				enterprise = result.getString(7);
			}
			
			if(birthday != null){
				return new CustomerPO(new UserPO(0, username, null), birthday, phone, email, address, member, enterprise);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param username
	 * @return 删除用户信息
	 * @throws RemoteException
	 */
	@Override
	public boolean deleteCustomer(String username) throws RemoteException {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			statement = connect.prepareStatement("delete from customer where username = ?");
			statement.setString(1, username);
			
			result = !statement.execute();
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
