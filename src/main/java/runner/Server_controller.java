package runner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Server_controller {
	public static Stage stage;
	
	@FXML
	private void onStart(ActionEvent event) throws Exception {
		System.out.println("Start");
	}
	
	@FXML
	private void onClose(ActionEvent event) throws Exception {
		System.out.println("Close");
	}
}
