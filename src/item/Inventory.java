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
		hp.amount = 1;
		mp.amount = 1;
		mix.amount = 1;
	}

	public void add(Item item) {
		if (item instanceof HpPotion) {
			inventory.get(0).add();
			;
		} else if (item instanceof MpPotion) {
			inventory.get(1).add();
		} else if (item instanceof MixPotion) {
			inventory.get(2).add();
		}
	}

	public Item use(Item item) {
		if (item instanceof HpPotion) {
			inventory.get(0).use();
		} else if (item instanceof MpPotion) {
			inventory.get(1).use();
		} else if (item instanceof MixPotion) {
			inventory.get(2).use();
		}
		return item;

	}

	public static List<Item> getInventory() {
		return inventory;
	}

}
