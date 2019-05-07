package creature.hero;

import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.GameObject;
import SharedObject.Pair;
import SharedObject.ResourceLoader;
import creature.entity.HeroInBat;
import item.Inventory;
import javafx.scene.canvas.GraphicsContext;

public class Archer extends Hero{

	public Archer() {
		super();
		heroImage = ResourceLoader.archer;
		attackMultiply = 0.8;
		attackRange = new Pair(100, 32);
		attackSpeed = 2;
		hpMultiply = 0.9;
		hpRegen = 2;
		mpRegen = 3;
		animationImg = ResourceLoader.archerFace;
		heroName = "Archer";
		attackObj = new GameObject(200 + 20, 200, 500) {

			@Override
			public void draw(GraphicsContext gc) {
				try {
					if (owner instanceof HeroInBat) {
						if (owner.getAtkType().getAttackTime() > 3) {
							// gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
							if (owner.getDirection() == Constant.ENTITY_RIGHT) {
								gc.drawImage(ResourceLoader.aEffect, pos.x - getWidth() / 2, pos.y - getHeight() / 2,
										ResourceLoader.aEffect.getWidth() * 0.8,
										ResourceLoader.aEffect.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_LEFT) {
								gc.drawImage(ResourceLoader.aEffect2, pos.x - getWidth() ,pos.y - getHeight() / 2, 
										ResourceLoader.aEffect2.getWidth() * 0.8,
										ResourceLoader.aEffect2.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_BACK) {
								gc.drawImage(ResourceLoader.aEffect3, pos.x - getWidth() / 6,pos.y - getHeight() * 0.6,
										ResourceLoader.aEffect3.getWidth() * 0.8,
										ResourceLoader.aEffect3.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_FRONT) {
								gc.drawImage(ResourceLoader.aEffect4, pos.x - getWidth() * 0.4,pos.y - getHeight()*0.2, 
										ResourceLoader.aEffect4.getWidth() * 0.8,
										ResourceLoader.aEffect4.getHeight() * 0.8);
							}
						}
					} else {
						if (owner.getAtkType().getAttackTime() > 0) {
							// gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
							if (owner.getDirection() == Constant.ENTITY_RIGHT) {
								gc.drawImage(ResourceLoader.aEffect2, pos.x - getWidth() * 5 / 6,
										pos.y - getHeight());
							} else if (owner.getDirection() == Constant.ENTITY_LEFT) {
								gc.drawImage(ResourceLoader.aEffect, pos.x - getWidth() * 1.3,
										pos.y - getHeight());
							} else if (owner.getDirection() == Constant.ENTITY_BACK) {
								gc.drawImage(ResourceLoader.aEffect3, pos.x - getWidth() / 2,
										pos.y - getHeight() * 5 / 6);
							} else if (owner.getDirection() == Constant.ENTITY_FRONT) {
								gc.drawImage(ResourceLoader.aEffect4, pos.x - getWidth() / 2,
										pos.y - getHeight() * 5 / 6);
							}
						}
					}
				} catch (NullPointerException e) {
					System.out.println("cannot draw attack effect");
				}
			}

			@Override
			public double getWidth() {
				return ((GameLogic.heroInBat.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.y
						: attackRange.x * 1.5;
			}

			@Override
			public double getHeight() {
				return ((GameLogic.heroInBat.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.x * 2 : attackRange.y;
			}
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
			this.attackObj.setY(y - attackObj.getHeight() + owner.getHeight() * 5 / 6);
		} else if (direction == Constant.ENTITY_FRONT) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y + owner.getHeight() * 2 / 3);
		} else if (direction == Constant.ENTITY_BACK) {
			this.attackObj.setZ(owner.getZ() - 10);
		} else
			this.attackObj.setZ(owner.getZ() + 10);
	}
	

	@Override
	public int getManaUsed() {
		return Constant.BASE_MP_USE;
	}

}
