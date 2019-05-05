package drawing.field;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.InputUtility;
import SharedObject.Pair;
import SharedObject.ResourceLoader;
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
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(0)).getAmount()), 555, 105);
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(1)).getAmount()), 640, 105);
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(3)).getAmount()), 725, 105);
	
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
	
		//name
		gc.setFont(Font.font("Castellar", 25));
		gc.fillText(name, 75, 125);
		
		//money
		gc.setFont(Font.font("Castellar", 25));
		gc.fillText(Double.toString(Logic.GameLogic.heroInBat.getMoney()), 855, Constant.SCENE_HEIGHT - 30);
	
		// Hp & Mp
		double maxH = GameLogic.heroInBat.getMaxHp();
		double currentH = GameLogic.heroInBat.getCurrentHp();
		double maxM = GameLogic.heroInBat.getMaxMp();
		double currentM = GameLogic.heroInBat.getCurrentMp();
		
		gc.setFill(Color.CRIMSON);
		gc.fillRect(236, 47, Math.min((currentH / maxH),1) * 247, 15);

		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(236, 89, Math.min((currentM / maxM),1) * 247, 15);


		gc.drawImage(GameLogic.heroInBat.getAtkType().getImage(), 45, 567, 91, 91);

	}
	
	public void update() {
		// TODO Auto-generated method stub
		double xPos = InputUtility.mouseX;
		double yPos = InputUtility.mouseY;

		if ((xPos >= 529 && xPos <= 590 && yPos >= 30 && yPos <= 90 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.F1))) {
			//use HpPotion
			try {
				inventory.use(Inventory.getInventory().get(0));
			}Catch (//out of potion){
				
			}
		} else if ((xPos >= 608 && xPos <= 669 && yPos >= 30 && yPos <= 90 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.F2))) {
			//use MpPotion
			try {
				inventory.use(Inventory.getInventory().get(1));
			}Catch (//out of potion){
				
			}
		} else if ((xPos >= 691 && xPos <= 752 && yPos >= 30 && yPos <= 90 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.F3))) {
			//use MixPotion
			try {
				inventory.use(Inventory.getInventory().get(2));
			}Catch (//out of potion){
		
			}
		} else if (xPos >= 811 && xPos <= 871 && yPos >= 27 && yPos <= 92 && InputUtility.isMouseClick())
			|| (InputUtility.isKeyPressed(KeyCode.B))) {
				InputUtility.mouseX = 0;
				InputUtility.mouseY = 0;
				ResourceLoader.clickSound.play(100);
				//open shop
		} else if (xPos >= 545 && xPos <= 585 && yPos >= 576 && yPos <= 615 && InputUtility.isMouseClick()) {
			|| (InputUtility.isKeyPressed(KeyCode.ENTER))) {
				InputUtility.mouseX = 0;
				InputUtility.mouseY = 0;
				ResourceLoader.clickSound.play(100);
				//open pause
		}
	}
}
