package labyrinth.test;

import static org.junit.Assert.*;
import labyrinth.logic.Labyrinth;

import org.junit.Test;

public class BasicLabyrinthTest {
	private Labyrinth lab = new Labyrinth();

	@Test
	public void heroMoveFree() {
		lab.nextMoves("s");
		assertEquals(2,lab.getBoard().charXPos('G', 0));
		assertEquals(1,lab.getBoard().charYPos('G', 0));
	}
	
	@Test
	public void heroMoveWall() {
		lab.nextMoves("a");
		assertEquals(1,lab.getBoard().charXPos('G', 0));
		assertEquals(1,lab.getBoard().charYPos('G', 0));
	}
	
	@Test
	public void pickUpSpade() {
		for(int i = 0; i < 6; i++)
			lab.nextMoves("s");
		assertEquals('A',lab.getBoard().charAt(7, 1));
	}
	
	@Test
	public void killdragon() {
		String[] moves = {"s","s","s","s","s","s","w","w","d","d","d","d","d","d","s","s","s","d"};
		for(int i = 0; i < moves.length;i++)
			lab.nextMoves(moves[i]);
		assertEquals(' ',lab.getBoard().charAt(8, 8));
	}
	
	@Test
	public void winGame() {
		String[] moves = {"s","s","s","s","s","s","w","w","d","d","d","d","d","d","s","s","s","d","d","w","w","w","d"};
		for(int i = 0; i < moves.length;i++)
			lab.nextMoves(moves[i]);
		assertEquals(true,lab.isGameOver());
		assertEquals(true,lab.playerWin());
	}
	
	@Test
	public void loseGame() {
		String[] moves = {"s","s","s","s","d","d","d","d","d","d","s","s","s","d","d"};
		for(int i = 0; i < moves.length;i++)
			lab.nextMoves(moves[i]);
		assertEquals(true,lab.isGameOver());
		assertEquals(false,lab.playerWin());
	}
	
	@Test
	public void nextToExit() {
		String[] moves = {"d","d","d","d","d","d","d","s","s","s","s"};
		for(int i = 0; i < moves.length;i++)
			lab.nextMoves(moves[i]);
		assertEquals('G',lab.getBoard().charAt(5, 8));
	}
}
