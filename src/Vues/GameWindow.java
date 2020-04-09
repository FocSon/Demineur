package Vues;

import java.awt.Color;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	
	public GameWindow(String difficulty) {
		super("Minesweeper");
				
		add(new GamePanel(difficulty, this));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		if(difficulty=="easy")
			setSize(495,445);
		else if(difficulty=="medium")
			setSize(660,610);
		else
			setSize(825,775);
		
		setVisible(true);
		setLocation(100,100);
		setBackground(new Color(255,255,255));
		setResizable(false);
	}
}
