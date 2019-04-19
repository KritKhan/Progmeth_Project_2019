package creature;

import Interface.regenable;
import item.Inventory;

public abstract class Hero extends Creature implements regenable{

	private int mp;
	private int maxMp;
	private int mpConsumption;
	private Inventory inventory;

	public Hero(String name, int maxHp, int coin, int atk, int maxMp, int mpConsumption) {
		super(name, maxHp, coin, atk);
		setMp(maxHp);
		setMaxHp(maxHp);
		setMpConsumption(mpConsumption);
		inventory = new Inventory();
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
