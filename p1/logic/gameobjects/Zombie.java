package tp1.p1.logic.gameobjects;

import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

public class Zombie {
	
	public static int INI_RESISTENCIA = 5;
	public static int DANYO = 1;
	public static int INI_FRECUENCIA = 2;
	public static int AVANCE = 1;
	
	private int x;
	private int y;
	private int resistencia = INI_RESISTENCIA;
	private int frecuencia = INI_FRECUENCIA;
	private int vel;
	private Game game;
	
	public Zombie(int x, int y, Game game) { // crear un zombie
		this.x = x;
		this.y = y;
		this.game = game;
	}
	
	public boolean poderMover(int x, int y) {
		
		return game.comprobarPosicionObjetos(x - 1, y);
	}
	
	public boolean estaVivo() {
		if(this.resistencia == 0) {
			return false;
		}
		return true;
	}
	
	public void updateZombie() {
		
		if (this.frecuencia == 0 && poderMover(x, y)) {
			this.x -= AVANCE;
			// si hay una planta adyacente el zombie come
			
			this.frecuencia = INI_FRECUENCIA - 1;
		}
		else if (game.existeSunflower(x - 1, y) || game.existePeashooter(x - 1, y)){
			game.AtacarPlanta(x, y, DANYO);
		}
		else {
			this.frecuencia--;
		}
		
	}
	
	
	public int getx() {
		return this.x;
	}
	
	public int gety() {
		return this.y;
	}

	public int getResistencia() {
		return this.resistencia;
	}

	public void disminuirvidaz(int x) {
		this.resistencia -= x;
	}
	
	public String positionToStringZombies() {
		return Messages.ZOMBIE_ICON.formatted(resistencia);
	}
	
}
