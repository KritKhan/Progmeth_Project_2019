package item;

import creature.Hero;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Shop {
	
	public void buy(Hero hero, Item item) {
		if(hero.getCoin() >= item.getPrice()) {
			hero.getInventory().add(item);
			hero.setCoin(hero.getCoin() - item.getPrice());
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Cannot buy item, check your money");
			alert.show();
		}
	}

}
