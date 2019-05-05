package Logic;

import SharedObject.Constant;
import SharedObject.RenderableHolder;
import creature.entity.HeroInBat;
import creature.hero.Hero;
import drawing.field.BattleField;
import drawing.field.StatusBar;

public class GameLogic {
	public static BattleField battleField;
	public static HeroInBat heroInBat;
	public static StatusBar status;

	public GameLogic() {
		battleField = new BattleField();
		RenderableHolder.getInstance().add(battleField);
		status = new StatusBar();
		RenderableHolder.getInstance().add(status);
	}
	
	public <T extends Hero>void newHero(T atkType) {
		heroInBat = new HeroInBat(Constant.ENTITY_FRONT, atkType);
		BattleField.addEntities(heroInBat);
	}

	public void logicUpdate() {
		battleField.update();
		status.update();
	}
}