package labyrinth.logic;

public class Labyrinth {
	
	public Board board;
	public Hero hero;
	public Dragon[] dragons;
	public Eagle eagle;

	private boolean win;
	
	public Labyrinth(int boardSize, int howManyDragons, int dragonMoveType) {
		board = new Board(boardSize, howManyDragons);
		
		dragons = new Dragon[howManyDragons];
		
		for(int i = 0; i < howManyDragons; i++) {
			Dragon dragon = new Dragon(board.charXPos('D',i),board.charYPos('D',i),'D',dragonMoveType);
			dragons[i] = dragon;
		} 
		
		hero = new Hero(board.charXPos('H',0),board.charYPos('H',0),'H');
		eagle = new Eagle(hero.getX(),hero.getY(),'B');
	}

	public void canKillDragon(Dragon dragon) {
		if(!dragon.isDead())
			if(board.isCharNearBy(hero.getX(), hero.getY(), dragon.getSymbol()) && hero.hasSpade() && (dragon.getSymbol() == 'D' || dragon.getSymbol() == 'd')) {
					dragon.kill();
					board.setCharAt(dragon.getX(), dragon.getY(), ' ');
			}
	}
	
	public void moveChar(Character c,char direction) { // w:up d:right s:down a:left
		switch(direction) {
		case 'w':
			switch(board.charAboveOf(c.getX(),c.getY())) {
				case ' ':
					if(c.getSymbol() == 'F') {
						c.handleSpade();
						board.setCharAt(c.getX()-1,c.getY(),c.getSymbol());
						board.setCharAt(c.getX(),c.getY(),'E');
					}
					else {
						board.setCharAt(c.getX()-1,c.getY(),c.getSymbol());
						board.setCharAt(c.getX(),c.getY(),' ');	
					}
					c.moveUp();
					break;
				case 'E':
					if(c.getSymbol() == 'H') {
						board.setCharAt(c.getX()-1, c.getY(), 'A');
						board.setCharAt(c.getX(),c.getY(),' ');

					}
					if(c.getSymbol() == 'D') {
						board.setCharAt(c.getX()-1, c.getY(), 'F');
						board.setCharAt(c.getX(),c.getY(),' ');
					}
					c.moveUp();
					c.handleSpade();
					break;
				case 'S':
					if(c.hasSpade()) {
						board.setCharAt(c.getX()-1, c.getY(), 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						c.moveUp();
					}
					break;
				case 'D':
					if(c.hasSpade()) {
						board.setCharAt(c.getX()-1, c.getY(), 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						c.moveLeft();	
					}
					break;
			}			
			break;
			
		case 'd':
			switch(board.charAtRightOf(c.getX(),c.getY())) {
				case ' ':
					if(c.getSymbol() == 'F') {
						c.handleSpade();
						board.setCharAt(c.getX(),c.getY()+1,c.getSymbol());
						board.setCharAt(c.getX(),c.getY(),'E');
					}
					else {
						board.setCharAt(c.getX(),c.getY()+1,c.getSymbol());
						board.setCharAt(c.getX(),c.getY(),' ');	
					}
					c.moveRight();
					break;
				case 'E':
					if(c.getSymbol() == 'H') {
						board.setCharAt(c.getX(), c.getY()+1, 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
					}
					if(c.getSymbol() == 'D') {
						board.setCharAt(c.getX(), c.getY()+1, 'F');
						board.setCharAt(c.getX(),c.getY(),' ');
					}
					c.moveRight();
					c.handleSpade();
					break;
				case 'S':
					if(c.hasSpade()) {
						board.setCharAt(c.getX(), c.getY()+1, 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						c.moveRight();
					}
					break;
				case 'D':
					if(c.hasSpade()) {
						board.setCharAt(c.getX(), c.getY()+1, 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						c.moveLeft();
					}
					break;
			}			
			break;
			
		case 's':
			switch(board.charBelowOf(c.getX(),c.getY())) {
				case ' ':
					if(c.getSymbol() == 'F') {
						c.handleSpade();
						board.setCharAt(c.getX()+1,c.getY(),c.getSymbol());
						board.setCharAt(c.getX(),c.getY(),'E');
					}
					else {
						board.setCharAt(c.getX()+1,c.getY(),c.getSymbol());
						board.setCharAt(c.getX(),c.getY(),' ');	
					}
					c.moveDown();
					break;
				case 'E':
					if(c.getSymbol() == 'H') {
						board.setCharAt(c.getX()+1, c.getY(), 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
					}
					if(c.getSymbol() == 'D') {
						board.setCharAt(c.getX()+1, c.getY(), 'F');
						board.setCharAt(c.getX(),c.getY(),' ');
					}
					c.moveDown();
					c.handleSpade();
					break;
				case 'S':
					if(c.hasSpade()) {
						board.setCharAt(c.getX()+1, c.getY(), 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						c.moveDown();
					}
					break;
				case 'D':
					if(c.hasSpade()) {
						board.setCharAt(c.getX()+1, c.getY(), 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						c.moveLeft();
					}
					break;
			}
			break;
			
		case 'a':
			switch(board.charAtLeftOf(c.getX(),c.getY())) {
				case ' ':
					if(c.getSymbol() == 'F') {
						c.handleSpade();
						board.setCharAt(c.getX(),c.getY()-1,c.getSymbol());
						board.setCharAt(c.getX(),c.getY(),'E');
					}
					else {
						board.setCharAt(c.getX(),c.getY()-1,c.getSymbol());
						board.setCharAt(c.getX(),c.getY(),' ');	
					}
					c.moveLeft();
					break;
				case 'E':
					if(c.getSymbol() == 'H') {
						board.setCharAt(c.getX(), c.getY()-1, 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
					}
					if(c.getSymbol() == 'D') {
						board.setCharAt(c.getX(), c.getY()-1, 'F');
						board.setCharAt(c.getX(),c.getY(),' ');
					}
					c.moveLeft();
					c.handleSpade();
					break;
				case 'S':
					if(c.hasSpade()) {
						board.setCharAt(c.getX(), c.getY()-1, 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						c.moveLeft();
					}
					break;
				case 'D':
					if(c.hasSpade()) {
						board.setCharAt(c.getX(), c.getY()-1, 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						c.moveLeft();
					}
					break;
			}			
			break;
		}
	}
	
	public boolean isGameOver() {
		if(board.charXPos('S',0) == -1) {
			win = true;
			return true;
		}
		if(board.isCharNearBy(hero.getX(), hero.getY(), 'D') && !hero.hasSpade()) {
			board.setCharAt(hero.getX(), hero.getY(), ' ');
			return true;
		}
		return false;
	}
	
	public boolean playerWin() {
		return win;
	}
}
