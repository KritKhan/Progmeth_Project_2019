package drawing.select;

import com.sun.javafx.tk.FontLoader;

import Exception.HeroException;
import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.ResourceLoader;
import creature.hero.Archer;
import creature.hero.Hero;
import creature.hero.Knight;
import creature.hero.Magician;
import drawing.battlefield.BattleFieldScene;
import drawing.field.BattleField;
import drawing.manager.SceneManager;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.BattleFieldMain;

public class SelectCanvas extends Canvas {
	private GraphicsContext gc;
	private FontLoader fontloader = ResourceLoader.fontLoader;
	public static String selected = "";
	private Image[] hero = new Image[3];
	public Hero he;

	public SelectCanvas() {
		super(1000, 700);
		gc = this.getGraphicsContext2D();
		this.initialHeroImage();
		this.drawSelectMenu();
		try {
			this.addKeyEventHandler();
		} catch (HeroException e) {
			System.out.println("HeroException : Please choose hero");
		}
	}

	public void initialHeroImage() {
		hero[0] = ResourceLoader.archerFace;
		hero[1] = ResourceLoader.magicianFace;
		hero[2] = ResourceLoader.knightFace;
	}

	public void drawSelectMenu() {
		// draw bg
		gc.drawImage(ResourceLoader.selectbg, 0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);

		// draw title
		gc.drawImage(ResourceLoader.select, Constant.SCENE_WIDTH / 2 - ResourceLoader.select.getWidth() / 2,
				Constant.SCENE_HEIGHT / 2.2 - ResourceLoader.select.getHeight() * 1.7);
		// draw hero1
		double h, w;
		w = Constant.hero_width / 2;
		h = Constant.hero_height / 2;
		gc.drawImage(hero[0], (Constant.SCENE_WIDTH / 4) * 2 - w, Constant.SCENE_HEIGHT / 2 - h);
		gc.drawImage(hero[1], Constant.SCENE_WIDTH / 4 - w, Constant.SCENE_HEIGHT / 2 - h);
		gc.drawImage(hero[2], (Constant.SCENE_WIDTH / 4) * 3 - w, Constant.SCENE_HEIGHT / 2 - h);

		Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 30);
		gc.setFont(theFont);
		gc.setFill(Color.FLORALWHITE);

		double font_width;
		font_width = fontloader.computeStringWidth("Archer", gc.getFont());
		gc.fillText("Archer", Constant.SCENE_WIDTH / 2 - font_width / 2, Constant.SCENE_HEIGHT / 1.5);

		font_width = fontloader.computeStringWidth("Magician", gc.getFont());
		gc.fillText("Magician", Constant.SCENE_WIDTH / 4 - font_width / 2, Constant.SCENE_HEIGHT / 1.5);

		font_width = fontloader.computeStringWidth("Knight", gc.getFont());
		gc.fillText("Knight", (Constant.SCENE_WIDTH / 4) * 3 - font_width / 2, Constant.SCENE_HEIGHT / 1.5);

