package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie{
	
	public static final int ENDURANCE = 2;
    public static final int SPEED = 1;
    
    public Sporty() {}
    
    public Sporty(GameWorld game, int col, int row) {
    	super(game, col,row);
    	this.life = Sporty.ENDURANCE;
    	this.ini_cycle = Sporty.SPEED;
    	this.ciclo = ini_cycle;
    }
    
    public Sporty create(GameWorld game, int col, int row) {
		return new Sporty(game,col,row);
	}
    
    @Override
   	protected String getSymbol() {
   		return Messages.SPORTY_ZOMBIE_SYMBOL;
   	}
   	
   	
   	public String getName() {
   		return Messages.SPORTY_ZOMBIE_NAME;
   	}

   	@Override
   	public String getDescription() {
   		return Messages.zombieDescription(Messages.SPORTY_ZOMBIE_NAME, SPEED, DAMAGE, ENDURANCE);
   	}
}
