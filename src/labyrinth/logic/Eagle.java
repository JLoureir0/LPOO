package labyrinth.logic;

import java.util.Stack;

/**
 * Classe que armazena o estado da �guia no tabuleiro: posi��o, se est� a voar ou n�o, se tem espada e o trajecto para voltar ao Her�i
 */

public class Eagle extends Character{
	private static final long serialVersionUID = 1L;
	private boolean flying;
	private boolean hasSpade;
	private char previousChar = 'H';
	private Stack<Cell> path = new Stack<Cell>();
	
	public Eagle(int x, int y, char symbol) {
		super(x, y, symbol);
		hasSpade = false;
		flying = false;
	}
	
	public void fly() {
		flying = !flying;
	}
	
	public boolean isFlying() {
		return flying;
	}


	public boolean hasSpade() {
		return hasSpade;
	}

	
	public void handleSpade() {
		hasSpade = !hasSpade;
	}
	
	public void setPreviousChar(char previousChar) {
		this.previousChar = previousChar;
	}
	
	public char getPreviousChar() {
		return previousChar;
	}
	
	public void addPos(int x, int y) {
		Cell c = new Cell(x,y);
		path.push(c);
	}
	
	public void moveBack() {
		Cell c = path.pop();
		x = c.getX();
		y = c.getY();
	}
	
	public boolean isOrigin() {
		return path.isEmpty();
	}
}
