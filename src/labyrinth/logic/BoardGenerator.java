package labyrinth.logic;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;


public class BoardGenerator {

	private char[][]board;
	
	public char[][] nextBoard(int n, int howManyDragons) {
		
		board = new char[n][n];
		fillBasicBoard();
		
		createThePaths();
		renameInnerWalls();

		createExit();
		
		putPiecesInPos('H');
		
		for(int i = 0; i < howManyDragons; i++)
			putPiecesInPos('D');
		
		putPiecesInPos('E');
		
		return board;
	}

	private void renameInnerWalls() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length;j++) {
				if(board[i][j] == 'N')
					board[i][j] = 'X';
			}
		}
	}

	private void createThePaths() {
		
		String[] directions = {"W","S","A","D"}; 
		Stack<Cell> directionStack = new Stack<Cell>();
		Random r = new Random();
		int x = r.nextInt(board.length-2)+1;
		int y = r.nextInt(board.length-2)+1;
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
	}

	private void fillBasicBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				board[i][j]='N';
			}
		}
		for(int i = 0; i < board.length;i++) {
			board[i][0] = 'X';
			board[0][i] = 'X';
			board[board.length-1][i] = 'X';
			board[i][board.length-1] = 'X';
		}
	}
	
	private int howManyPaths(int x,int y) {
		int ret = 0;
		if(board[x-1][y] == ' ')
			ret++;
		if(board[x+1][y] == ' ')
			ret++;
		if(board[x][y-1] == ' ')
			ret++;
		if(board[x][y+1] == ' ')
			ret++;
		return ret;
	}
	
	private void putPiecesInPos(char c) {
		Random r = new Random();
		boolean validE = false;
		int x,y;
		do {
			x = r.nextInt(board.length-2)+1;
			y = r.nextInt(board.length-2)+1;
			if(board[x][y] == ' ' && !isCharNearBy(x, y, 'E') && !isCharNearBy(x, y, 'D') && !isCharNearBy(x, y, 'H')) {
				board[x][y] = c;
				validE = true;
			}
		}while(!validE);
	}
	
	private void createExit() {
		Random r = new Random();
		int wall,pos;
		boolean validS = false;
		do {
			wall = r.nextInt(4);
			pos = r.nextInt(board.length-2)+1;
			switch(wall) { //0 - Up 1 - Down 2- Right 3 - Left
			case 0:
				if(board[1][pos] == ' ') {
					board[0][pos] = 'S';
					validS = true;
				}
				break;
			case 1:
				if(board[board.length-2][pos] == ' ') {
					board[board.length-1][pos] = 'S';
					validS = true;
				}
				break;
			case 2:
				if(board[pos][board.length-2] == ' ') {
					board[pos][board.length-1] = 'S';
					validS = true;
				}
				break;
			case 3:
				if(board[pos][1] == ' ') {
					board[pos][0] = 'S';
					validS = true;
				}
				break;
			}
		}while(!validS);
	}
	
	private boolean isCharNearBy(int x,int y,char c) {
		return(board[x+1][y] == c || board[x-1][y] == c || board[x][y+1] == c || board[x][y-1] == c);
	}
}
