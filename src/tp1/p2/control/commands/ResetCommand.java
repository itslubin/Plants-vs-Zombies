package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;
	
	public ResetCommand() {
		super(false);
	}
	

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		game.reset(level, seed);
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, game.getLevelName()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, game.getSeed()));
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		if(parameters.length == 0) {
			return new ResetCommand();
		}
		
		
		Level level = Level.valueOfIgnoreCase(parameters[0]);
		
		if (level == null) {
			throw new CommandParseException(Messages.INVALID_COMMAND);
		}
		
		try {
			if (parameters.length == 2) {
				seed = Long.parseLong(parameters[1]);
			}
			
		} 
		
		catch (NumberFormatException e) {
			throw new CommandParseException(Messages.SEED_NOT_A_NUMBER_ERROR.formatted(parameters[1]));
		}
		
		
		return new ResetCommand(level, seed);
		
	}

}
