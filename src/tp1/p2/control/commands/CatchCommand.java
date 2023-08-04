package tp1.p2.control.commands;


import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotCatchablePositionException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		super(false);
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		// TODO add your code here
		if (row < 0 || row >= GameWorld.NUM_ROWS || col < 0 || col >= GameWorld.NUM_COLS) {
    		throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(this.col, this.row));
    	}
		
		else if (caughtSunThisCycle) {
			throw new CommandExecuteException(Messages.SUN_ALREADY_CAUGHT);
		}
		
		else if (!caughtSunThisCycle && game.tryToCatchObject(col, row)) {
			caughtSunThisCycle = true;
			return true;
		}
		
		// Si no hay un sol en la posicion
		throw new NotCatchablePositionException(Messages.INVALID_POSITION.formatted(this.col, this.row));
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		
		if(parameters.length < 2) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		
		try {
			col = Integer.parseInt(parameters[0]);
			row = Integer.parseInt(parameters[1]);
		}
		
		// Podemos utilizar Exception o la NumberFormatException
    	catch(Exception e) {
    		throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[0], parameters[1]));
    	}
		
		return new CatchCommand(col, row);
		
	}

}
