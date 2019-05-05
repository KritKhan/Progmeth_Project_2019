package creature.monster;

import creature.entity.BattleFieldableEntity;
import creature.hero.Hero;

public abstract class Monster extends BattleFieldableEntity {

	public Monster(String name, int maxHp, int coin, int atk) {
		super(name, maxHp, coin, atk);
	}
	
	public double giveCoin(Hero hero) {
		return getCoin();
	}
	
}
