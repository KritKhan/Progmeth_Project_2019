package SharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import SharedObject.IRenderable;
import javafx.scene.image.Image;

public class RenderableHolder {
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static final RenderableHolder instance = new RenderableHolder();
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
	
	static {
		loadResource();
	}
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator =(IRenderable o1,IRenderable o2) -> {
			if(o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	
	private static void loadResource() {
		try {
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
			map = new Image(ClassLoader.getSystemResource("BG/Map.jpg").toString());
			dirtland = new Image(ClassLoader.getSystemResource("BG/Dirt.jpg").toString());
			dungeon1 = new Image(ClassLoader.getSystemResource("BG/Dun1.jpg").toString());
			dungeon2 = new Image(ClassLoader.getSystemResource("BG/Dun2.jpg").toString());
			waterDun = new Image(ClassLoader.getSystemResource("BG/DunWater.jpg").toString());
			grassland = new Image(ClassLoader.getSystemResource("BG/Grass.jpg").toString());
			waterland = new Image(ClassLoader.getSystemResource("BG/Water.jpg").toString());
		}catch (NullPointerException e) {
			System.out.println("resource not found");
		}
		
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities,comparator);
	}
	
	public void update() {
		for (int i = entities.size() - 1; i>=0; i--) {
			if(entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}
	
	public static RenderableHolder getInstance() {
		return instance;
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
	
}