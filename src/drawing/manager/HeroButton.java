package drawing.manager;

import static javafx.scene.layout.BorderStrokeStyle.SOLID;

import java.awt.event.MouseEvent;

import SharedObject.RenderableHolder;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class HeroButton extends Button {
	
	private String hero;
	
	public HeroButton(String hero) {
		this.hero = hero;
		setPadding(new Insets(20));
		setBackground(new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		WritableImage heroFace = null;
		switch(hero) {
		case "Magician" : heroFace = new WritableImage(RenderableHolder.magicianFace.getPixelReader(), 64, 0,32, 32); break;
		case "Archer" : heroFace = new WritableImage(RenderableHolder.archerFace.getPixelReader(),0,0,32,32); break;
		case "Knight" : heroFace = new WritableImage(RenderableHolder.knightFace.getPixelReader(), 0,0,32,32); break;
		}
		ImageView face = new ImageView(heroFace);
		setGraphic(face);
	}
	
	public void hightlight() {
		setBorder(new Border(new BorderStroke(Color.AQUA, SOLID, 
				CornerRadii.EMPTY, BorderWidths.FULL)));
		
	}
	
	public void unhightlight() {
		setBorder(new Border(new BorderStroke(Color.CORNSILK, SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	public String getHero() {
		return hero;
	}
	
	
}
