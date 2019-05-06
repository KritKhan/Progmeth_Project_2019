package drawing.field;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.InputUtility;
import SharedObject.Pair;
import SharedObject.ResourceLoader;
import creature.entity.HeroInBat;
import drawing.manager.SceneManager;
import item.Inventory;
import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class StatusBar extends Field {
	private Inventory inventory = new Inventory();
	private static String name;
	
	public StatusBar() {
		super(ResourceLoader.statusBar,ResourceLoader.statusBar.getWidth(),ResourceLoader.statusBar.getHeight(),
				new Pair(0,150));
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
		gc.drawImage(ResourceLoader.statusBar, 0, 0);
		
		gc.setFill(Color.MEDIUMSLATEBLUE);
		gc.setFont(Font.font("Castellar",15));
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(0)).getAmount()), 555, 110);
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(1)).getAmount()), 637, 110);
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(3)).getAmount()), 720, 110);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
	
		//name
		gc.setFont(Font.font("Castellar", 20));
		gc.fillText(GameLogic.heroInBat.getName(), 60, 132);
		
		//money
		gc.setFont(Font.font("Castellar", 20));
		gc.fillText(Double.toString(Logic.GameLogic.heroInBat.getMoney()), 855, 130);
	
		// Hp & Mp
		double maxH = GameLogic.heroInBat.getMaxHp();
		double currentH = GameLogic.heroInBat.getCurrentHp();
		double maxM = GameLogic.heroInBat.getMaxMp();
		double currentM = GameLogic.heroInBat.getCurrentMp();
		
		gc.setFill(Color.CRIMSON);
		gc.fillRect(236, 47, Math.min((currentH / maxH),1) * 246, 15);

		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(236, 89, Math.min((currentM / maxM),1) * 246, 15);

		gc.drawImage(GameLogic.heroInBat.getAtkType().getImage(), 38, 11, 102, 100);

	}
	
	public void update() {
		// TODO Auto-generated method stub
		double xPos = InputUtility.mouseX;
		double yPos = InputUtility.mouseY;
		if ((xPos >= 529 && xPos <= 590 && yPos >= 30 && yPos <= 90 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.F1))) {
			//use HpPotion
				inventory.use(Inventory.getInventory().get(0));
		} else if ((xPos >= 608 && xPos <= 669 && yPos >= 30 && yPos <= 90 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.F2))) {
			//use MpPotion
				inventory.use(Inventory.getInventory().get(1));
		} else if ((xPos >= 691 && xPos <= 752 && yPos >= 30 && yPos <= 90 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.F3))) {
			//use MixPotion
				inventory.use(Inventory.getInventory().get(2));
		} else if ((xPos >= 811 && xPos <= 876 && yPos >= 27 && yPos <= 92 && InputUtility.isMouseClick())
			|| (InputUtility.isKeyPressed(KeyCode.ENTER))) {
				InputUtility.mouseX = 0;
				InputUtility.mouseY = 0;
				//ResourceLoader.clickSound.play(100);
				//open setting
				System.out.println("setting open");
		} else if ((xPos >= 545 && xPos <= 590 && yPos >= 27 && yPos <= 92 && InputUtility.isMouseClick())
			|| (InputUtility.isKeyPressed(KeyCode.B))) {
				InputUtility.mouseX = 0;
				InputUtility.mouseY = 0;
				//ResourceLoader.clickSound.play(100);
				//open shop
				System.out.println("shop open");
		}
	}
}
