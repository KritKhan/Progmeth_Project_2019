package drawing.manager;

import SharedObject.RenderableHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class HeroSlot extends HBox {
	private ObservableList<HeroButton> heroButtonList = FXCollections.observableArrayList();
	private HeroButton selectedHeroButton;
	
	public HeroSlot() {
		super(300);
		setAlignment(Pos.CENTER);
		HeroButton archer = new HeroButton("Archer");
		HeroButton magician = new HeroButton("Magician");
		HeroButton knight = new HeroButton("Knight");
		
		//on action
	}
}
