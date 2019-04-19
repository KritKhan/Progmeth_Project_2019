package item;

public class HpPotion extends Item{
	private static final String des = "Immediately heals HP by + 500";
	
	public HpPotion() {
		this.name = "HP Potion";
		this.price = 100;
		this.description = des;
		this.increaseHp = 500;
		this.increaseMp = 0;
//		this.imgae =
	}

}
