package creature.minion;

import creature.Creature;
import creature.Monster;

public abstract class Armor extends Monster{

	public Armor(String name) {
		super(name, 25, 10, 20);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}

}