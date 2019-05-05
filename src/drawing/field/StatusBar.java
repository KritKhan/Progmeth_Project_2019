package drawing.field;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import SharedObject.Constant;
import SharedObject.Pair;
import SharedObject.ResourceLoader;
import item.Inventory;
import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;
import utility.InputUtility;
import view.SceneManeger;


public class StatusBar extends Field {
	
	private static String name;
	
	public StatusBar() {
		super(ResourceLoader.statusBar,ResourceLoader.statusBar.getWidth(),ResourceLoader.statusBar.getHeight(),
				new Pair(0,Constant.SCENE_HEIGHT-ResourceLoader.statusBar.getHeight()));
		this.z = 2000;
	}
	
	public static void setName(String name) {
		StatusBar.name = name;
	}

	public static String getName() {
		return name;
	}

	public boolean isInBorder(double x, double y) {
		return isInBorderX(x) && isInBorderY(y);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(ResourceLoader.statusBar, 0, Constant.SCENE_HEIGHT-ResourceLoader.statusBar.getHeight());
		
		gc.setFill(Color.MEDIUMSLATEBLUE);
		gc.setFont(Font.font("Castellar",15));
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(0)).getAmount()), 555, 655);
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(1)).getAmount()), 640, 655);
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(3)).getAmount()), 725, 655);
	
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
	
		//name
		gc.setFont(Font.font("Castellar", 25));
		gc.fillText(name, 75, 575);
		
		//money
		gc.setFont(Font.font("Castellar", 25));
		gc.fillText(Double.toString(Logic.GameLogic.hero.getCoin()), 855, Constant.SCENE_HEIGHT - 30);
	
		// Hp & Mp
		double maxH = Logic.GameLogic.hero.getMaxHp();
		double currentH = Logic.GameLogic.hero.getHp();
		double maxM = Logic.GameLogic.hero.getMaxMp();
		double currentM = Logic.GameLogic.hero.getMp();
		
		gc.setFill(Color.CRIMSON);
		gc.fillRect(236, 598, Math.min((currentH / maxH),1) * 247, 15);

		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(236, 639, Math.min((currentM / maxM),1) * 247, 15);

		gc.drawImage(Logic.GameLogic.hero.getName().getImage(), 45, 567, 91, 91);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		double xPos = InputUtility.mouseX;
		double yPos = InputUtility.mouseY;

		if ((xPos >= 704 && xPos <= 741 && yPos >= 575 && yPos <= 615 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.I))) {
			InputUtility.mouseX = 0;
			InputUtility.mouseY = 0;
			ResourceLoader.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(1);
		} else if ((xPos >= 762 && xPos <= 800 && yPos >= 575 && yPos <= 615 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.B))) {
			InputUtility.mouseX = 0;
			InputUtility.mouseY = 0;
			ResourceLoader.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(2);
		} else if ((xPos >= 823 && xPos <= 861 && yPos >= 575 && yPos <= 615 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.ESCAPE))) {
			InputUtility.mouseX = 0;
			InputUtility.mouseY = 0;
			ResourceLoader.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(3);
		} else if (xPos >= 484 && xPos <= 527 && yPos >= 576 && yPos <= 615 && InputUtility.isMouseClick()) {
			Inventory.getBag()[0].use();
		} else if (xPos >= 545 && xPos <= 585 && yPos >= 576 && yPos <= 615 && InputUtility.isMouseClick()) {
			Inventory.getBag()[1].use();
		}
	}
}
