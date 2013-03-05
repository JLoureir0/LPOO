package labyrinth.cli;


import java.util.Scanner;

import labyrinth.logic.Labyrinth;

public class Game {
	public static void main(String[] args) {
		Labyrinth labyrinth;		
		String heroNextMove;
		int boardSize, dragonMoveType, howManyDragons;
		Scanner s = new Scanner(System.in);
		
		
		System.out.print("Qual o tamanho para o tabuleiro (min. 10): ");
		
		try {
			boardSize = Integer.valueOf(s.nextLine());
		}catch(Exception e) {
			boardSize = 10;
		}
		
		System.out.print("Quantos dragões em campo(max. (NxN)/50): ");
		
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
			System.out.println(labyrinth.getBoard());
			System.out.print("\nw - Up   \ta - Left\ts - Down\td - Right\n\n"
					+ "What is the direction you want to move your hero ?  (w/a/s/d) ");
			
			heroNextMove = s.nextLine();
			
			labyrinth.nextMoves(heroNextMove);
			
		}while(!labyrinth.isGameOver());
		
		System.out.println("\n\n\n");
		System.out.println(labyrinth.getBoard());
		
		System.out.print("\n\nGAME OVER, you ");
		if(labyrinth.playerWin())
			System.out.println("WON !!!");
		else
			System.out.println("LOSE !!!");
		
		s.close();
	}
}

