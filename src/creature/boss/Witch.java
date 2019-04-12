package creature.boss;

import creature.Creature;
import creature.Monster;

public abstract class Witch extends Monster {

	public Witch(String name) {
		super(name, 500, 1500, 50);
	}
	
	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}

}
