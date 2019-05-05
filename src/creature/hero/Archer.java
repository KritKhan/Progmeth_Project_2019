package creature.hero;

import creature.Creature;
import javafx.scene.canvas.GraphicsContext;

public abstract class Archer extends Hero{

	public Archer() {
		super("Archer", 200, 0, 25, 200, 15);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
	}
	
}
