package tp1.p2.logic;

public interface GameStatus {
	
	boolean isPlayerQuits();
	boolean isFinished();

	/**
	 * Get game cycles.
	 * 
	 * @return the game cycles
	 */
	int getCycle();

	/**
	 * Get available suncoins
	 * 
	 * @return the available suncoins
	 */
	int getSuncoins();

	/**
	 * Get number of remaining zombies for this game.
	 * 
	 * @return the remaining zombies.
	 */
	int getRemainingZombies();

	/**
	 * Draw a cell of the game.
	 * 
	 * @param col Column of the cell to draw.
	 * @param row Row of the cell to draw.
	 * 
	 * @return a string that represents the content of the cell (col, row).
	 */
	String positionToString(int col, int row);

	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns
	 */
	
	boolean isZombieWins();
	
	int getScore();
	
	boolean isNewRecord();
	
	int getCatchedSuns();
	
	int getGeneratedSuns();
	

}
