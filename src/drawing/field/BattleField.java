
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
<<<<<<< HEAD
	private static final Set<BattleFieldableEntity<Hero>> entities_holder = new HashSet<>();
	private static final Set<BattleFieldableEntity<Hero>> graveyard = new HashSet<>();
//	public static MonsterDen monsterDen;
||||||| merged common ancestors
	private static final Set<DungeonableEntity<Attribute>> entities_holder = new HashSet<>();
	private static final Set<DungeonableEntity<Attribute>> graveyard = new HashSet<>();
	public static MonsterDen monsterDen;
=======
	private static final Set<BattleFieldableEntity<Hero>> entities_holder = new HashSet<>();
	private static final Set<BattleFieldableEntity<Hero>> graveyard = new HashSet<>();
	public static MonsterGen monsterGen;
>>>>>>> c2ca9cdda72c67dd90723de57a56051dbfb2d146
	private int lvl;
	private static int lvlChangetimer;
	
	public BattleField() {
		super(ResourceLoader.dungeon1, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT - ResourceLoader.statusBar.getHeight(),
				new Pair(0, 150));
		this.lvl = 0;
		this.z = -99999;
<<<<<<< HEAD
//		monsterDen = new MonsterDen();
||||||| merged common ancestors
		monsterDen = new MonsterDen();
=======
		monsterGen = new MonsterGen();
>>>>>>> c2ca9cdda72c67dd90723de57a56051dbfb2d146
		lvlChangetimer = Constant.DUNGEON_CHANGE_TIME_MAX;
	}

	public boolean isInBoarder(Entity e, double x, double y) {
		return (0 - e.getWidth() / 6 <= x && x <= this.width - e.getWidth() * 5 / 6)
				&& (0 <= y && y <= this.height - e.getHeight());
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
<<<<<<< HEAD
//		monsterDen.update();
||||||| merged common ancestors
		monsterDen.update();
=======
		monsterGen.update();
>>>>>>> c2ca9cdda72c67dd90723de57a56051dbfb2d146
		graveyard.clear();
		for (BattleFieldableEntity<Hero> e : entities_holder) {
			e.update();
		}
<<<<<<< HEAD
		for (BattleFieldableEntity<Hero> e : graveyard) {
			if (e instanceof HeroInBat) {
				//SceneManager.BattleFieldScene; // dead
||||||| merged common ancestors
		for (DungeonableEntity<Attribute> e : graveyard) {
			if (e instanceof Hero) {
				SceneManager.BattleFieldScene; // dead
=======
		for (BattleFieldableEntity<Hero> e : graveyard) {
			if (e instanceof HeroInBat) {
				SceneManager.BattleFieldScene; // dead
>>>>>>> c2ca9cdda72c67dd90723de57a56051dbfb2d146
			} else
				entities_holder.remove(e);
		}
//		if (isLevelClear())
//			upLevel();
		if (lvlChangetimer > 0)
			lvlChangetimer--;
	}

<<<<<<< HEAD
//	public boolean isLevelClear() {
//		return (entities_holder.size() == 1 && entities_holder.contains(Logic.GameLogic.heroInBat) && !monsterDen.isGenerate());
//	}
||||||| merged common ancestors
	public boolean isLevelClear() {
		return (entities_holder.size() == 1 && entities_holder.contains(Logic.GameLogic.hero) && !monsterDen.isGenerate());
	}
=======
	public boolean isLevelClear() {
		return (entities_holder.size() == 1 && entities_holder.contains(Logic.GameLogic.hero) && !monsterGen.isGenerate());
	}
>>>>>>> c2ca9cdda72c67dd90723de57a56051dbfb2d146

	private void upLevel() {
		if (lvlChangetimer == 0) {
			lvl++;
			lvlChangetimer = 400;
<<<<<<< HEAD
//			monsterDen.setDunLvl(this.lvl);
||||||| merged common ancestors
			monsterDen.setDunLvl(this.lvl);
=======
			monsterGen.setDunLvl(this.lvl);
>>>>>>> c2ca9cdda72c67dd90723de57a56051dbfb2d146
		}
	}

	public void restart() {
		lvl = 0;
		lvlChangetimer = 0;
<<<<<<< HEAD
//		monsterDen.restart();
||||||| merged common ancestors
		monsterDen.restart();
=======
		monsterGen.restart();
>>>>>>> c2ca9cdda72c67dd90723de57a56051dbfb2d146
		RenderableHolder.getInstance().clear();
		entities_holder.clear();
		graveyard.clear();
	}

	public static Set<BattleFieldableEntity<Hero>> getEntitiesHolder() {
		return entities_holder;
	}

<<<<<<< HEAD
	public static ArrayList<BattleFieldableEntity<Hero>> getEntityInArea(GameObject object, double x, double y) {
		ArrayList<BattleFieldableEntity<Hero>> result = new ArrayList<>();
		for (BattleFieldableEntity<Hero> e : entities_holder) {
			if (e.hashCode() != object.hashCode() && ((Entity) object).isCollide(e, x, y)) {
||||||| merged common ancestors
	public static ArrayList<DungeonableEntity<Attribute>> getEntityInArea(GameObject object, double x, double y) {
		ArrayList<DungeonableEntity<Attribute>> result = new ArrayList<>();
		for (DungeonableEntity<Attribute> e : entities_holder) {
			if (e.hashCode() != object.hashCode() && object.isCollide(e, x, y)) {
=======
	public static ArrayList<BattleFieldableEntity<Hero>> getEntityInArea(GameObject object, double x, double y) {
		ArrayList<BattleFieldableEntity<Hero>> result = new ArrayList<>();
		for (BattleFieldableEntity<Hero> e : entities_holder) {
			if (e.hashCode() != object.hashCode() && object.isCollide(e, x, y)) {
>>>>>>> c2ca9cdda72c67dd90723de57a56051dbfb2d146
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
