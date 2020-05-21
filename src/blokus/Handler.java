package blokus;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	private LinkedList<GameObject> objects;
	
	public Handler() {
		objects = new LinkedList<GameObject>();
	}
	
	public void tick() {
		for(GameObject obj : objects) {
			obj.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject obj = objects.get(i);
			obj.render(g);
		}
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
	}
	
	public void removeObject(GameObject obj) { //we will probably never do this so linked list is fine.
		objects.remove(obj);
	}
	
	public LinkedList<GameObject> getHandlerCopy() {
		LinkedList<GameObject> copy = new LinkedList<GameObject>();
		
		for(GameObject obj : objects) {
			copy.add(obj);
		}
		
		return copy;
	}
	
	public void moveToFront(GameObject gameObj) {
		objects.addLast(gameObj);
		objects.removeFirstOccurrence(gameObj);
	}
	

}
