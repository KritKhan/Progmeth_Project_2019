package creature.hero;

import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.GameObject;
import SharedObject.Pair;
import SharedObject.ResourceLoader;
import creature.entity.HeroInBat;
import javafx.scene.canvas.GraphicsContext;

public class Knight extends Hero {

	public Knight() {
		super();
		heroImage = ResourceLoader.knight;
		attackMultiply = 1;
		attackRange = new Pair(70, 32);
		attackSpeed = 1;
		hpMultiply = 1.4;
		hpRegen = 5;
		mpRegen = 1;
		animationImg = ResourceLoader.knightFace;
		heroName = "Knight";
		attackObj = new GameObject(0, 0, 500) {
			@Override
			public void draw(GraphicsContext gc) {
				try {
					if (owner.getAtkType().getAttackTime() > 3) {
						if (owner.getDirection() == Constant.ENTITY_RIGHT) {
							gc.drawImage(ResourceLoader.kEffect, pos.x - getWidth() / 3.2, pos.y - getHeight(),
									ResourceLoader.kEffect.getWidth() * 0.5, ResourceLoader.kEffect.getHeight() * 0.5);
						} else if (owner.getDirection() == Constant.ENTITY_LEFT) {
							gc.drawImage(ResourceLoader.kEffect2, pos.x - getWidth() / 2, pos.y - getHeight(),
									ResourceLoader.kEffect2.getWidth() * 0.5,
									ResourceLoader.kEffect2.getHeight() * 0.5);
						} else if (owner.getDirection() == Constant.ENTITY_BACK) {
							gc.drawImage(ResourceLoader.kEffect3, pos.x - getWidth() / 6, pos.y - getHeight() / 1.2,
									ResourceLoader.kEffect3.getWidth() * 0.5,
									ResourceLoader.kEffect3.getHeight() * 0.5);
						} else if (owner.getDirection() == Constant.ENTITY_FRONT) {
							gc.drawImage(ResourceLoader.kEffect4, pos.x - getWidth() * 0.4, pos.y - getHeight() * 0.4,
									ResourceLoader.kEffect4.getWidth() * 0.5,
									ResourceLoader.kEffect4.getHeight() * 0.5);
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
				return ((GameLogic.heroInBat.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.x * 2
						: attackRange.y;
			}
		};
	}

	public void update(int direction, double x, double y) {
		try {
			super.update(direction, x, y);
			if (direction == Constant.ENTITY_RIGHT) {
				this.attackObj.setX(x + owner.getWidth() * 2 / 3);
				this.attackObj.setY(y + owner.getHeight() / 3);
			} else if (direction == Constant.ENTITY_LEFT) {
				this.attackObj.setX(x + owner.getWidth() / 3 - attackRange.x);
				this.attackObj.setY(y + owner.getHeight() / 3);
			} else if (direction == Constant.ENTITY_BACK) {
				this.attackObj.setX(x + (owner.getWidth() - this.attackObj.getWidth()) / 2);
				this.attackObj.setY(y - attackRange.y + owner.getHeight() * 5 / 6);
			} else if (direction == Constant.ENTITY_FRONT) {
				this.attackObj.setX(x + (owner.getWidth() - this.attackObj.getWidth()) / 2);
				this.attackObj.setY(y + owner.getHeight() * 2 / 3);
			}

			else if (direction == Constant.ENTITY_BACK) {
				this.attackObj.setZ(owner.getZ() - 10);
			} else
				this.attackObj.setZ(owner.getZ() + 10);
		} catch (NullPointerException e) {
			System.out.println("cannot update attack effect");
		}
	}

	@Override
	public int getManaUsed() {
		return 0;
	}
}
