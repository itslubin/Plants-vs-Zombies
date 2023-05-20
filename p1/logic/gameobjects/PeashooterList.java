package tp1.p1.logic.gameobjects;

import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

public class PeashooterList {
	private int cont = 0;
	private Peashooter[] ps;
	private  Game game;
	
	public PeashooterList(Game game) {
		this.ps = new Peashooter[Game.NUM_COLS * Game.NUM_ROWS];
		this.game = game;
	}
	
	public String positionToStringPeashooter(int x, int y) {
		
		for (int i = 0; i < cont; i++) {
			if ((ps[i].getX() == x) && (ps[i].getY() == y) && ps[i].getResistencia() > 0) {
				return ps[i].positionToStringPeashooter();
			}
		}
		return "";
	}
	
	public void crearPeashooter(int col, int row) {
		Peashooter newps = new Peashooter(col, row, game);
		ps[cont] = newps;
		cont++;
		
	}
	
	public void updatePeashooters(){
		
		for (int i = 0; i < cont; i++) {
			ps[i].updatePeashooter();
			
		}
		
	}
	
	public boolean existePeashooter(int x, int y) {
		int i = 0;
		while (i < cont) {
			if (ps[i].getX() == x && ps[i].getY() == y) {
				return true;
			}
			i++;
		}
		
		return false;
	}
	
	public void disminuirvidaP(int x, int y, int danyo) {
		int i = 0, pos = -1;
		while (i < cont) {
			if (ps[i].getX() == x - 1 && ps[i].getY() == y) {
				pos = i;
			}
			i++;
		}
		
		if (pos != -1) {
			ps[pos].disminuirvida(danyo);
			if (ps[pos].getResistencia() == 0) {
				// borrar peashooter de la lista
				for (int j = pos; j < cont - 1; j++) {
					ps[j] = ps[j + 1];
				}
				cont--;
			}
		}
		
		
		
	}
}
