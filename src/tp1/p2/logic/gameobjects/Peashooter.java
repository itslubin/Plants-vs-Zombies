package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {
	
	public static final int COST = 50;
	public static final int ENDURANCE = 3;
	public static final int DAMAGE = 1;
	public static final int SPEED = 1;
	
	public Peashooter() {}
	
	public Peashooter(GameWorld game, int col, int row) {
		this.ciclo = Peashooter.SPEED;
		this.life = Peashooter.ENDURANCE;
		this.game = game;
		this.col = col;
		this.row = row;
		
	}

	@Override
	protected String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.plantDescription(Messages.PEASHOOTER_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}

	@Override
	public int getCost() {
		return Peashooter.COST;
	}

	@Override
	public String getName() {
		return Messages.PEASHOOTER_NAME;
	}
	
	@Override
	protected Peashooter create(GameWorld game, int col, int row) {
		return new Peashooter(game, col, row);
	}

	@Override
	public void update() {
		if(isAlive()) {
			for(int i = this.col + 1; i < GameWorld.NUM_COLS; i++) {
				GameItem item = game.getGameObjectInPosition(i, row);
				if(item != null && item.receivePlantAttack(Peashooter.DAMAGE, false)) {
					break;
				}
			}
    	}
	}
	
}
