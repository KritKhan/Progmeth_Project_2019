package drawing.manager;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import drawing.battlefield.BattleFieldScene;
import drawing.home.*;
import drawing.select.SelectScene;

public class SceneManager {
	
	private static Stage stage;
	public static HomeScene MainScene = new HomeScene();
	public static SelectScene SelectScene = new SelectScene();
	public static BattleFieldScene BattleFieldScene = new BattleFieldScene();
	
	public static void initialize(Stage primaryStage) {
		
		stage = primaryStage;
		stage.setTitle("Game Name");
		stage.show();
	}
	
	public static void goToScene(Scene scene) {
		stage.setScene(scene);
	}
	
	public static void goToCanvas(Canvas canvas) {
		StackPane root = new StackPane(canvas);
		Scene scene = new Scene(root);
		root.getChildren().add(canvas);
		canvas.requestFocus();
		stage.setScene(scene);
	}
}
