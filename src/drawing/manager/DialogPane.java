package drawing.manager;

import SharedObject.RenderableHolder;
import drawing.battlefield.BattleFieldScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DialogPane extends VBox {
	private ImageView HpPotion;
	private ImageView MpPotion;
	private ImageView MixPotion;
	private BattleFieldScene scene;
	
	public DialogPane(BattleFieldScene battleScene) {
		super(10);
		this.scene = battleScene;
	}
	
	public void defaultDraw(BattleFieldScene battleScene, Image image) {
		setMaxSize(1000, 700);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	
	public void opening() {
		Text text = new Text("Choose Your Hero.");
		text.setFont(Font.font("Lucida Console",36));
		
		TextField textField = new TextField("Please enter your name.");
		textField.setFont(Font.font("Lucida Console",36));
		textField.setMaxWidth(250);
		textField.setPrefHeight(50);
		//textField.setStyle("-fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1");
		
		Button okBtn = new Button("OK");
		okBtn.setFont(Font.font("Lucida Console",36));
		
		this.getChildren().addAll(text,textField,okBtn);
	}
}
