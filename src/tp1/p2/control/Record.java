package tp1.p2.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.logic.GameStatus;
import tp1.p2.view.Messages;

public class Record {
	
	private GameStatus game;
	private Level current_level;
	private String[] record;
	private int current_record = 0;
	
	
	/*
	 * Cambios: a la hora de perder, no se cambia el fichero de record.txt, solo si se gana se escribe
	 * Es decir, no hay que ir cambiando los valores cada vez, cuando hay un nuevo 'score', sino que solo se actualiza cuando se gana
	 * 
	 * */
	
	public Record(Level current_level, GameStatus game) throws GameException {
		
		this.current_level = current_level;
		this.game = game;
		BufferedReader inChars = null;
		try {
			inChars = new BufferedReader(new FileReader(Messages.RECORD_FILENAME));
			String l; // con BufferedReader se puede leer líneas enteras
			StringBuilder sb = new StringBuilder(); // Inicializamos
			while ((l = inChars.readLine()) != null){
				sb.append(l + Messages.LINE_SEPARATOR); // metemos el "record.txt"
				String words[] = l.split(":");
				if (words[0].equalsIgnoreCase(this.current_level.toString())) {
					this.current_record = Integer.parseInt(words[1]);
				}
			}
			
			record = sb.toString().split("\n");
		} 
		
		catch(IOException e) {
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
		
		finally {
		if (inChars != null) { try {
			inChars.close();
		} catch (IOException e) {
			e.printStackTrace();
		} }
		}
	}
	
	public boolean newScore(int score) {
		if(score > current_record) {
			return true;
		}
		return false;
	}
	
	public void update() throws GameException {
		if(game.isNewRecord() && !game.isPlayerQuits() && !game.isZombieWins()) {
			/*Cuando hay un nuevo record (solo cuando se gana la partida) se escribe la solucion
			 * Para escribir esa solucion, hay que machacar el archivo de nuevo
			 * Entonces, en el nivel actual se trata de buscarla en el archivo y se actualiza, mientras
			 * que todo el resto que escribe tal cual como estaba en el archivo, 
			 * es decir, hay que pasarle desde record un string con la linea del nivel anterior (la que no hemos encontrado)
			 * */
			// Abrir fichero y guardar cambios (se puede meter en un metodo "save")
			
			current_record = game.getScore();
			
			BufferedWriter outChars = null;
			try {
				outChars = new BufferedWriter(new FileWriter(Messages.RECORD_FILENAME));
				boolean found = false;
				for (int i = 0; i < record.length; ++i) {
					String[] words = record[i].split(":");
					if (!found && words[0].equalsIgnoreCase(current_level.toString())) {
						outChars.append(current_level.name() + ":" + this.current_record);
						found = true;
					}
					
					else outChars.append(record[i]);
				}
				
				if(!found) {
					outChars.append(current_level.name() + ":" + this.current_record);
				}
				
			} 
			
			catch(IOException e) {
				throw new RecordException(Messages.RECORD_READ_ERROR);
			}
			
			finally {
				if (outChars != null) { try {
					outChars.close();
				} catch (IOException e) {
					e.printStackTrace();
				} }
				}
		}
	}
	
	public String currentScore() {
		
		StringBuilder buffer = new StringBuilder();
		buffer.append(Messages.CURRENT_RECORD.formatted(current_level.toString(), current_record));
		
		return buffer.toString();
	}
}
