package drawing.select;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
		gc.drawImage(img, 0, 0, 1000, 700);
		
		//draw title
		gc.setFill(Color.DARKTURQUOISE);
		f = Font.font("Georgia", 170);
		gc.setFont(f);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Select Hero", 500, 700/4);
		
		//draw hero1
		gc.drawImage(RenderableHolder.archerFace, 500, 700/2);
		gc.drawImage(RenderableHolder.magicianFace, 250, 700/2);
		gc.drawImage(RenderableHolder.knightFace, 750, 700/2);
		
		//draw input name
		
		//draw ok btn
	}
	
	public void gotoBattleField() {
		BattleFieldMain battle = new BattleFieldMain();
		SceneManager.gotoScene(SceneManager.BattlefieldScene);
	}

}
