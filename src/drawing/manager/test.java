package drawing.manager;

import SharedObject.RenderableHolder;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class test {

	public void start(Stage stage) {
		StackPane root = new StackPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		Canvas canvas = new Canvas(400,200);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		WritableImage heroFace = new WritableImage(RenderableHolder.magicianFace.getPixelReader(), 64, 0,32, 32);
		gc.drawImage(heroFace, 40, 100);
		root.getChildren().add(canvas);
	}
}
