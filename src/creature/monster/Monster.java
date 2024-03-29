package creature.monster;

import java.util.ArrayList;

import Interface.Obstructable;
import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.RandomUtility;
import creature.entity.BattleFieldableEntity;
import creature.hero.Hero;
import drawing.field.BattleField;
import javafx.scene.image.Image;

public class Monster extends BattleFieldableEntity<Hero> implements Obstructable {
	private int idleParameter;
	private int timidParameter;
	private int persistentParameter;
	private int eyesight;
	private int bounty;
	private int rand;
	private double size;
	private int count;

	public Monster(Image img, int row, int column, int movespeed, int mass, int maxHp, int baseAtk, Hero atkType,
			int idleParameter, int timidParaneter, int persistentParameter, int eyesight, int bounty, double size) {
		super(RandomUtility.randomInt((int) (Constant.SCENE_WIDTH * 0.01), (int) (Constant.SCENE_WIDTH * 0.9)),
				RandomUtility.randomInt((int) (Constant.SCENE_HEIGHT * 0.01),
						(int) ((Constant.SCENE_HEIGHT - Constant.STATUSBAR_HEIGHT) * 0.8)),
				img, row, column, Constant.ENTITY_FRONT, movespeed / 2, mass, maxHp, baseAtk, atkType);
		this.idleParameter = idleParameter;
		this.timidParameter = timidParaneter;
		this.persistentParameter = persistentParameter;
		this.eyesight = eyesight;
		this.bounty = bounty;
		this.size = size;
		this.count = idleParameter;
		this.race = Constant.ENTITY_MONSTER;
	}

	@Override
	public double getWidth() {
		return super.getWidth() * size;
	}

	@Override
	public double getHeight() {
		return super.getHeight() * size;
	}

	private int heroDirection() {
		double dx = GameLogic.heroInBat.getX();
		double dy = GameLogic.heroInBat.getY();
		if (pos.diffD(dx, dy) >= eyesight)
			return RandomUtility.randomByPercent(rand, this.direction, 98);
		if (pos.diffX(dx) > pos.diffY(dy)) {
			return (pos.x - dx) >= 0 ? Constant.ENTITY_LEFT : Constant.ENTITY_RIGHT;
		} else {
			return (pos.y - dy) >= 0 ? Constant.ENTITY_BACK : Constant.ENTITY_FRONT;
		}
	}

	@Override
	public void update() {
		if (!isAlive || this.currentHp == 0) {
			BattleField.destroyEntities(this);
			this.atkType.getAttackObj().setVisible(false);
			GameLogic.heroInBat.earnMoney(bounty);
		} else if ((isAlive) && (dmgTimer == 0) && (count < idleParameter / 2)) {
			rand = RandomUtility.randomInt(0, 100);
			if (((rand) % 100 < persistentParameter / 100) || (dmgTimer > 1000 / persistentParameter))
				move(heroDirection());
			else
				move(RandomUtility.randomByPercent(rand, this.direction, 98));
			attack();
			if (isBlock(pos.x, pos.y))
				struct = true;
			else
				struct = false;
		}
		if (isAlive) {
			this.atkType.update(this.direction, this.pos.x, this.pos.y);
			dmgTimer = dmgTimer == 0 ? 0 : dmgTimer - 1;
			count = count <= 1 ? idleParameter : count - 10 / timidParameter;
		}
	}

	@Override
	protected boolean isBlock(double x, double y) {
		if (struct)
			return false;
		ArrayList<BattleFieldableEntity<Hero>> inArea = BattleField.getEntityInArea(this, x, y);
		for (BattleFieldableEntity<Hero> other : inArea) {
			if (this.race != other.getRace())
				other.damage(this.getBaseAtk(), other.getDirection());
			return true;
		}
		return false;
	}

}
