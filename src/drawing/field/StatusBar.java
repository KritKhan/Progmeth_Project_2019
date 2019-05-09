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
import main.BattleFieldMain;

public class StatusBar extends Field {
	private Inventory inventory = new Inventory();
	private static String name;
	private FontLoader fontloader = ResourceLoader.fontLoader;

	public StatusBar() {
		super(ResourceLoader.statusBar, Constant.STATUSBAR_WIDTH, Constant.STATUSBAR_HEIGHT,
				new Pair(0, Constant.SCENE_HEIGHT - Constant.STATUSBAR_HEIGHT));
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
		gc.drawImage(ResourceLoader.statusBar, 0, 550);

		gc.setFill(Color.MEDIUMSLATEBLUE);
		gc.setFont(Font.font("Castellar", 15));
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(0)).getAmount()), 555, 660);
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(1)).getAmount()), 638, 660);
		gc.fillText(Integer.toString(((Item) Inventory.getInventory().get(2)).getAmount()), 721, 660);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();

		// name

		gc.setFont(Font.font("Castellar", 20));
		double font_width = fontLoader.computeStringWidth(GameLogic.heroInBat.getName(), gc.getFont());
		gc.fillText(GameLogic.heroInBat.getName(), 89 - font_width / 2, 682);

		// money
		gc.setFont(Font.font("Castellar", 20));
		gc.fillText(Double.toString(Logic.GameLogic.heroInBat.getMoney()), 855, 680);

		// Hp & Mp
		double maxH = GameLogic.heroInBat.getMaxHp();
		double currentH = GameLogic.heroInBat.getCurrentHp();
		double maxM = GameLogic.heroInBat.getMaxMp();
		double currentM = GameLogic.heroInBat.getCurrentMp();

		gc.setFill(Color.CRIMSON);
		gc.fillRect(236, 597, Math.min((currentH / maxH), 1) * 246, 15);

		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(236, 89 + 550, Math.min((currentM / maxM), 1) * 246, 15);

		gc.drawImage(GameLogic.heroInBat.getAtkType().getImage(), 38, 561, 102, 100);

	}

	public void update() {
		// TODO Auto-generated method stub
		double xPos = InputUtility.mouseX;
		double yPos = InputUtility.mouseY;

		if ((xPos >= 529 && xPos <= 590 && yPos >= 580 && yPos <= 640 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.DIGIT1))) {
			// use HpPotion
			ResourceLoader.click.play(100);
			inventory.use(Inventory.getInventory().get(0));
		} else if ((xPos >= 608 && xPos <= 669 && yPos >= 580 && yPos <= 640 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.DIGIT2))) {
			// use MpPotion
			ResourceLoader.click.play(100);
			inventory.use(Inventory.getInventory().get(1));
		} else if ((xPos >= 691 && xPos <= 752 && yPos >= 30 + 550 && yPos <= 90 + 550 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.DIGIT3))) {
			// use MixPotion
			ResourceLoader.click.play(100);
			inventory.use(Inventory.getInventory().get(2));
		} else if (xPos >= Constant.SCENE_WIDTH - 192 && xPos <= Constant.SCENE_WIDTH - 124 && yPos >= 575
				&& yPos <= 645 && InputUtility.isMouseClick() || (InputUtility.isKeyPressed(KeyCode.B))) {
			InputUtility.mouseX = 0;
			InputUtility.mouseY = 0;
			BattleFieldMain.stop();
			ResourceLoader.click.play(100);
			SceneManager.BattleFieldScene.toDialog(0);
		} else if (xPos >= Constant.SCENE_WIDTH - 106 && xPos <= Constant.SCENE_WIDTH - 38 && yPos >= 575 && yPos <= 645
				&& InputUtility.isMouseClick() || (InputUtility.isKeyPressed(KeyCode.ENTER))) {
			InputUtility.mouseX = 0;
			InputUtility.mouseY = 0;
			BattleFieldMain.stop();
			ResourceLoader.click.play(100);
			// open pause
			SceneManager.BattleFieldScene.toDialog(1);
		}
	}
}