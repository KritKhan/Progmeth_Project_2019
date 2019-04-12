package creature.hero;

import creature.Creature;
import creature.Hero;

public abstract class Archer extends Hero{
	
	public Archer(String name) {
		super(name, 200, 0, 25, 200, 15);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
