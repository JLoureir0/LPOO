package labyrinth.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import labyrinth.logic.*;

/**
 * Esta classe extende um JPanel e é nela que é desenhado o labirinto que depois é mostrado na janela principal
 */

public class Map extends JPanel implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Labyrinth labyrinth;
	private int iconSize = 50;
	private int labSize;
	private int numDragons;
	private int dragonMoveType;
	private int up;
	private int down;
	private int left;
	private int right;
	private int fly;
	private OptionWindow ow;
	
	private Image heroSword = new ImageIcon("src\\firemario.png").getImage();
	private Image wall = new ImageIcon("src\\wall.png").getImage();
	private Image dragon = new ImageIcon("src\\bowser.png").getImage();
	private Image eagle = new ImageIcon("src\\yoshi.png").getImage();
	private Image hero = new ImageIcon("src\\mario.png").getImage();
	private Image space = new ImageIcon("src\\grass.png").getImage();
	private Image spade = new ImageIcon("src\\toadstool.png").getImage();
	private Image exitNorth = new ImageIcon("src\\pipe_north.png").getImage();
	private Image exitSouth = new ImageIcon("src\\pipe_south.png").getImage();
	private Image exitEast = new ImageIcon("src\\pipe_east.png").getImage();
	private Image exitWest = new ImageIcon("src\\pipe_west.png").getImage();
	private Image armedBowser = new ImageIcon("src\\armed_bowser.png").getImage();
	private Image sleepyBowser = new ImageIcon("src\\sleepy_bowser.png").getImage();
	private Image won = new ImageIcon("src\\won.png").getImage();
	private Image lose = new ImageIcon("src\\lose.png").getImage();
	
	public Map(OptionWindow ow) {
		this.ow = ow;
		timer = new Timer(25, this);
		timer.start();
		labSize = ow.getLabSize();
		numDragons = ow.getNumDragons();
		dragonMoveType = ow.getDragonMoveType();
		labyrinth = new Labyrinth(labSize,numDragons,dragonMoveType);
		up = ow.getUp();
		down = ow.getDown();
		left = ow.getLeft();
		right = ow.getRight();
		fly = ow.getFly();
		addKeyListener(this);
	}
	public Map(OptionWindow ow, char[][] board) {
		this.ow = ow;
		timer = new Timer(25, this);
		timer.start();
		labSize = ow.getLabSize();
		numDragons = ow.getNumDragons();
		dragonMoveType = ow.getDragonMoveType();
		labyrinth = new Labyrinth(board,numDragons,dragonMoveType);
		up = ow.getUp();
		down = ow.getDown();
		left = ow.getLeft();
		right = ow.getRight();
		fly = ow.getFly();
		addKeyListener(this);
	}
	
	/**
	 * Desenha no painel o jogo
	 * @param g graficos do painel
	 * 
	 */
	
	public void paint(Graphics g) {
		super.paint(g);
		Image draw = createImage((iconSize*labSize),(iconSize*labSize));
		drawImage(draw.getGraphics());
		Image scaledDraw = draw.getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST);
		g.drawImage(scaledDraw,0,0,null);
		if(labyrinth.isGameOver()){

			if(labyrinth.playerWin()) {
				int x = (this.getWidth() - won.getWidth(null)) / 2;
			    int y = (this.getHeight() - won.getHeight(null)) / 2;
				g.drawImage(won,x,y,null);
			}
			else {
				int x = (this.getWidth() - lose.getWidth(null)) / 2;
			    int y = (this.getHeight() - lose.getHeight(null)) / 2;
				g.drawImage(lose,x,y,null);
			}
			//System.exit(0);
		}
	}
	
	/**
	 * Desenha uma imagem com todos os objectos do tabuleiro para ser redimensionada posteriormente
	 * @param g graficos da imagem
	 * 
	 */
	
	public void drawImage(Graphics g) {
		Board board = labyrinth.getBoard();
		char c;
		for(int i = 0; i < labSize; i++) {
			for(int j = 0; j < labSize; j++) {
				c = board.charAt(j, i);
				switch(c) {
				case ' ':
					g.drawImage(space,i*iconSize,j*iconSize,null);
					break;
				case 'G':
					g.drawImage(hero,i*iconSize,j*iconSize,null);
					break;
				case 'H':
					g.drawImage(hero,i*iconSize,j*iconSize,null);
					break;
				case 'V':
					g.drawImage(eagle,i*iconSize,j*iconSize,null);
					break;
				case 'D':
					g.drawImage(dragon,i*iconSize,j*iconSize,null);
					break;
				case 'E':
					g.drawImage(spade,i*iconSize,j*iconSize,null);
					break;
				case 'X':
					g.drawImage(wall,i*iconSize,j*iconSize,null);
					break;
				case 'S':
					if(j == 0)
						g.drawImage(exitNorth,i*iconSize,j*iconSize,null);
					if(i == 0)
						g.drawImage(exitWest,i*iconSize,j*iconSize,null);
					if(j == (labSize-1))
						g.drawImage(exitSouth,i*iconSize,j*iconSize,null);
					if(i == (labSize-1))
						g.drawImage(exitEast,i*iconSize,j*iconSize,null);
					break;
				case 'A':
					g.drawImage(heroSword,i*iconSize,j*iconSize,null);
					break;
				case 'F':
					g.drawImage(armedBowser,i*iconSize,j*iconSize,null);
					break;
				case 'd':
					g.drawImage(sleepyBowser,i*iconSize,j*iconSize,null);
					break;
				case 'Z':
					g.drawImage(dragon,i*iconSize,j*iconSize,null);
					g.drawImage(eagle,i*iconSize,j*iconSize,null);
					break;
				case 'z':
					g.drawImage(sleepyBowser,i*iconSize,j*iconSize,null);
					g.drawImage(eagle,i*iconSize,j*iconSize,null);
					break;
				case 'W':
					g.drawImage(wall,i*iconSize,j*iconSize,null);
					g.drawImage(eagle,i*iconSize,j*iconSize,null);
					break;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!labyrinth.isGameOver()) {
			if(e.getExtendedKeyCode() == up)
				labyrinth.nextMoves("w");
			if(e.getExtendedKeyCode() == down)
				labyrinth.nextMoves("s");
			if(e.getExtendedKeyCode() == left)
				labyrinth.nextMoves("a");
			if(e.getExtendedKeyCode() == right)
				labyrinth.nextMoves("d");
			if(e.getExtendedKeyCode() == fly)
				labyrinth.nextMoves("f");
			}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void reloadKeys() {
		up = ow.getUp();
		down = ow.getDown();
		left = ow.getLeft();
		right = ow.getRight();
		fly = ow.getFly();
	}
	
	public Labyrinth getLab() {
		return labyrinth;
	}
	public void setLab(Labyrinth lab) {
		this.labyrinth = lab;
	}
}