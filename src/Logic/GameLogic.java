package Logic;

import SharedObject.Constant;
import SharedObject.RenderableHolder;
import creature.hero.Hero;

public class GameLogic {
	public static BattleField battleField;
	public static Hero hero;
	public static Navigation navig;

	public GameLogic() {
		battleField = new BattleField();
		RenderableHolder.getInstance().add(battleField);
		navig = new Navigation();
		RenderableHolder.getInstance().add(navig);
	}
	
	public <T extends Hero>void newHero(T atkType) {
		hero = new Hero(Constant.ENTITY_FRONT, atkType);
		BattleField.addEntities(hero);
	}

	public void logicUpdate() {
		battleField.update();
		navig.update();
	}
}
