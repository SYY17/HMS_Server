package runner;

import rmi.RemoteController;

public class DataServiceServerRunner {
	
	RemoteController remoteController;
	
	public DataServiceServerRunner() {
		// TODO Auto-generated constructor stub
		remoteController = new RemoteController();
	}
	
	public void startRunner(){
		remoteController = new RemoteController();
	}
	
	public void stopRunner(){
		remoteController.stopServer();
	}
	
	public static void main(String[] args) {
		new DataServiceServerRunner();
	}
}
