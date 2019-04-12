package creature;

public abstract class Creature {
	
	private String name;
	private int hp;
	private int maxHp;
	private int coin;
	private int atk;
	
	public Creature(String name, int maxHp, int coin, int atk) {
		setName(name);
		setHp(maxHp);
		setMaxHp(maxHp);
		setCoin(coin);
		setAtk(atk);
	}

	public boolean isAlive() {
		if(hp>0) return true;
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
		if(hp < 1) {
			hp = 1;
		}
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		if(maxHp < 1 ) {
			maxHp = 1;
		}
		this.maxHp = maxHp;
	}

	public int getCoin() {
		return (int) coin;
	}

	public void setCoin(int coin) {
		if(coin < 0) {
			coin = 0;
		}
		this.coin = coin;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		if(atk < 0) atk = 0;
		this.atk = atk;
	}
	
	
}
