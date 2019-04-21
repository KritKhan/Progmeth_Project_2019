package creature;

import Interface.Attackable;
import SharedObject.IRenderable;

public abstract class Creature implements IRenderable, Attackable {

	private String name;
	private int hp;
	private int maxHp;
	private double coin;
	private int atk;

	public Creature(String name, int maxHp, double coin, int atk) {
		setName(name);
		setHp(maxHp);
		setMaxHp(maxHp);
		setCoin(coin);
		setAtk(atk);
	}

	@Override
	public void attack(Creature creature) {
		if (creature.isAlive()) {
			creature.setHp(creature.getHp() - this.getAtk());
		}
	}

	public boolean isAlive() {
		if (hp > 0)
			return true;
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp < 1) {
			hp = 1;
		}
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		if (maxHp < 1) {
			maxHp = 1;
		}
		this.maxHp = maxHp;
	}

	public double getCoin() {
		return (double) coin;
	}

	public void setCoin(double coin) {
		if (coin < 0) {
			coin = 0;
		}
		this.coin = coin;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		if (atk < 0)
			atk = 0;
		this.atk = atk;
	}

}
