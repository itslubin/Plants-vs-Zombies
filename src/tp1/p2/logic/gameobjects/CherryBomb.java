package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant {
	
	public static final int COST = 50;
	public static final int ENDURANCE = 2;
	public static final int DAMAGE = 10;
	public static final int SPEED = 2;
	
	public CherryBomb() {}
	
	public CherryBomb(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.ciclo = SPEED;
		this.life = ENDURANCE;
	}

	@Override
	protected Plant create(GameWorld game, int col, int row) {
		return new CherryBomb(game, col, row);
	}

	@Override
	public int getCost() {
		return CherryBomb.COST;
	}

	@Override
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}

	@Override
	protected String getSymbol() {
		return Messages.CHERRY_BOMB_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.plantDescription(Messages.CHERRY_BOMB_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}
	
	@Override
	public String toString() {
		if (isAlive()) {
			if (ciclo == 0) {
				return Messages.status(getSymbol().toUpperCase(), life);
			}
			return Messages.status(getSymbol(), life);
		} 
		return "";
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(isAlive()) {
			if (this.ciclo == 0) {
				ExplosionAction explosion = new ExplosionAction(col, row, CherryBomb.DAMAGE, true);
				game.addGameAction(explosion);
				life = 0;
			}
			else {
				this.ciclo--;
			}
		}
		
		
	}

}
