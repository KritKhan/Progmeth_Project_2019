package creature.hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.GameObject;
import SharedObject.Pair;
import SharedObject.ResourceLoader;
import creature.entity.BattleFieldableEntity;
import creature.entity.HeroInBat;
import drawing.field.BattleField;
import javafx.scene.canvas.GraphicsContext;

public class Magician extends Hero {
	private Map<Pair, Pair> magicTime;
	private double maxMagicTime;
	private List<Pair> delTemp;
	private double magicW;
	private double magicH;

	public Magician() {
		super();
		heroImage = ResourceLoader.magician;
		attackMultiply = 1.5;
		attackRange = new Pair(100, 20);
		attackSpeed = 0.6;
		hpMultiply = 1;
		hpRegen = 1;
		mpRegen = 5;
		maxMagicTime = 10;
		magicTime = new HashMap<>();
		delTemp = new LinkedList<>();
		magicW = attackRange.x * 2;
		magicH = attackRange.y * 0.7;
		heroName = "Magician";
		animationImg = ResourceLoader.magicianFace;
		attackObj = new GameObject(0, 0, 500) {
			@Override
			public void draw(GraphicsContext gc) {
//				try {
//					if (owner instanceof HeroInBat) {
						if (owner.getAtkType().getAttackTime() > 3) {
							// gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
							if (owner.getDirection() == Constant.ENTITY_RIGHT) {
								gc.drawImage(ResourceLoader.mEffect, pos.x , pos.y - 2*getHeight(),
										ResourceLoader.mEffect2.getWidth() * 0.8, ResourceLoader.mEffect2.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_LEFT) {
								gc.drawImage(ResourceLoader.mEffect2, pos.x - getWidth()/1.55, pos.y - 2*getHeight(),
										ResourceLoader.mEffect.getWidth() * 0.8, ResourceLoader.mEffect.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_BACK) {
								gc.drawImage(ResourceLoader.mEffect3, pos.x - 1.5*getWidth() , pos.y - getHeight(),
										ResourceLoader.mEffect3.getWidth() * 0.8, ResourceLoader.mEffect3.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_FRONT) {
								gc.drawImage(ResourceLoader.mEffect4, pos.x - 1.5*getWidth(), pos.y,
										ResourceLoader.mEffect4.getWidth() * 0.8, ResourceLoader.mEffect4.getHeight() * 0.8);
							}
						}
//					} else {
//						if (owner.getAtkType().getAttackTime() > 0) {
//							// gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
//							if (owner.getDirection() == Constant.ENTITY_RIGHT) {
//								gc.drawImage(ResourceLoader.mEffect, pos.x - getWidth() * 5 / 6, pos.y - getHeight());
//							} else if (owner.getDirection() == Constant.ENTITY_LEFT) {
//								gc.drawImage(ResourceLoader.mEffect2, pos.x - getWidth() * 1.3, pos.y - getHeight());
//							} else if (owner.getDirection() == Constant.ENTITY_BACK) {
//								gc.drawImage(ResourceLoader.mEffect3, pos.x - getWidth() / 2, pos.y - getHeight() * 5 / 6);
//							} else if (owner.getDirection() == Constant.ENTITY_FRONT) {
//								gc.drawImage(ResourceLoader.mEffect4, pos.x - getWidth() / 2, pos.y - getHeight() * 5 / 6);
//							}
//						}
//					}
//				} catch (NullPointerException e) {
//					System.out.println("cannot draw attack effect");
//				}
			}

			@Override
			public double getWidth() {
				return ((GameLogic.heroInBat.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.y : attackRange.x * 1.5;
			}

			@Override
			public double getHeight() {
				return ((GameLogic.heroInBat.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.x * 2 : attackRange.y;
			}
//			@Override
//			public boolean isCollide(GameObject other, double x, double y) {
//				boolean isMagicHit = false;
//				if (GameLogic.heroInBat.getCurrentMp() > 5) {
//					for (Entry<Pair, Pair> e : magicTime.entrySet()) {
//						double a = 0, b = 0;
//						if (e.getValue().x == Constant.ENTITY_FRONT) {
//							a = e.getKey().x + (owner.getWidth() - magicW);
//							b = e.getKey().y + e.getValue().y + owner.getHeight() / 3;
//						} else if (e.getValue().x == Constant.ENTITY_BACK) {
//							a = e.getKey().x + (owner.getWidth() - magicW);
//							b = e.getKey().y - e.getValue().y;
//						} else if (e.getValue().x == Constant.ENTITY_LEFT) {
//							a = e.getKey().x - e.getValue().y - magicW / 2;
//							b = e.getKey().y;
//						} else if (e.getValue().x == Constant.ENTITY_RIGHT) {
//							a = e.getKey().x + e.getValue().y;
//							b = e.getKey().y;
//						}
//						if ((((a - other.getWidth()) <= other.getX())
//								&& (other.getX() <= (a + magicW + other.getWidth())))
//								&& (((b - other.getHeight()) <= other.getY())
//										&& (other.getY() <= (b + other.getHeight() + magicH)))
//								&& ((((a - other.getWidth()) <= (other.getX() + other.getWidth()))
//										&& ((other.getX() + other.getWidth()) <= (a + magicW + other.getWidth())))
//										&& (((b - other.getHeight()) <= (other.getY() + other.getHeight()))
//												&& ((other.getY() + other.getHeight()) <= (b + other.getHeight()
//														+ magicH)))))
//							isMagicHit = true;
//					}
//				}
//				if ((owner instanceof HeroInBat) && magicTime.size() == 0 && getAttackTime() == 0)
//					return false;
//				else if (owner.getAtkType().getAttackTime() == 0)
//					return (super.isCollide(other, x, y) || isMagicHit);
//				return isMagicHit;
//			}

		};
	}

	@Override
	public void update(int direction, double x, double y) {
		super.update(direction, x, y);
		if (direction == Constant.ENTITY_RIGHT) {
			this.attackObj.setX(x + owner.getWidth() * 2 / 3);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction == Constant.ENTITY_LEFT) {
			this.attackObj.setX(x + owner.getWidth() / 3 - attackRange.x);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction == Constant.ENTITY_BACK) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y - attackRange.y + owner.getHeight() * 5 / 6);
		} else if (direction == Constant.ENTITY_FRONT) {
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
		if (magicTime.size() <= Constant.MAX_SPELL_ACTIVE)
			magicTime.put(new Pair(attackObj.getX(), attackObj.getY()), new Pair(owner.getDirection(), 0));

		if (owner instanceof HeroInBat)
			GameLogic.heroInBat.useMp(getManaUsed());
	}

	public int getManaUsed() {
		return Constant.BASE_MP_USE * 2;
	}

}
