package drawing.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;

public class ItemBox extends HBox {
	
	private ObservableList<PotionButton> potionButtonList = FXCollections.observableArrayList();
	private PotionButton selectedPotion;
	
	private ItemBox() {
		this.setAlignment(Pos.TOP_CENTER);
		setSpacing(10);
		PotionButton hp = new PotionButton("HpPotion");
		PotionButton mp = new PotionButton("MpPotion");
		PotionButton mix = new PotionButton("MixPotion");
		potionButtonList.addAll(hp,mp,mix);
		for(PotionButton btn : potionButtonList) {
			btn.setOnAction((ActionEvent event)->{
				setSelectedPotion(btn);
			});
		}
		this.getChildren().addAll(hp,mp,mix);
	}
	public PotionButton getSelectedPotion() {
		return selectedPotion;
	}
	public void setSelectedPotion(PotionButton selectedPotion) {
		this.selectedPotion = selectedPotion;
		this.selectedPotion.highlight();
		for(PotionButton btn : potionButtonList) {
			if(!btn.equals(this.selectedPotion)) btn.unhighlight();
		}
	}
	
}
