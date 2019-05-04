package drawing.manager;

import SharedObject.ResourceLoader;
import drawing.battlefield.BattleFieldScene;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class DialogPane extends VBox {

	private BattleFieldScene scene;
	
	public DialogPane(BattleFieldScene battleScene) {
		super(10);
		this.scene = battleScene;
		defaultDraw(scene, ResourceLoader.dungeon1);
	}
	
	public void defaultDraw(BattleFieldScene battleScene, Image image) {
		setMaxSize(1000, 700);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
}
