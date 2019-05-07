package item;

import javax.xml.ws.handler.LogicalHandler;

import SharedObject.ResourceLoader;

public class MixPotion extends Item {
	private static final String des = "Immediately heals HP by +250 and MP by +250";

	public MixPotion() {
		this.name = "MIX Potion";
		this.price = 120;
		this.description = des;
		this.increaseHp = 250;
		this.increaseMp = 250;
		this.imgae = ResourceLoader.mix;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		if (isUsable()) {
			Logic.GameLogic.heroInBat.healHp(increaseHp);
			Logic.GameLogic.heroInBat.healMp(increaseMp);
			amount--;
		}
	}

	@Override
	public boolean isBuyable() {
		// TODO Auto-generated method stub
		return Logic.GameLogic.heroInBat.getMoney()>=this.price &&Logic.GameLogic.heroInBat.getMoney()!=0;
	}

	@Override
	public boolean isUsable() {
		// TODO Auto-generated method stub
		return this.amount > 0;
	}

}
