package creature.boss;

import creature.Creature;
import creature.Monster;

public abstract class DarkLord extends Monster{

	public DarkLord(String name) {
		super(name, 2600, 0, 200);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
