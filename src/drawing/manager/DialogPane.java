package drawing.manager;

import com.sun.javafx.tk.FontLoader;

import Exception.PurchaseException;
import Logic.GameLogic;
import SharedObject.ResourceLoader;
import drawing.battlefield.BattleFieldScene;
import drawing.field.StatusBar;
import drawing.select.SelectCanvas;
import drawing.select.SelectScene;
import item.Item;
import item.Shop;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.BattleFieldMain;

public class DialogPane extends VBox {
	private BattleFieldScene scene;
	private ImageView HpPotion;
	private ImageView MpPotion;
	private ImageView MixPotion;
	private String selectedItem = "";
	private FontLoader fontloader = ResourceLoader.fontLoader;

	public DialogPane(BattleFieldScene battleScene, Image image) {
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

		gp.add(HpPotion, 1, 1);
		gp.add(MpPotion, 2, 1);
		gp.add(MixPotion, 3, 1);

		Button close = new Button("X");
		close.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, null, null)));
		close.setTextFill(Color.CORNSILK);
		close.setOnMouseClicked((MouseEvent e) -> {
			this.getChildren().clear();
			ResourceLoader.click.play(100);
			scene.toBattleField();
		});
		this.setOnKeyPressed((KeyEvent e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				this.getChildren().clear();
				ResourceLoader.click.play(100);
				scene.toBattleField();
			}
		});
		gp.add(close, 2, 13);
		return gp;
	}

	public void shop() {
		// TODO Auto-generated method stub
		defaultDraw(scene, ResourceLoader.shop);
		GridPane gp = generate(ResourceLoader.shop);
		Shop shop = new Shop();

		Text t1 = new Text();
		t1.setFont(Font.font(15));
		t1.setFill(Color.BLACK);
		Text t2 = new Text();
		t2.setFont(Font.font(15));
		t2.setFill(Color.BLACK);

		TextField money = new TextField();
		money.setText(Integer.toString(Logic.GameLogic.heroInBat.getMoney()));
		money.setEditable(false);
		money.setPrefSize(80, 20);

		Button buy = new Button("BUY");
		buy.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, null, null)));
		buy.setTextFill(Color.CORNSILK);
		gp.add(t1, 1, 3, 4, 4);
		gp.add(t2, 1, 5, 4, 4);
		gp.add(buy, 3, 9);
		gp.add(money, 3, 12, 2, 2);

		HpPotion.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.click.play(100);
			selectedItem = "HpPotion";
			t1.setText("Hp Potion\nHeal 500 points to Hp.");
			t2.setText("Price : 100");
		});

		MpPotion.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.click.play(100);
			selectedItem = "MpPotion";
			t1.setText("Mp Potion\nHeal 500 points to Mp.");
			t2.setText("Price : 100");
		});

		MixPotion.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.click.play(100);
			;
			selectedItem = "MixPotion";
			t1.setText("Mix Potion\nHeal 250 points to Mp.\nHeal 250 points to Hp.");
			t2.setText("Price : 120");
		});

		buy.setOnMouseClicked((MouseEvent event0) -> {
			ResourceLoader.click.play(100);
			try {
				shop.buy(selectedItem);
			} catch (PurchaseException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please check item or your  money!");
				alert.setHeaderText("Shop Error");
				alert.showAndWait();
			}
			money.setText(Integer.toString(Logic.GameLogic.heroInBat.getMoney()) + " g");
		});

		this.getChildren().add(gp);
	}

	public void setting() {
		// TODO Auto-generated method stub
		defaultDraw(scene, ResourceLoader.setting);
		Button resume = new Button("RESUME");
		resume.setStyle("-fx-color: red;-fx-border: none ");
		resume.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.click.play(100);
			scene.toBattleField();
		});
		resume.setOnKeyPressed((KeyEvent e) -> {
			if (e.getCode() == KeyCode.ENTER) {

			}
		});
		this.getChildren().add(resume);
	}

	public void dead() {
		// TODO Auto-generated method stub
		BattleFieldMain.getBattleFieldCanvas().canvasUpdate();
		defaultDraw(scene, ResourceLoader.end);
		Button close = new Button("Restart");
		close.setStyle("-fx-color: red;-fx-border: none ");
		close.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.click.play(100);
			this.getChildren().clear();
			GameLogic.battleField.restart();
			//GameLogic.heroInBat.destroyed();
			SceneManager.SelectScene = new SelectScene();
			SceneManager.goToScene(SceneManager.MainScene);
		});
		this.getChildren().add(close);
	}
}
