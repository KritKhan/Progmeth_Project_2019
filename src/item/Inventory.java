package item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	public static List<Item> inventory = new ArrayList<>();
	Item hp = new HpPotion();
	Item mp = new MpPotion();
	Item mix = new MixPotion();
	
	public Inventory() {
		reset();
		inventory.add(hp);
		inventory.add(mp);
		inventory.add(mix);
	}
	
	public void reset() {
		hp.amount = 0;
		mp.amount = 0;
		mix.amount = 0;
	}
	public void add(Item item) {
		if(item instanceof HpPotion) {
			inventory.get(0).amount++;
		}
		else if(item instanceof MpPotion) {
			inventory.get(1).amount++;
		}
		else if(item instanceof MixPotion) {
			inventory.get(2).amount++;
		}
	}
	
	public Item use(Item item) {
		if(item instanceof HpPotion) {
			inventory.get(0).amount--;
		}
		else if(item instanceof MpPotion) {
			inventory.get(1).amount--;
		}
		else if(item instanceof MixPotion) {
			inventory.get(2).amount--;
		}
		return item;
		
	}

	public static List<Item> getInventory() {
		return inventory;
	}
	
	
}
