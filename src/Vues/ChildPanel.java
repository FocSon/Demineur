package Vues;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ChildPanel extends JPanel implements ActionListener{
	private String difficulty;
	private CardLayout card;
	
	private JRadioButton[] choice;
	private JButton lauchGame;
	private StartPanel start;
	
	private GamePanel game;
	
	private MenuWindow mother;
	
	public ChildPanel(MenuWindow motherWindow) {
		mother=motherWindow;
		card = new CardLayout();
		setLayout(card);
		
		start = new StartPanel(this);
		choice=start.getButton();
		lauchGame = start.getStart();
		add(start,0);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==lauchGame) {
			if(choice[0].isSelected())
				difficulty="easy";
			else if(choice[1].isSelected())
				difficulty="medium";
			else
				difficulty="hard";
			
			GameWindow game = new GameWindow(difficulty);
			mother.dispose();
		}
	}
}
