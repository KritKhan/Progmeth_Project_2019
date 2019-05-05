package Logic;

import SharedObject.Constant;
import SharedObject.RenderableHolder;
import creature.hero.Hero;
import drawing.field.BattleField;
import drawing.field.StatusBar;

public class GameLogic {
	public static BattleField battleField;
	public static Hero hero;
	public static StatusBar statusBar;

	public GameLogic() {
		battleField = new BattleField();
		RenderableHolder.getInstance().add(battleField);
		statusBar = new StatusBar();
		RenderableHolder.getInstance().add(statusBar);
	}
	
	public <T extends Hero>void newHero(T atkType) {
		hero = new Hero(Constant.ENTITY_FRONT, atkType);
		BattleField.addEntities(hero);
	}

	public void logicUpdate() {
		battleField.update();
		statusBar.update();
	}
}
