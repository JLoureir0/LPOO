package labyrinth.logic;

/**
 * Classe que armazena o estado do Her�i no tabuleiro: posi��o, espada, �guia e se est� vivo ou n�o
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
