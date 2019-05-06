
package drawing.field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.GameObject;
import SharedObject.Pair;
import SharedObject.RenderableHolder;
import SharedObject.ResourceLoader;
import creature.entity.BattleFieldableEntity;
import creature.entity.Entity;
import creature.entity.HeroInBat;
import creature.hero.Hero;
import creature.monster.MonsterGen;
import drawing.manager.SceneManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BattleField extends Field {
	private static final Set<BattleFieldableEntity<Hero>> entities_holder = new HashSet<>();
	private static final Set<BattleFieldableEntity<Hero>> graveyard = new HashSet<>();
//	public static MonsterDen monsterDen;

	private int lvl;
	private static int lvlChangetimer;
	
	public BattleField() {
		super(ResourceLoader.dungeon1, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT,
				new Pair(0, 0));
		this.lvl = 0;
		this.z = -99999;
//		monsterDen = new MonsterDen();
		lvlChangetimer = Constant.DUNGEON_CHANGE_TIME_MAX;
	}

	public boolean isInBoarder(Entity e, double x, double y) {
		return (0 - e.getWidth() / 6 <= x && x <= this.width - e.getWidth() * 5 / 6)
				&& (150 <= y && y <= this.height - e.getHeight());
	}

	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc); 
		if (lvlChangetimer != 0) {
			gc.setGlobalAlpha(0.6);
			gc.setFill(Color.BLACK);
			gc.fillRect(0, height / 2 - 50, width, 100 + ResourceLoader.fontLoader.getFontMetrics(gc.getFont()).getLineHeight());
			gc.setFill(Color.WHITE);
			gc.fillText("LEVEL " + lvl,
					(width - ResourceLoader.fontLoader.computeStringWidth("LEVEL " + lvl, gc.getFont()) )/ 2,
					(height + ResourceLoader.fontLoader.getFontMetrics(gc.getFont()).getLineHeight() )/ 2);
			gc.setGlobalAlpha(1.0);
		}
	}

	public static void addEntities(BattleFieldableEntity<Hero> e) {
		RenderableHolder.getInstance().add(e);
		getEntitiesHolder().add(e);
		if (lvlChangetimer != 0)
			lvlChangetimer = 0;
	}

	public static <T extends Hero> void destroyEntities(BattleFieldableEntity<T> e) {
		e.setVisible(false);
		graveyard.add((BattleFieldableEntity<Hero>) e);
	}

	public void update() {

//		monsterDen.update();

		graveyard.clear();
		for (BattleFieldableEntity<Hero> e : entities_holder) {
			e.update();
		}

		for (BattleFieldableEntity<Hero> e : graveyard) {
			if (e instanceof HeroInBat) {
				//SceneManager.BattleFieldScene; // dead

			} else
				entities_holder.remove(e);
		}
//		if (isLevelClear())
//			upLevel();
		if (lvlChangetimer > 0)
			lvlChangetimer--;
	}


	public boolean isLevelClear() {
		return false;
//		return (entities_holder.size() == 1 && entities_holder.contains(Logic.GameLogic.heroInBat) && !monsterDen.isGenerate());
	}


	private void upLevel() {
		if (lvlChangetimer == 0) {
			lvl++;
			lvlChangetimer = 400;
//			monsterDen.setDunLvl(this.lvl);

		}
	}

	public void restart() {
		lvl = 0;
		lvlChangetimer = 0;
//		monsterDen.restart();
		RenderableHolder.getInstance().clear();
		entities_holder.clear();
		graveyard.clear();
	}

	public static Set<BattleFieldableEntity<Hero>> getEntitiesHolder() {
		return entities_holder;
	}
 
	public static ArrayList<BattleFieldableEntity<Hero>> getEntityInArea(GameObject object, double x, double y) {
		ArrayList<BattleFieldableEntity<Hero>> result = new ArrayList<>();
		for (BattleFieldableEntity<Hero> e : entities_holder) {
			if (e.hashCode() != object.hashCode() && object.isCollide(e, x, y)) {
				result.add(e);
			}
		}
		return result;
	}

	public int getLvl() {
		return lvl;
	}

	public static int getLvlChangetimer() {
		return lvlChangetimer;
	}
}
