
public class Character {
	private int x,y;
	private char symbol;
	
	public Character(int x,int y,char symbol) {
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public void handleSpade() {
		if(symbol == 'H')
			symbol = 'A';
		else if(symbol == 'D')
			symbol = 'F';
		else if(symbol == 'F')
			symbol = 'D';
	}
	
	public void kill() {
		if(symbol == 'D')
			symbol = ' ';
	}
	
	public boolean hasSpade() {
		if(symbol == 'A')
			return true;
		return false;
	}
	
	public boolean isDead() {
		if(symbol == ' ')
			return true;
		return false;
	}
	
	public void moveLeft() {
		--y;
	}
	
	public void moveRight() {
		++y;
	}
	
	public void moveUp() {
		--x;
	}
	
	public void moveDown() {
		++x;
	}
}
