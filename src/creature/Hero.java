package creature;

import item.Inventory;
import Interface.Regenable;

public abstract class Hero extends Creature implements Regenable {

	private int mp;
	private int maxMp;
	private int mpConsumption;
	private Inventory inventory;
	private int lvl;

	public Hero(String name, int maxHp, int coin, int atk, int maxMp, int mpConsumption) { 
		super(name, maxHp, coin, atk);
		setMp(maxHp);
		setMaxHp(maxHp);
		setMpConsumption(mpConsumption);
		inventory = new Inventory();
		lvl = 1;
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

	public int lvlUp() {
		if(lvl>0 && lvl<=15) {
			lvl++;
			setMaxHp((int)(getMaxHp()*1.3));
			setMaxMp((int)(getMaxMp()*1.15));
			setAtk((int)(getAtk()*1.2));
			setMpConsumption((int)(getMpConsumption()*1.1));
		}
		return lvl;
	}
	
	@Override
	public void regenHp() {
		setHp((int)(getHp()+(30*Math.pow(1.25, lvl))));
	}

	@Override
	public void regenMp() {
		setMp((int)(getMp()+(25*Math.pow(1.1, lvl))));
	}

	public Inventory getInventory() {
		return inventory;
	}
	
}
