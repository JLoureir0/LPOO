import java.util.Random;
import java.util.Scanner;


public class Labyrinth {
	
	private static Board board = new Board();
	private static Character hero = new Character(board.charXPos('H'),board.charYPos('H'),'H');
	private static Character dragon = new Character(board.charXPos('D'),board.charYPos('D'),'D');
	private static boolean win = false;
	

	public static void main(String[] args) {
		String heroNextMove;
		int dragonNextMove;
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		do {
			System.out.println("\n\n\n");
			board.printBoard();
			System.out.print("\nw - Up   \ta - Left\ts - Down\td - Right\n\n"
					+ "What is the direction you want to move your hero ?  (w/a/s/d) ");
					
			heroNextMove = s.nextLine();
			
			moveChar(hero,heroNextMove.toLowerCase().charAt(0));
			canKillDragon();
			if(!dragon.isDead()) {
				dragonNextMove = r.nextInt(4);
				switch(dragonNextMove) {
				case 0:
					moveChar(dragon,'w');
					break;
				case 1:
					moveChar(dragon,'a');
					break;
				case 2:
					moveChar(dragon,'s');
					break;
				case 3:
					moveChar(dragon,'d');
					break;
				}
			}
			canKillDragon();			
		}while(!isGameOver());
		System.out.println("\n\n\n");
		board.printBoard();
		s.close();
		System.out.print("\n\nGAME OVER, you ");
		if(win)
			System.out.println("WON !!!");
		else
			System.out.println("LOSE !!!");
	}

	private static void canKillDragon() {
		if(!dragon.isDead())
			if(board.isCharNearBy(hero.getX(), hero.getY(), dragon.getSymbol()) && hero.hasSpade()) {
					dragon.kill();
					board.setCharAt(dragon.getX(), dragon.getY(), ' ');
			}
	}
	
	private static void moveChar(Character c,char direction) { // w:up d:right s:down a:left
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
	
	private static boolean isGameOver() {
		if(board.charXPos('S') == -1) {
			win = true;
			return true;
		}
		if(board.isCharNearBy(hero.getX(), hero.getY(), dragon.getSymbol()) && !hero.hasSpade())
			return true;
		return false;
	}
}
