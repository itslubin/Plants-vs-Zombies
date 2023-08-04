package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie {
	
	public static final int DAMAGE = 3;
    
	public ExplosiveZombie() {}
	    
    public ExplosiveZombie(GameWorld game, int col, int row) {
    	super(game, col, row);
    	// No sobreescribimos los atributos ya que son iguales que los de la clase Zombie
    }
    
    public ExplosiveZombie create(GameWorld game, int col, int row) {
		return new ExplosiveZombie(game,col,row);
	}
    
    @Override
   	protected String getSymbol() {
   		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
   	}
   	
   	
   	public String getName() {
   		return Messages.EXPLOSIVE_ZOMBIE_NAME;
   	}

   	@Override
   	public String getDescription() {
   		return Messages.zombieDescription(Messages.EXPLOSIVE_ZOMBIE_NAME, SPEED, Zombie.DAMAGE, ENDURANCE);
   	}
   	
   	public void onExit() {
		super.onExit();
		ExplosionAction explosion = new ExplosionAction(col, row, ExplosiveZombie.DAMAGE, false);
		game.addGameAction(explosion);
	}
}
