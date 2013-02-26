import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;


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
		{ 'X', 'D', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	public Board() {
	}
	
	public Board(int n) {
		if(n>5)
			generateBoard(n);
		else
			generateBoard(5);
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
	
	private void generateBoard(int n) {
		board = new char[n][n];
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				board[i][j]='N';
			}
		}
		for(int i = 0; i < board.length;i++) {
			board[i][0] = 'X';
			board[0][i] = 'X';
			board[n-1][i] = 'X';
			board[i][n-1] = 'X';
		}
		
		String[] directions = {"W","S","A","D"};
		Stack<Cell> directionStack = new Stack<Cell>();
		Random r = new Random();
		int x = r.nextInt(n-2)+1;
		int y = r.nextInt(n-2)+1;
		Collections.shuffle(Arrays.asList(directions));
		int dir;
		boolean findDirection;
		do {
			dir = 0;
			findDirection = false;

				if(board[x][y] == 'N') {
					board[x][y] = ' ';
				}
				do {
					switch(directions[dir].charAt(0)) {
					case 'W':
						if(board[x-1][y] == 'N' && (x-1)>0 && howManyPaths(x-1, y) == 1 && board[x-2][y+1] != ' ' && board[x-2][y-1] != ' ') {
							x--;
							findDirection = true;
							Cell current = new Cell(x,y);
							directionStack.push(current);
						}
						if((x-1)>0 && howManyPaths(x-1, y) > 1 && dir == 3){
							Cell current = directionStack.pop();
							x = current.getX();
							y= current.getY();
						}
						break;
					case 'S':
						if(board[x+1][y] == 'N' && (x+1)<(board.length-1) && howManyPaths(x+1, y) == 1 && board[x+2][y+1] != ' ' && board[x+2][y-1] != ' ') {
							x++;
							findDirection = true;
							Cell current = new Cell(x,y);
							directionStack.push(current);
						}
						if((x+1)<(board.length-1) && howManyPaths(x+1, y) > 1 && dir == 3) {
							Cell current = directionStack.pop();
							x = current.getX();
							y= current.getY();
						}
						break;
					case 'A':
						if(board[x][y-1] == 'N' && (y-1)> 0 && howManyPaths(x, y-1) == 1 && board[x+1][y-2] != ' ' && board[x-1][y-2] != ' ') {
							y--;
							findDirection = true;
							Cell current = new Cell(x,y);
							directionStack.push(current);
						}
						if((y-1)> 0 && howManyPaths(x, y-1) > 1 && dir == 3){
							Cell current = directionStack.pop();
							x = current.getX();
							y= current.getY();
						}
						break;
					case 'D':
						if(board[x][y+1] == 'N' && (y+1)<(board.length-1) && howManyPaths(x, y+1) == 1 && board[x+1][y+2] != ' ' && board[x-1][y+2] != ' ') {
							y++;
							findDirection = true;
							Cell current = new Cell(x,y);
							directionStack.push(current);
						}
						if((y+1)<(board.length-1) && howManyPaths(x, y+1) > 1 && dir == 3) {
							Cell current = directionStack.pop();
							x = current.getX();
							y= current.getY();
						}
						break;
					}
					++dir;
					
				}while(dir < 4 && !findDirection);

			Collections.shuffle(Arrays.asList(directions));
		}while(!directionStack.empty());
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length;j++) {
				if(board[i][j] == 'N')
					board[i][j] = 'X';
			}
		}
		int wall,pos;
		boolean validS = false;
		do {
			wall = r.nextInt(4);
			pos = r.nextInt(n-2)+1;
			switch(wall) { //0 - Up 1 - Down 2- Right 3 - Left
			case 0:
				if(charBelowOf(0, pos) == ' ') {
					board[0][pos] = 'S';
					validS = true;
				}
				break;
			case 1:
				if(charAboveOf(n-1, pos) == ' ') {
					board[n-1][pos] = 'S';
					validS = true;
				}
				break;
			case 2:
				if(charAtLeftOf(pos, n-1) == ' ') {
					board[pos][n-1] = 'S';
					validS = true;
				}
				break;
			case 3:
				if(charBelowOf(n-1, 0) == ' ') {
					board[pos][0] = 'S';
					validS = true;
				}
				break;
			}
		}while(!validS);
		
		boolean validH = false;
		do {
			x = r.nextInt(n-2)+1;
			y = r.nextInt(n-2)+1;
			if(board[x][y] == ' ') {
				board[x][y] = 'H';
				validH = true;
			}
		}while(!validH);
		
		boolean validD = false;
		do {
			x = r.nextInt(n-2)+1;
			y = r.nextInt(n-2)+1;
			if(board[x][y] == ' ') {
				board[x][y] = 'D';
				validD = true;
			}
		}while(!validD);
		
		boolean validE = false;
		do {
			x = r.nextInt(n-2)+1;
			y = r.nextInt(n-2)+1;
			if(board[x][y] == ' ') {
				board[x][y] = 'E';
				validE = true;
			}
		}while(!validE);
	}
	
	private int howManyPaths(int x,int y) {
		int ret = 0;
		if(charAboveOf(x, y) == ' ')
			ret++;
		if(charBelowOf(x, y) == ' ')
			ret++;
		if(charAtLeftOf(x, y) == ' ')
			ret++;
		if(charAtRightOf(x, y) == ' ')
			ret++;
		return ret;
	}
}