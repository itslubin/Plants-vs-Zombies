package tp1.p1.logic.gameobjects;

import tp1.p1.logic.Game;
import tp1.p1.view.*;

public class ZombieList {
	private int cont = 0;
	private Zombie[] z;
	private  Game game;

	public ZombieList(int remainingZombies, Game game) {
		this.z = new Zombie[remainingZombies];
		this.game = game;
	}
	
	public void insertarZombie(int col, int fila) { // insertar zombie nuevo
		Zombie newz = new Zombie(col, fila, game);
		z[cont] = newz;
		cont++;
	}
	
    public void updateZombies(){
		
		for (int i = 0; i < cont; i++) {
			z[i].updateZombie();
			
		}
		
	}
    
	
	// to_string de zombies 
    public String positionToStringZombies(int x, int y) {
		
		for (int i = 0; i < cont; i++) {
			if ((z[i].getx() == x) && (z[i].gety() == y) && z[i].getResistencia() > 0) {
				return z[i].positionToStringZombies();
			}
		}
		return "";
	}
	
   public boolean isPositionEmpty(int col, int fila) {
		
		for (int i = 0; i < cont; ++i) {
			if (z[i].getx() == col && z[i].gety() == fila) {
				return false;
			}
		}
		
		return true;
		
	}

	public void disminuirvidaz(int x_ps, int y, int danyo) {
		int min = Game.NUM_COLS, pos = -1;
		for (int i = 0; i < cont; ++i) {
			if (z[i].gety() == y && z[i].getx() < min && z[i].getx() > x_ps && z[i].getResistencia() > 0) {
				min = z[i].getx();
				pos = i;
			}
		}
		if (pos != -1) {
			z[pos].disminuirvidaz(danyo);
			// comprobamos si el zombie ha muerto
			if (z[pos].getResistencia() == 0) {
				// borrar zombie de la lista
				for (int j = pos; j < cont - 1; j++) {
					z[j] = z[j + 1];
				}
				cont--;
			}
		}
	}
	
	public boolean winz() {
		for (int i = 0; i < cont; ++i) {
			if (z[i].getx() < 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean exzombievivo() {
		for (int i = 0; i < cont; ++i) {
			if (z[i].getResistencia() > 0) {
				return true;
			}
		}
		return false;
	}

}
