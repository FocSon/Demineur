package Vues;

import java.awt.Color;

import javax.swing.JFrame;

public class RulesWindow extends JFrame{
	
	public RulesWindow() {
		super("Minesweeper Rules");
		
		RulesPanel panel = new RulesPanel(this);
		
		add(panel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(755,540);
		setVisible(true);
		setLocation(100,100);
		setBackground(new Color(255,255,255));
		setResizable(false);
	}
}
