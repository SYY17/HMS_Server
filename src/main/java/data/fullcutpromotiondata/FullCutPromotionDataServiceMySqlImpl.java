package data.fullcutpromotiondata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
			ArrayList<FullCutPromotionPO> list = findsFullPromotion(fpo.getID(), fpo.getContent(), fpo.getStartTime());
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getContent().equals(fpo.getContent())) return;
			}
			
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

	@Override
	public ArrayList<FullCutPromotionPO> findsFullPromotion(int id, String content, Date start)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<FullCutPromotionPO> list = findsFullPromotion(id, start);
		for(int i = 0; i< list.size(); i++){
			FullCutPromotionPO fpo = list.get(i);
			if(!fpo.getContent().contains(content)){
				list.remove(fpo);
				i--;
			}
		}
		return list;
	}

	@Override
	public ArrayList<FullCutPromotionPO> findsFullPromotion(int id) throws RemoteException {
		// TODO Auto-generated method stu
		ArrayList<FullCutPromotionPO> list = new ArrayList<FullCutPromotionPO>();
		
		try {
			statement = connect.prepareStatement("select * from fullcutpromotion where id = ?");
			
			//列：id; name; content; start； stop; every, cut
			statement.setString(1, String.valueOf(id));
			
			result = statement.executeQuery();
			String tempName;
			String tempContent;
			String tempStart;
			String tempStop;
			String tempEvery;
			String tempCut;////............................
			
			//遍历result
			while(result.next()){
				tempName = result.getString(2);
				tempContent = result.getString(3);
				tempStart = result.getString(4);
				tempStop = result.getString(5);//
				tempEvery = result.getString(6);//
				tempCut = result.getString(7);//
				list.add(new FullCutPromotionPO( tempName, tempContent, parse(tempStart), parse(tempStop), PromotionType.FULL_CUT, id, Double.parseDouble(tempEvery),Double.parseDouble(tempCut)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<FullCutPromotionPO> findsFullPromotion(int id, Date start) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<FullCutPromotionPO> list = new ArrayList<FullCutPromotionPO>();
		
		try {
			statement = connect.prepareStatement("select * from fullcutpromotion where id = ? and start = ?");
			
			//列：id; name; content; start; stop; every, cut
			statement.setString(1, String.valueOf(id));
			statement.setString(2, parse(start));
			
			result = statement.executeQuery();
			String tempName;
			String tempContent;
			String tempStop;
			String tempEvery;
			String tempCut;
			
			//遍历result
			while(result.next()){
				tempName = result.getString(2);
				tempContent = result.getString(3);
				tempStop = result.getString(5);
				tempEvery = result.getString(6);//
				tempCut = result.getString(7);//
				list.add(new FullCutPromotionPO( tempName, tempContent, start, parse(tempStop), PromotionType.FULL_CUT, id, Double.parseDouble(tempEvery),Double.parseDouble(tempCut)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<FullCutPromotionPO> findsFullPromotion(int id, String content) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<FullCutPromotionPO> list = new ArrayList<FullCutPromotionPO>();
		list = findsFullPromotion(id);
		for(int i = 0; i< list.size(); i++){
			FullCutPromotionPO fpo = list.get(i);
			if(!fpo.getContent().contains(content)){
				list.remove(fpo);
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
	
	
//	public static void main(String[]args) throws RemoteException{
//		FullCutPromotionDataServiceMySqlImpl p = new FullCutPromotionDataServiceMySqlImpl();
//		p.initFullCutPromotionDataService();
		
//		//p.insertDiscountPromotion(new DiscountPromotionPO("Third","ThirdPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
//		p.insertFullCutPromotion(new FullCutPromotionPO("AAA","SixthPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.FULL_CUT,20902341,200,20));
///		//p.deleteDiscountPromotion(new DiscountPromotionPO("Third","ThirdPromotion",Date.valueOf("2016-12-01"),Date.valueOf("2016-12-31"),PromotionType.DISCOUNT,20902341,0.8));
//		p.finishFullCutPromotionDataService();
//	}
	
}
