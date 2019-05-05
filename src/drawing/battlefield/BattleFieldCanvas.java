package drawing.battlefield;

import java.util.ConcurrentModificationException;

import SharedObject.Constant;
import SharedObject.IRenderable;
import SharedObject.InputUtility;
import SharedObject.RenderableHolder;
import SharedObject.ResourceLoader;
import drawing.select.SelectCanvas;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BattleFieldCanvas extends Canvas {
	
	private GraphicsContext gc;
	private BattleFieldScene scene;
	private String hero = SelectCanvas.selected;
	private IRenderable heroImg;
	
	public BattleFieldCanvas(BattleFieldScene scene) {
		super(Constant.SCENE_WIDTH,Constant.SCENE_HEIGHT);
		this.scene = scene;
		gc = this.getGraphicsContext2D();
<<<<<<< HEAD
//		gc.drawImage(ResourceLoader.map, 0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
//		gc.setFill(Color.BLACK);
//		gc.fillRect(0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
		
||||||| merged common ancestors
		gc.drawImage(ResourceLoader.dungeon1, 0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
=======
		gc.drawImage(ResourceLoader.grassland, 0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
>>>>>>> b620ca6ede30795c2fca328c92d1d4710f2b19c1
		InputUtility.bindMouseOnListeners(this);
		switch(hero) {
		case "Archer": heroImg = (IRenderable)ResourceLoader.archer;
		case "Knight": heroImg = (IRenderable)ResourceLoader.knight;
		case "Magician": heroImg = (IRenderable)ResourceLoader.magician;
		}
		RenderableHolder.getInstance().add(heroImg);
		
	}
	
	public void canvasUpdate() throws ConcurrentModificationException {
		if (!ResourceLoader.isLoadFinish()) {
			gc.setFill(Color.WHITE);
			gc.fillText("NOWLOADING",
					Constant.SCENE_WIDTH/2 - ResourceLoader.fontLoader.computeStringWidth("NOWLOADING", gc.getFont()) / 2,
					Constant.SCENE_HEIGHT/2 - ResourceLoader.fontLoader.getFontMetrics(gc.getFont()).getLineHeight() / 2);
		} else {
			for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
				if (entity.isVisible()) {
					entity.draw(gc);
				}
			}
		}
	}

}
