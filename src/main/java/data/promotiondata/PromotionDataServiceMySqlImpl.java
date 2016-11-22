package data.promotiondata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.configuration.ConfigurationServiceMySqlImpl;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;

public class PromotionDataServiceMySqlImpl implements PromotionDataService{
	
	Connection connect;
	PreparedStatement statement;
	ResultSet result;
	ConfigurationServiceMySqlImpl configure;
	
	public PromotionDataServiceMySqlImpl() {
		// TODO Auto-generated constructor stub
		configure = new ConfigurationServiceMySqlImpl();
	}
	
	/**
	 * 
	 * @param id
	 * @param start
	 * @param content
	 * @return 根据ID, 营销策略内容和起始时间查找并返回营销策略信息
	 * @throws RemoteException
	 */
	@Override
	public ArrayList <PromotionPO> findsPromotion(int id, String content, Date start) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PromotionPO> list = findsPromotion(id, start);
		for(int i = 0; i< list.size(); i++){
			PromotionPO po = list.get(i);
			if(!po.getContent().contains(content)){
				list.remove(po);
				i--;
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @return 根据ID查找并返回所有营销策略信息
	 * @throws RemoteException
	 */
	@Override
	public ArrayList <PromotionPO> findsPromotion(int id) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PromotionPO> list = new ArrayList<>();
		
		try {
			statement = connect.prepareStatement("select * from promotion where id = ?");
			
			//列：id; content; start
			statement.setString(1, String.valueOf(id));
			
			result = statement.executeQuery();
			String tempContent;
			String tempStart;
			
			//遍历result
			while(result.next()){
				tempContent = result.getString(2);
				tempStart = result.getString(3);
				list.add(new PromotionPO(tempContent, parse(tempStart), id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	/**
	 * 
	 * @param start
	 * @return 根据ID和起始时间查找并返回营销策略信息
	 * @throws RemoteException
	 */
	@Override
	public ArrayList <PromotionPO> findsPromotion(int id, Date start) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PromotionPO> list = new ArrayList<>();
		
		try {
			statement = connect.prepareStatement("select * from promotion where id = ? and start = ?");
			
			//列：id; content; start
			statement.setString(1, String.valueOf(id));
			statement.setString(2, parse(start));
			
			result = statement.executeQuery();
			String tempContent;
			
			//遍历result
			while(result.next()){
				tempContent = result.getString(2);
				list.add(new PromotionPO(tempContent, start, id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	/**
	 * 
	 * @param id
	 * @param content
	 * @return 根据ID和内容查找并返回营销策略信息
	 * @throws RemoteException
	 */
	@Override
	public ArrayList <PromotionPO> findsPromotion(int id, String content) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PromotionPO> list = new ArrayList<>();
		list = findsPromotion(id);
		for(int i = 0; i< list.size(); i++){
			PromotionPO po = list.get(i);
			if(!po.getContent().contains(content)){
				list.remove(po);
				i--;
			}
		}
		return list;
		
	}

	/**
	 * 
	 * @param ppo
	 * @throws RemoteException
	 */
	@Override
	public void insertPromotion(PromotionPO ppo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//为防止重复插入信息应进行检查
			ArrayList<PromotionPO> list = findsPromotion(ppo.getID(), ppo.getContent(), ppo.getStartTime());
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getContent().equals(ppo.getContent())) return;
			}
			
			//列：id; content; start
			statement = connect.prepareStatement("insert into promotion values(?, ?, ?)");
			
			statement.setString(1, String.valueOf(ppo.getID()));
			statement.setString(2, String.valueOf(ppo.getContent()));
			statement.setString(3, parse(ppo.getStartTime()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ppo
	 * @throws RemoteException
	 */
	@Override
	public void deletePromotion(PromotionPO ppo) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			//列：id; content; start
			statement = connect.prepareStatement("delete from promotion where id = ? and content = ? and start = ?");
			statement.setString(1, String.valueOf(ppo.getID()));
			statement.setString(2, ppo.getContent());
			statement.setString(3, parse(ppo.getStartTime()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void initPromotionDataService() throws RemoteException {
		// TODO Auto-generated method stub
		connect = configure.init();
	}

	/**
	 * 
	 * @throws RemoteException
	 */
	@Override
	public void finishPromotionDataService() throws RemoteException {
		// TODO Auto-generated method stub
		configure.finish(connect, statement, result);
	}
	
	private String parse(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	private Date parse(String s) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(s);
	}

}
