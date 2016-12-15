package dataserviceimpltest.fullcutpromotiondata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import data.fullcutpromotiondata.FullCutPromotionDataServiceMySqlImpl;
import dataservice.fullcutpromotiondataservice.FullCutPromotionDataService;
import po.FullCutPromotionPO;
import po.PromotionType;

public class FullCutPromotionDataServiceTest {

	private FullCutPromotionDataService fullCutPromotionDataService;
	private String promotionName;
	private String content;
	private PromotionType promotionType;
	private Date start;
	private Date stop;
	private int id;
	private double every;
	private double cut;
	private FullCutPromotionPO fpo;
	
	@Before
	public void setUp() throws Exception {
		fullCutPromotionDataService = new FullCutPromotionDataServiceMySqlImpl();
		id = 30161215;
		promotionName = "两年后的满减";
		content = "2019年即可满减";
		promotionType = PromotionType.FULL_CUT;
		start = Date.valueOf("2019-11-01");
		stop = Date.valueOf("2019-12-01");
		every = 200;
		cut = 50;
		fullCutPromotionDataService.initFullCutPromotionDataService();
	}
	
	@Test
	public void testInsertFullCutPromotion() throws RemoteException   {
		fpo = new FullCutPromotionPO( promotionName, content, start, stop, promotionType, id, every, cut);
		fullCutPromotionDataService.insertFullCutPromotion(fpo);
		
		ArrayList<FullCutPromotionPO> fpoList = fullCutPromotionDataService.findsFullPromotion(id, content, start);
		FullCutPromotionPO fpo = fpoList.get(0);
		
		assertEquals( promotionName, fpo.getPromotionName());
		assertEquals( content, fpo.getContent());
		assertEquals( start, fpo.getStartTime());
		assertEquals( stop, fpo.getStopTime());
		assertEquals( promotionType, fpo.getPromotionType());
		assertEquals(id, fpo.getID());
		assertEquals(every, fpo.getEvery());//
		assertEquals(cut, fpo.getCut());//
		fullCutPromotionDataService.deleteFullCutPromotion(fpo);
	}

	@Test
	public void testDeleteFullCutPromotion() throws RemoteException   {
		fpo = new FullCutPromotionPO( promotionName, content, start, stop, promotionType, id, every, cut);
		fullCutPromotionDataService.insertFullCutPromotion(fpo);
		fullCutPromotionDataService.deleteFullCutPromotion(fpo);
		
		ArrayList<FullCutPromotionPO> fpoList = fullCutPromotionDataService.findsFullPromotion(id, content, start);
		assertEquals(fpoList, null);
	}
	
	@Test
	public void testFindsFullPromotionICS() throws RemoteException  {
		fpo = new FullCutPromotionPO( promotionName, content, start, stop, promotionType, id, every, cut);
		fullCutPromotionDataService.insertFullCutPromotion(fpo);
		
		ArrayList<FullCutPromotionPO> fpoList = fullCutPromotionDataService.findsFullPromotion(id, content, start);
		FullCutPromotionPO ppo = fpoList.get(0);
		

		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		assertEquals(every, fpo.getEvery());//
		assertEquals(cut, fpo.getCut());//
		fullCutPromotionDataService.deleteFullCutPromotion(fpo);
	}
	
	@Test
	public void testFindsFullPromotionI() throws RemoteException  {
		fpo = new FullCutPromotionPO( promotionName, content, start, stop, promotionType, id, every, cut);
		fullCutPromotionDataService.insertFullCutPromotion(fpo);
		
		ArrayList<FullCutPromotionPO> fpoList = fullCutPromotionDataService.findsFullPromotion(id);
		FullCutPromotionPO ppo = fpoList.get(0);
		

		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		assertEquals(every, fpo.getEvery());//
		assertEquals(cut, fpo.getCut());//
		fullCutPromotionDataService.deleteFullCutPromotion(fpo);
	}

	@Test
	public void testFindsFullPromotionIS() throws RemoteException  {
		fpo = new FullCutPromotionPO( promotionName, content, start, stop, promotionType, id, every, cut);
		fullCutPromotionDataService.insertFullCutPromotion(fpo);
		
		ArrayList<FullCutPromotionPO> fpoList = fullCutPromotionDataService.findsFullPromotion(id, start);
		FullCutPromotionPO ppo = fpoList.get(0);
		

		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		assertEquals(every, fpo.getEvery());//
		assertEquals(cut, fpo.getCut());//
		fullCutPromotionDataService.deleteFullCutPromotion(fpo);
	}
	
	@Test
	public void testFindsFullPromotionIC() throws RemoteException  {
		fpo = new FullCutPromotionPO( promotionName, content, start, stop, promotionType, id, every, cut);
		fullCutPromotionDataService.insertFullCutPromotion(fpo);
		
		ArrayList<FullCutPromotionPO> fpoList = fullCutPromotionDataService.findsFullPromotion(id, content);
		FullCutPromotionPO ppo = fpoList.get(0);
		

		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		assertEquals(every, fpo.getEvery());//
		assertEquals(cut, fpo.getCut());//
		fullCutPromotionDataService.deleteFullCutPromotion(fpo);
	}
}
