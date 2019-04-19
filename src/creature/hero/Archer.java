package creature.hero;

import creature.Creature;
import creature.Hero;

public abstract class Archer extends Hero{
	
	public Archer(int lvl) {
		super("Archer", 200, 0, 25, 200, 15);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
