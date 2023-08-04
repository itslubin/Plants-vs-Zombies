package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {

	protected GameWorld game;

	protected int col;

	protected int row;
	
	protected int life;

	protected int ciclo;


	GameObject() {
	}

	GameObject(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
	}

	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}
	
	public int getlife() {
		return life;
	}
	
	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
	
	public boolean isAlive() {
		return life > 0;
	}

	public String toString() {
		if (isAlive()) {
			return Messages.status(getSymbol(), life);
		} else {
			return "";
		}
	}
	

	abstract protected String getSymbol();

	abstract public String getDescription();

	abstract public void update();
	
	abstract public void onEnter();
	
	abstract public void onExit();
}
