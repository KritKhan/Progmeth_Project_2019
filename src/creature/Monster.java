package creature;

public class Monster extends Creature {

	public Monster(String name, int maxHp, int coin, int atk) {
		super(name, maxHp, coin, atk);
	}
	
	public int giveCoin(Hero hero) {
		return getCoin();
	}
	
}
