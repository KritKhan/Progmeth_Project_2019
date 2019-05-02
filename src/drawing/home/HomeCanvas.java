package drawing.home;

import SharedObject.RenderableHolder;
import drawing.manager.SceneManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.SelectMain;
import utility.Constant;
import utility.ResourceLoader;

public class HomeCanvas extends Canvas {
	private GraphicsContext gc;
	private Font f;
	
	
	public HomeCanvas() {
		super(1000, 700);
		gc = this.getGraphicsContext2D();
		
		this.drawMainMenu();
		
	}
	
	public void drawMainMenu() {
		//draw bg
		gc.drawImage(img, 0, 0, 1000, 700);
		
		//draw title
		gc.setFill(Color.DARKTURQUOISE);
		f = Font.font("Georgia", 200);
		gc.setFont(f);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("B x M", 500, 700/3);
		
		//draw btn
		gc.drawImage(img, 500, 700/2);
		
	}
	
	public void goToSeclect() {
		SelectMain select = new SelectMain();
		SceneManager.goToScene(SceneManager.SelectScene);
		
	}
	
	private void onButton(MouseEvent event, boolean isGoNext) {
		if (event.getSceneX() >= 10000 / 3 && event.getSceneX() <= 1000 / 3 + 300
				&& event.getSceneY() >= 700 / 2 && event.getSceneY() <= 700 / 2 + 87) {
			//area of event
			if (isGoNext) {				
				goToSeclect();
			} else {
				//gc.drawImage(img, x, y, btn's w, btn's h);
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
					SelectMain.stop();
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
