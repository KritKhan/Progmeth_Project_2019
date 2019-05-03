package drawing.select;

import SharedObject.Constant;
import SharedObject.RenderableHolder;
import SharedObject.ResourceLoader;
import drawing.manager.SceneManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.BattleFieldMain;

public class SelectCanvas extends Canvas{
	private GraphicsContext gc;
	private Font f;
	
	public SelectCanvas() {
		super(1000,700);
		gc = this.getGraphicsContext2D();
		
		this.drawSelectMenu();
		
	}
	
	public void drawSelectMenu() {
		//draw bg
		gc.drawImage(img, 0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
		
		//draw title
		gc.setFill(Color.DARKTURQUOISE);
		f = Font.font("Georgia", 170);
		gc.setFont(f);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Select Hero", Constant.SCENE_WIDTH/2, Constant.SCENE_HEIGHT/4);
		
		//draw hero1
		gc.drawImage(ResourceLoader.archerFace, (Constant.SCENE_WIDTH/4)*2, Constant.SCENE_HEIGHT/2);
		gc.drawImage(ResourceLoader.magicianFace, Constant.SCENE_WIDTH/4, Constant.SCENE_HEIGHT/2);
		gc.drawImage(ResourceLoader.knightFace, (Constant.SCENE_WIDTH/4)*3, Constant.SCENE_HEIGHT/2);
		
		//draw input name
		
		//draw ok btn
	}
	
	public void goToBattleField() {
		BattleFieldMain battle = new BattleFieldMain();
		SceneManager.goToScene(SceneManager.BattleFieldScene);
	}
	
	private void onButton(MouseEvent event, boolean isGoNext) {
		if (event.getSceneX() >= Constant.SCENE_WIDTH / 3 && event.getSceneX() <= Constant.SCENE_WIDTH / 3 + 300
				&& event.getSceneY() >= Constant.SCENE_HEIGHT / 2 && event.getSceneY() <= Constant.SCENE_HEIGHT / 2 + 87) {
			//area of event
			if (isGoNext) {				
				goToBattleField();;
			} else {
				//gc.drawImage(img, x, y, btn's w, btn's h);
			}
		} 
		else {
			drawSelectMenu();
		}
	}

}
