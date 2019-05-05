package Logic;

import SharedObject.Constant;
import SharedObject.RenderableHolder;
import creature.Hero;
import drawing.field.Navigation;

public class GameLogic {
	public static Dungeon dungeon;
	public static Hero hero;
	public static Navigation navig;

	public GameLogic() {
		dungeon = new Dungeon();
		RenderableHolder.getInstance().add(dungeon);
		navig = new Navigation();
		RenderableHolder.getInstance().add(navig);
	}
	
	public <T extends Hero>void newHero(T atkType) {
		hero = new Hero(Constant.ENTITY_FRONT, atkType);
		Dungeon.addEntities(hero);
	}

	public void logicUpdate() {
		dungeon.update();
		navig.update();
	}
}
