package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HeroException extends Exception{
	
	public HeroException() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Please choose hero!!!");
		alert.showAndWait();
//		"Please choose hero!!!"
	}

}
