package tp1.p1.logic.gameobjects;

import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

public class Sunflower {
	
	
	public static int COSTE = 20;
	public static int INI_RESISTENCIA = 1;
	public static int DANYO = 0;
	public static int INI_FRECUENCIA = 3;
	public static int SOLES = 10;
	
	
	private int x;
	private int y;
	private int resistencia = INI_RESISTENCIA;
	private int frecuencia = INI_FRECUENCIA;
	private Game game;
	
	
	public Sunflower( int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	
	public int getResistencia() {
		return this.resistencia;
	}
	
	
	public void disminuirvida(int danyo) {
		this.resistencia -= danyo;
	}
	
	public static String getDescription() {
		return Messages.SUNFLOWER_DESCRIPTION.formatted(COSTE, DANYO, INI_RESISTENCIA);
	}
	
	public void updateSunflower() {
		if (this.frecuencia == 0) {
			game.aumentarSoles(SOLES);
			this.frecuencia = INI_FRECUENCIA - 1;
		}
		else {
			this.frecuencia--;
		}
		
	}
	
	public String positionToStringSunflower() {
		return Messages.SUNFLOWER_ICON.formatted(resistencia);
	}

}
