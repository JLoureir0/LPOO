package labyrinth.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Classe que contém a janela principal da interface gráfica, e permite ter acesso às outras janelas
 */

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	OptionWindow ow = new OptionWindow();
	EditWindow ew = new EditWindow(ow);
	private JPanel panel = new Map(ow);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		panel.setFocusable(true);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ow.setVisible(true);
				transferFocus();
			}
		});
		ow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				if(ow.getLastButton() == 0) {
					((Map) panel).reloadKeys();
					panel.requestFocus();
					ew.draw(ow);
				}
			}
		});
		ew.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				if(ew.getCustom()) {
					ew.negCustom();
					contentPane.remove(panel);
					panel = new Map(ow,ew.getBoard());
					contentPane.add(panel, BorderLayout.CENTER);
					contentPane.revalidate();
					contentPane.repaint();
					panel.requestFocus();
				}
			}
		});
		panel_1.add(btnOptions);

		JButton btnRetry = new JButton("Retry");
		btnRetry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Do you want to restart the game?","Confirm", JOptionPane.YES_NO_OPTION,	JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					contentPane.remove(panel);
					panel = new Map(ow);
					contentPane.add(panel, BorderLayout.CENTER);
					contentPane.revalidate();
					contentPane.repaint();
				}
				panel.requestFocus();
			}
		});
		panel_1.add(btnRetry);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane
						.showConfirmDialog(null, "Do you want to quit?",
								"Confirm", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				panel.requestFocus();
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog(MainWindow.this, "Save", FileDialog.SAVE);
                fd.setFile("untitled");
                fd.setVisible(true);

                FileManager fm = new FileManager();
                fm.saveFile(((Map)panel).getLab(), fd.getDirectory()+ fd.getFile()+".sav");
                panel.requestFocus();
			}
		});
		panel_1.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(MainWindow.this, "Load", FileDialog.LOAD);
				fd.setFile("*.sav");
                fd.setVisible(true);
                FileManager fm = new FileManager();
                ((Map)panel).setLab(fm.loadFile(fd.getDirectory()+ fd.getFile()));
                panel.requestFocus();
			}
		});
		panel_1.add(btnLoad);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ew.setVisible(true);
				ew.setLocationRelativeTo(null);
			}
		});
		panel_1.add(btnEdit);
		panel_1.add(btnExit);
	}
}
