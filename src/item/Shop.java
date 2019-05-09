package item;

import Exception.PurchaseException;
import Logic.GameLogic;
import creature.entity.HeroInBat;
import creature.hero.Hero;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Shop {
	private Item item;

	public void buy(String name) throws PurchaseException {
		if (name.equals("HpPotion"))
			item = new HpPotion();
		if (name.equals("MpPotion"))
			item = new MpPotion();
		if (name.equals("MixPotion"))
			item = new MixPotion();
		if (item.isBuyable()) {
			GameLogic.heroInBat.useMoney(item.price);
			HeroInBat.inventory.add(item);
		} else {
			throw new PurchaseException();
		}
	}
}
