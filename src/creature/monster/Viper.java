package creature.monster;

import creature.Creature;

public abstract class Viper extends Monster {

	public Viper(String name) {
		super(name, 1000, 1500, 100);
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
