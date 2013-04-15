package labyrinth.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe responsável pelo menu de opções do jogo e de guardar toda essa informação
 */

public class OptionWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField size;
	private JTextField dragons;
	private JComboBox<Object> comboBox;
	private int labSize = 10;
	private int numDragons = 1;
	private int dragonMoveType = 1;
	private int up = KeyEvent.VK_UP;
	private int down = KeyEvent.VK_DOWN;
	private int left = KeyEvent.VK_LEFT;
	private int right = KeyEvent.VK_RIGHT;
	private int fly = KeyEvent.VK_F;
	private JTextField upCommand;
	private JTextField downCommand;
	private JTextField leftCommand;
	private JTextField rightCommand;
	private int lastButton = -1;
	private JTextField flyCommand;


	/**
	 * Create the dialog.
	 */
	public OptionWindow() {
		setResizable(false);
		setBounds(100, 100, 657, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblLabyrinthSize = new JLabel("Labyrinth Size");
			lblLabyrinthSize.setBounds(66, 66, 120, 14);
			contentPanel.add(lblLabyrinthSize);
		}
		setLocationRelativeTo(null);
		
		size = new JTextField();
		size.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				size.setText("");
			}
		});
		size.setBounds(196, 63, 86, 20);
		size.setText("10");
		contentPanel.add(size);
		size.setColumns(10);
		
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setBounds(66, 111, 120, 14);
		contentPanel.add(lblNumberOfDragons);
		
		dragons = new JTextField();
		dragons.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dragons.setText("");
			}
		});
		dragons.setBounds(196, 108, 86, 20);
		dragons.setText("1");
		contentPanel.add(dragons);
		dragons.setColumns(10);
		
		JLabel lblDragonMoves = new JLabel("Dragon Moves");
		lblDragonMoves.setBounds(66, 162, 120, 14);
		contentPanel.add(lblDragonMoves);
		
		String[] dragonMove = { "Quiet", "Random", "Random and Sleepy"};
		comboBox = new JComboBox<Object>(dragonMove);
		comboBox.setBounds(196, 156, 164, 20);
		comboBox.setSelectedIndex(0);
		contentPanel.add(comboBox);
		
		JLabel lblmax = new JLabel("(max. 50)");
		lblmax.setBounds(292, 66, 68, 14);
		contentPanel.add(lblmax);
		
		JLabel lblmaxsizeX = new JLabel("(max. [size x size]/50)");
		lblmaxsizeX.setBounds(292, 111, 142, 14);
		contentPanel.add(lblmaxsizeX);
		
		JLabel lblBoardOptions = new JLabel("Board Options");
		lblBoardOptions.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBoardOptions.setBounds(254, 11, 136, 30);
		contentPanel.add(lblBoardOptions);
		
		JLabel lblKeyOptions = new JLabel("Key Options");
		lblKeyOptions.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblKeyOptions.setBounds(269, 204, 110, 20);
		contentPanel.add(lblKeyOptions);
		
		JLabel lblUp = new JLabel("UP");
		lblUp.setBounds(81, 255, 46, 14);
		contentPanel.add(lblUp);
		
		JLabel lblDown = new JLabel("DOWN");
		lblDown.setBounds(81, 280, 46, 14);
		contentPanel.add(lblDown);
		
		upCommand = new JTextField();
		upCommand.setText("Up");
		upCommand.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() != down && e.getExtendedKeyCode() != left && e.getExtendedKeyCode() != right && e.getExtendedKeyCode() != fly) {
					upCommand.setText("");
					up = e.getExtendedKeyCode();
					if((e.getExtendedKeyCode()<65 || e.getExtendedKeyCode() > 90) && (e.getExtendedKeyCode()<48 || e.getExtendedKeyCode()>57)&& (e.getExtendedKeyCode()<96 || e.getExtendedKeyCode()>105))
						upCommand.setText(KeyEvent.getKeyText(e.getExtendedKeyCode()));
				}
			}
		});
		upCommand.setBounds(137, 252, 86, 20);
		contentPanel.add(upCommand);
		upCommand.setColumns(10);
		
		downCommand = new JTextField();
		downCommand.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() != up && e.getExtendedKeyCode() != left && e.getExtendedKeyCode() != right && e.getExtendedKeyCode() != fly) {
					downCommand.setText("");				
					down = e.getExtendedKeyCode();
					if((e.getExtendedKeyCode()<65 || e.getExtendedKeyCode() > 90) && (e.getExtendedKeyCode()<48 || e.getExtendedKeyCode()>57)&& (e.getExtendedKeyCode()<96 || e.getExtendedKeyCode()>105))
						downCommand.setText(KeyEvent.getKeyText(e.getExtendedKeyCode()));
				}
			}
		});
		downCommand.setText("Down");
		downCommand.setBounds(137, 277, 86, 20);
		contentPanel.add(downCommand);
		downCommand.setColumns(10);
		
		JLabel lblLeft = new JLabel("LEFT");
		lblLeft.setBounds(364, 252, 46, 14);
		contentPanel.add(lblLeft);
		
		JLabel lblRight = new JLabel("RIGHT");
		lblRight.setBounds(364, 280, 46, 14);
		contentPanel.add(lblRight);
		
		leftCommand = new JTextField();
		leftCommand.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() != down && e.getExtendedKeyCode() != up && e.getExtendedKeyCode() != right && e.getExtendedKeyCode() != fly) {
					leftCommand.setText("");
					left = e.getExtendedKeyCode();
					if((e.getExtendedKeyCode()<65 || e.getExtendedKeyCode() > 90) && (e.getExtendedKeyCode()<48 || e.getExtendedKeyCode()>57)&& (e.getExtendedKeyCode()<96 || e.getExtendedKeyCode()>105))
						leftCommand.setText(KeyEvent.getKeyText(e.getExtendedKeyCode()));
				}
			}
		});
		leftCommand.setText("Left");
		leftCommand.setBounds(420, 249, 86, 20);
		contentPanel.add(leftCommand);
		leftCommand.setColumns(10);
		
		rightCommand = new JTextField();
		rightCommand.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() != down && e.getExtendedKeyCode() != left && e.getExtendedKeyCode() != up && e.getExtendedKeyCode() != fly) {
					rightCommand.setText("");
					right = e.getExtendedKeyCode();
					if((e.getExtendedKeyCode()<65 || e.getExtendedKeyCode() > 90) && (e.getExtendedKeyCode()<48 || e.getExtendedKeyCode()>57)&& (e.getExtendedKeyCode()<96 || e.getExtendedKeyCode()>105))
						rightCommand.setText(KeyEvent.getKeyText(e.getExtendedKeyCode()));
				}
			}
		});
		rightCommand.setText("Right");
		rightCommand.setBounds(420, 274, 86, 20);
		contentPanel.add(rightCommand);
		rightCommand.setColumns(10);
		
		JLabel lblFly = new JLabel("FLY");
		lblFly.setBounds(283, 314, 46, 14);
		contentPanel.add(lblFly);
		
		flyCommand = new JTextField();
		flyCommand.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() != down && e.getExtendedKeyCode() != left && e.getExtendedKeyCode() != right && e.getExtendedKeyCode() != up) {
					flyCommand.setText("");
					fly = e.getExtendedKeyCode();
					if((e.getExtendedKeyCode()<65 || e.getExtendedKeyCode() > 90) && (e.getExtendedKeyCode()<48 || e.getExtendedKeyCode()>57)&& (e.getExtendedKeyCode()<96 || e.getExtendedKeyCode()>105))
						flyCommand.setText(KeyEvent.getKeyText(e.getExtendedKeyCode()));
				}
			}
		});
		flyCommand.setText("f");
		flyCommand.setBounds(254, 333, 86, 20);
		contentPanel.add(flyCommand);
		flyCommand.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							labSize = Integer.valueOf(size.getText());
						}catch(Exception ex) {
							labSize = 10;
						}
						
						try {
							numDragons = Integer.valueOf(dragons.getText());
						}catch(Exception ex) {
							numDragons = 1;
						}
						
						if(labSize > 50)
							labSize = 50;
						if(labSize < 10)
							labSize = 10;
						if(numDragons > (labSize*labSize/50))
							numDragons = (labSize*labSize/50);
						if(numDragons < 1)
							numDragons = 1;
						dragonMoveType = comboBox.getSelectedIndex()+1;
						lastButton = 0;
						setVisible(false);
					    transferFocus();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lastButton = 1;
						setVisible(false);
						transferFocus();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public int getLabSize() {
		return labSize;
	}
	
	public int getNumDragons() {
		return numDragons;
	}
	
	public int getDragonMoveType() {
		return dragonMoveType;
	}
	public int getUp() {
		return up;
	}
	public int getDown() {
		return down;
	}
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}
	
	public int getLastButton() {
		return lastButton;
	}
	
	public int getFly() {
		return fly;
	}
}
