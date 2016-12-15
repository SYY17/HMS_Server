package dataserviceimpltest.discountpromotiondata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import data.discountpromotiondata.DiscountPromotionDataServiceMySqlImpl;
import dataservice.discountpromotiondataservice.DiscountPromotionDataService;
import po.DiscountPromotionPO;
import po.PromotionType;

public class DiscountPromotionDataServiceTset {

	private DiscountPromotionDataService discountPromotionDataService;
	private String promotionName;
	private String content;
	private PromotionType promotionType;
	private Date start;
	private Date stop;
	private int id;
	private double discount;
	DiscountPromotionPO dpo;
	
	@Before
	public void setUp() throws Exception {
		discountPromotionDataService = new DiscountPromotionDataServiceMySqlImpl();
		id = 30161215;
		promotionName = "两年后的满减";
		content = "2019年即可满减";
		promotionType = PromotionType.DISCOUNT;
		start = Date.valueOf("2019-11-01");
		stop = Date.valueOf("2019-12-01");
		discount = 200;
		discountPromotionDataService.initDiscountPromotionDataService();
	}
	
	@Test
	public void testInsertDiscountPromotion() throws RemoteException {
		dpo = new DiscountPromotionPO( promotionName, content, start, stop, promotionType, id, discount);
		discountPromotionDataService.insertDiscountPromotion(dpo);
		
		ArrayList<DiscountPromotionPO> dpoList = discountPromotionDataService.findsDiscountPromotion(id, content, start);
		DiscountPromotionPO dpo = dpoList.get(0);
		
		assertEquals( promotionName, dpo.getPromotionName());
		assertEquals( content, dpo.getContent());
		assertEquals( start, dpo.getStartTime());
		assertEquals( stop, dpo.getStopTime());
		assertEquals( promotionType, dpo.getPromotionType());
		assertEquals(id, dpo.getID());
		assertEquals(discount, dpo.getDiscount());//
		discountPromotionDataService.deleteDiscountPromotion(dpo);
	}

	@Test
	public void testDeleteDiscountPromotion() throws RemoteException {
		dpo = new DiscountPromotionPO( promotionName, content, start, stop, promotionType, id, discount);
		discountPromotionDataService.insertDiscountPromotion(dpo);
		discountPromotionDataService.deleteDiscountPromotion(dpo);
		
		ArrayList<DiscountPromotionPO> dpoList = discountPromotionDataService.findsDiscountPromotion(id, content, start);
		assertEquals(dpoList, null);
	}
	
	@Test
	public void testFindsDiscountPromotionICS() throws RemoteException  {
		dpo = new DiscountPromotionPO( promotionName, content, start, stop, promotionType, id, discount);
		discountPromotionDataService.insertDiscountPromotion(dpo);
		
		ArrayList<DiscountPromotionPO> dpoList = discountPromotionDataService.findsDiscountPromotion(id, content, start);
		DiscountPromotionPO ppo = dpoList.get(0);
		

		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		assertEquals(discount, ppo.getDiscount());
		discountPromotionDataService.deleteDiscountPromotion(dpo);
	}
	
	@Test
	public void testFindsDiscountPromotionI() throws RemoteException  {
		dpo = new DiscountPromotionPO( promotionName, content, start, stop, promotionType, id, discount);
		discountPromotionDataService.insertDiscountPromotion(dpo);
		
		ArrayList<DiscountPromotionPO> dpoList = discountPromotionDataService.findsDiscountPromotion(id);
		DiscountPromotionPO ppo = dpoList.get(0);
		
		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		assertEquals(discount, ppo.getDiscount());
		discountPromotionDataService.deleteDiscountPromotion(dpo);
	}

	@Test
	public void testFindsDiscountPromotionIS() throws RemoteException  {
		dpo = new DiscountPromotionPO( promotionName, content, start, stop, promotionType, id, discount);
		discountPromotionDataService.insertDiscountPromotion(dpo);
		
		ArrayList<DiscountPromotionPO> dpoList = discountPromotionDataService.findsDiscountPromotion(id, start);
		DiscountPromotionPO ppo = dpoList.get(0);
		
		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		assertEquals(discount, ppo.getDiscount());
		discountPromotionDataService.deleteDiscountPromotion(dpo);
	}
	
	@Test
	public void testFindsDiscountPromotionIC() throws RemoteException  {
		dpo = new DiscountPromotionPO( promotionName, content, start, stop, promotionType, id, discount);
		discountPromotionDataService.insertDiscountPromotion(dpo);
		
		ArrayList<DiscountPromotionPO> dpoList = discountPromotionDataService.findsDiscountPromotion(id, content);
		DiscountPromotionPO ppo = dpoList.get(0);
		
		assertEquals( promotionName, ppo.getPromotionName());
		assertEquals( content, ppo.getContent());
		assertEquals( start, ppo.getStartTime());
		assertEquals( stop, ppo.getStopTime());
		assertEquals( promotionType, ppo.getPromotionType());
		assertEquals(id, ppo.getID());
		assertEquals(discount, ppo.getDiscount());
		discountPromotionDataService.deleteDiscountPromotion(dpo);
	}
}
