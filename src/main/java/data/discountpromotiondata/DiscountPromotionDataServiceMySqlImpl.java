package data.discountpromotiondata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.discountpromotiondataservice.DiscountPromotionDataService;
import po.DiscountPromotionPO;
import po.PromotionType;

public class DiscountPromotionDataServiceMySqlImpl implements DiscountPromotionDataService {

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public DiscountPromotionDataServiceMySqlImpl(){
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	@Override
	public void insertDiscountPromotion(DiscountPromotionPO dpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			/*
			ArrayList<DiscountPromotionPO> list = findsDiscountPromotion(dpo.getID(), dpo.getContent(), dpo.getStartTime());
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getContent().equals(dpo.getContent())) return;
			}*/
			//列：id; name; content; start; stop; discount
			statement = connect.prepareStatement("insert into discountpromotion values(?, ?, ?, ?, ?, ?)");
			
			statement.setString(1, String.valueOf(dpo.getID()));
			statement.setString(2, String.valueOf(dpo.getPromotionName()));
			statement.setString(3, String.valueOf(dpo.getContent()));
			statement.setString(4, parse(dpo.getStartTime()));
			statement.setString(5, parse(dpo.getStopTime()));
			statement.setString(6, String.valueOf(dpo.getDiscount()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDiscountPromotion(DiscountPromotionPO dpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id; name; content; start; stop; discount
			statement = connect.prepareStatement("delete from discountpromotion where start = ? and stop = ? and name = ?");//

			statement.setString(1, parse(dpo.getStartTime()));
			statement.setString(2, parse(dpo.getStopTime()));
			statement.setString(3, dpo.getPromotionName());
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initDiscountPromotionDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	@Override
	public void finishDiscountPromotionDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}
	
	private String parse(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
/*	public static void main(String[]args) throws RemoteException{
		DiscountPromotionDataServiceMySqlImpl p = new DiscountPromotionDataServiceMySqlImpl();
		p.initDiscountPromotionDataService();
		
		//p.insertDiscountPromotion(new DiscountPromotionPO("Third","ThirdPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
		p.insertDiscountPromotion(new DiscountPromotionPO("QQQ","SeventhPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
		//p.deleteDiscountPromotion(new DiscountPromotionPO("Third","ThirdPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
		p.finishDiscountPromotionDataService();
	}
	*/

	@Override
	public ArrayList<DiscountPromotionPO> findsDiscountPromotion(int id, String content, Date start) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<DiscountPromotionPO> list = findsDiscountPromotion(id, start);
		for(int i = 0; i< list.size(); i++){
			DiscountPromotionPO dpo = list.get(i);
			if(!dpo.getContent().contains(content)){
				list.remove(dpo);
				i--;
			}
		}
		return list;
	}

	@Override
	public ArrayList<DiscountPromotionPO> findsDiscountPromotion(int id) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<DiscountPromotionPO> list = new ArrayList<DiscountPromotionPO>();
		
		try {
			statement = connect.prepareStatement("select * from discountpromotion where id = ?");
			
			//列：id; name; content; start； stop; discount
			statement.setString(1, String.valueOf(id));
			
			result = statement.executeQuery();
			String tempName;
			String tempContent;
			String tempStart;
			String tempStop;
			String tempDiscount;////............................
			
			//遍历result
			while(result.next()){
				tempName = result.getString(2);
				tempContent = result.getString(3);
				tempStart = result.getString(4);
				tempStop = result.getString(5);//
				tempDiscount = result.getString(6);//
				list.add(new DiscountPromotionPO( tempName, tempContent, parse(tempStart), parse(tempStop), PromotionType.DISCOUNT, id, Double.parseDouble(tempDiscount)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public ArrayList<DiscountPromotionPO> findsDiscountPromotion(int id, Date start) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<DiscountPromotionPO> list = new ArrayList<DiscountPromotionPO>();
		
		try {
			statement = connect.prepareStatement("select * from discountpromotion where id = ? and start = ?");
			
			//列：id; name; content; start; stop; discount
			statement.setString(1, String.valueOf(id));
			statement.setString(2, parse(start));
			
			result = statement.executeQuery();
			String tempName;
			String tempContent;
			String tempStop;
			String tempDiscount;
			
			//遍历result
			while(result.next()){
				tempName = result.getString(2);
				tempContent = result.getString(3);
				tempStop = result.getString(5);
				tempDiscount = result.getString(6);//
				list.add(new DiscountPromotionPO( tempName, tempContent, start, parse(tempStop), PromotionType.DISCOUNT, id, Double.parseDouble(tempDiscount)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<DiscountPromotionPO> findsDiscountPromotion(int id, String content) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<DiscountPromotionPO> list = new ArrayList<DiscountPromotionPO>();
		list = findsDiscountPromotion(id);
		for(int i = 0; i< list.size(); i++){
			DiscountPromotionPO dpo = list.get(i);
			if(!dpo.getContent().contains(content)){
				list.remove(dpo);
				i--;
			}
		}
		return list;
	}
	
	private Date parse(String s){
		java.util.Date d = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			d = (Date) format.parse(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		Date date = new Date(d.getTime());
		return date;
	}
	
}
