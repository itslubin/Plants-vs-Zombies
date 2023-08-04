package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		super(false);
		this.consumeCoins = true;

	}

	public AddPlantCommand(boolean consumeCoins) {
		super(false);
		this.consumeCoins = consumeCoins;
	}
	
	public AddPlantCommand(int col, int row, String plantName) {
		this.col = col;
		this.row = row;
		this.plantName = plantName;
		this.consumeCoins = true;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}


	@Override
	public boolean execute(GameWorld game) throws GameException {
		// TODO add your code here
		if(!PlantFactory.isValidPlant(plantName)) {
			throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
		}
		
		else if (row < 0 || row >= GameWorld.NUM_ROWS || col < 0 || col >= GameWorld.NUM_COLS) {
			throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(this.col, this.row));
    	}
		
		else if(!game.isFullyOcuppied(col, row)) {
			Plant plant = PlantFactory.spawnPlant(plantName, game, col, row);
			// Si es el modo normal, se consumen monedas y si es 
			// el modo "cheat" no se consumen
			if(consumeCoins && game.checkCoins(plant.getCost())) {
				game.addItem(plant);
				game.substractCoins(plant.getCost());
				game.update();
				return true;
			}
			else if (!consumeCoins) {
				game.addItem(plant);
				game.update();
				return true;
			}
			else throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
		}
		
		// Si está ocupado
		else throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(col, row));
		
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		// TODO add your code here
		if(parameters.length < 3) {
            throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		
		try {
    		plantName = parameters[0];
    		col = Integer.parseInt(parameters[1]);
    		row = Integer.parseInt(parameters[2]);
    	}
		
    	catch(NumberFormatException e) {
    		 throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]));
    	}
		
		try {
			return (Command) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}


