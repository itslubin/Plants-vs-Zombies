package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.*;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {

	private int zombieIdx;

	private int col;

	private int row;

	public AddZombieCommand() {
		super(false);
	}

	private AddZombieCommand(int zombieIdx, int col, int row) {
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		// TODO add your code here
		if(!ZombieFactory.isValidZombie(zombieIdx)) {
			throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
		}
		
		else if (row < 0 || row >= GameWorld.NUM_ROWS || col < 0 || col > GameWorld.NUM_COLS) {
    		throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(col, row));
    	}
		
		else if(!game.isFullyOcuppied(col, row)) {
			game.addItem(ZombieFactory.spawnZombie(zombieIdx, game, col, row));
			game.update();
			return true;
		}
		
		// Si esta ocupado
		else throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(this.col, this.row));
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		// TODO add your code here
		if(parameters.length < 3) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		
		try {
			zombieIdx = Integer.parseInt(parameters[0]);
    		col = Integer.parseInt(parameters[1]);
    		row = Integer.parseInt(parameters[2]);
    	}
		
		// Podemos utilizar Exception o la NumberFormatException
    	catch(Exception e) {
    		throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]));
    	}
		
		return new AddZombieCommand(zombieIdx, col, row);
		
	}

}
