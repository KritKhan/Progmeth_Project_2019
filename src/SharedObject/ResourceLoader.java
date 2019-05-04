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
	public static Image minion;
	
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

	
	public static Thread loadThread;
	
	static {
		ResourceLoader.loadResource();
	}
	
	static void loadResource() {
		try {
			fontLoader = Toolkit.getToolkit().getFontLoader();
			
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
			
		}catch (NullPointerException e) {
			System.out.println("resource not found");
		}
		
	}
	
	public static boolean isLoadFinish() {
		return !(loadThread.isAlive());
	}

}