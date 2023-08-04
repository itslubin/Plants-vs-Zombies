package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant {
	
	public static final int COST = 50;
	public static final int ENDURANCE = 10;
	public static final int DAMAGE = 0;
	
	public WallNut() {}
	
	public WallNut(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.life = ENDURANCE;
	}

	@Override
	protected Plant create(GameWorld game, int col, int row) {
		return new WallNut(game, col, row);
	}

	@Override
	public int getCost() {
		return WallNut.COST;
	}

	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}

	@Override
	protected String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.plantDescription(Messages.WALL_NUT_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}

	@Override
	public void update() {
		
	}

}
