package creature.entity;

import java.util.ArrayList;

import SharedObject.Constant;
import SharedObject.ForceUtility;
import creature.hero.Hero;
import drawing.field.BattleField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class BattleFieldableEntity<T extends Hero> extends Entity {

	protected double maxHp;
	protected double currentHp;
	protected int baseAtk;
	protected T atkType;
	protected int[] damageTake;
	protected boolean struct;
	protected int dmgTimer;
	private int hpBarTimer;
	private ArrayList<Integer> dmgTimerDelay;
	private int dmg;

	public BattleFieldableEntity(double x, double y, Image img, int row, int col, int direction, int movespeed,
			int mass, int maxHp, int baseAtk, T atkType) {
		super(x, y, img, row, col, direction, movespeed, mass);
		this.atkType = atkType;
		this.atkType.setOwner((BattleFieldableEntity<Hero>) this); // add the entity to list
		this.baseAtk = baseAtk;
		this.mass = mass;
		this.maxHp = maxHp;
		this.damageTake = new int[4];
		this.currentHp = getMaxHp();
		this.dmgTimer = 0;
		this.hpBarTimer = 0;
		dmgTimerDelay = new ArrayList<>();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (!(this instanceof HeroInBat) && (dmgTimer != 0 || hpBarTimer != 0)) {
			// draw hp bar of monsters
			gc.setFill(Color.BLACK);
			gc.fillRect(pos.x + this.getWidth() / 6, pos.y - this.getHeight() / 6, this.getWidth() * 5 / 6, 8);
			gc.setFill(Color.DARKRED);
			gc.fillRect(pos.x + this.getWidth() / 6, pos.y - this.getHeight() / 6,
					(getCurrentHp() / getMaxHp()) * this.getWidth() * 5 / 6, 8);
			hpBarTimer--;
		}
		if (!dmgTimerDelay.isEmpty()) {
			// draw damage to hero
			gc.setFill(Color.RED);
			for (int i = 0; i < dmgTimerDelay.size(); i++) {
				gc.fillText("-" + Integer.toString(dmg), pos.x + getWidth() / 3,
						pos.y + getHeight() / 3 - (50 - dmgTimerDelay.get(i)));
				dmgTimerDelay.set(i, dmgTimerDelay.get(i) - 1);
				if (dmgTimerDelay.get(i) == 0)
					dmgTimerDelay.remove(i);
				else if (dmgTimerDelay.size() >= 10 && dmgTimerDelay.get(i) < 20)
					dmgTimerDelay.remove(i);
			}
		}
		if (dmgTimer % 5 == 0)
			super.draw(gc);
	}

	public boolean attack() {
		if (atkType.getAttackTime() == 0) {
			ArrayList<BattleFieldableEntity<Hero>> inArea = BattleField.getEntityInArea(atkType.getAttackObj(),
					atkType.getAttackObj().getX(), atkType.getAttackObj().getY());
			if (inArea == null || inArea.size() <= 1)
				return false;
			for (BattleFieldableEntity<Hero> other : inArea) {
				if (other.hashCode() != this.hashCode() && this.race != other.race) {
					atkType.use(); // use mana
					atkType.attack(this, other);
				}
			}

			return true;
		}
		return false; // if attackTime != 0 -> it can't attack to other
	}

	public void damage(int dmg, int direction) {
		this.dmg = dmg;
		if (dmgTimerDelay.size() <= 20)
			dmgTimerDelay.add(50);
		dmgTimer = Constant.DMG_TIME_MAX;
		this.damageTake[direction] += ForceUtility.<T>calculateForce(dmg, getAxis(direction), this);
		this.currentHp = this.currentHp - dmg >= 0 ? this.currentHp - dmg : 0;
		if (this.currentHp == 0)
			isAlive = false;
		ForceUtility.reactionEffect(this, direction);
		this.hpBarTimer = 180;
	}

	public double getMaxHp() {
		return maxHp * atkType.getHpMultiply();
	}

	public double getCurrentHp() {
		return currentHp;
	}

	public int[] getDamageTake() {
		return damageTake;
	}

	public int getDmgTimer() {
		return dmgTimer;
	}

	public int getBaseAtk() {
		return baseAtk;
	}

	public T getAtkType() {
		return atkType;
	}

	@Override
	public void update() {
		if (!isAlive || this.currentHp == 0) { // if entity is dead -> delete it ; if not ->
			BattleField.destroyEntities(this);
			this.atkType.getAttackObj().setVisible(false);
		} else if (isAlive) {
			dmgTimer = dmgTimer == 0 ? 0 : dmgTimer - 1;
		}
		this.atkType.update(this.direction, this.pos.x, this.pos.y); // update the position of hero
	}

}
