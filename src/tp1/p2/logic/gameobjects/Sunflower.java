package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant {
	
	public static final int COST = 20;
	public static final int ENDURANCE = 1;
	public static final int DAMAGE = 0;
	public static final int SPEED = 3;
	
	
	public Sunflower() {};
	
	public Sunflower(GameWorld game, int col, int row) {
		this.ciclo = Sunflower.SPEED;
		this.life = Sunflower.ENDURANCE;
		this.game = game;
		this.col = col;
		this.row = row;
		
	}
	
	@Override
	protected String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.SUNFLOWER_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}

	@Override
	public int getCost() {
		return Sunflower.COST;
	}

	@Override
	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}
	
	@Override
	protected Sunflower create(GameWorld game, int col, int row) {
		return new Sunflower(game, col, row);
	}
	
	@Override
	public void update() {
		if(isAlive()) {
			if(this.ciclo == 0) {
	    		// Genera un objeto Sun en una casilla aleatoria
				game.addSun();
	    		this.ciclo = Sunflower.SPEED - 1;
	    	} else this.ciclo--;
		}
	}

}
