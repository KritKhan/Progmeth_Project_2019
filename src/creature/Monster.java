package creature;

public class Monster extends Creature {

	public Monster(String name, int hp, int maxHp, int coin) {
		super(name, hp, maxHp, coin);
	}
	
	public void giveCoin(Hero hero) {
		hero.recieveCoin(this.getCoin());	
	}
	
}
