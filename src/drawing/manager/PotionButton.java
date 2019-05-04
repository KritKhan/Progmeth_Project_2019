package drawing.manager;

import SharedObject.ResourceLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class PotionButton extends Button {
	private String potion;
	
	public PotionButton(String potion) {
		this.potion = potion;
		this.setPadding(new Insets(5));
	}
	
	public void highlight() {
		ImageView img = null;
		switch(this.potion) {
		case "HpPotion": img = new ImageView(ResourceLoader.hphili);
		case "MpPotion": img = new ImageView(ResourceLoader.mphili);
		case "MixPotion": img = new ImageView(ResourceLoader.mixhili);		
		}
		setGraphic(img);
	}
	
	public void unhighlight() {
		ImageView img = null;
		switch(potion) {
		case "HpPotion": img = new ImageView(ResourceLoader.hp);
		case "MpPotion": img = new ImageView(ResourceLoader.mp);
		case "MixPotion": img = new ImageView(ResourceLoader.mix);		
		}
		setGraphic(img);
	}
}
