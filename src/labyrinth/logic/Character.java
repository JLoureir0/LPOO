package labyrinth.logic;

import java.io.Serializable;

/**
 * Classe m�e de todos os objectos que possam existir no tabuleiro
 */
public abstract class Character implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int x,y;
	protected char symbol;
	
	public Character(int x,int y,char symbol) {
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
	
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	public final char getSymbol() {
		return symbol;
	}
	
	public abstract boolean hasSpade();
	
	public abstract void handleSpade();

	public final void kill() {
		symbol = ' ';
	}
	
	public final boolean isDead() {
		if(symbol == ' ')
			return true;
		return false;
	}
	
	public final void moveLeft() {
		--y;
	}
	
	public final void moveRight() {
		++y;
	}
	
	public final void moveUp() {
		--x;
	}
	
	public final void moveDown() {
		++x;
	}
}
