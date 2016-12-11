package data.fullcutpromotiondata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.fullcutpromotiondataservice.FullCutPromotionDataService;
import po.FullCutPromotionPO;
import po.PromotionType;

public class FullCutPromotionDataServiceMySqlImpl implements FullCutPromotionDataService {

	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public FullCutPromotionDataServiceMySqlImpl(){
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	@Override
	public void insertFullCutPromotion(FullCutPromotionPO fpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id; name; content; start; stop; every; cut
			statement = connect.prepareStatement("insert into fullcutpromotion values(?, ?, ?, ?, ?, ?, ?)");
			
			statement.setString(1, String.valueOf(fpo.getID()));
			statement.setString(2, String.valueOf(fpo.getPromotionName()));
			statement.setString(3, String.valueOf(fpo.getContent()));
			statement.setString(4, parse(fpo.getStartTime()));
			statement.setString(5, parse(fpo.getStopTime()));
			statement.setString(6, String.valueOf(fpo.getEvery()));
			statement.setString(7, String.valueOf(fpo.getCut()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFullCutPromotion(FullCutPromotionPO fpo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id; name; content; start; stop; every; cut
			statement = connect.prepareStatement("delete from fullcutpromotion where start = ? and stop = ? and name = ?");//
			
			statement.setString(1, parse(fpo.getStartTime()));
			statement.setString(2, parse(fpo.getStopTime()));//
			statement.setString(3, fpo.getPromotionName());
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initFullCutPromotionDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	@Override
	public void finishFullCutPromotionDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}
	
	private String parse(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/*
	public static void main(String[]args) throws RemoteException{
		FullCutPromotionDataServiceMySqlImpl p = new FullCutPromotionDataServiceMySqlImpl();
		p.initFullCutPromotionDataService();
		
		//p.insertDiscountPromotion(new DiscountPromotionPO("Third","ThirdPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
		p.insertFullCutPromotion(new FullCutPromotionPO("Sixth","SixthPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.FULL_CUT,20902341,200,20));
		//p.deleteDiscountPromotion(new DiscountPromotionPO("Third","ThirdPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
		p.finishFullCutPromotionDataService();
	}
	*/
}
