package main;

import drawing.manager.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static boolean isGameRunning;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			isGameRunning = true;
			SceneManager.initialize(primaryStage);
			SceneManager.goToScene(SceneManager.MainScene);

			primaryStage.setTitle("Battle x Monsters");
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void stop() throws Exception {
		try {
			BattleFieldMain.stop();
			
		} catch (NullPointerException e) {
		} finally {
			isGameRunning = false;
			Platform.exit();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
