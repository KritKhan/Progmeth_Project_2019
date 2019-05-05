package creature.hero;

import creature.Creature;

public abstract class Magician extends Hero{

	public Magician(String name) {
		super(name, 200, 0, 30, 250, 15);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
