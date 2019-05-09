package SharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import SharedObject.IRenderable;

public class RenderableHolder {
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	private static final RenderableHolder instance = new RenderableHolder();

	static {
		ResourceLoader.loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void sort() throws IllegalArgumentException {
		entities.sort(comparator);
	}

	public void clear() {
		for (int i = entities.size() - 1; i <= 0; i--) {
			if (entities.get(i) instanceof GameObject) {
				entities.remove(i);
			}
		}
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
		this.entities.sort(comparator);
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public int size() {
		return entities.size();
	}

}