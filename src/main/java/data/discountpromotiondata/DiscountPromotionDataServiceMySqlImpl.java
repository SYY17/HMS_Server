package data.discountpromotiondata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
	/*
	public static void main(String[]args) throws RemoteException{
		DiscountPromotionDataServiceMySqlImpl p = new DiscountPromotionDataServiceMySqlImpl();
		p.initDiscountPromotionDataService();
		
		//p.insertDiscountPromotion(new DiscountPromotionPO("Third","ThirdPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
		p.insertDiscountPromotion(new DiscountPromotionPO("新的尝试","SeventhPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
		//p.deleteDiscountPromotion(new DiscountPromotionPO("Third","ThirdPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
		p.finishDiscountPromotionDataService();
	}
	*/
}
