package item;

import Interface.Useable;
import javafx.scene.image.Image;

public abstract class Item implements Useable {
	protected String name;
	protected int price;
	protected String description;
	protected int increaseHp;
	protected int increaseMp;
	protected Image imgae;
	protected int amount;
	
	public abstract boolean isBuyable();
	public abstract boolean isUsable();
	
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
	
	public void add() {
		this.amount++;
	}
}
