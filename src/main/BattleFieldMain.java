package main;

import java.util.ConcurrentModificationException;

import Logic.GameLogic;
import SharedObject.RenderableHolder;
import SharedObject.ResourceLoader;
import drawing.battlefield.BattleFieldCanvas;
import drawing.manager.SceneManager;
import javafx.animation.AnimationTimer;

public class BattleFieldMain {
	private static BattleFieldCanvas canvas;
	private static GameLogic logic;
	
	public BattleFieldMain() {
		canvas = (BattleFieldCanvas) SceneManager.BattleFieldScene.getCanvas();
	}

	public static void start() {
		Main.isGameRunning = true;
		animation.start();
	}
	
	private static AnimationTimer animation = new AnimationTimer() {
		public void handle(long now) {
			if (ResourceLoader.isLoadFinish()) {
				try {
					canvas.canvasUpdate();
					//logic.logicUpdate();
					RenderableHolder.getInstance().update();
				} catch (IllegalArgumentException | ConcurrentModificationException e) {
					System.out.println("cannot update");
				}
			}else {
				canvas.canvasUpdate();
			}
		}
	};

	public static void stop() {
		Main.isGameRunning = false;
		animation.stop();
	}

	public static BattleFieldCanvas getBattleFieldCanvas() {
		return canvas;
	}
	
	public static GameLogic getGameLogic() {
		return logic;
		
	}

}
