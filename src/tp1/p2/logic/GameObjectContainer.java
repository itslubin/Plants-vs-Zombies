package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) {
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public boolean removeDead() {
		boolean dead = false;
		for (int i = 0; i < gameObjects.size(); ++i) {
			GameObject o = gameObjects.get(i);
			if(!o.isAlive()) {
				o.onExit();
				gameObjects.remove(o);
				dead = true;
			}
		}
		return dead;
	}


	public boolean isPositionEmpty(int col, int row) {
		for (GameObject go : gameObjects) {
			if(go.isInPosition(col, row)) {
				return false;
			}
		}
		return true;
	}

	public boolean add(GameObject o) {
		return gameObjects.add(o);
		
	}
	
	public void update() {
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).update();
		}
	}

	public GameItem get(int col, int row) {
		for(int i = 0; i < gameObjects.size(); ++i) {
			// unicamente se hace "get" de los objetos de los que no pueda hacer "catch" como zombies o plantas
			if(!gameObjects.get(i).catchObject() && gameObjects.get(i).isInPosition(col, row)) {
				return gameObjects.get(i);
			}
		}
		return null;
	}
	

	public boolean isFullyOccupied(int col, int row) {
		int i = 0;
		boolean fullyOcuppied = false;

		while (i<gameObjects.size() && !fullyOcuppied) {
			GameObject g = gameObjects.get(i);
			if (g.isAlive() && g.isInPosition(col, row)) {
				fullyOcuppied = g.fillPosition();
			}
			i++;
		}

		return fullyOcuppied;
	}
	
	public boolean tryCatchSun(int col, int row) {
		for (GameObject obj : gameObjects) {
			// Si catchObject es true, el objeto es un Sun
			if (obj.isInPosition(col, row) && obj.catchObject()) {
				gameObjects.remove(obj);
				return true;
			}
		}
		return false;
	}

}
