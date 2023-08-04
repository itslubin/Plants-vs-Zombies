package tp1.p2.logic;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.gameobjects.GameObject;

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	// TODO add your code here
	
	boolean execute(Command command) throws GameException;

	void addSun();

	boolean tryToCatchObject(int col, int row) throws GameException;

	boolean addItem(GameObject gameObject);

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed Random seed Used to initialize the game.
	 */
	void reset(Level level, long seed) throws GameException;

	/**
	 * Resets the game.
	 */
	void reset() throws GameException;

	/**
	 * Executes the game actions and update the game objects in the board.
	 * 
	 */
	void update() throws GameException;

	void reduceZombies();

	GameItem getGameObjectInPosition(int col, int row);

	boolean checkCoins(int plantcoins);
	
	void substractCoins(int coins);

	void ZombieWins();
	
	void playerQuits();

	boolean isFullyOcuppied(int col, int row);

	void addGameAction(GameAction action);
	
	void addScore(boolean explosiveAttack);
	
	void addSunCoins();
	
	public String currentScore();
	
	long getSeed();
	
	String getLevelName();
	

}
