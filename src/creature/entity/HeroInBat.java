package creature.entity;

import creature.hero.Hero;
import javafx.scene.canvas.GraphicsContext;

public class HeroInBat extends BattleFieldableEntity<Hero> {

	public HeroInBat(double x, double y) {
		super(x, y, img, baseAtk, baseAtk, baseAtk, baseAtk, baseAtk, baseAtk, baseAtk, atkType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean isBlock(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

}
