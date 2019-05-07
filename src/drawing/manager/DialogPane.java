package drawing.manager;

import com.sun.javafx.tk.FontLoader;
import SharedObject.ResourceLoader;
import drawing.battlefield.BattleFieldScene;
import drawing.field.StatusBar;
import item.Item;
import item.Shop;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.BattleFieldMain;

public class DialogPane extends VBox {
	private BattleFieldScene scene;
	private ImageView HpPotion;
	private ImageView MpPotion;
	private ImageView MixPotion;
	private String selectedItem = "" ;
	Item item = null;
	private FontLoader fontloader = ResourceLoader.fontLoader;
	public DialogPane(BattleFieldScene battleScene,Image image) {
		super(10);
		this.scene = battleScene;
		defaultDraw(scene, image);
	}
	
	public void defaultDraw(BattleFieldScene battleScene, Image image) {
		setMaxSize(1000, 700);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	
	public GridPane generate(Image img) {
		HpPotion = new ImageView(ResourceLoader.hp);
		MpPotion = new ImageView(ResourceLoader.mp);
		MixPotion = new ImageView(ResourceLoader.mix);

		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.setHgap(5);
		gp.setVgap(20);
		gp.setAlignment(Pos.TOP_CENTER);

		gp.add(HpPotion, 0, 1);
		gp.add(MpPotion, 1, 1);
		gp.add(MixPotion, 2, 1);

		Button close = new Button("X");
		//close.setStyle("-fx-color: red;-fx-border: none ");
		close.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		close.setTextFill(Color.WHITE);
		close.setOnMouseClicked((MouseEvent e) -> {
			this.getChildren().clear();
			//ResourceLoader.clickSound.play(100);
			scene.toBattleField();
		});
		this.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode()==KeyCode.ENTER) {
				this.getChildren().clear();
				//ResourceLoader.clickSound.play(100);
				scene.toBattleField();
			}
		});
		gp.add(close, 2, 13);
		return gp;
	}
	
	public void shop() {
		// TODO Auto-generated method stub
		defaultDraw(scene, ResourceLoader.waterDun);
		GridPane gp = generate(ResourceLoader.waterDun);
		Shop shop = new Shop();

		Text t1 = new Text();
		t1.setFill(Color.ALICEBLUE);
		Text t2 = new Text();
		t2.setFill(Color.ALICEBLUE);

		TextField money = new TextField();
		money.setText(Integer.toString(Logic.GameLogic.heroInBat.getMoney()));
		money.setEditable(false);
		money.setPrefSize(80, 20);

		Button buy = new Button("BUY");
		buy.setTextFill(Color.WHITE);
		buy.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		gp.add(t1, 1, 3, 4, 4);
		gp.add(t2, 1, 5, 4, 4);
		gp.add(buy, 3, 9);
		gp.add(money, 3, 12, 2, 2);

		HpPotion.setOnMouseClicked((MouseEvent e) -> {
			//ResourceLoader.clickSound.play(100);
			selectedItem = "HpPotion";
			t1.setText("Hp Potion\nHeal 500 points to Hp.");
			t2.setText("Price : 100");
		});

		MpPotion.setOnMouseClicked((MouseEvent e) -> {
			//ResourceLoader.clickSound.play(100);
			selectedItem = "MpPotion";
			t1.setText("Mp Potion\nHeal 500 points to Mp.");
			t2.setText("Price : 100");
		});
		
		MixPotion.setOnMouseClicked((MouseEvent e) -> {
			//ResourceLoader.clickSound.play(100);
			selectedItem = "MixPotion";
			t1.setText("Mix Potion\nHeal 250 points to Mp.\nHeal 250 points to Hp.");
			t2.setText("Price : 120");
		});
		switch(selectedItem) {
		case "MpPotion": 
			item = new item.MpPotion();
			break;
		case "HpPotion": 
			item = new item.HpPotion();
			break;
		case "MixPotion" : 
			item = new item.MixPotion();
		}
		buy.setOnMouseClicked((MouseEvent event0) -> {
			shop.buy(item);
			money.setText(Integer.toString(Logic.GameLogic.heroInBat.getMoney()) );
		});

		this.getChildren().add(gp);
	}
	
	public void setting() {
		// TODO Auto-generated method stub
		defaultDraw(scene, ResourceLoader.grassland);
		Button resume = new Button("RESUME");
		resume.setStyle("-fx-color: red;-fx-border: none ");
		resume.setOnMouseClicked((MouseEvent e) -> {
			//ResourceLoader.clickSound.play(100);
			scene.toBattleField();
		});
		resume.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode()==KeyCode.ENTER) {
				
			}
		});
		this.getChildren().add(resume);
	}
	
	public void dead() {
		// TODO Auto-generated method stub
		defaultDraw(scene, ResourceLoader.dirtland);
	}
}
