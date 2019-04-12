package creature.minion;

import creature.Creature;
import creature.Monster;

public abstract class Wolf extends Monster {

	public Wolf(String name) {
		super(name, 20, 20, 25);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
