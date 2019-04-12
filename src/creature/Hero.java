package creature;

public class Hero extends Creature {
	private int mp;
	private int maxMp;
	private int mpConsumption;

	public Hero(String name, int hp, int maxHp, int coin) {
		super(name, hp, maxHp, coin);
		
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

	public int getMpConsumption() {
		return mpConsumption;
	}

	public void setMpConsumption(int mpConsumption) {
		this.mpConsumption = mpConsumption;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}
	

}
