package tp1.p2.logic;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.Record;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;

public class Game implements GameStatus, GameWorld {
	
	// ATRIBUTOS
	public static final int INITIAL_SUNCOINS = 50;
	public static final int INITIAL_CYCLES = 0;
	public static final int INITIAL_SCORE = 0;

	private long seed = 0;
	private Level level;
	private int cycle = INITIAL_CYCLES;
	private Random rand;
	private int suncoins = INITIAL_SUNCOINS;
	private ZombiesManager zm;
	private SunsManager sm;
	private GameObjectContainer container;
	private Record record;
	private boolean playerQuits;
	private boolean zombiesWin;
	private boolean newRecord;
	private int score;

	private Deque<GameAction> actions;

	public Game(long seed, Level level) throws GameException {
		this.seed = seed;
		this.level = level;
		this.rand = new Random(this.seed);
		reset();
	}

	/**
	 * Resets the game.
	 * @throws IOException 
	 */
	@Override
	public void reset() throws GameException {
		reset(this.level, this.seed);
	}

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed Random seed Used to initialize the game.
	 * @throws IOException 
	 */
	@Override
	public void reset(Level level, long seed) throws GameException {
		if(level != null) {
			this.level = level;
		}
        if(seed != 0) {
        	this.seed = seed;
        }
        
        this.rand = new Random(this.seed);
        this.actions = new ArrayDeque<>();
        this.record = new Record(this.level, this);
		this.cycle = INITIAL_CYCLES;
		this.suncoins = INITIAL_SUNCOINS;
		this.score = INITIAL_SCORE;
		this.zm = new ZombiesManager(this, this.level, rand);
		this.sm = new SunsManager(this, rand);
		this.container = new GameObjectContainer();
		this.playerQuits = false;
		this.newRecord = false;
		
		this.actions = new ArrayDeque<>();
		
	}
	
	public void addGameAction(GameAction action) {
		actions.add(action);
	}


	/**
	 * Executes the game actions and update the game objects in the board.
	 * @throws IOException 
	 * 
	 */
	@Override
	public void update() throws GameException {

		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		zm.update();
		sm.update();

		// 3. Game object updates
		container.update();

		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.container.removeDead();

			// 5. execute pending actions
			executePendingActions();
		}

		this.cycle++;

		// 6. Notify commands that a new cycle started
		Command.newCycle();
		
		// 7. Update record
		record.update();

	}
	
	@Override
	public void addSun() {
		sm.addSun();
	}
	
	public void addSunCoins() {
		this.suncoins += 10;
	}
	
	
	public int getCatchedSuns() {
		return sm.getCatchedSuns();
	}
	
	public int getGeneratedSuns() {
		return sm.getGeneratedSuns();
	}


	@Override
	public boolean tryToCatchObject(int col, int row) {
		boolean catched = false;
		while(container.tryCatchSun(col, row)) {
			suncoins += 10;
			catched = true;
			sm.addCatchedSuns();
			
		}
		return catched;
	}

	@Override
	public boolean addItem(GameObject gameObject) {
		return container.add(gameObject);
		
	}
	
	public int getScore() {
		return score;
	}
	
	
	public void addScore(boolean explosiveAttack) {
		if(explosiveAttack) {
			this.score += 20;
		}
		else {
			this.score += 10;
		}
		
		this.newRecord = record.newScore(score);
	}

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}
	

	@Override
	public int getCycle() {
		return cycle;
	}

	@Override
	public int getSuncoins() {
		return suncoins;
	}
	
	@Override
	public boolean isPlayerQuits() {
		return playerQuits;
	}
	
	@Override
	public void playerQuits() {
		playerQuits = true;
	}
	
	@Override
	public int getRemainingZombies() {
		return zm.getRemainingZombies();
	}

	@Override
	public boolean isZombieWins() {
		return zombiesWin;
	}
	
	@Override
	public void ZombieWins() {
		zombiesWin = true;
	}

	public boolean isFinished() {
		return playerQuits || zombiesWin || zm.isPlayerWins();
	}
	
	public String currentScore() {
		return record.currentScore();
	}
	
	public boolean isNewRecord() {
		return this.newRecord;
	}

	@Override
	public void reduceZombies() {
		zm.reduceZombies();
	}

	
	@Override
	public GameItem getGameObjectInPosition(int col, int row) {
		return container.get(col, row);
	}
	
	
	public boolean execute (Command command) throws GameException {
		return command.execute(this);
	}
	
	public long getSeed() {
		return this.seed;
	}
	
	public String getLevelName() {
		return this.level.name();
	}

	@Override
	public String positionToString(int col, int row) {
		return container.positionToString(col, row);
	}

	

	@Override
	public boolean checkCoins(int plantcoins) {
		return suncoins >= plantcoins;
	}
	
	public void substractCoins(int coins) {
		suncoins -= coins;
	}
	

	@Override
	public boolean isFullyOcuppied(int col, int row) {
		return this.container.isFullyOccupied(col, row);
	}

}
