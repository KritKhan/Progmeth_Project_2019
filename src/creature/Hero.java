package creature;

public class Hero extends Creature {

	private int mp;
	private int maxMp;
	private int mpConsumption;

	public Hero(String name, int hp, int maxHp, int coin, int atk, int mp, int maxMp, int mpConsumption) {
		super(name, hp, maxHp, coin, atk);
		setMp(mp);
		setMaxHp(maxHp);
		setMpConsumption(mpConsumption);
	}
	
	public boolean canConsumeMp() {
		if(getMp() - getMpConsumption()> 0 ) return true;
		return false;
	}
	
	public void mpConsume() {
		if(canConsumeMp()) {
			setMp(getMp() - getMpConsumption());
		}
	}
	
	public void recieveCoin(int coin) {
		this.setCoin(getCoin()+coin);
	}
	public int getMpConsumption() {
		return mpConsumption;
	}

	public void setMpConsumption(int mpConsumption) {
		if(mpConsumption < 1) mpConsumption = 1;
		this.mpConsumption = mpConsumption;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		if(mp < 0) mp = 0;
		this.mp = mp;
	}

	public int getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(int maxMp) {
		if(maxMp < 1) maxMp = 1;
		this.maxMp = maxMp;
	}
	

}
