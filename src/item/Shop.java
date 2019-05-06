package item;

import Logic.GameLogic;
import creature.entity.HeroInBat;
import creature.hero.Hero;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Shop {
	public void buy(Item item) {
		if(item.isBuyable()) {
			GameLogic.heroInBat.useMoney(item.price);
			HeroInBat.inventory.add(item);
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Please check your money!" );
			alert.setHeaderText("Shop Error");
			alert.showAndWait();
		}
	}
}
