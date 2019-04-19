package item;

public class MixPotion extends Item {
	private static final String des = "Immediately heals HP by +250 and MP by +250";

	public MixPotion() {
		this.name = "MIX Potion";
		this.price = 120;
		this.description = des;
		this.increaseHp = 250;
		this.increaseMp = 250;
//		this.imgae = 
	}

}
