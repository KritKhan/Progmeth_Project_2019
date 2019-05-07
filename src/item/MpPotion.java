package item;

import Logic.GameLogic;
import SharedObject.ResourceLoader;

public class MpPotion extends Item {
	private static final String des = "Immediately heals MP by +500";

	public MpPotion() {
		this.name = "MP Potion";
		this.price = 100;
		this.description = des;
		this.increaseHp = 0;
		this.increaseMp = 500;
		this.imgae = ResourceLoader.mp;
	}
	
	public void use() {
		if (isUsable()) {
			GameLogic.heroInBat.healMp(increaseMp);
			amount--;
		}
	}

	@Override
	public boolean isBuyable() {
		// TODO Auto-generated method stub
		return GameLogic.heroInBat.getMoney()>=this.price && Logic.GameLogic.heroInBat.getMoney()!=0;

	}

	@Override
	public boolean isUsable() {
		// TODO Auto-generated method stub
		return this.amount > 0;
	}

}
