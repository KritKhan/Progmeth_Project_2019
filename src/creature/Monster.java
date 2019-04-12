package creature;

public class Monster extends Creature {

	public Monster(String name, int hp, int maxHp, int coin, int atk) {
		super(name, hp, maxHp, coin, atk);
	}
	
	public void giveCoin(Hero hero) {
		hero.recieveCoin(this.getCoin());	
	}
	
}
