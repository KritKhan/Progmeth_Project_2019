package drawing.select;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class SelectScene extends Scene{
	private StackPane root;
	private Canvas canvas;

	public SelectScene() {
		super(new StackPane());
		root = (StackPane) this.getRoot();
		canvas = new SelectCanvas();
		
	}
	

}
