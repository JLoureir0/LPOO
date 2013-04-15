package labyrinth.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * Classe que contém toda a lógica da movimentação das peças no tabuleiro
 */

public class Labyrinth implements Serializable{

	private static final long serialVersionUID = 1L;
	private Board board;
	private Hero hero;
	private Dragon[] dragons;
	private Eagle eagle;

	private boolean win;
	
	public Labyrinth(int boardSize, int howManyDragons, int dragonMoveType) {
		if(boardSize < 10)
			boardSize = 10;
		if(howManyDragons > (boardSize*boardSize/50) || howManyDragons < 1)
			howManyDragons = boardSize*boardSize/50;
		
		board = new Board(boardSize, howManyDragons);
		
		dragons = new Dragon[howManyDragons];
		
		for(int i = 0; i < howManyDragons; i++) {
			Dragon dragon = new Dragon(board.charXPos('D',i),board.charYPos('D',i),'D',dragonMoveType);
			dragons[i] = dragon;
		} 
		
		hero = new Hero(board.charXPos('G',0),board.charYPos('G',0),'G');
		eagle = new Eagle(hero.getX(),hero.getY(),'V');
	}
	
	public Labyrinth() {
		
		board = new Board();
		dragons = new Dragon[1];
		Dragon dragon = new Dragon(board.charXPos('D',0),board.charYPos('D',0),'D',1);
		dragons[0] = dragon;
		
		hero = new Hero(board.charXPos('G',0),board.charYPos('G',0),'G');
		eagle = new Eagle(hero.getX(),hero.getY(),'V');
	}
	
	public Labyrinth(char[][] board, int howManyDragons, int dragonMoveType) {	
		this.board = new Board(board);
		dragons = new Dragon[howManyDragons];
		
		for(int i = 0; i < howManyDragons; i++) {
			Dragon dragon = new Dragon(this.board.charXPos('D',i),this.board.charYPos('D',i),'D',dragonMoveType);
			dragons[i] = dragon;
		} 
		
		hero = new Hero(this.board.charXPos('G',0),this.board.charYPos('G',0),'G');
		eagle = new Eagle(hero.getX(),hero.getY(),'V');
	}
	
