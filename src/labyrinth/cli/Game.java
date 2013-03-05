package labyrinth.cli;

import java.util.Random;
import java.util.Scanner;

import labyrinth.logic.Labyrinth;

public class Game {
	public static void main(String[] args) {
		Labyrinth labyrinth;
		
		String heroNextMove;
		int boardSize, dragonNextMove, putDragonToSpleep, dragonMoveType, howManyDragons;
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		System.out.print("Qual o tamanho para o tabuleiro (min. 10): ");
		
		try {
			boardSize = Integer.valueOf(s.nextLine());
		}catch(Exception e) {
			boardSize = 10;
		}
		
		System.out.print("Quantos dragões em campo(max. TamanhoTabuleiro/5): ");
		
		try {
			howManyDragons = Integer.valueOf(s.nextLine());
		}catch(Exception e) {
			howManyDragons = 1;
		}
		
		try {
			do {
				System.out.println("Qual a dificuldade que do Dragão: ");
				System.out.println("1 - Dragão parado");
				System.out.println("2 - Dragão com movimentação aleatória");
				System.out.println("3 - Dragão com movimentação aleatória intercalada com dormir");
				System.out.print("Opção (default:2): ");
				dragonMoveType = Integer.valueOf(s.nextLine());
			}while(dragonMoveType < 1 || dragonMoveType > 3);
		}catch(Exception e) {
			dragonMoveType = 2;
		}
		
		labyrinth = new Labyrinth(boardSize, howManyDragons, dragonMoveType);
		
		do {
			System.out.println("\n\n\n");
			System.out.println(labyrinth.board);
			System.out.print("\nw - Up   \ta - Left\ts - Down\td - Right\n\n"
					+ "What is the direction you want to move your hero ?  (w/a/s/d) ");
			
			heroNextMove = s.nextLine();

			try {
				labyrinth.moveChar(labyrinth.hero,heroNextMove.toLowerCase().charAt(0));
			}catch(Exception e) {}
			
			for(int i = 0; i < labyrinth.dragons.length; i++) {
				if(!labyrinth.isGameOver())
					labyrinth.canKillDragon(labyrinth.dragons[i]);
				if(!labyrinth.dragons[i].isSleeping() && !labyrinth.dragons[i].quietDragon() && !labyrinth.dragons[i].isDead()) {
					putDragonToSpleep = r.nextInt(4);
					if(putDragonToSpleep != 0) {
						if(!labyrinth.dragons[i].isDead()) {
							dragonNextMove = r.nextInt(4);
							switch(dragonNextMove) {
							case 0:
								labyrinth.moveChar(labyrinth.dragons[i],'w');
								break;
							case 1:
								labyrinth.moveChar(labyrinth.dragons[i],'a');
								break;
							case 2:
								labyrinth.moveChar(labyrinth.dragons[i],'s');
								break;
							case 3:
								labyrinth.moveChar(labyrinth.dragons[i],'d');
								break;
							}
						} 
					}
					else if(labyrinth.dragons[i].sleepyDragon() && !labyrinth.dragons[i].isDead()) {
						labyrinth.dragons[i].goToSleep();
						labyrinth.board.setCharAt(labyrinth.dragons[i].getX(), labyrinth.dragons[i].getY(), 'd');
					}
					
				}
				if(!labyrinth.isGameOver())
					labyrinth.canKillDragon(labyrinth.dragons[i]);
			}
		}while(!labyrinth.isGameOver());
		System.out.println("\n\n\n");
		System.out.println(labyrinth.board);
		s.close();
		System.out.print("\n\nGAME OVER, you ");
		if(labyrinth.playerWin())
			System.out.println("WON !!!");
		else
			System.out.println("LOSE !!!");
	}
}

