package labyrinth.logic;

public class Hero extends Character {

	public Hero(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	public void handleSpade() {
		symbol = 'A';
	}
	
	public boolean hasSpade() {
		if(symbol == 'A')
			return true;
		return false;
	}
	
}
