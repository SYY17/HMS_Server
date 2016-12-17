package dataserviceimpltest.hoteldata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import data.hoteldata.HotelDataServiceMySqlImpl;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelDataServiceTest {
	private HotelDataService hotelDataService;
	
	private int hotelID;
	private String hotelName;
	private String hotelAddress;
	private String businessArea;
	private String hotelDescription;
	private int starLevel;
	private double rating;
	private String staffName;
	private String phoneNumber;
	
	
	private HotelPO hpo;
	
	/**
	 * 初始化
	 * @throws Excpetion
	 */
	@Before
	public void setUp() throws Exception {
		hotelDataService = new HotelDataServiceMySqlImpl();
		hotelID = 00;
		hotelName = "名字";
		hotelAddress = "地址";
		businessArea = "商圈";
		hotelDescription = "简介";
		starLevel = 3;
		rating = 4.0;
		staffName = "人名";
		phoneNumber = "13333333333";
		
		hotelDataService.initHotelDataService();
	}
	
	@Test
	public void test6_DeleteHotel() throws RemoteException {
		hpo = new HotelPO(hotelID, hotelName, hotelAddress, businessArea, hotelDescription, starLevel, 
				rating, staffName, phoneNumber);
		hotelDataService.deleteHotel(hotelID);
		HotelPO h = hotelDataService.findHotel(hotelName);
		assertEquals(h, null);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test1_InsertHotel() throws RemoteException{
		hpo = new HotelPO(hotelID, hotelName, hotelAddress, businessArea, hotelDescription, starLevel, 
				rating, staffName, phoneNumber);
		hotelDataService.insertHotel(hpo);
		HotelPO h = hotelDataService.findHotel(hotelName);
		assertEquals(hotelName, h.getHotelName());
		assertEquals(hotelAddress, h.getHotelAddress());
		assertEquals(businessArea, h.getBusinessArea());
		assertEquals(hotelDescription, h.getHotelDescription());
		assertEquals(starLevel, h.getStarLevel());
		assertEquals(staffName, h.getStaffName());
		assertEquals(phoneNumber, h.getPhoneNumber());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test2_updateHotel() throws RemoteException{
		HotelPO hpo2 = new HotelPO(hotelID, "名字", "花果山", businessArea, hotelDescription, starLevel, 
				rating, staffName, phoneNumber);
		hotelDataService.updateHotel(hpo2);
		HotelPO h = hotelDataService.findHotel("名字");
//		assertEquals(hpo2.getHotelName(), h.getHotelName());
		assertEquals("花果山", h.getHotelAddress());
		assertEquals(businessArea, h.getBusinessArea());
		assertEquals(hotelDescription, h.getHotelDescription());
		assertEquals(starLevel, h.getStarLevel());
		assertEquals(String.valueOf(rating), String.valueOf(h.getRating()));
		assertEquals(staffName, h.getStaffName());
		assertEquals(phoneNumber, h.getPhoneNumber());
		hpo = new HotelPO(hotelID, hotelName, hotelAddress, businessArea, hotelDescription, starLevel, 
				rating, staffName, phoneNumber);
		hotelDataService.updateHotel(hpo);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test3_findHotel() throws RemoteException{
		hpo = new HotelPO(hotelID, hotelName, hotelAddress, businessArea, hotelDescription, starLevel, 
				rating, staffName, phoneNumber);
		HotelPO h = hotelDataService.findHotel(hotelName);
		assertEquals(hotelName, h.getHotelName());
		assertEquals(hotelAddress, h.getHotelAddress());
		assertEquals(businessArea, h.getBusinessArea());
		assertEquals(hotelDescription, h.getHotelDescription());
		assertEquals(starLevel, h.getStarLevel());
//		assertEquals(rating, h.getRating());
		assertEquals(staffName, h.getStaffName());
		assertEquals(phoneNumber, h.getPhoneNumber());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test4_findsHotel() throws RemoteException{
		hpo = new HotelPO(hotelID, hotelName, hotelAddress, businessArea, hotelDescription, starLevel, 
				rating, staffName, phoneNumber);
		ArrayList<HotelPO> list = hotelDataService.findsHotel("address", hotelAddress);
		assertEquals(hotelName, list.get(0).getHotelName());
		assertEquals(hotelAddress, list.get(0).getHotelAddress());
		assertEquals(businessArea, list.get(0).getBusinessArea());
		assertEquals(hotelDescription, list.get(0).getHotelDescription());
		assertEquals(starLevel, list.get(0).getStarLevel());
//		assertEquals(rating, list.get(0).getRating());
		assertEquals(staffName, list.get(0).getStaffName());
		assertEquals(phoneNumber, list.get(0).getPhoneNumber());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test5_findsAllHotel() throws RemoteException{
		hpo = new HotelPO(hotelID, hotelName, hotelAddress, businessArea, hotelDescription, starLevel, 
				rating, staffName, phoneNumber);
		ArrayList<HotelPO> list = hotelDataService.findsHotel();
		assertNotEquals(null, list);
	}
}
