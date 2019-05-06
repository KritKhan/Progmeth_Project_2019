package drawing.battlefield;

import Logic.GameLogic;
import SharedObject.InputUtility;
import SharedObject.ResourceLoader;
import drawing.manager.DialogPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import main.BattleFieldMain;

public class BattleFieldScene extends Scene{
	private StackPane root;
	private Canvas canvas;
	private DialogPane dialog;
	private Image[] images;

	public BattleFieldScene() {
		super(new StackPane());
		initialImages(); 
		root = (StackPane) this.getRoot();
		root.setAlignment(Pos.CENTER);
		
		canvas = new BattleFieldCanvas(this);
		root.getChildren().add(canvas);
		
		dialog = new DialogPane(this,ResourceLoader.logo);
		root.getChildren().add(dialog);
//		this.toBattleField();
		
		InputUtility.bindListeners(this);
	}
	
	public void initialImages() {
		images = new Image[11];
		images[0] = ResourceLoader.dungeon1;
		images[1] = ResourceLoader.dirtland;
		images[2] = ResourceLoader.dirtland;
		images[3] = ResourceLoader.dungeon1;
		images[4] = ResourceLoader.waterDun;
		images[5] = ResourceLoader.waterDun;
		images[6] = ResourceLoader.grassland;
		images[7] = ResourceLoader.dungeon1;
		images[8] = ResourceLoader.grassland;
		images[9] = ResourceLoader.dungeon2;
		images[10] = ResourceLoader.dungeon2;
	}
	
	public void toBattleField() {
		dialog.getChildren().clear();
		dialog.setVisible(false);
		BattleFieldMain.start();
		canvas.requestFocus();
	}
	public void toDialog(int c) {
		dialog.getChildren().clear();
		dialog.setVisible(true);
		dialog.requestFocus();
		BattleFieldMain.stop();
		switch(c) {
		case 0:			
			dialog.shop();
			break;
		case 1:
			dialog.setting();
			break;
		case 2:
			dialog.dead();
			break;
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}

}