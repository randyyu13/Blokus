package blokus;

import java.awt.Graphics;

public abstract class GameObject {
	protected int x;
	protected int y;
	
	protected ID id;
	
	public GameObject() { //default constructor
		
	}
	
	public GameObject(int x, int y, ID id) { //loaded constructor
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
}
