package creature.hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.Pair;
import SharedObject.ResourceLoader;
import creature.entity.BattleFieldableEntity;
import creature.entity.HeroInBat;
import drawing.field.BattleField;

public abstract class Magician extends Hero{
	private Map<Pair, Pair> magicTime;
	private double maxMagicTime;
	private List<Pair> delTemp;
	private double magicW;
	private double magicH;

	public Magician() {
		super();
		attackMultiply = 1.5;
		//attackRange = new Pair(getHeroWeapon().getWidth() * 1.2, getHeroWeapon().getHeight());
		attackSpeed = 0.6;
		hpMultiply = 1;
		hpRegen = 1;
		mpRegen = 5;
		maxMagicTime = Constant.MAX_MAGIC_TIME;
		magicTime = new HashMap<>();
		delTemp = new LinkedList<>();
		magicW = attackRange.x*2;
		magicH = attackRange.y*0.7;
		animationImg = ResourceLoader.intelligence;
		//attackObj = new GameObject(heroWeapon.getX() + 20, heroWeapon.getY(), 500)
		
	}
	@Override
	public void update(int direction, double x, double y) {
		super.update(direction, x, y);
		if (direction ==  Constant.ENTITY_RIGHT) {
			this.attackObj.setX(x + owner.getWidth() * 2 / 3);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction ==  Constant.ENTITY_LEFT) {
			this.attackObj.setX(x + owner.getWidth() / 3 - attackRange.x);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction ==  Constant.ENTITY_BACK) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y - attackRange.y + owner.getHeight() * 5 / 6);
		} else if (direction ==  Constant.ENTITY_FRONT) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y + owner.getHeight() * 2 / 3);
		}
			ArrayList<BattleFieldableEntity<Hero>> inArea = BattleField.getEntityInArea(owner.getAtkType().getAttackObj(),
					owner.getAtkType().getAttackObj().getX(), owner.getAtkType().getAttackObj().getY());
			if (!(inArea == null || inArea.size() <= 1))
				for (BattleFieldableEntity<Hero> other : inArea) {
					if (other.getAtkType().getAttackObj().hashCode() != owner.getAtkType().getAttackObj().hashCode() 
							&& owner.getRace() != other.getRace()) {
						owner.getAtkType().attack(owner, other);
					}
				}
		
			for (Entry<Pair, Pair> e : magicTime.entrySet()) {
				e.getValue().y += 2;
				if (e.getValue().y >= maxMagicTime)
					delTemp.add(e.getKey());
			}
			for (Pair e : delTemp) {
				magicTime.remove(e);
			}
			delTemp.clear();
		
	}
	@Override
	public void setOwner(BattleFieldableEntity<Hero> owner) {
		// TODO Auto-generated method stub
		super.setOwner(owner);
	}
	
	@Override
	public void use() {
		super.use();
		if (magicTime.size() <= Constant.MAX_SPELL_ACTIVE )
			magicTime.put(new Pair(attackObj.getX(), attackObj.getY()), new Pair(owner.getDirection(), 0));

		if(owner instanceof HeroInBat) GameLogic.heroInBat.useMp(getManaUsed());
	}

	public int getManaUsed() {
		return Constant.BASE_MP_USE*2;
	}

}
