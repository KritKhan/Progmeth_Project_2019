package item;

import Logic.GameLogic;
import SharedObject.ResourceLoader;


public class HpPotion extends Item{
	private static final String des = "Immediately heals HP by + 500";
	
	public HpPotion() {
		this.name = "HP Potion";
		this.price = 100;
		this.description = des;
		this.increaseHp = 500;
		this.increaseMp = 0;
		this.imgae = ResourceLoader.hp;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		if (isUsable()) {
			Logic.GameLogic.heroInBat.healHp(increaseHp);
			amount--;
		}
	}

	@Override
	public boolean isBuyable() {
		// TODO Auto-generated method stub
		return Logic.GameLogic.heroInBat.getMoney()>=this.price && Logic.GameLogic.heroInBat.getMoney()!=0;
	}

	@Override
	public boolean isUsable() {
		// TODO Auto-generated method stub
		return this.amount > 0;
	}

}
