package item;

import creature.hero.Hero;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Shop { 
	
	public void buy(Hero hero, Item item) {
		if (hero.getCoin() >= item.getPrice()) {
			hero.inventory.add(item);
			hero.setCoin(hero.getCoin() - item.getPrice());
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Cannot buy item, check your money");
			alert.show();
		}

	}
}
