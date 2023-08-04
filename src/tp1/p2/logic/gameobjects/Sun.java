package tp1.p2.logic.gameobjects;

import tp1.p2.logic.*;
import tp1.p2.view.Messages;

public class Sun extends GameObject {
	
	// Remember that a Sun is updated the very same cycle is added to the container
	public static final int SUN_COOLDOWN = 10;
	
	public static int GeneratedSuns = 0;
	
	public Sun() {}
	
	public Sun(GameWorld game, int col, int row) {
		super(game, col, row);
		this.life = SUN_COOLDOWN + 1;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage, boolean explosion) {
		return false;
	}
	
	// fillPosition me dice si ocupa la posicion o no
	@Override
	public boolean fillPosition() {
		return false;
	}

	@Override
	public boolean catchObject() {
		return true;
	}

	@Override
	protected String getSymbol() {
		return Messages.SUN_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	}

	@Override
	public void update() {
		life--;
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void onExit() {
		
	}

}
