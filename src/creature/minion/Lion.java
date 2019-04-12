package creature.minion;

import creature.Creature;
import creature.Monster;

public abstract class Lion extends Monster{

	public Lion(String name) {
		super(name, 20, 20, 20);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
