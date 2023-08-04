package tp1.p2.logic.actions;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;
	
	// Para sasber si la explosion ha sido de una planta o de un zombie
	private boolean plantexplosion;

	public ExplosionAction(int col, int row, int damage, boolean plantexplosion) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.plantexplosion = plantexplosion;
	}

	@Override
	public void execute(GameWorld game) {
		int[] r = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] c = {0, 1, 1, 1, 0, -1, -1, -1};
		if (plantexplosion) {
			for (int i = 0; i < 8; ++i) {
				GameItem item = game.getGameObjectInPosition(this.col + c[i], this.row + r[i]);
				// Si el zombie explota debido a una planta ganamos 20 puntos
				if (item != null && item.receivePlantAttack(damage, true));
			}
		}
		
		else {
			for (int i = 0; i < 8; ++i) {
				GameItem item = game.getGameObjectInPosition(this.col + c[i], this.row + r[i]);
				if (item != null && item.receiveZombieAttack(damage));
			}
		}
		
		
	}

}
