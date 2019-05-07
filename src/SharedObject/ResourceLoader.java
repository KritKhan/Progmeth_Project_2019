package SharedObject;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public abstract class ResourceLoader {
	//font
	public static FontLoader fontLoader;
	
	//character
	public static Image magician;
	public static Image knight;
	public static Image archer;
	public static Image viper;
	public static Image witch;
	public static Image darklorde;
	public static Image lion;
	public static Image wolf;
	public static Image armor;
	public static Image[] monsterImage = new Image[3];
	//hero face
	public static Image magicianFace;
	public static Image knightFace;
	public static Image archerFace;
	public static Image magicianFaceH;
	public static Image knightFaceH;
	public static Image archerFaceH;
	
	//background
	public static Image map;
	public static Image dirtland;
	public static Image dungeon1;
	public static Image dungeon2;
	public static Image waterDun;
	public static Image grassland;
	public static Image waterland;
	public static Image homebg;
	public static Image selectbg;
	
	//button
	public static Image startbtn;
	public static Image starthili;
	public static Image okbtn;
	public static Image okhili;
	
	//item
	public static Image hp;
	public static Image mp;
	public static Image mix;
	public static Image hphili;
	public static Image mphili;
	public static Image mixhili;
	
	//effect
	public static Image aEffect;
	public static Image aEffect2;
	public static Image aEffect3;
	public static Image aEffect4;
	public static Image mEffect;
	public static Image mEffect2;
	public static Image mEffect3;
	public static Image mEffect4;
	public static Image kEffect;
	public static Image kEffect2;
	public static Image neweff;
	
	public static Image logo;

	public static Image statusBar;
	//select
	public static Image select;
	public static Thread loadThread;
	
	static {
		ResourceLoader.loadResource();
		initializeSecondLoad();
	}
	
	static void loadResource() {
		try {
			fontLoader = Toolkit.getToolkit().getFontLoader();
			
			//Character
			magician = new Image(ClassLoader.getSystemResource("Character/Magician.png").toString());
			knight = new Image(ClassLoader.getSystemResource("Character/Knight.png").toString());
			archer = new Image(ClassLoader.getSystemResource("Character/Archer.png").toString());
			darklorde = new Image(ClassLoader.getSystemResource("Character/DarkLord.png").toString());
			witch = new Image(ClassLoader.getSystemResource("Character/Witch.png").toString());
			viper = new Image(ClassLoader.getSystemResource("Character/Viper.png").toString());
			lion = new Image(ClassLoader.getSystemResource("Character/Lion.png").toString());
			wolf = new Image(ClassLoader.getSystemResource("Character/Wolf.png").toString());
			armor = new Image(ClassLoader.getSystemResource("Character/Armor.png").toString());
			
			
			//Hero Face
			magicianFace = new Image(ClassLoader.getSystemResource("Character/MagicianFace.png").toString());
			knightFace = new Image(ClassLoader.getSystemResource("Character/KnightFace.png").toString());
			archerFace = new Image(ClassLoader.getSystemResource("Character/ArcherFace.png").toString());
			magicianFaceH = new Image(ClassLoader.getSystemResource("Character/MagicianFaceH.png").toString());
			knightFaceH = new Image(ClassLoader.getSystemResource("Character/KnightFaceH.png").toString());
			archerFaceH = new Image(ClassLoader.getSystemResource("Character/ArcherFaceH.png").toString());
			
			//BG
			map = new Image(ClassLoader.getSystemResource("BGandButton/Map.jpg").toString());
			dirtland = new Image(ClassLoader.getSystemResource("BGandButton/Dirt.jpg").toString());
			dungeon1 = new Image(ClassLoader.getSystemResource("BGandButton/Dun1.jpg").toString());
			dungeon2 = new Image(ClassLoader.getSystemResource("BGandButton/Dun2.jpg").toString());
			waterDun = new Image(ClassLoader.getSystemResource("BGandButton/DunWater.jpg").toString());
			grassland = new Image(ClassLoader.getSystemResource("BGandButton/Grass.jpg").toString());
			waterland = new Image(ClassLoader.getSystemResource("BGandButton/Water.jpg").toString());
			homebg = new Image(ClassLoader.getSystemResource("BGandButton/Main.jpg").toString());
			selectbg = new Image(ClassLoader.getSystemResource("BGandButton/Select8.jpg").toString());
			
			//Button
			startbtn = new Image(ClassLoader.getSystemResource("BGandButton/Startbutton.png").toString());
			starthili = new Image(ClassLoader.getSystemResource("BGandButton/StartbuttonH.png").toString());
			okbtn = new Image(ClassLoader.getSystemResource("BGandButton/OKbutton.png").toString());
			okhili = new Image(ClassLoader.getSystemResource("BGandButton/OKbuttonH.png").toString());
			
			//item
			hp = new Image(ClassLoader.getSystemResource("Potion/hpPotion.png").toString());
			mp = new Image(ClassLoader.getSystemResource("Potion/mpPotion.png").toString());
			mix = new Image(ClassLoader.getSystemResource("Potion/mixPotion.png").toString());
			hphili = new Image(ClassLoader.getSystemResource("Potion/HpPotionH.png").toString());
			mphili = new Image(ClassLoader.getSystemResource("Potion/HpPotionH.png").toString());
			mixhili = new Image(ClassLoader.getSystemResource("Potion/MixPotion.png").toString());
			
			logo = new Image(ClassLoader.getSystemResource("BGandButton/Logo8.png").toString());
			
			//Select
			select = new Image(ClassLoader.getSystemResource("BGandButton/choose hero2.png").toString());
			
			statusBar = new Image(ClassLoader.getSystemResource("BGandButton/StatusBar.png").toString());
		}catch (NullPointerException e) {
			System.out.println("resource1 not found");
		}
		
	}
	
	public static void initializeSecondLoad() {

		loadThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					
					aEffect = new Image(ClassLoader.getSystemResource("Character/ArcherFxR.gif").toString());
					aEffect2 = new Image(ClassLoader.getSystemResource("Character/ArcherFxL.gif").toString());
					aEffect3 = new Image(ClassLoader.getSystemResource("Character/ArcherFxU.gif").toString());
					aEffect4 = new Image(ClassLoader.getSystemResource("Character/ArcherFxD.gif").toString());
					mEffect = new Image(ClassLoader.getSystemResource("Character/MageFxR.gif").toString());
					mEffect2 = new Image(ClassLoader.getSystemResource("Character/MageFxL.gif").toString());
					mEffect3 = new Image(ClassLoader.getSystemResource("Character/MageFxU.gif").toString());
					mEffect4 = new Image(ClassLoader.getSystemResource("Character/MageFxD.gif").toString());
					kEffect = new Image(ClassLoader.getSystemResource("Character/KnightFxL.gif").toString());
					kEffect2 = new Image(ClassLoader.getSystemResource("Character/KnightFxR.gif").toString());
					neweff = new Image(ClassLoader.getSystemResource("Character/neweff.gif").toString());

					monsterImage[0] = lion;
					monsterImage[1] = wolf;
					monsterImage[2] = armor;
				}catch (NullPointerException e) {
					System.out.println("resource2 not found");
				}
			}
		});
		loadThread.setPriority(Thread.MAX_PRIORITY);		
	}
	
	public static void startSecondLoad() {
		try {
			loadThread.start();
		} catch (NullPointerException e) {
			System.out.println("not initialize");
		}
	}
	
	public static boolean isLoadFinish() {
		return !(loadThread.isAlive());
	}

}