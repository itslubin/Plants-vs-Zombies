package tp1.p2.control;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.commands.ShowRecordCommand;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.commands.AddPlantCheatCommand;
import tp1.p2.control.commands.AddPlantCommand;
import tp1.p2.control.commands.AddZombieCommand;
import tp1.p2.control.commands.CatchCommand;
import tp1.p2.control.commands.ExitCommand;
import tp1.p2.control.commands.HelpCommand;
import tp1.p2.control.commands.ListPlantsCommand;
import tp1.p2.control.commands.ListZombiesCommand;
import tp1.p2.control.commands.NoneCommand;
import tp1.p2.control.commands.ResetCommand;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	/* @formatter:off */
	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
		new AddPlantCommand(),
		new ListPlantsCommand(),
		new ResetCommand(),
		new HelpCommand(),
		new ExitCommand(),
		new NoneCommand(),
		new ListZombiesCommand(),
		new AddZombieCommand(),
		new AddPlantCheatCommand(),
		new CatchCommand(),
		new ShowRecordCommand()
	);
	/* @formatter:on */

	private static Command defaultCommand;
	
	public Command() {
		this(false);
	}
	
	public Command(boolean ok) {
		if (ok) {
			// Toma el valor del objeto Command correspondiente
			defaultCommand = this;
		}
	}

	public static Command parse(String[] commandWords) throws GameException{
		if (commandWords.length == 1 && commandWords[0].isEmpty()) {
			return defaultCommand;
		}

		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(commandWords[0])) {
				// hay que pasar los parámetros correspondientes a cada comando sin incluir el comando en sí
				String[] p = new String[commandWords.length - 1];
				for(int i = 1; i < commandWords.length; i++) {
					p[i - 1] = commandWords[i];
				}
				return command.create(p);
			}
		}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	}

	public static Iterable<Command> getAvailableCommands() {
		return Collections.unmodifiableList(AVAILABLE_COMMANDS);
	}

	public static void newCycle() {
		for(Command c : AVAILABLE_COMMANDS) {
			c.newCycleStarted();
		}
	}

	abstract protected String getName();

	abstract protected String getShortcut();

	abstract public String getDetails();

	abstract public String getHelp();

	public boolean isDefaultCommand() {
		return Command.defaultCommand == this;
	}

	public boolean matchCommand(String token) {
		String shortcut = getShortcut();
		String name = getName();
		return shortcut.equalsIgnoreCase(token) || name.equalsIgnoreCase(token)
				|| (isDefaultCommand() && "".equals(token));
	}

	/**
	 * Execute the command.
	 * 
	 * @param game Where to execute the command.
	 * 
	 * @return {@code true} if game board must be printed {@code false} otherwise.
	 */
	public abstract boolean execute(GameWorld game) throws GameException;

	public Command create(String[] parameters) throws GameException {
		if (parameters.length != 0) {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		return this;
	}

	/**
	 * Notifies the {@link Command} that a new cycle has started.
	 */
	protected void newCycleStarted() {
	}
}