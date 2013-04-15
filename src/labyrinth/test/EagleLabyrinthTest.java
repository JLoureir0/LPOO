package labyrinth.test;

import static org.junit.Assert.*;
import labyrinth.logic.Board;
import labyrinth.logic.Labyrinth;

import org.junit.Test;

public class EagleLabyrinthTest {
	
	private char[][] board1 = new char[][] {
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'G', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', 'E', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	private char[][] board2 = new char[][] {
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'G', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', 'E', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	private Labyrinth lab = new Labyrinth(board1,1,1);
	
	@Test
	public void testDeadEagle() {
		boolean isDead = true;
		for(int i = 0; i < 7; i++)
			lab.nextMoves("f");
		assertEquals('H', lab.getBoard().charAt(1, 1));
		assertEquals('E', lab.getBoard().charAt(7, 8));
		Board b = lab.getBoard();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10;j++) {
				if(b.charAt(i, j) != 'E' && b.charAt(i, j) != 'X' && b.charAt(i, j) != 'H' && b.charAt(i, j) != ' ' && b.charAt(i, j) != 'D' && b.charAt(i, j) != 'S')
					isDead = false;
			}
		}
		assertEquals(true,isDead);
	}
	
	@Test
	public void testEagle() {
		boolean isDead = true;
		lab = new Labyrinth();
		for(int i = 0; i < 12; i++)
			lab.nextMoves("f");
		assertEquals('A', lab.getBoard().charAt(1, 1));
		assertEquals(' ', lab.getBoard().charAt(7, 1));
		Board b = lab.getBoard();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10;j++) {
				if(b.charAt(i, j) != 'E' && b.charAt(i, j) != 'X' && b.charAt(i, j) != 'A' && b.charAt(i, j) != ' ' && b.charAt(i, j) != 'D' && b.charAt(i, j) != 'S')
					isDead = false;
			}
		}
		assertEquals(true,isDead);
	}
	
	@Test
	public void testEagle2() {
		boolean isDead = true;
		lab = new Labyrinth(board2,1,1);
		for(int i = 0; i < 14; i++)
			lab.nextMoves("f");
		assertEquals('A', lab.getBoard().charAt(1, 1));
		assertEquals(' ', lab.getBoard().charAt(6, 8));
		Board b = lab.getBoard();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10;j++) {
				if(b.charAt(i, j) != 'E' && b.charAt(i, j) != 'X' && b.charAt(i, j) != 'A' && b.charAt(i, j) != ' ' && b.charAt(i, j) != 'D' && b.charAt(i, j) != 'S')
					isDead = false;
			}
		}
		assertEquals(true,isDead);
	}

}
