package dataservice.hoteldataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.HotelPO;

public interface HotelDataService extends Remote{
	
	/**
	 * 
	 * @param hpo
	 * @throws RemoteException
	 */
	public void deleteHotel(int id) throws RemoteException; 
	
	/**
	 * 
	 * @param hpo
	 * @throws RemoteException
	 */
	public void insertHotel(HotelPO hpo) throws RemoteException;
	
	/**
	 * 
	 * @param hpo
	 * @throws RemoteException
	 */
	public void updateHotel(HotelPO hpo) throws RemoteException;
	
	/**
	 * 
	 * @param name
	 * @return 按酒店姓名查找并返回酒店信息
	 * @throws RemoteException
	 */
	public HotelPO findHotel(String name) throws RemoteException;
	
	/**
	 * 
	 * @param field
	 * @param value
	 * @return 按给定字段名与值查找并返回酒店信息
	 * @throws RemoteException
	 */
	public ArrayList<HotelPO> findsHotel(String field,String value) throws RemoteException;
	
	/**
	 * 
	 * @return 查找并返回所有酒店信息
	 * @throws RemoteException
	 */
	public ArrayList<HotelPO> findsHotel() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initHotelDataService() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishHotelDataService() throws RemoteException;
}
