package labyrinth.logic;

/**
 * Classe que armazena o estado do Herói no tabuleiro: posição, espada, águia e se está vivo ou não
 */

public class Hero extends Character {
	private static final long serialVersionUID = 1L;

	public Hero(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	public void handleSpade() {
		symbol = 'A';
	}
	
	public void handleEagle() {
		if(symbol == 'H')
			symbol = 'G';
		else if(symbol == 'G')
			symbol = 'H';
	}
	
	public boolean hasSpade() {
		if(symbol == 'A')
			return true;
		return false;
	}
	
}
