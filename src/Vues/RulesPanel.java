package Vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RulesPanel extends JPanel implements ActionListener{
	JButton rules;
	RulesWindow motherWindow;
	
	public RulesPanel(RulesWindow parMotherWindow) {
		motherWindow = parMotherWindow;
		
		rules = new JButton("",new ImageIcon("pics" + File.separator + "rules.png"));
		rules.addActionListener(this);
		add(rules);
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==rules)
			motherWindow.dispose();
	}
}
