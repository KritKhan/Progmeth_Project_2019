package SharedObject;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class InputUtility {
	public static double mouseX,mouseY;
	public static boolean mouseOnSceen = false;
	private static Set<KeyCode> keyPressed = new HashSet<>();
	private static boolean mousePressed = false;
	private static int mouseClick = 0;
	private static boolean mouseRightPressed = false;
	
	//set keyboard
	private static void addKey(KeyCode code) {
		keyPressed.add(code);
	}
	
	private static void removeKey(KeyCode code) {
		keyPressed.remove(code);
	}
	
	//get key
	public static boolean isKeyPressed(KeyCode code) {
		return keyPressed.contains(code);
	}
	
	//get mouse
	public static boolean isMousePressed() {
		return mousePressed;
	}
	
	public static boolean isMouseClick() {
		if(mousePressed) mouseClick++;
		else mouseClick = 0;
		return mouseClick == 1;
	}
	
	public static boolean isMouseRightPresed() {
		return mouseRightPressed;
	}
	
	//set common Listener on scene
	public static void bindListeners(Scene scene) {
		scene.setOnKeyPressed((KeyEvent e) -> addKey(e.getCode()));
		scene.setOnKeyReleased((KeyEvent e) -> removeKey(e.getCode()));
		scene.setOnMousePressed((MouseEvent e) -> {
			if(e.getButton() == MouseButton.PRIMARY) {
				mousePressed = true;
			}else if( e.getButton()== MouseButton.SECONDARY) {
				mouseRightPressed = true;
			}
		});
		scene.setOnMouseReleased((MouseEvent e) -> {
			if(e.getButton() == MouseButton.PRIMARY) {
				mousePressed = false;
			}
			if(e.getButton() == MouseButton.SECONDARY) {
				mouseRightPressed = false;
			}
		});
	}
	
	//set unique listener on some canvas
	public static void bindMouseOnListeners(Canvas canvas) {
		canvas.setOnMouseDragEntered((MouseEvent e) -> mouseOnSceen = true);
		canvas.setOnMouseExited((MouseEvent e) -> mouseOnSceen = false);
		canvas.setOnMouseMoved((MouseEvent e) -> {
			if(mouseOnSceen) {
				mouseX = e.getSceneX();
				mouseY = e.getSceneY();
			}
		});
	}
}