	/**
	 * Verifica se pode matar o dragão.
	 * @param dragon dragao a verificar
	 */
	private void canKillDragon(Dragon dragon) {
		if(!dragon.isDead())
			if(board.isCharNearBy(dragon.getX(), dragon.getY(), hero.getSymbol()) && hero.hasSpade() && (dragon.getSymbol() == 'D' || dragon.getSymbol() == 'd')) {
					dragon.kill();
					board.setCharAt(dragon.getX(), dragon.getY(), ' ');
			}
	}
	
	
	/**
	 * Funçao genérica para mover as peças no tabuleiro à excepção da águia
	 * @param c peça a mover
	 * @param direction direcção para a qual se pretende mover a peça
	 */
	private void moveChar(Character c,char direction) { // w:up d:right s:down a:left
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
					if(c.getSymbol() == 'G')
						eagle.moveUp();
					c.moveUp();
					break;
				case 'E':
					if(c.getSymbol() == 'H' || c.getSymbol() == 'G') {
						board.setCharAt(c.getX()-1, c.getY(), 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						if(c.getSymbol() == 'G')
							eagle.moveUp();
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
						if(c.getSymbol() == 'G')
							eagle.moveUp();
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
					if(c.getSymbol() == 'G')
						eagle.moveRight();
					c.moveRight();
					break;
				case 'E':
					if(c.getSymbol() == 'H'|| c.getSymbol() == 'G') {
						board.setCharAt(c.getX(), c.getY()+1, 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						if(c.getSymbol() == 'G')
							eagle.moveRight();
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
						if(c.getSymbol() == 'G')
							eagle.moveRight();
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
					if(c.getSymbol() == 'G')
						eagle.moveDown();
					c.moveDown();
					break;
				case 'E':
					if(c.getSymbol() == 'H' || c.getSymbol() == 'G') {
						board.setCharAt(c.getX()+1, c.getY(), 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						if(c.getSymbol() == 'G')
							eagle.moveDown();
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
						if(c.getSymbol() == 'G')
							eagle.moveDown();
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
					if(c.getSymbol() == 'G')
						eagle.moveLeft();
					c.moveLeft();
					break;
				case 'E':
					if(c.getSymbol() == 'H' || c.getSymbol() == 'G') {
						board.setCharAt(c.getX(), c.getY()-1, 'A');
						board.setCharAt(c.getX(),c.getY(),' ');
						if(c.getSymbol() == 'G')
							eagle.moveLeft();
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
						if(c.getSymbol() == 'G')
							eagle.moveLeft();
					}
					break;
			}			
			break;		
		case 'f':
			if(!eagle.isDead() && !eagle.isFlying() && hero.getSymbol() == 'G')
				fly();
			break;
		}
	}
	
	
	public final boolean isGameOver() {
		if(board.charXPos('S',0) == -1) {
			win = true;
			return true;
		}
		if(hero.isDead())
			return true;
		if((board.isCharNearBy(hero.getX(), hero.getY(), 'D')) && !hero.hasSpade()) {
			board.setCharAt(hero.getX(), hero.getY(), ' ');
			return true;
		}
		return false;
	}
	
	public final boolean playerWin() {
		return win;
	}
	
	public final Board getBoard() {
		return board;
	}
	
	/**
	 * Função que trata do proximo estado do jogo, tratando de todas as peças
	 * @param heroNextMove Proxima opção tomada pelo utilizador
	 */
	
	public void nextMoves(String heroNextMove) {
		Random r = new Random();
		int putDragonToSpleep, dragonNextMove;
		try {
			moveChar(hero,heroNextMove.toLowerCase().charAt(0));
		}catch(Exception e) {}
		
		if(eagle.isFlying())
			eagleNextMove();
		
		for(int i = 0; i < dragons.length; i++) {
			if(!isGameOver())
				canKillDragon(dragons[i]);
			if(!dragons[i].isSleeping() && !dragons[i].quietDragon() && !dragons[i].isDead()) {
				putDragonToSpleep = r.nextInt(10);
				if(putDragonToSpleep != 0 || !dragons[i].sleepyDragon()) {
					if(!dragons[i].isDead() || !isGameOver()) {
						dragonNextMove = r.nextInt(4);
						switch(dragonNextMove) {
						case 0:
							moveChar(dragons[i],'w');
							break;
						case 1:
							moveChar(dragons[i],'a');
							break;
						case 2:
							moveChar(dragons[i],'s');
							break;
						case 3:
							moveChar(dragons[i],'d');
							break;
						}
					} 
				}
				else if(dragons[i].sleepyDragon()&& (board.charXPos('E', 0) != -1) && !dragons[i].isDead() && !isGameOver()) {
					dragons[i].goToSleep();
					board.setCharAt(dragons[i].getX(), dragons[i].getY(), 'd');
				}
				
			}
			if(!isGameOver())
				canKillDragon(dragons[i]);
		}
	}
	
	private void fly() {
		eagle.fly();
		hero.handleEagle();
	}
	
	/**
	 * Função que trata única e exclusivamente do movimento da águia
	 */
	
	private void eagleNextMove() {
		int xSpade = board.charXPos('E', 0), ySpade = board.charYPos('E', 0);
		if(xSpade == -1) {
			xSpade = board.charXPos('F', 0);
			ySpade = board.charYPos('F', 0);
		}
		if(eagle.isFlying()) {
			if(!eagle.hasSpade()) {
				eagle.addPos(eagle.getX(), eagle.getY());
				board.setCharAt(eagle.getX(), eagle.getY(), eagle.getPreviousChar());
				if(eagle.getX() > xSpade && eagle.getY() > ySpade) {
					eagle.moveUp();
					eagle.moveLeft();
					eagle.setPreviousChar(board.charAt(eagle.getX(), eagle.getY()));
				}
				else if(eagle.getX() > xSpade && eagle.getY() < ySpade) {
					eagle.moveUp();
					eagle.moveRight();
					eagle.setPreviousChar(board.charAt(eagle.getX(), eagle.getY()));
				}
				else if(eagle.getX() < xSpade && eagle.getY() > ySpade) {
					eagle.moveDown();
					eagle.moveLeft();
					eagle.setPreviousChar(board.charAt(eagle.getX(), eagle.getY()));
				}
				else if(eagle.getX() < xSpade && eagle.getY() < ySpade) {
					eagle.moveDown();
					eagle.moveRight();
					eagle.setPreviousChar(board.charAt(eagle.getX(), eagle.getY()));
				}
				else if(eagle.getX() == xSpade && eagle.getY() != ySpade) {
					if(eagle.getY() > ySpade)
						eagle.moveLeft();
					else
						eagle.moveRight();
					eagle.setPreviousChar(board.charAt(eagle.getX(), eagle.getY()));
				}
				else if(eagle.getX() != xSpade && eagle.getY() == ySpade) {
					if(eagle.getX() > xSpade)
						eagle.moveUp();
					else
						eagle.moveDown();
					eagle.setPreviousChar(board.charAt(eagle.getX(), eagle.getY()));
				}
				if(eagle.getX() == xSpade && eagle.getY() == ySpade){
					if(board.isCharNearBy(eagle.getX(), eagle.getY(), 'D') || board.charAt(eagle.getX(), eagle.getY()) == 'F')
						eagle.kill();
					else
						eagle.handleSpade();
				}
				if(!eagle.isDead()) {
					if(board.charAt(eagle.getX(), eagle.getY()) == ' ' || board.charAt(eagle.getX(), eagle.getY()) == 'E')
						board.setCharAt(eagle.getX(), eagle.getY(), 'V');
					if(board.charAt(eagle.getX(), eagle.getY()) == 'D')
						board.setCharAt(eagle.getX(), eagle.getY(), 'Z');
					if(board.charAt(eagle.getX(), eagle.getY()) == 'd')
						board.setCharAt(eagle.getX(), eagle.getY(), 'z');
					if(board.charAt(eagle.getX(), eagle.getY()) == 'X')
						board.setCharAt(eagle.getX(), eagle.getY(), 'W');
				}
			}
			else if(!eagle.isDead()) {
				if(eagle.getPreviousChar() != 'E')
					board.setCharAt(eagle.getX(), eagle.getY(), eagle.getPreviousChar());
				else
					board.setCharAt(eagle.getX(), eagle.getY(), ' ');
				eagle.moveBack();
				eagle.setPreviousChar(board.charAt(eagle.getX(), eagle.getY()));
				if(board.charAt(eagle.getX(), eagle.getY()) == 'H'){
					hero.handleSpade();
					board.setCharAt(eagle.getX(), eagle.getY(), 'A');
				}
				else {
					if(board.charAt(eagle.getX(), eagle.getY()) == ' ')
						board.setCharAt(eagle.getX(), eagle.getY(), 'V');
					if(board.charAt(eagle.getX(), eagle.getY()) == 'D')
						board.setCharAt(eagle.getX(), eagle.getY(), 'Z');
					if(board.charAt(eagle.getX(), eagle.getY()) == 'd')
						board.setCharAt(eagle.getX(), eagle.getY(), 'z');
					if(board.charAt(eagle.getX(), eagle.getY()) == 'X')
						board.setCharAt(eagle.getX(), eagle.getY(), 'W');
				}
				if(eagle.isOrigin()) {
					eagle.fly();
					if(board.charAt(eagle.getX(), eagle.getY()) != 'A')
						board.setCharAt(eagle.getX(), eagle.getY(), 'E');
				}
			}
		}
	}
}
