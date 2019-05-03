package SharedObject;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public abstract class ResourceLoader {
	//font
	public static Font mainFont = Font.font("Font/Dipaculiwae-Regular.ttf");
	public static FontLoader fontLoader;
	
	//character
	public static Image magician;
	public static Image knight;
	public static Image archer;
	public static Image viper;
	public static Image witch;
	public static Image darklord;
	public static Image minion;
	
	//hero face
	public static Image magicianFace;
	public static Image knightFace;
	public static Image archerFace;
	
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
	
	public static Thread loadThread;
	
	private static void loadResource() {
		try {
			fontLoader = Toolkit.getToolkit().getFontLoader();
			
			//Character
			magician = new Image(ClassLoader.getSystemResource("Character/Magician.png").toString());
			knight = new Image(ClassLoader.getSystemResource("Character/Knight.png").toString());
			archer = new Image(ClassLoader.getSystemResource("Character/Archer.png").toString());
			viper = new Image(ClassLoader.getSystemResource("Character/Viper.png").toString());
			witch = new Image(ClassLoader.getSystemResource("Character/Witch.png").toString());
			darklord = new Image(ClassLoader.getSystemResource("Character/Darklord.png").toString());
			minion = new Image(ClassLoader.getSystemResource("Character/Minion.png").toString());
			
			//Hero Face
			magicianFace = new Image(ClassLoader.getSystemResource("Character/Magicianface.png").toString());
			knightFace = new Image(ClassLoader.getSystemResource("Character/Knightface.png").toString());
			archerFace = new Image(ClassLoader.getSystemResource("Character/Archerface.png").toString());
			
			//BG
			map = new Image(ClassLoader.getSystemResource("BGandButton/Map.jpg").toString());
			dirtland = new Image(ClassLoader.getSystemResource("BGandButton/Dirt.jpg").toString());
			dungeon1 = new Image(ClassLoader.getSystemResource("BGandButton/Dun1.jpg").toString());
			dungeon2 = new Image(ClassLoader.getSystemResource("BGandButton/Dun2.jpg").toString());
			waterDun = new Image(ClassLoader.getSystemResource("BGandButton/DunWater.jpg").toString());
			grassland = new Image(ClassLoader.getSystemResource("BGandButton/Grass.jpg").toString());
			waterland = new Image(ClassLoader.getSystemResource("BGandButton/Water.jpg").toString());
			homebg = new Image(ClassLoader.getSystemResource("BGandButton/Main.jpg").toString());
			selectbg = new Image(ClassLoader.getSystemResource("BGandButton/Select.jpg").toString());
			
			//Button
			startbtn = new Image(ClassLoader.getSystemResource("BGandButton/Startbutton.png").toString());
			okbtn = new Image(ClassLoader.getSystemResource("BGandButton/OKbutton.png").toString());
			
		}catch (NullPointerException e) {
			System.out.println("resource not found");
		}
		
	}
	
	public static boolean isLoadFinish() {
		return !(loadThread.isAlive());
	}

}