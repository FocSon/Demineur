package Vues;

import java.awt.Color;

import javax.swing.JFrame;

public class MenuWindow extends JFrame{
	
	public MenuWindow() {
		super("Minesweeper Menu");
		
		ChildPanel child = new ChildPanel(this);
		
		add(child);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(350,300);
		setVisible(true);
		setLocation(100,100);
		setBackground(new Color(255,255,255));
		setResizable(false);
	}
}
