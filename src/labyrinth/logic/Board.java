package labyrinth.logic;

import java.io.Serializable;

/**
 * Classe responsável pela representação do tabuleiro
 */

public class Board implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private char[][] board = new char[][] {
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
		{ 'X', 'G', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', 'E', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'D', 'X' },
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	public Board() {
	}
	
	public Board(int n, int howManyDragons) {
		BoardGenerator random = new BoardGenerator();
		board = random.nextBoard(n,howManyDragons);

	}
	
	public Board(char[][] board) {
		this.board = board;
	}
	
	public char charAt(int x,int y) {
		return board[x][y];
	}
	
	public char charAtLeftOf(int x,int y) {
		return board[x][y-1];
	}
	public char charAtRightOf(int x,int y) {
		return board[x][y+1];
	}
	public char charAboveOf(int x,int y) {
		return board[x-1][y];
	}
	public char charBelowOf(int x,int y) {
		return board[x+1][y];
	}
	
	public boolean isCharNearBy(int x,int y,char c) {
		return(charAtLeftOf(x,y) == c || charAtRightOf(x,y) == c || charAboveOf(x,y) == c || charBelowOf(x,y) == c);
	}
	
	public void setCharAt(int x, int y,char c) {
		board[x][y] = c;
	}
	
	public String toString() {
		String ret = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				ret += board[i][j] + " ";
			}
			ret += "\n";
		}
		return ret;
	}
	
	public int charXPos(char c,int n) {
		if(c == 'H' || c == 'D' || c == 'S' || c == 'G' || c == 'E' || c == 'F') {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board.length; j++) {
					if(board[i][j] == c && n == 0)
						return i;
					else if(board[i][j] == c && n>0)
						n--;
				}
			}
		}
		return -1;
	}
	
	public int charYPos(char c,int n) {
		if(c == 'H' || c == 'D' || c == 'S' || c == 'G' || c == 'E' || c == 'F') {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board.length; j++) {
					if(board[i][j] == c && n == 0)
						return j;
					else if(board[i][j] == c && n != 0)
						n--;
				}
			}
		}
		return -1;
	}
}