package drawing.home;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class HomeScene extends Scene {
	private StackPane root;
	private HomeCanvas canvas;

	public HomeScene() {
		super(new StackPane());
		root = (StackPane) this.getRoot();
		canvas = new HomeCanvas();
		root.getChildren().add(canvas);
		canvas.requestFocus();
	}

	public HomeCanvas getHomeCanvas() {
		return canvas;
	}

}
