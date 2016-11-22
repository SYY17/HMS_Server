package dataservice.roomdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.RoomPO;
import po.RoomType;

public interface RoomDataService extends Remote{
	
	/**
	 * 
	 * @param rpo
	 */
	public void insertRoom(RoomPO rpo) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 */
	public void deleteAllRooms(int id) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @param type
	 */
	public void deleteRoom(int id, RoomType type) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @param type
	 * @param price
	 */
	public void updatePrice(int id, RoomType type, int price) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @param type
	 * @param remain
	 */
	public void updateRemainSum(int id, RoomType type, int remain) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @param type
	 * @param total
	 */
	public void updateTotalSum(int id, RoomType type, int total) throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @param type
	 * @return 根据酒店工作人员ID与房间类型查找并返回相应房间信息
	 */
	public RoomPO findRoom(int id, RoomType type) throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void initRoomDataService() throws RemoteException;
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public void finishRoomDataService() throws RemoteException;
}
