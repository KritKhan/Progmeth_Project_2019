package drawing.battlefield;

import java.util.ConcurrentModificationException;

import SharedObject.IRenderable;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.BattleFieldMain;

public class BattleFieldCanvas extends Canvas {
	private GraphicsContext gc;
	private BattleFieldScene scene;
	
	public BattleFieldCanvas(BattleFieldScene scene) {
		super(1000,700);
		this.scene = scene;
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 10000, 700);

		InputUtility.bindMouseOnListeners(this);
	}
	
	public void canvasUpdate() throws ConcurrentModificationException {
		if (!ResourceLoader.isLoadFinish()) {
			gc.setFill(Color.WHITE);
			gc.fillText("NOWLOADING",
					1000/2 - RenderableHolder.fontLoader.computeStringWidth("NOWLOADING", gc.getFont()) / 2,
					700/2 - RenderableHolder.fontLoader.getFontMetrics(gc.getFont()).getLineHeight() / 2);
		} else {
			for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
				if (entity.isVisible()) {
					entity.draw(gc);
				}
			}
		}
	}

}
