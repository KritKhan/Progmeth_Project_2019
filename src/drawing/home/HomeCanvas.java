package drawing.home;

import javax.annotation.Resource;

import SharedObject.Constant;
import SharedObject.ResourceLoader;
import drawing.manager.SceneManager;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class HomeCanvas extends Canvas {
	private GraphicsContext gc;
	private Font f;
	
	public HomeCanvas() {
		super(Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
		gc = this.getGraphicsContext2D();
		
		this.drawMainMenu();
		this.addKeyEventHandler();
	}
	
	private void drawMainMenu() {
		//draw bg
		gc.drawImage(ResourceLoader.homebg, 0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
		
		//draw title
		gc.setFill(Color.DARKTURQUOISE);
		f = Font.font("Georgia", 90);
		gc.setFont(f);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Battle x Monsters", Constant.SCENE_WIDTH/2, Constant.SCENE_HEIGHT/3);
//		gc.drawImage(ResourceLoader.logo, 250, 700/10, 500, 700/10+250);
		//draw btn
		double h = ResourceLoader.startbtn.getHeight()/2.0;
		double w = ResourceLoader.startbtn.getWidth()/2.0;
		gc.drawImage(ResourceLoader.startbtn, (Constant.SCENE_WIDTH/2)-w, (Constant.SCENE_HEIGHT/1.5)-h);
	}
	
	private void goToSeclect() {
		SceneManager.goToScene(SceneManager.SelectScene);
	}
	
	private void onButton(MouseEvent event, boolean isGoNext) {
		double h = ResourceLoader.startbtn.getHeight()/2.0;
		double w = ResourceLoader.startbtn.getWidth()/2.0;
		if (event.getSceneX() >= (Constant.SCENE_WIDTH / 2)-w && event.getSceneX() <= (Constant.SCENE_WIDTH / 2)+w
				&& event.getSceneY() >= (Constant.SCENE_HEIGHT / 1.5)-h && event.getSceneY() <= (Constant.SCENE_HEIGHT / 1.5)+h) {
			//area of event
			if (isGoNext) {				
				goToSeclect();
			} else {
				double h2 = ResourceLoader.starthili.getHeight()/2.0;
				double w2 = ResourceLoader.starthili.getWidth()/2.0;
				gc.drawImage(ResourceLoader.starthili, (Constant.SCENE_WIDTH/2)-w2, (Constant.SCENE_HEIGHT/1.5)-h2);
			}
		} 
		else {
			drawMainMenu();
		}
	}
	
	private void addKeyEventHandler() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					//SelectMain.stop();
					Runtime.getRuntime().exit(0);
				}
				if (event.getCode() == KeyCode.ENTER) {
					goToSeclect();
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
	
}