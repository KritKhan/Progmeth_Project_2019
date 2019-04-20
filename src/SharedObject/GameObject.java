package SharedObject;

public abstract class GameObject implements IRenderable{
	protected Pair pos;
	protected int z;
	protected boolean visible;
	
	public GameObject(double x,double y,int z) {
		super();
		this.pos = new Pair(x, y);
		this.z = z;
		this.visible = true;	
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible ==  true;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return visible == false;
	}
	
	public void destroyed() {
		visible = false;
	}
	public double getX() {
		return pos.x;
	}
	public double getY() {
		return pos.y;
	}
	public void setX(double x) {
		pos.x = x;
	}
	public void setY(double y) {
		pos.y = y;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public abstract double getWidth();
	public abstract double getHeight();

}
