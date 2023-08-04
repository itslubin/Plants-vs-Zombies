package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie {
	
	public static final int ENDURANCE = 8;
    public static final int SPEED = 4;
    
    public BucketHead() {}
    
    public BucketHead(GameWorld game, int col, int row) {
    	super(game, col, row);
    	this.life = BucketHead.ENDURANCE;
    	this.ini_cycle = BucketHead.SPEED;
    	this.ciclo = ini_cycle;
    	
    }
    
    public BucketHead create(GameWorld game, int col, int row) {
		return new BucketHead(game,col,row);
	}
    
    @Override
	protected String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
	}
	
	
	public String getName() {
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}

	@Override
	public String getDescription() {
		return Messages.zombieDescription(Messages.BUCKET_HEAD_ZOMBIE_NAME, SPEED, DAMAGE, ENDURANCE);
	}
}
