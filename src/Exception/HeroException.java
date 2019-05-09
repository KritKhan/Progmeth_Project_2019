package Exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HeroException extends Exception {

	public HeroException() {
		System.out.println("HeroException : Please choose hero");
	}

}
