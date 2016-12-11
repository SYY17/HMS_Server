package runner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Server_controller {
	public static Stage stage;
	
	@FXML
	private Label startButton;
	
	@FXML
	private ImageView startImage;
	
	@FXML
	private Label stopButton;
	
	@FXML
	private ImageView stopImage;
	
	@FXML
	private TextField onLine;
	
	private DataServiceServerRunner runner;
	
	@FXML
	private void onClickedStart(MouseEvent event) throws Exception {
		startButton.setDisable(true);
		startImage.setDisable(true);
		startButton.setVisible(false);
		startImage.setVisible(false);
		
		stopButton.setDisable(false);
		stopImage.setDisable(false);
		stopButton.setVisible(true);
		stopImage.setVisible(true);
		
		if(runner == null){
			runner = new DataServiceServerRunner();
		}else{
			runner.startRunner();
		}
	}
	
	@FXML
	private void onClickedStop(MouseEvent event) throws Exception {
		stopButton.setDisable(true);
		stopImage.setDisable(true);
		stopButton.setVisible(false);
		stopImage.setVisible(false);
		
		startButton.setDisable(false);
		startImage.setDisable(false);
		startButton.setVisible(true);
		startImage.setVisible(true);
		
		runner.stopRunner();
	}

}
