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
import creature.entity.Entity;
import creature.hero.Hero;
import drawing.manager.SceneManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BattleField extends Field {
	private static final Set<DungeonableEntity<Attribute>> entities_holder = new HashSet<>();
	private static final Set<DungeonableEntity<Attribute>> graveyard = new HashSet<>();
	public static MonsterDen monsterDen;
	private int lvl;
	private static int lvlChangetimer;
	
	public BattleField() {
		super(ResourceLoader.dungeon1, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT - ResourceLoader.statusBar.getHeight(),
				new Pair(0, 150));
		this.lvl = 0;
		this.z = -99999;
		monsterDen = new MonsterDen();
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

	public static void addEntities(DungeonableEntity<Attribute> e) {
		RenderableHolder.getInstance().add(e);
		getEntitiesHolder().add(e);
		if (lvlChangetimer != 0)
			lvlChangetimer = 0;
	}

	public static <T extends Attribute> void destroyEntities(DungeonableEntity<T> e) {
		e.setVisible(false);
		graveyard.add((DungeonableEntity<Attribute>) e);
	}

	public void update() {
		monsterDen.update();
		graveyard.clear();
		for (DungeonableEntity<Attribute> e : entities_holder) {
			e.update();
		}
		for (DungeonableEntity<Attribute> e : graveyard) {
			if (e instanceof Hero) {
				SceneManager.BattleFieldScene; // dead
			} else
				entities_holder.remove(e);
		}
		if (isLevelClear())
			upLevel();
		if (lvlChangetimer > 0)
			lvlChangetimer--;
	}

	public boolean isLevelClear() {
		return (entities_holder.size() == 1 && entities_holder.contains(Logic.GameLogic.hero) && !monsterDen.isGenerate());
	}

	private void upLevel() {
		if (lvlChangetimer == 0) {
			lvl++;
			lvlChangetimer = 400;
			monsterDen.setDunLvl(this.lvl);
		}
	}

	public void restart() {
		lvl = 0;
		lvlChangetimer = 0;
		monsterDen.restart();
		RenderableHolder.getInstance().clear();
		entities_holder.clear();
		graveyard.clear();
	}

	public static Set<DungeonableEntity<Attribute>> getEntitiesHolder() {
		return entities_holder;
	}

	public static ArrayList<DungeonableEntity<Attribute>> getEntityInArea(GameObject object, double x, double y) {
		ArrayList<DungeonableEntity<Attribute>> result = new ArrayList<>();
		for (DungeonableEntity<Attribute> e : entities_holder) {
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
