package drawing.select;

import com.sun.javafx.tk.FontLoader;

import SharedObject.Constant;
import SharedObject.ResourceLoader;
import drawing.manager.SceneManager;
import exception.HeroException;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
	private String selected = "";
	private Image[] hero = new Image[3];

	public SelectCanvas() {
		super(1000, 700);
		gc = this.getGraphicsContext2D();
		this.initialHeroImage();
		this.drawSelectMenu();
		this.addKeyEventHandler();
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
		font_width = fontloader.computeStringWidth("Ancher", gc.getFont());
		gc.fillText("Ancher", Constant.SCENE_WIDTH / 2 - font_width / 2, Constant.SCENE_HEIGHT / 1.5);

		font_width = fontloader.computeStringWidth("Magician", gc.getFont());
		gc.fillText("Magician", Constant.SCENE_WIDTH / 4 - font_width / 2, Constant.SCENE_HEIGHT / 1.5);

		font_width = fontloader.computeStringWidth("Knight", gc.getFont());
		gc.fillText("Knight", (Constant.SCENE_WIDTH / 4) * 3 - font_width / 2, Constant.SCENE_HEIGHT / 1.5);

		// draw ok btn
		h = ResourceLoader.okbtn.getHeight() / 2.0;
		w = ResourceLoader.okbtn.getWidth() / 2.0;
		gc.drawImage(ResourceLoader.okbtn, Constant.SCENE_WIDTH / 2 - w, Constant.SCENE_HEIGHT / 1.2 - h);
	}

	public void goToBattleField() {
		BattleFieldMain battle = new BattleFieldMain();
		SceneManager.goToScene(SceneManager.BattleFieldScene);
	}

	private void onButton(MouseEvent event, boolean isClicked) {
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
			if (isClicked && !selected.equals("")) {
				goToBattleField();
				System.out.println(getSelected());
			} else {
				gc.drawImage(ResourceLoader.okhili, Constant.SCENE_WIDTH / 2 - wok, Constant.SCENE_HEIGHT / 1.2 - hok);
			}
		} else {
			drawSelectMenu();
		}
	}

	private void addKeyEventHandler() {
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
			onButton(event, false);
		});
		setOnMouseClicked((MouseEvent event) -> {
			onButton(event, true);
		});
		
		setOnMouseDragEntered((MouseEvent event) -> {
			onButton(event, true);
		});
	}

	public String getSelected() {
		return selected;
	}
}
