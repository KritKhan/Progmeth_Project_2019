package creature.monster;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import Logic.GameLogic;
import SharedObject.Constant;
import SharedObject.InputUtility;
import SharedObject.RandomUtility;
import SharedObject.RenderableHolder;
import SharedObject.ResourceLoader;
import creature.entity.BattleFieldableEntity;
import creature.entity.HeroInBat;
import creature.hero.Archer;
import creature.hero.Hero;
import creature.hero.Knight;
import creature.hero.Magician;
import drawing.field.BattleField;
import drawing.manager.SceneManager;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.Main;


public class MonsterGen {
	private static Thread monsterThread;
	private int monsterCount;
	private int maxMonster;
	private int dunLvl;
	private Monster darkLord;
	private Monster viper;
	private Monster witch;
	public MonsterGen() {
		monsterCount = 0;
		maxMonster = 10;
		monsterThread = new Thread((new Runnable() {
			@Override 
			public void run() {
				while (true) {
					try {
						Thread.sleep(RandomUtility.randomTime(RenderableHolder.getInstance().size()));
					} catch (InterruptedException e) {
						System.out.println("monsterThread has been interrupted.");
						break;
					}
					if (Main.isGameRunning
							&& RenderableHolder.getInstance().size() < 35
							&& GameLogic.heroInBat.isAlive()) {
						if (monsterCount < maxMonster && (BattleField.getLvlChangetimer() == 0)) {
							try {
								addMonster();
							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
									| InvocationTargetException | SecurityException e) {
								System.out.println("cannot add monster");
							}

						} else if (GameLogic.battleField.isLevelClear() && monsterCount >= maxMonster) {
							monsterCount = 1;
						}
					}

				}
			}
		}));
		monsterThread.start();
	}

	public static void stop() {
		monsterThread.interrupt();
	}

	public boolean isGenerate() {
		return monsterCount < maxMonster;
	}

	public void restart() {
		maxMonster = 0;
		monsterCount = 0;
	}

