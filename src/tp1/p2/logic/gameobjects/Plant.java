package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;

public abstract class Plant extends GameObject {

	@Override
	public boolean receiveZombieAttack(int damage) {
		if(isAlive()) {
			life -= damage;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean receivePlantAttack(int damage, boolean explosion) {
		return false;
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void onExit() {
		
	}
	
	@Override
	public boolean catchObject() {
		return false;
	}
	
	@Override
	public boolean fillPosition() {
		return true;
	}
	
	protected abstract Plant create(GameWorld game, int col, int row);
	
	abstract public int getCost();
	
	abstract public String getName();
	
	
	
}
