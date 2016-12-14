package dataserviceimpltest.customerdata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import data.customerdata.CustomerDataServiceMySqlImpl;
import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import po.UserPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDataServiceTest{

	CustomerDataService customerDataService;
	String username;
	Date birthday;
	String phoneNumber;
	String email;
	String address;
	UserPO upo;
	CustomerPO cpo;
	
	/**
	 * 初始化
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		customerDataService = new CustomerDataServiceMySqlImpl();
		username = "testuser";
		birthday = new Date(new java.util.Date().getTime());
		phoneNumber = "10011011100";
		email = "test@email.com";
		address = "test address";
		upo = new UserPO(10912012, username, "000000");
		cpo = new CustomerPO(upo, birthday, phoneNumber, email, address);
		customerDataService.initCustomerDataService();
	}
	
	/**
	 * 添加顾客信息的测试用例套件
	 */
	@Test
	public void test1_InsertCustomer() {
		boolean result = false;
		
		try {
			result = customerDataService.insertCustomer(username);
			
			//添加成功
			assertTrue(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新用户信息的测试用例套件
	 */
	@Test
	public void test2_UpdateCustomerInfo() {
		boolean result = false;
		
		try {
			result = customerDataService.updateCustomerInfo(cpo);
			
			//更新成功
			assertTrue(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取顾客信息的测试用例套件
	 */
	@Test
	public void test3_GetCustomerInfo() {
		try {
			cpo = customerDataService.getCustomerInfo(username);
			
			//信息完全相同
			assertEquals(username, cpo.getName());
			assertEquals(phoneNumber, cpo.getPhoneNumber());
			assertEquals(address, cpo.getAddress());
			assertEquals(email, cpo.getEmail());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除顾客信息的测试用例套件
	 */
	@Test
	public void test4_DeleteCustomer(){
		boolean result = false;
		try {
			result = customerDataService.deleteCustomer(username);
			
			//删除成功
			assertTrue(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 关闭服务
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		customerDataService.finishCustomerDataService();
	}

}
