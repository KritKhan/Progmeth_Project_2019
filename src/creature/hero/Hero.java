package creature.hero;

import Interface.Regenable;
import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.ForceUtility;
import SharedObject.GameObject;
import SharedObject.Pair;
import SharedObject.RenderableHolder;
import creature.entity.BattleFieldableEntity;
import item.Inventory;
import javafx.scene.image.Image;

public abstract class Hero {

	protected GameObject attackObj;
	protected double attackMultiply;
	protected Pair attackRange;
	protected double attackSpeed;
	protected double hpMultiply;
	protected int hpRegen;
	protected int mpRegen;
	protected int attackTime;
	protected BattleFieldableEntity<Hero> owner;
	protected Image animationImg;
	protected Image heroImage;

	public Hero() {
		attackTime = 0;
	}
	
	public void update(int direction, double x, double y) {
		if (attackTime > 0)
			attackTime--;
		if (this.owner.isDestroyed())
			this.attackObj.setVisible(false);
	};

	public <T1 extends Hero, T2 extends Hero> void attack(BattleFieldableEntity<T1> attacker,
			BattleFieldableEntity<T2> other) {
		if (other.getDmgTimer() < Constant.DMG_TIME_MAX / 2) {
			other.damage((int) (attacker.getBaseAtk() * attackMultiply * ((GameLogic.battleField.getLvl() / 20) + 1)),
					ForceUtility.calculateDirection(attacker.getDirection()));
		}
	}

	public GameObject getAttackObj() {
		return attackObj;
	}

	public void setAttackObj(GameObject attackObj) {
		this.attackObj = attackObj;
	}

	public int getAtkTimeMax() {
		return (int) (Constant.BASE_ATTACK_TIMER_MAX / getAttackSpeed());
	}

	public double getAttackMultiply() {
		return attackMultiply;
	}

	public Pair getAttackRange() {
		return attackRange;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public double getHpMultiply() {
		return hpMultiply;
	}

	public int getHpRegen() {
		return hpRegen;
	}

	public int getMpRegen() {
		return mpRegen;
	}

	public int getAttackTime() {
		return attackTime;
	}

	public void resetAttackTime() {
		if (getAttackTime() == 0)
			attackTime = getAtkTimeMax();
	}

	public void setOwner(BattleFieldableEntity<Hero> owner) {
		this.owner = owner;
		attackObj.setVisible(true);
		RenderableHolder.getInstance().add(getAttackObj());
	}

	public void use() {
		resetAttackTime();
	}

	public Image getImage() {
		return animationImg;
	}
	public Image getHeroImage() {
		return heroImage;
	}
	public abstract int getManaUsed();
	
}
