package creature;

public abstract class Monster extends Creature {

	public Monster(String name, int maxHp, int coin, int atk) {
		super(name, maxHp, coin, atk);
	}
	
	public double giveCoin(Hero hero) {
		return getCoin();
	}
	
}
