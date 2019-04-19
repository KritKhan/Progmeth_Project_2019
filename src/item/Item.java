package item;

import javafx.scene.image.Image;

public class Item {
	protected String name;
	protected int price;
	protected String description;
	protected int increaseHp;
	protected int increaseMp;
	protected Image imgae;
	protected int amount;
	
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getIncreaseHp() {
		return increaseHp;
	}

	public int getIncreaseMp() {
		return increaseMp;
	}
	
	public int getAmount() {
		return amount;
	}
	
	
}
