package creature.minion;

import creature.Creature;
import creature.monster.Monster;

public abstract class Wolf extends Monster {

	public Wolf(int lvl) {
		super("Wolf",20,10,25);
		switch(lvl) {
		case 1: break;
		case 2: 
			setMaxHp(30);
			setCoin(13.5);
			setAtk(31);
		case 3:
			setMaxHp(45);
			setCoin(18.25);
			setAtk(39);
		case 4:
			setMaxHp(68);
			setCoin(25);
			setAtk(49);
		case 5:
			setMaxHp(101);
			setCoin(33.25);
			setAtk(61);
		case 6:
			setMaxHp(152);
			setCoin(45);
			setAtk(76);
		case 7:
			setMaxHp(228);
			setCoin(60.5);
			setAtk(95);
		case 8:
			setMaxHp(342);
			setCoin(81.75);
			setAtk(119);
		case 9:
			setMaxHp(513);
			setCoin(110.25);
			setAtk(149);
		case 10:
			setMaxHp(770);
			setCoin(149);
			setAtk(186);
		}
	}

	@Override
	public void attack(Creature creature) {
		creature.setHp(creature.getHp()-this.getAtk());
	}
}