	public void update() {
		if (InputUtility.isKeyPressed(KeyCode.P)) { // add monster
			try {
				addMonster();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
		} else if (InputUtility.isKeyPressed(KeyCode.O)) { // restore hero hp
			GameLogic.heroInBat.restoreHp();
		} else if (InputUtility.isKeyPressed(KeyCode.Y)) { // clear dungeon floor
			for (BattleFieldableEntity<Hero> e : BattleField.getEntitiesHolder())
				if (!(e instanceof HeroInBat))
					BattleField.destroyEntities(e);
		} else if (InputUtility.isKeyPressed(KeyCode.U)) { // hero assassinate
			GameLogic.heroInBat.healHp(-1000);
			GameLogic.heroInBat.setAlive(false);
		} else if (InputUtility.isKeyPressed(KeyCode.L)) { // skip level
			try {
				addMonster();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
			this.monsterCount = maxMonster;
			BattleField.getEntitiesHolder().stream().filter(i -> i instanceof Monster).map(i -> (Monster) i)
					.forEach(m -> m.setAlive(false));
			BattleField.getEntitiesHolder().stream().filter(i -> i instanceof Monster).map(i -> (Monster) i)
					.forEach(BattleField::destroyEntities);
			
		} else if (InputUtility.isKeyPressed(KeyCode.M)) { // money hag
			GameLogic.heroInBat.earnMoney(999999);
		}

	}

	private Image monsterImg(int i) {
		if (i < 1)
			i = 1;
		else if (i > 3)
			i = 3;
		return ResourceLoader.monsterImage[i - 1];
	}

	public void setDunLvl(int dunLvl) {
		this.dunLvl = dunLvl;
	}

	public void genMonster(int img, int row, int col, int size, int time) {
		if (time <= 0 || time > 4)
			time = 1;
		if (img < 1 || img > 3)
			return;
		if (row < 0 || row > 1)
			return;
		if (col < 0 || col > 3)
			return;
		int mass = (int) (Math.pow((float) ((4 - col) / (row + 1)), 2) * 27 + img * 23);
		int hp = (int) (Math.pow(11.0, (3 - img % 3)) + ((col % 2 + 1) * 23) / (row + 1)) + (size - 1) * 221;
		int idle = (int) Math.max(((img % 3 + 1) * (30 - (dunLvl / 100.0))) + (mass / 100.0), 70);
		int speed = (int) (9 - ((4 - col) / (row + 1)) - (mass / 2000.0));
		Hero hero;
		int rand = RandomUtility.randomInt(0, 4);
		System.out.println(rand);
		if (rand < 2) {
			hero = rand % 2 == 0 ? new Magician() : new Knight();
			
		} else {
			hero = new Archer();
		}
		for (int i = 0; i < time; i++) {
			BattleField.addEntities(new Monster(ResourceLoader.monsterImage[img-1], row, col, speed, mass, hp * ((dunLvl / 20) + 1), Math.max(
					//(int) ((((2 - img % 3) / 3.0 * 300.0) + 13 * (col * img) + 6 * (row) + hp / 1377.0 * 400) / 2), 15),
					RandomUtility.randomInt(10, 60),40+rand*12),
					hero, idle, (1 + img % 3), (3 - img % 3) * 300, speed * (3 - img % 3) * 40,
					(int) ((hp / Constant.BOUNTY_MULTIPLYER * dunLvl)
							+ ((9 - ((4 - col) / (row + 1)) - (mass / 2000.0))) * 3 + (img - 1) * 50),
					size));
			monsterCount += 1;
		}
		if(dunLvl == 10&& monsterCount==maxMonster) {
			BattleField.addEntities(new Monster(ResourceLoader.darklorde, row, col, speed,(int) 1.7*mass,1000, //(int)1.7*Math.max(
//					(int) ((((2 - img % 3) / 3.0 * 300.0) + 13 * (col * img) + 6 * (row) + hp / 1377.0 * 400) / 2), 15),
					300,
					new Archer(), idle, (1 + img % 3), (3 - img % 3) * 300, speed * (3 - img % 3) * 40,
					(int) ((hp / Constant.BOUNTY_MULTIPLYER * dunLvl)
							+ ((9 - ((4 - col) / (row + 1)) - (mass / 2000.0))) * 3 + (img - 1) * 50),
					1.5*size));
		}else if(dunLvl == 7&& monsterCount==maxMonster) {
			BattleField.addEntities(new Monster(ResourceLoader.witch, row, col, speed,(int)1.5*mass,500,//(int)1.5* Math.max(
//					(int) ((((2 - img % 3) / 3.0 * 300.0) + 13 * (col * img) + 6 * (row) + hp / 1377.0 * 400) / 2), 15),
					250,
					new Magician(), idle, (1 + img % 3), (3 - img % 3) * 300, speed * (3 - img % 3) * 40,
					(int) ((hp / Constant.BOUNTY_MULTIPLYER * dunLvl)
							+ ((9 - ((4 - col) / (row + 1)) - (mass / 2000.0))) * 3 + (img - 1) * 50),
					1.5*size));
		}else if(dunLvl == 3&& monsterCount==maxMonster) {
			BattleField.addEntities(new Monster(ResourceLoader.viper, row, col, speed, (int) 1.3*mass,200,//(int) 1.3* Math.max(
//					(int) ((((2 - img % 3) / 3.0 * 300.0) + 13 * (col * img) + 6 * (row) + hp / 1377.0 * 400) / 2), 15),
					200,
					new Archer(), idle, (1 + img % 3), (3 - img % 3) * 300, speed * (3 - img % 3) * 40,
					(int) ((hp / Constant.BOUNTY_MULTIPLYER * dunLvl)
							+ ((9 - ((4 - col) / (row + 1)) - (mass / 2000.0))) * 3 + (img - 1) * 50),
					1.5*size));
		}
	}

	public void genByLvl(int rand) {
		if (rand < 0)
			return;
		for (int i = 0; i < rand; i++) {
			int col = RandomUtility.random() % 4;
			genMonster(RandomUtility.randomInt(0,4), (dunLvl % 10) > 5 ? 0 : col % 2, col, 1, i);
		}
	}

	private void addMonster() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException {
		if (RenderableHolder.getInstance().size() >= 25)
			return;
		int rand = RandomUtility.randomByLevel(dunLvl) % 3 + 1;
		if (dunLvl > 8) {
			genByLvl(1);
			genByLvl(rand - 1);
		} else if (dunLvl > 6) {
			genByLvl(rand - 3);
			genByLvl(2);
			genByLvl(1);
		} else if (dunLvl > 4) {
			genByLvl(rand - 1);
			genByLvl(1);
		} else if (dunLvl > 2) {
			genByLvl(rand % 2);
			genByLvl(rand - 1);
		} else {
			genByLvl(rand);
		}
	}
}
