package drawing.battlefield;

import Main.DungeonMain;
import SharedObject.InputUtility;
import drawing.manager.DialogPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import main.BattleFieldMain;

public class BattleFieldScene extends Scene{
	private StackPane root;
	private Canvas canvas;
	private DialogPane dialog;

	public BattleFieldScene() {
		super(new StackPane());
		root = (StackPane) this.getRoot();
		root.setAlignment(Pos.CENTER);
		
		canvas = new BattleFieldCanvas(this);
		root.getChildren().add(canvas);
		
		dialog = new DialogPane(this);
		root.getChildren().add(dialog);
		
		InputUtility.bindListeners(this);
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
			dialog.opening();
			break;
		case 1:
			dialog.inventory();
			break;
		case 2:
			dialog.shop();
			break;
		case 3:
			dialog.setting();
			break;
		case 4:
			dialog.dead();
			break;
		case 5 :
			dialog.openingNext();
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}

}
