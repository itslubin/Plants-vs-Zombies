package tp1.p1.logic;

import java.util.Random;

import tp1.p1.control.Level;
import tp1.p1.logic.gameobjects.*;
import tp1.p1.view.Messages;


public class Game {

	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;
	public static final int INI_SOLES = 50;
	public static int INI_CICLOS = 0;
	
	
	private long seed;
	private Level level;
	private Random random;
	private SunflowerList sfl;
	private PeashooterList psl;
	private ZombiesManager zm; // es el que controla y añadde los zombies
	private int soles = INI_SOLES; 
	private int ciclos = INI_CICLOS;
	

	public Game(long iniseed, Level inilevel) {
		
		this.seed = iniseed;
		this.level = inilevel;
		this.random = new Random(iniseed);
		// Crear las listas
		sfl = new SunflowerList(this);
		psl = new PeashooterList(this);
		zm = new ZombiesManager(this, inilevel, random);
		
		
	}
	
	public boolean comprobarmonedas(int monedasUsuario, int costePlanta){
		if(monedasUsuario >= costePlanta) {
			return true;
		}
		return false;
	}
	
	public boolean comprobarFueraTablero(int x, int y) {
		if (x >= 0 && x < NUM_COLS && y >= 0 && y < NUM_ROWS) {
			return true;
		}
		return false;
	}
	public boolean comprobarPosicionObjetos(int x, int y) {
		return comprobarPosicionPlanta(x, y) && comprobarPosicionZombie(x, y);
	}
	
	public boolean comprobarPosicionPlanta(int x, int y) {
		return !sfl.existeSunflower(x, y) && !psl.existePeashooter(x, y);
		
	}
	
	public boolean existeSunflower(int x, int y) {
		return this.sfl.existeSunflower(x, y);
	}
	
	public boolean existePeashooter(int x, int y) {
		return this.psl.existePeashooter(x, y);
	}
	
	public boolean comprobarPosicionZombie(int x, int y) {
		
		return zm.noExisteZombies(x, y);
	}
	
	public int getciclos() {
		return this.ciclos;
	}
	
	public int getsoles() {
		return this.soles;
	}
	
	public void AtacarPlanta(int x, int y, int danyo){
		this.sfl.disminuirvidaS(x, y, danyo);
		this.psl.disminuirvidaP(x, y, danyo);
	}
	
	
	
	public void disminuirvidaz(int x_ps, int y, int danyo) {
		this.zm.disminuirvidaz(x_ps, y, danyo);
	}
	
	
	public int getRemainingZombies() {
		return this.zm.getRemainingZombies();
	}
	
	public void aumentarSoles(int s) {
		this.soles += s;
	}
	
	private void disminuirSoles(int s) {
		this.soles -= s;
	}
	
	public boolean reset() {
        this.soles = Game.INI_SOLES;
        this.ciclos = Game.INI_CICLOS;
    	this.sfl = new SunflowerList(this);
        this.psl = new PeashooterList(this);
        this.zm = new ZombiesManager(this, this.level, random);
        return true;
    }

	public String positionToString(int col, int row) {
		return (sfl.positionToStringSunflower(col, row) + psl.positionToStringPeashooter(col, row) 
		+ zm.positionToStringZombies(col, row));
	}
	
	public boolean insertarSunflower(int col, int row) {
		
		if (this.comprobarmonedas(this.getsoles(),Sunflower.COSTE)) {
			if (this.comprobarFueraTablero(col, row) && this.comprobarPosicionObjetos(col, row)) {
				
				sfl.crearSunflower(col, row);
				this.disminuirSoles(Sunflower.COSTE);
				this.update();
				return true;
			}
			else {
				System.out.println(Messages.ERROR.formatted(Messages.INVALID_POSITION));
			}
			
		}
		else {
			System.out.println(Messages.ERROR.formatted(Messages.NOT_ENOUGH_COINS));
		}
		
		return false;
		
	}
	
	public boolean insertarPeashooter(int col, int row) {
		if (this.comprobarmonedas(getsoles(),Peashooter.COSTE)) {
			
			if (this.comprobarFueraTablero(col, row) && this.comprobarPosicionObjetos(col, row)) {
				psl.crearPeashooter(col, row);
				this.disminuirSoles(Peashooter.COSTE);
				this.update();
				return true;
			}
			
			else {
				System.out.println(Messages.ERROR.formatted(Messages.INVALID_POSITION));
			}
			
		}
		
		else {
			System.out.println(Messages.ERROR.formatted(Messages.NOT_ENOUGH_COINS));
		}
		
		return false;
		
	}
	
	public boolean Playerwins() {
		
		return zm.winp();
	}
	
	public boolean Zombieswin() {
		
		return zm.winz();
	}
	
	public void update() {
		
		if(zm.addZombie()); // se disminuyen zombies directamente en add
		
		sfl.updateSunflowers();
		psl.updatePeashooters();
		zm.updateZombies();
		ciclos++;
		
	}

}
