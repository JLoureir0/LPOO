package labyrinth.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe que contém uma janela de edição manual de um labirinto
 */

public class EditWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private boolean custom = false;
	private int tool;
	private int labSize;
	char board[][];
	private ImageIcon wall = new ImageIcon("C:\\git\\labyrinth-lpoo\\src\\wall.png");
	private ImageIcon dragon = new ImageIcon("C:\\git\\labyrinth-lpoo\\src\\bowser.png");
	private ImageIcon hero = new ImageIcon("C:\\git\\labyrinth-lpoo\\src\\mario.png");
	private ImageIcon spade = new ImageIcon("C:\\git\\labyrinth-lpoo\\src\\toadstool.png");
	private ImageIcon exitNorth = new ImageIcon("C:\\git\\labyrinth-lpoo\\src\\pipe_north.png");
	private ImageIcon exitSouth = new ImageIcon("C:\\git\\labyrinth-lpoo\\src\\pipe_south.png");
	private ImageIcon exitEast = new ImageIcon("C:\\git\\labyrinth-lpoo\\src\\pipe_east.png");
	private ImageIcon exitWest = new ImageIcon("C:\\git\\labyrinth-lpoo\\src\\pipe_west.png");
	JButton buttonBoard[][];


	/**
	 * Create the frame.
	 */
	public EditWindow(OptionWindow ow) {
		setBounds(100, 100, 773, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 7, 0, 0));
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tool = 0;
			}
		});
		btnNewButton.setIcon(hero);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool = 1;
			}
		});
		btnNewButton_1.setIcon(wall);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool = 2;
			}
		});
		btnNewButton_2.setIcon(exitSouth);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool = 3;
			}
		});
		btnNewButton_3.setIcon(dragon);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool = 4;
			}
		});
		btnNewButton_4.setIcon(spade);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("ERASE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool = 5;
			}
		});
		panel.add(btnNewButton_5);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				custom = true;
				setVisible(false);
			}
		});
		panel.add(btnOk);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		draw(ow);
	}
	
	public void draw(OptionWindow ow) {
		labSize = ow.getLabSize();
		board = new char[labSize][labSize];
		buttonBoard = new JButton[labSize][labSize];
		panel_1.removeAll();
		panel_1.setLayout(new GridLayout(labSize, labSize, 0, 0));
		
		for(int i = 0; i < labSize; i++) {
			for(int j = 0; j < labSize; j++) {
				final JButton button = new JButton("");
				if(i == 0) {
					button.setIcon(wall);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(tool == 2)
								button.setIcon(exitNorth);
							if(tool == 5)
								button.setIcon(wall);
						}
					});
				}
				if(j == 0) {
					button.setIcon(wall);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(tool == 2)
								button.setIcon(exitWest);
							if(tool == 5)
								button.setIcon(wall);
						}
					});
				}
				else if(i == (labSize-1)) {
					button.setIcon(wall);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(tool == 2)
								button.setIcon(exitSouth);
							if(tool == 5)
								button.setIcon(wall);
						}
					});
				}
				else if(j == (labSize-1)) {
					button.setIcon(wall);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(tool == 2)
								button.setIcon(exitEast);
							if(tool == 5)
								button.setIcon(wall);
						}
					});
				}
				else {
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(tool == 0)
								button.setIcon(hero);
							if(tool == 1)
								button.setIcon(wall);
							if(tool == 3)
								button.setIcon(dragon);
							if(tool == 4)
								button.setIcon(spade);
							if(tool == 5)
								button.setIcon(null);
						}
					});
				}
				buttonBoard[i][j] = button;
				panel_1.add(button);
			}
		}
	}
	
	public char[][] getBoard() {
		for(int i = 0; i < labSize; i++) {
			for(int j = 0; j < labSize; j++) {
				if(buttonBoard[i][j].getIcon() == null)
					board[i][j] = ' ';
				else if(buttonBoard[i][j].getIcon().equals(wall))
					board[i][j] = 'X';
				else if(buttonBoard[i][j].getIcon().equals(hero))
					board[i][j] = 'G';
				else if(buttonBoard[i][j].getIcon().equals(dragon))
					board[i][j] = 'D';
				else if(buttonBoard[i][j].getIcon().equals(spade))
					board[i][j] = 'E';
				else if(buttonBoard[i][j].getIcon().equals(exitEast) || buttonBoard[i][j].getIcon().equals(exitWest) || buttonBoard[i][j].getIcon().equals(exitSouth) || buttonBoard[i][j].getIcon().equals(exitNorth))
					board[i][j] = 'S';
			}
		}
		return board;
	}
	
	public boolean getCustom() {
		return custom;
	}
	
	public void negCustom() {
		custom = !custom;
	}
}
