package item;

import SharedObject.ResourceLoader;

public class MpPotion extends Item{
	private static final String des = "Immediately heals MP by +500";

	public MpPotion() {
		this.name = "MP Potion";
		this.price = 100;
		this.description = des;
		this.increaseHp = 0;
		this.increaseMp = 500;
		this.imgae = ResourceLoader.mp;
	}

}