		// draw ok btn
		h = ResourceLoader.okbtn.getHeight() / 2.0;
		w = ResourceLoader.okbtn.getWidth() / 2.0;
		gc.drawImage(ResourceLoader.okbtn, Constant.SCENE_WIDTH / 2 - w, Constant.SCENE_HEIGHT / 1.2 - h);
	}

	public void goToBattleField(Hero hero) {
		BattleFieldMain battleMain = new BattleFieldMain();
		System.out.println("create battleMain");
		BattleFieldMain.getGameLogic().newHero(he);
		System.out.println("pass add new hero");
		SceneManager.goToScene(SceneManager.BattleFieldScene);
		SceneManager.BattleFieldScene.toBattleField();
	}

	private void onButton(MouseEvent event, boolean isClicked) throws HeroException {
		double h, w, hok, wok;
		h = Constant.hero_height / 2;
		w = Constant.hero_width / 2;
		hok = ResourceLoader.okbtn.getHeight() / 2;
		wok = ResourceLoader.okbtn.getWidth() / 2;
		if (event.getSceneX() >= (Constant.SCENE_WIDTH / 4) * 2 - w
				&& event.getSceneX() <= (Constant.SCENE_WIDTH / 4) * 2 + w
				&& event.getSceneY() >= (Constant.SCENE_HEIGHT / 2) - h
				&& event.getSceneY() <= Constant.SCENE_HEIGHT / 2 + h) {
			if (isClicked) {
				this.initialHeroImage();
				hero[0] = ResourceLoader.archerFaceH;
				this.selected = "Archer";
				ResourceLoader.click.play(100);
				drawSelectMenu();
			} else {
				gc.drawImage(ResourceLoader.archerFaceH, (Constant.SCENE_WIDTH / 4) * 2 - w,
						Constant.SCENE_HEIGHT / 2 - h);
			}
		} else if (event.getSceneX() >= (Constant.SCENE_WIDTH / 4) - w
				&& event.getSceneX() <= (Constant.SCENE_WIDTH / 4) + w
				&& event.getSceneY() >= (Constant.SCENE_HEIGHT / 2) - h
				&& event.getSceneY() <= Constant.SCENE_HEIGHT / 2 + h) {
			if (isClicked) {
				this.initialHeroImage();
				hero[1] = ResourceLoader.magicianFaceH;
				this.selected = "Magician";
				ResourceLoader.click.play(100);
				drawSelectMenu();
			} else {
				gc.drawImage(ResourceLoader.magicianFaceH, Constant.SCENE_WIDTH / 4 - w, Constant.SCENE_HEIGHT / 2 - h);
			}
		} else if (event.getSceneX() >= (Constant.SCENE_WIDTH / 4) * 3 - w
				&& event.getSceneX() <= (Constant.SCENE_WIDTH / 4) * 3 + w
				&& event.getSceneY() >= (Constant.SCENE_HEIGHT / 2) - h
				&& event.getSceneY() <= Constant.SCENE_HEIGHT / 2 + h) {
			if (isClicked) {
				this.initialHeroImage();
				hero[2] = ResourceLoader.knightFaceH;
				this.selected = "Knight";
				ResourceLoader.click.play(100);
				drawSelectMenu();
			} else {
				gc.drawImage(ResourceLoader.knightFaceH, (Constant.SCENE_WIDTH / 4) * 3 - w,
						Constant.SCENE_HEIGHT / 2 - h);
			}
		} else if (event.getSceneX() >= Constant.SCENE_WIDTH / 2 - wok
				&& event.getSceneX() <= Constant.SCENE_WIDTH / 2 + wok
				&& event.getSceneY() >= Constant.SCENE_HEIGHT / 1.2 - hok
				&& event.getSceneY() <= Constant.SCENE_HEIGHT / 1.2 + hok) {
			// area of event
			if (isClicked && selected.equals("")) {
				throw new HeroException();
			} else if (isClicked && !selected.equals("")) {
				// create Hero here
				ResourceLoader.click.play(100);
				switch (selected) {
				case "Magician":
					he = new Magician();
					break;
				case "Archer":
					he = new Archer();
					break;
				case "Knight":
					he = new Knight();
				}
				System.out.println("pass select hero");
				System.out.println("Hero name : " + he.getHeroName());
				goToBattleField(he);
			} else {
				gc.drawImage(ResourceLoader.okhili, Constant.SCENE_WIDTH / 2 - wok, Constant.SCENE_HEIGHT / 1.2 - hok);
			}
		} else {
			drawSelectMenu();
		}
	}

	private void addKeyEventHandler() throws HeroException {
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					// SelectMain.stop();
					Runtime.getRuntime().exit(0);
				}
			}
		});

		setOnMouseMoved((MouseEvent event) -> {
			try {
				onButton(event, false);
			} catch (HeroException e) {
//				System.out.println("HeroException : Please choose hero");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please choose hero!");
				alert.setHeaderText("Hero Error");
				alert.showAndWait();
			}
		});
		setOnMouseClicked((MouseEvent event) -> {
			try {
				onButton(event, true);
			} catch (HeroException e) {
//				System.out.println("HeroException : Please choose hero");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please choose hero!");
				alert.setHeaderText("Hero Error");
				alert.showAndWait();
			}
		});

		setOnMouseDragEntered((MouseEvent event) -> {
			try {
				onButton(event, true);
			} catch (HeroException e) {
//				System.out.println("HeroException : Please choose hero");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please choose hero!");
				alert.setHeaderText("Hero Error");
				alert.showAndWait();
			}
		});
	}

	public String getSelected() {
		return selected;
	}
	
	public SelectCanvas getSelecteCanvas(){
		return this;
	}
	
	public void clearSelectCanvas() {
		this.he = null;
		this.selected = "";
	}
}
