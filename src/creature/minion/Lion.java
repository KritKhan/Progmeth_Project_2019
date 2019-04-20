package creature.minion;

import creature.Creature;
import creature.Monster;

public abstract class Lion extends Monster{

	public Lion(int lvl) {
		super("Lion", 20, 20, 20);
		switch(lvl) {
		case 1: break;
		case 2: 
			setMaxHp(30);
			setCoin(27);
			setAtk(25);
		case 3:
			setMaxHp(45);
			setCoin(36.5);
			setAtk(31);
		case 4:
			setMaxHp(68);
			setCoin(49.25);
			setAtk(39);
		case 5:
			setMaxHp(101);
			setCoin(66.5);
			setAtk(49);
		case 6:
			setMaxHp(152);
			setCoin(89.5);
			setAtk(61);
		case 7:
			setMaxHp(228);
			setCoin(121);
			setAtk(76);
		case 8:
			setMaxHp(342);
			setCoin(163.5);
			setAtk(95);
		case 9:
			setMaxHp(513);
			setCoin(220.5);
			setAtk(119);
		case 10:
			setMaxHp(770);
			setCoin(298);
			setAtk(149);
		}
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
