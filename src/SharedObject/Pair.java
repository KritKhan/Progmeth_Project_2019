package SharedObject;

public class Pair {
	public double x;
	public double y;

	public Pair(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Pair(Pair p) {
		this.x = p.x;
		this.y = p.y;
	}

	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}

	public double diffX(double x) {
		return Math.abs(this.x - x);
	}

	public double diffY(double y) {
		return Math.abs(this.y - y);
	}

	public double diffD(double x, double y) {
		return Math.pow((Math.pow(diffX(x), 2)) + (Math.pow(diffY(y), 2)), 0.5);
	}
}
