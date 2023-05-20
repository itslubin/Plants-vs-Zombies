package tp1.p1.control;

import static tp1.p1.view.Messages.*;

import java.util.Scanner;

import tp1.p1.logic.Game;
import tp1.p1.view.GamePrinter;
import tp1.p1.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic.
 *
 */
public class Controller {

	private Game game;

	private Scanner scanner;

	private GamePrinter gamePrinter;
	
	private boolean finJuego = false;
	

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game);
	}

	/**
	 * Draw / Paint the game.
	 */
	private void printGame() {
		System.out.println(gamePrinter.toString());
	}

	/**
	 * Prints the final message once the match is finished.
	 */
	public void printEndMessage() {
		System.out.println(gamePrinter.endMessage());
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic.
	 */
	public void run() {
		printGame();
		while (!finJuego) {
			//leer comandos
			String[]comando = prompt();
			comprobarComando(comando);
		}
		
	}
	
	
	private void insertarPlanta(String planta, int x, int y) {
		switch (planta) {
		case "s":
		case "sunflower":
			if (game.insertarSunflower(x, y)) {
				printGame();
			}
			break;
			
		case "p":
		case "peashooter":
			if(game.insertarPeashooter(x, y)) {
				printGame();
			}
			break;
			
		default:
			System.out.println(Messages.ERROR.formatted(Messages.INVALID_COMMAND));
		}
		
	}
	
	
	public void comprobarComando(String[]comando) {
		String c0 = comando[0];
		switch (c0) {
		case "a":
		case "add":
			try {
				
				insertarPlanta(comando[1], Integer.parseInt(comando[2]),Integer.parseInt(comando[3]));
				
			}
			
			catch (Exception c) {
				System.out.println(Messages.ERROR.formatted(Messages.COMMAND_PARAMETERS_MISSING));
			}
			break;
			
		case "l":
		case "list": 
			System.out.println(Messages.LIST);
			break;
		
		case "r":
		case "reset":
			game.reset();
			printGame();
			break;
		case "h":
		case "help":
			System.out.println(Messages.HELP);
			
			break;
		case "e":
		case "exit":
			finJuego = true;
			printGame();
			System.out.println(Messages.GAME_OVER);
			System.out.println(Messages.PLAYER_QUITS);
			break;
		case "":
		case "n":
		case "none":
			game.update();
			printGame();
			break;
		default:
			System.out.println(Messages.ERROR.formatted(Messages.UNKNOWN_COMMAND));
		}

		if (game.Playerwins() || game.Zombieswin()) {
			finJuego = true;
			printEndMessage();
		}
		
	}

}
