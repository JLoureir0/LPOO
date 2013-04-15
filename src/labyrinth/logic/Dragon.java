package labyrinth.logic;

import java.util.Random;

/**
 * Classe que armazena o estado do Dragão no tabuleiro: posição, tipo de movimento e se está vivo ou não
 */

public class Dragon extends Character{
	
	private static final long serialVersionUID = 1L;
	private int random = 0;
	private int moveType;
	
	public Dragon(int x, int y, char symbol, int moveType) {
		super(x, y, symbol);
		this.moveType = moveType;
	}
	
	public boolean quietDragon() {
		if(moveType == 1)
			return true;
		return false;
	}

	public boolean randomDragon() {
		if(moveType == 2)
			return true;
		return false;		
	}
	
	public boolean sleepyDragon() {
		if(moveType == 3)
			return true;
		return false;		
	}
	
	public void handleSpade() {
		if(symbol == 'D')
			symbol = 'F';
		else if (symbol == 'F')
			symbol = 'D';
	}

	public boolean hasSpade() {
		return false;
	}

	public void goToSleep() {
		Random r  = new Random();
		random = r.nextInt(5)+1;
		symbol = 'd';
	}

	public boolean isSleeping() {
		if(random != 0){
			random--;
			if(random == 0 && symbol == 'd')
				symbol = 'D';
			return true;
		}			
		return false;
	}
}
