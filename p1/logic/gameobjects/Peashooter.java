package tp1.p1.logic.gameobjects;

import tp1.p1.logic.Game;
import tp1.p1.view.Messages;
import tp1.p1.logic.gameobjects.*;

public class Peashooter {
	
	public static int COSTE = 50;
	public static int INI_RESISTENCIA = 3;
	public static int DANYO = 1;
	public static int INI_FRECUENCIA = 1;

	
	
	private int x;
	private int y;
	private int resistencia = INI_RESISTENCIA;
	private int frecuencia = INI_FRECUENCIA;
	private Game game;
	
	
	public Peashooter( int x, int y, Game game) {
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
	
	
	
	public static String getDescription() {
		return Messages.PEASHOOTER_DESCRIPTION.formatted(COSTE, DANYO, INI_RESISTENCIA);
	}
	
	public void disminuirvida(int danyo) {
		this.resistencia -= danyo;
	}
	
	
	public void updatePeashooter() {
		this.frecuencia--;
		if (this.frecuencia == 0) {
			game.disminuirvidaz(x, y, DANYO);
			this.frecuencia = INI_FRECUENCIA;
		}
		
	}
	
public String positionToStringPeashooter() {
		return Messages.PEASHOOTER_ICON.formatted(resistencia);
	}


}
