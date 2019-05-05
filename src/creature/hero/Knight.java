package creature.hero;

import creature.Creature;

public abstract class Knight extends Hero {

	public Knight(String name) {
		super(name, 300, 0, 20, 200, 15);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
