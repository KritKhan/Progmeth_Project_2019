package creature.entity;

import java.util.ArrayList;

import javax.xml.ws.handler.LogicalHandler;

import Interface.Obstructable;
import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.InputUtility;
import SharedObject.ResourceLoader;
import creature.hero.Hero;
import drawing.field.BattleField;
import drawing.field.StatusBar;
import item.Inventory;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class HeroInBat extends BattleFieldableEntity<Hero> {
	private double maxMp;
	private double currentMp;
	private int money;
	private int moneyDelay;
	public static Inventory inventory;
	private String name;

	public HeroInBat(int direction, Hero atkType) {
		super(Constant.SCENE_WIDTH / 2, (Constant.SCENE_HEIGHT - 100) / 2, atkType.getHeroImage(), 0, 3, direction, 5,
				50, 1000, 200, atkType); // draw hero in the middle of screen
		// initialize all power of hero
		this.maxMp = 400;
		this.currentMp = 0;
		this.money = 300;
		this.z = -1;
		this.race = Constant.ENTITY_HUMANITY; // to identify hero of monster
		HeroInBat.inventory = new Inventory(); // create hero's inventory
		setAtktype(atkType); // type of hero
		name = atkType.getClass().toString(); // set name
		switch (name) {
		case "class creature.hero.Archer":
			name = "Archer";
			break;
		case "class creature.hero.Knight":
			name = "Knight";
			break;
		case "class creature.hero.Magician":
			name = "Magician";
			break;
		}
	}

	@Override
	public boolean attack() {
		if (atkType.getManaUsed() <= currentMp) {
			boolean r = super.attack(); // can the hero attack
			if (r)
				this.healMp(15);
			else
				this.atkType.use();
//			this.atkType.getHeroWeapon().use();
			return r;
		}
		return false;
	}

	@Override
	protected boolean isBlock(double x, double y) {
		if (struct)
			return false;
		if (dmgTimer != 0)
			return false;
		ArrayList<BattleFieldableEntity<Hero>> inArea = BattleField.getEntityInArea(this, x, y);
		for (BattleFieldableEntity<Hero> other : inArea) { // if it not the same type -> attack each other and return
															// true, it is blocked
			// System.out.println(other.getClass().getSimpleName());
			if (this.race != other.race)
				this.damage(other.baseAtk, this.direction);
			if (other instanceof Obstructable)
				return true;
		}
		return false;
	}

	@Override
	public void update() {
		super.update();
		if (isAlive && dmgTimer == 0) {
			if (GameLogic.battleField.getLvl() % 10 == 0)
				restoreHp();
			if (InputUtility.isKeyPressed(KeyCode.W) || InputUtility.isKeyPressed(KeyCode.UP))
				move(Constant.ENTITY_BACK);
			if (InputUtility.isKeyPressed(KeyCode.S) || InputUtility.isKeyPressed(KeyCode.DOWN))
				move(Constant.ENTITY_FRONT);
			if (InputUtility.isKeyPressed(KeyCode.A) || InputUtility.isKeyPressed(KeyCode.LEFT))
				move(Constant.ENTITY_LEFT);
			if (InputUtility.isKeyPressed(KeyCode.D) || InputUtility.isKeyPressed(KeyCode.RIGHT))
				move(Constant.ENTITY_RIGHT);
			if ((InputUtility.isKeyPressed(KeyCode.SPACE)
					|| ((!(GameLogic.status.isInBorder(InputUtility.mouseX, InputUtility.mouseY))
							&& InputUtility.isMousePressed())))
					&& atkType.getAttackTime() == 0)
				attack();

			if (isBlock(pos.x, pos.y))
				struct = true;
			else
				struct = false;
			if (currentHp != getMaxHp())
				healHp(Constant.BASE_HEAL_AMOUNT * (((GameLogic.battleField.getLvl() / 20) + 1)));
			if (currentMp != getMaxMp())
				healMp(Constant.BASE_HEAL_AMOUNT);
		}
		if (isAlive) {
			// this.atkType.getHeroWeapon().update(direction, pos.x, pos.y);
			this.atkType.update(this.direction, this.pos.x, this.pos.y);
			moneyDelay = moneyDelay <= 1 ? moneyDelay = 120 : moneyDelay - 1;
			if (moneyDelay == 5)
				money++;
		}
	}

	public void healHp(double i) {
		if (getCurrentHp() + i >= getMaxHp()) {
			restoreHp();
		} else {
			currentHp += i * atkType.getHpRegen();
		}
	}

	public void healMp(double i) {
		if (getCurrentMp() + i >= getMaxMp()) {
			currentMp = getMaxMp();
		} else {
			currentMp += i * atkType.getMpRegen();
		}
	}

	public void useMp(int i) {
		currentMp = currentMp < i ? 0 : currentMp - i;
	}

	public void revive() {
		this.restoreHp();
		BattleField.addEntities(this);
		setAtktype(atkType);
	}

	public void restoreHp() {
		currentHp = getMaxHp();
		setVisible(true);
		isAlive = true;
	}

	public void resetMp() {
		currentMp = 0;
	}

	public double getMaxMp() {
		return maxMp;
	}

	public double getMaxHp() {
		if (GameLogic.battleField.getLvl() < 6)
			return super.getMaxHp();
		return super.getMaxHp() * (GameLogic.battleField.getLvl() / 5);
	}

	public double getCurrentMp() {
		return currentMp;
	}

	public <T extends Hero> void setAtktype(T atkType) {
		if (this.atkType != null) {
			this.atkType.getAttackObj().destroyed();
		}
		this.atkType = atkType;
		this.atkType.setOwner(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void useMoney(int i) {
		money -= i;
		if (money < 0)
			money = 0;
	}

	public void earnMoney(int i) {
		money += i;
	}
}
