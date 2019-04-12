package creature;

public abstract class Creature {
	
	private String name;
	private int hp;
	private int maxHp;
	private int coin;
	
	
	public Creature(String name, int hp, int maxHp, int coin) {
		setName(name);
		setHp(maxHp);
		setMaxHp(maxHp);
		setCoin(coin);
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
		if(hp < 0) {
			hp = 0;
		}
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		if(maxHp < 0 ) {
			maxHp = 0;
		}
		this.maxHp = maxHp;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		if(coin < 0) {
			coin = 0;
		}
		this.coin = coin;
	}
	
	
}
