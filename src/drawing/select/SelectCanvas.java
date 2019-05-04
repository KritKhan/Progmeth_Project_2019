package drawing.select;

import SharedObject.Constant;
import SharedObject.ResourceLoader;
import drawing.manager.SceneManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.BattleFieldMain;

public class SelectCanvas extends Canvas {
	private GraphicsContext gc;
	private Font f;
	private Image isclicked;
	private Image[] hero = new Image[3];

	public SelectCanvas() {
		super(1000, 700);
		gc = this.getGraphicsContext2D();

		this.drawSelectMenu();
		this.addKeyEventHandler();

	}
	public void addHeroImage() {
		hero[0] = ResourceLoader.archerFace;
		hero[1] = ResourceLoader.magicianFace;
		hero[2] = ResourceLoader.knightFace;
	}

	public void drawSelectMenu() {
		// draw bg
		gc.drawImage(ResourceLoader.selectbg, 0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);

		// draw title
		gc.setFill(Color.DARKTURQUOISE);
		f = Font.font("Georgia", 70);
		gc.setFont(f);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Select Hero", Constant.SCENE_WIDTH / 2, Constant.SCENE_HEIGHT / 4);

		// draw hero1
		double h, w;
		w = ResourceLoader.archerFace.getWidth() / 2;
		h = ResourceLoader.archerFace.getHeight() / 2;
		gc.drawImage(ResourceLoader.archerFace, (Constant.SCENE_WIDTH / 4) * 2 - w, Constant.SCENE_HEIGHT / 2.5 - h);
		w = ResourceLoader.magicianFace.getWidth() / 2;
		h = ResourceLoader.magicianFace.getHeight() / 2;
		gc.drawImage(ResourceLoader.magicianFace, Constant.SCENE_WIDTH / 4 - w, Constant.SCENE_HEIGHT / 2.5 - h);
		w = ResourceLoader.knightFace.getWidth() / 2;
		h = ResourceLoader.knightFace.getHeight() / 2;
		gc.drawImage(ResourceLoader.knightFace, (Constant.SCENE_WIDTH / 4) * 3 - w, Constant.SCENE_HEIGHT / 2.5 - h);

		// draw ok btn
		h = ResourceLoader.okbtn.getHeight() / 2.0;
		w = ResourceLoader.okbtn.getWidth() / 2.0;
		gc.drawImage(ResourceLoader.okbtn, Constant.SCENE_WIDTH / 2 - w, Constant.SCENE_HEIGHT / 1.3 - h);
	}

	public void goToBattleField() {
		BattleFieldMain battle = new BattleFieldMain();
		SceneManager.goToScene(SceneManager.BattleFieldScene);
	}

	private void onButton(MouseEvent event, boolean isGoNext) {
		double harc, warc, hmag, wmag, hkni, wkni, hok, wok;
		warc = ResourceLoader.archerFace.getWidth() / 2.0;
		harc = ResourceLoader.archerFace.getHeight() / 2;
		wmag = ResourceLoader.magicianFace.getWidth() / 2;
		hmag = ResourceLoader.magicianFace.getHeight() / 2;
		wkni = ResourceLoader.knightFace.getWidth() / 2;
		hkni = ResourceLoader.knightFace.getHeight() / 2;
		hok = ResourceLoader.okbtn.getHeight() / 2.0;
		wok = ResourceLoader.okbtn.getWidth() / 2.0;

		if (event.getSceneX() >= (Constant.SCENE_WIDTH / 4) * 2 - warc
				&& event.getSceneX() <= (Constant.SCENE_WIDTH / 4) * 2 + warc
				&& event.getSceneY() >= (Constant.SCENE_HEIGHT / 2.5) - harc
				&& event.getSceneY() <= Constant.SCENE_HEIGHT / 2.5 + harc) {
			if (isGoNext) {
				
			} else {
				warc = ResourceLoader.archerFaceH.getWidth() / 2.0;
				harc = ResourceLoader.archerFaceH.getHeight() / 2;
				gc.drawImage(ResourceLoader.archerFaceH, (Constant.SCENE_WIDTH / 4) * 2 - warc,
						Constant.SCENE_HEIGHT / 2.5 - harc);
			}
		} else if (event.getSceneX() >= (Constant.SCENE_WIDTH / 4) - wmag
				&& event.getSceneX() <= (Constant.SCENE_WIDTH / 4) + wmag
				&& event.getSceneY() >= (Constant.SCENE_HEIGHT / 2.5) - hmag
				&& event.getSceneY() <= Constant.SCENE_HEIGHT / 2.5 + hmag) {
			if (isGoNext) {

			} else {
				wmag = ResourceLoader.magicianFaceH.getWidth() / 2;
				hmag = ResourceLoader.magicianFaceH.getHeight() / 2;
				gc.drawImage(ResourceLoader.magicianFaceH, Constant.SCENE_WIDTH / 4 - wmag,
						Constant.SCENE_HEIGHT / 2.5 - hmag);
			}
		} else if (event.getSceneX() >= (Constant.SCENE_WIDTH / 4) * 3 - wkni
				&& event.getSceneX() <= (Constant.SCENE_WIDTH / 4) * 3 + wkni
				&& event.getSceneY() >= (Constant.SCENE_HEIGHT / 2.5) - hkni
				&& event.getSceneY() <= Constant.SCENE_HEIGHT / 2.5 + hkni) {
			if (isGoNext) {

			} else {
				wkni = ResourceLoader.knightFaceH.getWidth() / 2;
				hkni = ResourceLoader.knightFaceH.getHeight() / 2;
				gc.drawImage(ResourceLoader.knightFaceH, (Constant.SCENE_WIDTH / 4) * 3 - wkni,
						Constant.SCENE_HEIGHT / 2.5 - hkni);
			}
		} else if (event.getSceneX() >= Constant.SCENE_WIDTH / 2 - wok
				&& event.getSceneX() <= Constant.SCENE_WIDTH / 2 + wok
				&& event.getSceneY() >= Constant.SCENE_HEIGHT / 1.3 - hok
				&& event.getSceneY() <= Constant.SCENE_HEIGHT / 1.3 + hok) {
			// area of event
			if (isGoNext) {
				goToBattleField();
				;
			} else {
				double h2 = ResourceLoader.okhili.getHeight() / 2.0;
				double w2 = ResourceLoader.okhili.getWidth() / 2.0;
				gc.drawImage(ResourceLoader.okhili, Constant.SCENE_WIDTH / 2 - w2, Constant.SCENE_HEIGHT / 1.3 - h2);
			}
		} else {
			drawSelectMenu();
		}
	}

	private void addKeyEventHandler() {

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
	

}
