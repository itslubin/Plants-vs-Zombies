package tp1.p1.logic;

import java.util.Random;

import tp1.p1.control.Level;
import tp1.p1.logic.gameobjects.ZombieList;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private Game game;

	private Level level;

	private Random rand;

	private int remainingZombies;

	private ZombieList zombies;
	
	
	

	public ZombiesManager(Game game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombies = new ZombieList(this.remainingZombies, game);
	}
	
	public boolean noExisteZombies(int x, int y) {
		return zombies.isPositionEmpty(x, y);
	}
	
	public boolean winz() {
		return this.zombies.winz();
	}
	
	public boolean winp() {
		if (remainingZombies == 0 && !zombies.exzombievivo()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void updateZombies() {
		this.zombies.updateZombies();
	}
	
	public String positionToStringZombies(int x, int y) {
		return this.zombies.positionToStringZombies(x, y);
	}
	
	public void disminuirvidaz(int x_ps, int y, int danyo) {
		this.zombies.disminuirvidaz(x_ps, y, danyo);
	}
	
	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}
	
	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(Game.NUM_ROWS);
	}
	
	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie()
				&& isPositionEmpty(Game.NUM_COLS, row);

		if(canAdd) {
			this.zombies.insertarZombie(Game.NUM_COLS, row);
			this.disminuirRemaningZombies();
		}
		return canAdd;
	}

	public boolean isPositionEmpty(int numCols, int row) {
		
		return this.zombies.isPositionEmpty(numCols, row);
		
	}
	
	

	public int getRemainingZombies() {
		return this.remainingZombies;
	}
	
	public int disminuirRemaningZombies() {
		return this.remainingZombies--;
	}

}
