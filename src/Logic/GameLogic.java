package Logic;

import SharedObject.Constant;
import SharedObject.RenderableHolder;
<<<<<<< HEAD
import creature.hero.Hero;
||||||| merged common ancestors
import creature.Hero;
=======
import creature.Hero;
import drawing.field.Navigation;
>>>>>>> b620ca6ede30795c2fca328c92d1d4710f2b19c1

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
