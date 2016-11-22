package po;
import java.io.*;
public class RoomPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int hotelID;
	RoomType roomtype;
	int totalSum;
	int remainSum;
	int price;
	
	public RoomPO(int hid,RoomType rt,int ts,int rs,int p){
		hotelID=hid;
		roomtype=rt;
		totalSum=ts;
		remainSum=rs;
		price=p;
	}
	
	public int getHotelID(){
		return hotelID;
	}
	
	public RoomType getRoomType(){
		return roomtype;
	}
	
	public int getTotalSum(){
		return totalSum;
	}
	
	public int getRemainSum(){
		return remainSum;
	}
	
	public int getPrice(){
		return price;
	}
}