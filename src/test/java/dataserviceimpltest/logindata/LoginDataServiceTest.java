package dataserviceimpltest.logindata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.logindata.LoginDataServiceMySqlImpl;
import dataservice.logindataservice.LoginDataService;

public class LoginDataServiceTest {

	LoginDataService loginDataService;
	String username;
	int id;
	String password;
	
	/**
	 * 初始化
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		//初始化数据
		loginDataService = new LoginDataServiceMySqlImpl();
		username = "庄宇州";
		id = 4;
		password = "000000";
		
		//启动服务
		loginDataService.initLoginDataService();
	}

	/**
	 * 
	 */
	@Test
	public void test1_IsValidateUser() {
		try {
			boolean result = loginDataService.isValidateUser(username, password, id);
			assertEquals(true, result);
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
		loginDataService.finishLoginDataService();
	}

}
