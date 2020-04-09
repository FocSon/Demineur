package Vues;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StartPanel extends JPanel implements ActionListener{
	private JRadioButton[] choice;
	private JButton rules;
	private JButton start;
	
	public StartPanel(ChildPanel motherPanel) {
		setLayout(new GridLayout(3,1,30,30));
		
		Font font = new Font("arial", Font.BOLD, 16);
		
		JLabel hello = new JLabel("Hello player !", JLabel.CENTER);
		hello.setFont(font);
		add(hello);
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new GridLayout(4,1));
		
		JLabel choose = new JLabel("choose a difficulty !", JLabel.CENTER);
		choose.setFont(font);
		difficultyPanel.add(choose);
		
		choice = new JRadioButton[3];
		
		choice[0] = new JRadioButton("Easy");
		choice[0].setSelected(true);
		choice[1] = new JRadioButton("Medium");
		choice[2] = new JRadioButton("Hard");
		
		ButtonGroup difficulty = new ButtonGroup();
		
		for(int i=0; i<3; i++) {
			difficulty.add(choice[i]);
			choice[i].setFont(font);
			difficultyPanel.add(choice[i]);
		}
			
		add(difficultyPanel);
		
		JPanel startRules = new JPanel();
		
		rules = new JButton("Rules");
		rules.addActionListener(this);
		rules.setFont(font);
		rules.setPreferredSize(new Dimension(150,50));
		startRules.add(rules);
		
		start = new JButton("Start");
		start.setFont(font);
		start.addActionListener(motherPanel);
		start.setPreferredSize(new Dimension(150,50));
		startRules.add(start);
		
		add(startRules);
	}

	public JRadioButton[] getButton() {
		return choice;
	}
	
	public JButton getStart() {
		return start;
	}


	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==rules) {
			new RulesWindow();
		}
	}

}
