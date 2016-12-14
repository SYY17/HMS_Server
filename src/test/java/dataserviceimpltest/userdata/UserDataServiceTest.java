package dataserviceimpltest.userdata;
import dataservice.userdataservice.UserDataService;
import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import data.userdata.UserDataServiceMySqlImpl;
import po.UserPO;

public class UserDataServiceTest {
	private UserDataService userDataService;
	private String name;
	private int id;
	private String password;
	UserPO upo;
	
	/**
	 * 初始化
	 * @throws Excpetion
	 */
	@Before
	public void setUp() throws Exception{
		userDataService = new UserDataServiceMySqlImpl();
		name = "testuser";
		id = 1;
		password = "000000";
		userDataService.initUserDataService();
	}
	
	/**
	 * 查找用户信息的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void TestFindUser() throws RemoteException {
		upo = userDataService.findUser("manager");
		
		//信息完全相同
		assertEquals("manager", upo.getName());
		assertEquals("666666", upo.getPassword());
		assertEquals(41128001, upo.getID());
		
		String username = userDataService.findUser(41128001);
		
		//获取信息不为空
		assertEquals("manager", username);
	}
	
	/**
	 * 添加用户信息的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testInsertUser() throws RemoteException {
		upo = new UserPO(id, name, password);
		userDataService.insertUser(upo);
		
		//信息完全相同
		assertEquals(name, upo.getName());
		assertEquals(password, upo.getPassword());
	}
	
	/**
	 * 更新用户信息的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testModifyUser() throws RemoteException {
		//初始化ID
		upo = userDataService.findUser(name);
		id = upo.getID();
		
		//重置密码
		password = "666666";
		upo = new UserPO(id, name, password);
		
		userDataService.updateUser(upo);
		
		//再次按照用户名查找用户
		upo = userDataService.findUser(name);
		
		//信息相同
		assertEquals(id, upo.getID());
		assertEquals(password, upo.getPassword());
		assertEquals(name, upo.getName());
	}
	
	/**
	 * 删除用户信息的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testDeleteUser() throws RemoteException {
		upo = userDataService.findUser(name);
		userDataService.deleteUser(upo.getID());
		upo = userDataService.findUser(name);
		
		//不再存在信息
		assertTrue(upo == null);
	}
}
