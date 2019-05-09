package drawing.select;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class SelectScene extends Scene {
	public StackPane root;
	private SelectCanvas canvas;

	public SelectScene() {
		super(new StackPane());
		root = (StackPane) this.getRoot();
		canvas = new SelectCanvas();
		root.getChildren().add(canvas);
		canvas.requestFocus();
		
	}

	public SelectCanvas getSelectCanvas() {
		return canvas;
	}

}
