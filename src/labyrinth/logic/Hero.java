package labyrinth.logic;

public class Hero extends Character {

	public Hero(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	public void handleSpade() {
		symbol = 'A';
	}
	
	public void handleEagle() {
		if(symbol == 'H')
			symbol = 'B';
		else if(symbol == 'B')
			symbol = 'H';
	}
	
	public boolean hasSpade() {
		if(symbol == 'A')
			return true;
		return false;
	}
	
}
