package drawing.manager;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import drawing.home.*;

public class SceneManager {
	
	private static Stage stage;
	private static TitleScene mainScene = new TitleScene();
	private static DungeonScene dungeonScene = new DungeonScene();
	
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
