package creature.minion;

import creature.Creature;
import creature.Monster;

public abstract class Armor extends Monster{

	public Armor(int lvl) {
		super("Armor", 25, 10, 20);
		switch(lvl) {
		case 1: break;
		case 2: 
			setMaxHp(38);
			setCoin(13.5);
			setAtk(25);
		case 3:
			setMaxHp(56);
			setCoin(18.25);
			setAtk(31);
		case 4:
			setMaxHp(84);
			setCoin(25);
			setAtk(39);
		case 5:
			setMaxHp(127);
			setCoin(33.25);
			setAtk(49);
		case 6:
			setMaxHp(190);
			setCoin(45);
			setAtk(61);
		case 7:
			setMaxHp(285);
			setCoin(60.5);
			setAtk(76);
		case 8:
			setMaxHp(427);
			setCoin(81.75);
			setAtk(95);
		case 9:
			setMaxHp(641);
			setCoin(110.25);
			setAtk(120);
		case 10:
			setMaxHp(961);
			setCoin(149);
			setAtk(149);
		}
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}

}