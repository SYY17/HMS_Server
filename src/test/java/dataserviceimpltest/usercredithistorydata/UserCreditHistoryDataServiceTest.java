package dataserviceimpltest.usercredithistorydata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import data.usercredithistorydata.UserCreditHistoryDataServiceMySqlImpl;
import dataservice.usercredithistoryservice.UserCreditHistoryDataService;
import po.CreditMovement;
import po.UserCreditHistoryPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCreditHistoryDataServiceTest {
	private UserCreditHistoryDataService userCreditHistoryDataService;
	private int userId;
	private Date time;
	private int change;
	private int remain;
	private CreditMovement creditMovement;

	@Before
	public void setUp() throws Exception {
		userCreditHistoryDataService = new UserCreditHistoryDataServiceMySqlImpl();
		userId = 10000000;
		time = new Date(System.currentTimeMillis());
		change = 0;
		remain = 0;
		creditMovement = CreditMovement.AddMoney;
	}

	@Test
	public void test1_UpdateHistory() throws RemoteException {
		userCreditHistoryDataService.initUserCreditHistoryDataService();
		userCreditHistoryDataService.updateHistory(userId, change, time, creditMovement, remain);
		UserCreditHistoryPO upo = userCreditHistoryDataService.findCreditHistory(userId).get(0);
		userCreditHistoryDataService.finishUserCreditHistoryDataService();
		assertEquals(upo.getChange(), 0);
	}

	@Test
	public void test2_FindCreditHistory() throws RemoteException {
		userCreditHistoryDataService.initUserCreditHistoryDataService();
		ArrayList<UserCreditHistoryPO> list = userCreditHistoryDataService.findCreditHistory(userId);
		userCreditHistoryDataService.finishUserCreditHistoryDataService();
		assertNotEquals(list.size(), 0);
	}

	@Test
	public void test3_GetAllCreditHistory() throws RemoteException {
		userCreditHistoryDataService.initUserCreditHistoryDataService();
		ArrayList<UserCreditHistoryPO> list = userCreditHistoryDataService.getAllCreditHistory();
		userCreditHistoryDataService.finishUserCreditHistoryDataService();
		assertNotEquals(list.size(), 0);
	}

}
