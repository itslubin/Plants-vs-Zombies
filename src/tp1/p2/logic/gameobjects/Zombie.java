package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Zombie extends GameObject {
	
	public static final int ENDURANCE = 5;
    public static final int DAMAGE = 1;
    public static final int SPEED = 2;
    
    protected int ini_cycle;
    
    public Zombie() {};
    
    public Zombie(GameWorld g, int c, int r) {
    	super(g, c, r);
    	this.life = Zombie.ENDURANCE;
    	this.ini_cycle = Zombie.SPEED;
    	this.ciclo = ini_cycle;
    	
    }
    
	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	protected String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}
	
	
	public String getName() {
		return Messages.ZOMBIE_NAME;
	}

	@Override
	public String getDescription() {
		return Messages.zombieDescription(Messages.ZOMBIE_NAME, Zombie.SPEED, Zombie.DAMAGE, Zombie.ENDURANCE);
	}

	@Override
	public void update() {
		// Si el zombie está vivo, miramos el objecto adyacente 
		if(isAlive()) {
			
			// Si la posicion del tablero esta completamente ocupada
			if(game.isFullyOcuppied(col - 1, row)) {
				GameItem item = game.getGameObjectInPosition(col - 1, row);
				item.receiveZombieAttack(DAMAGE);
				if(this.ciclo == 0) {
		    		this.ciclo = ini_cycle;
		    	}
			}
			// Si esta vacio o hay sol/es
			else {
				if(this.ciclo == 0) {
		    		this.col--;
		    		this.ciclo = ini_cycle;
		    	} 
				GameItem it = game.getGameObjectInPosition(col - 1, row);
				
	    		// Si esta vacio, no come; pero si es una planta come
	    		if(it != null) it.receiveZombieAttack(DAMAGE);
			}
			if(col < 0) {
				game.ZombieWins();		
			}
			
			this.ciclo--;
		}
		
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void onExit() {
		game.reduceZombies();
	}
	
	public Zombie create(GameWorld game, int col, int row) {
		return new Zombie(game,col,row);
	}

	/*Estaria mejor hacer 2 metodos distintos de receiveCherryBombattack y receivePeashooteracttack*/
	@Override
	public boolean receivePlantAttack(int damage, boolean explosion) {
		if(isAlive()) {
			life -= damage;
			if(!isAlive()) {
				game.addScore(explosion);
			}
			return true;
		}
		return false;
	}
	

	@Override
	public boolean fillPosition() {
		return true;
	}

	@Override
	public boolean catchObject() {
		return false;
	}

}
