package runner;

import rmi.RemoteController;

public class DataServiceServerRunner {
	
	public DataServiceServerRunner() {
		new RemoteController();
	}
	
	public static void main(String[] args) {
		new DataServiceServerRunner();
	}
}
