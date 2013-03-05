package labyrinth.cli;

import java.util.Random;
import java.util.Scanner;

import labyrinth.logic.Board;
import labyrinth.logic.Character;
import labyrinth.logic.Dragon;
import labyrinth.logic.Hero;


public class Labyrinth {
	
	private static Board board;
	private static Hero hero;
	private static boolean win = false;
	

	public static void main(String[] args) {
		String heroNextMove;
		int boardSize, dragonNextMove, putDragonToSpleep, dragonMoveType, howManyDragons;
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		System.out.print("Qual o tamanho para o tabuleiro (min. 10): ");
		boardSize = Integer.valueOf(s.nextLine());
		System.out.print("Quantos dragões em campo(max. TamanhoTabuleiro/5): ");
		howManyDragons = Integer.valueOf(s.nextLine());
		board = new Board(boardSize, howManyDragons);
		
		do {
			System.out.println("Qual a dificuldade que do Dragão: ");
			System.out.println("1 - Dragão parado");
			System.out.println("2 - Dragão com movimentação aleatória");
			System.out.println("3 - Dragão com movimentação aleatória intercalada com dormir");
			System.out.print("Opção: ");
			dragonMoveType = Integer.valueOf(s.nextLine());
		}while(dragonMoveType < 1 && dragonMoveType > 3);
		
		Dragon[] dragons = new Dragon[howManyDragons];
		
		for(int i = 0; i < howManyDragons; i++) {
			Dragon dragon = new Dragon(board.charXPos('D',i),board.charYPos('D',i),'D',dragonMoveType);
			dragons[i] = dragon;
		} 
		
		hero = new Hero(board.charXPos('H',0),board.charYPos('H',0),'H');
		
		do {
			System.out.println("\n\n\n");
			System.out.println(board);
			System.out.print("\nw - Up   \ta - Left\ts - Down\td - Right\n\n"
					+ "What is the direction you want to move your hero ?  (w/a/s/d) ");
			heroNextMove = s.nextLine();
			
			moveChar(hero,heroNextMove.toLowerCase().charAt(0));
			for(int i = 0; i < dragons.length; i++) {
				if(!isGameOver())
					canKillDragon(dragons[i]);
				if(!dragons[i].isSleeping() && !dragons[i].quietDragon() && !dragons[i].isDead()) {
					putDragonToSpleep = r.nextInt(3);
					if(putDragonToSpleep != 0) {
						if(!dragons[i].isDead()) {
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
					else if(dragons[i].sleepyDragon() && !dragons[i].isDead()) {
						dragons[i].goToSleep();
						board.setCharAt(dragons[i].getX(), dragons[i].getY(), 'd');
					}
					
				}
				if(!isGameOver())
					canKillDragon(dragons[i]);
			}
		}while(!isGameOver());
		System.out.println("\n\n\n");
		System.out.println(board);
		s.close();
		System.out.print("\n\nGAME OVER, you ");
		if(win)
			System.out.println("WON !!!");
		else
			System.out.println("LOSE !!!");
	}

	private static void canKillDragon(Dragon dragon) {
		if(!dragon.isDead())
			if(board.isCharNearBy(hero.getX(), hero.getY(), dragon.getSymbol()) && hero.hasSpade() && (dragon.getSymbol() == 'D' || dragon.getSymbol() == 'd')) {
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
}
