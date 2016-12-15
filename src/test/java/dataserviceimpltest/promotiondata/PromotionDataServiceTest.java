package dataserviceimpltest.promotiondata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import data.promotiondata.PromotionDataServiceMySqlImpl;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;
import po.PromotionType;

public class PromotionDataServiceTest {
	
	private PromotionDataService promotionDataService;
	private String promotionName;
	private String content;
	private PromotionType promotionType;
	private Date start;
	private Date stop;
	private int id;
	private PromotionPO promotionPO;
	
	/**
	 * 初始化
	 * @throws Excpetion
	 */
	@Before
	public void setUp() throws Exception {
		promotionDataService = new PromotionDataServiceMySqlImpl();
		id = 30161215;
		promotionName = "两年后的满减";
		content = "2019年即可满减";
		promotionType = PromotionType.FULL_CUT;
		start = Date.valueOf("2019-11-01");
		stop = Date.valueOf("2019-12-01");
		promotionDataService.initPromotionDataService();
	}
	
	/**
	 * 搜索策略的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testFindsPromotionICS() throws RemoteException  {
		promotionPO = new PromotionPO( promotionName, content, start, stop, promotionType, id);
		promotionDataService.insertPromotion(promotionPO);
		
		ArrayList<PromotionPO> ppoList = promotionDataService.findsPromotion(id, content, start);
		PromotionPO ppo = ppoList.get(0);
		

		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		promotionDataService.deletePromotion(promotionPO);
	}
	
	/**
	 * 搜索策略的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testFindsPromotionI() throws RemoteException  {
		promotionPO = new PromotionPO( promotionName, content, start, stop, promotionType, id);
		promotionDataService.insertPromotion(promotionPO);
		
		ArrayList<PromotionPO> ppoList = promotionDataService.findsPromotion(id);
		PromotionPO ppo = ppoList.get(0);
		
		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		promotionDataService.deletePromotion(promotionPO);
	}

	/**
	 * 搜索策略的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testFindsPromotionIS() throws RemoteException  {
		promotionPO = new PromotionPO( promotionName, content, start, stop, promotionType, id);
		promotionDataService.insertPromotion(promotionPO);
		
		ArrayList<PromotionPO> ppoList = promotionDataService.findsPromotion( id, start);
		PromotionPO ppo = ppoList.get(0);
		
		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		promotionDataService.deletePromotion(promotionPO);
	}
	
	/**
	 * 搜索策略的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testFindsPromotionIC() throws RemoteException  {
		promotionPO = new PromotionPO( promotionName, content, start, stop, promotionType, id);
		promotionDataService.insertPromotion(promotionPO);
		
		ArrayList<PromotionPO> ppoList = promotionDataService.findsPromotion(id, content);
		PromotionPO ppo = ppoList.get(0);
		
		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		promotionDataService.deletePromotion(promotionPO);
	}
	
	/**
	 * 增加策略的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testInsertPromotion() throws RemoteException {
		promotionPO = new PromotionPO( promotionName, content, start, stop, promotionType, id);
		promotionDataService.insertPromotion(promotionPO);
		
		ArrayList<PromotionPO> ppoList = promotionDataService.findsPromotion(id, content, start);
		PromotionPO ppo = ppoList.get(0);
		
		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		promotionDataService.deletePromotion(promotionPO);
	}
	
	/**
	 * 删除策略的测试用例套件
	 * @throws RemoteException
	 */
	@Test
	public void testDeletePromotion() throws RemoteException {
		promotionPO = new PromotionPO( promotionName, content, start, stop, promotionType, id);
		promotionDataService.insertPromotion(promotionPO);
		promotionDataService.deletePromotion(promotionPO);
		
		ArrayList<PromotionPO> ppoList = promotionDataService.findsPromotion(id, content, start);
		assertEquals(ppoList, null);
		
	}
}
