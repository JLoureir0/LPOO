public class Board {

	private char[][] board = new char[][] {
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
		{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
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
	
	public Board(int n) {
		BoardGenerator random = new BoardGenerator();
		if(n>5)
			board = random.nextBoard(n);
		else
			board = random.nextBoard(n);
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
	
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int charXPos(char c) {
		if(c == 'H' || c == 'D' || c == 'S') {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board.length; j++)
					if(board[i][j] == c)
						return i;
			}
		}
		return -1;
	}
	
	public int charYPos(char c) {
		if(c == 'H' || c == 'D' || c == 'S') {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board.length; j++)
					if(board[i][j] == c)
						return j;
			}
		}
		return -1;
	}
}