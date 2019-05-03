package drawing.select;

import javax.annotation.Resource;

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
		gc.drawImage(ResourceLoader.selectbg, 0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
		
		//draw title
		gc.setFill(Color.DARKTURQUOISE);
		f = Font.font("Georgia", 70);
		gc.setFont(f);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Select Hero", Constant.SCENE_WIDTH/2, Constant.SCENE_HEIGHT/4);
		
		//draw hero1
		double h,w;
		w = ResourceLoader.archerFace.getWidth()/2;
		gc.drawImage(ResourceLoader.archerFace, (Constant.SCENE_WIDTH/4)*2-w, Constant.SCENE_HEIGHT/2.5);
		w = ResourceLoader.magicianFace.getWidth()/2;
		gc.drawImage(ResourceLoader.magicianFace, Constant.SCENE_WIDTH/4-w, Constant.SCENE_HEIGHT/2.5);
		w = ResourceLoader.knightFace.getWidth()/2;
		gc.drawImage(ResourceLoader.knightFace, (Constant.SCENE_WIDTH/4)*3-w, Constant.SCENE_HEIGHT/2.5);
		
		//draw ok btn
		h = ResourceLoader.okbtn.getHeight()/2.0;
		w = ResourceLoader.okbtn.getWidth()/2.0;
		gc.drawImage(ResourceLoader.okbtn, Constant.SCENE_WIDTH/2 - w, Constant.SCENE_HEIGHT/1.3 - h);
	}
	
	public void goToBattleField() {
		BattleFieldMain battle = new BattleFieldMain();
		SceneManager.goToScene(SceneManager.BattleFieldScene);
	}
	
	private void onButton(MouseEvent event, boolean isGoNext) {
		double h = ResourceLoader.okbtn.getHeight()/2.0;
		double w = ResourceLoader.okbtn.getWidth()/2.0;
		if (event.getSceneX() >= Constant.SCENE_WIDTH / 2-w && event.getSceneX() <= Constant.SCENE_WIDTH / 3 + w
				&& event.getSceneY() >= Constant.SCENE_HEIGHT/1.3 - h && event.getSceneY() <= Constant.SCENE_HEIGHT /1.3 + h) {
			//area of event
			if (isGoNext) {				
				goToBattleField();;
			} else {
				double h2 = ResourceLoader.okhili.getHeight()/2.0;
				double w2 = ResourceLoader.okhili.getWidth()/2.0;
				gc.drawImage(ResourceLoader.okhili,Constant.SCENE_WIDTH/2-w2,Constant.SCENE_HEIGHT/1.3-h2);
			}
		} 
		else {
			drawSelectMenu();
		}
	}

}
