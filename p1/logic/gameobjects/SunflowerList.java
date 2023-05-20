package tp1.p1.logic.gameobjects;

import tp1.p1.logic.gameobjects.Sunflower;
import tp1.p1.view.Messages;
import tp1.p1.logic.Game;

public class SunflowerList {
	private int cont = 0;
	private Sunflower[] sf;
	private  Game game;
	
	
	public SunflowerList(Game game) {
		this.sf = new Sunflower[Game.NUM_COLS * Game.NUM_ROWS];
		this.game = game;
	}
	
	
	public String positionToStringSunflower(int x, int y) {
		
		for (int i = 0; i < cont; i++) {
			if ((sf[i].getX() == x) && (sf[i].getY() == y) && sf[i].getResistencia() > 0) {
				return sf[i].positionToStringSunflower();
			}
		}
		return "";
	}
	
	public void crearSunflower(int col, int row) {
		Sunflower newsf = new Sunflower(col, row, game);
		sf[cont] = newsf;
		cont++;
		
	}
	
	public void updateSunflowers(){
		
		for (int i = 0; i < cont; i++) {
			sf[i].updateSunflower();
			
		}
		
	}
	
	public boolean existeSunflower(int x, int y) {
		int i = 0;
		while (i < cont) {
			if (sf[i].getX() == x && sf[i].getY() == y) {
				return true;
			}
			else {
				i++;
			}
		}
		
		return false;
	}
	
	public void disminuirvidaS(int x, int y, int danyo) {
		int i = 0, pos = -1;
		while (i < cont) {
			if (sf[i].getX() == x - 1 && sf[i].getY() == y) {
				pos = i;
			}
			i++;
		}
		
		if (pos != -1) {
			sf[pos].disminuirvida(danyo);
			if (sf[pos].getResistencia() == 0) {
				// borrar sunflower de la lista
				for (int j = pos; j < cont - 1; j++) {
					sf[j] = sf[j + 1];
				}
				cont--;
			}
		}
		
		
	}
	
}
