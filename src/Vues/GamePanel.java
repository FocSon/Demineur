package Vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Modeles.Case;
import Modeles.FlagButton;

public class GamePanel extends JPanel implements ActionListener{
	GameWindow motherWindow;
	
	GridPanel grid;
	
	JPanel flagPanel;
	FlagButton flag;
	JLabel flagLabel;
	int nbFlagAvailable;
	
	JPanel menuPanel;
	JButton menu;
	
	JPanel confirmation;
	JButton yes;
	JButton no;
	
	boolean end;
	
	JPanel exitPanel;
	JButton exit;
	
	public GamePanel(String difficulty, GameWindow parMotherWindow) {
		setLayout(new BorderLayout());
		
		motherWindow = parMotherWindow;
		end=false;
				
		grid = new GridPanel(difficulty, this);		
		add(grid, BorderLayout.NORTH);
		
		nbFlagAvailable=grid.getNbBomb();
		
		flagPanel = new JPanel();
		flag = new FlagButton(false);
		flag.addActionListener(this);
		flagPanel.add(flag);
		
		flagLabel = new JLabel("Remaining bombs : " + nbFlagAvailable);
		flagLabel.setFont(new Font("arial", Font.BOLD, 16));
		flagPanel.add(flagLabel);
		
		add(flagPanel, BorderLayout.WEST);
		
		exit = new JButton("Exit game");
		exit.setPreferredSize(new Dimension(100,60));
		exit.addActionListener(this);
		
		exitPanel=new JPanel();
		exitPanel.setBorder(new EmptyBorder(5,0,0,0));
		exitPanel.add(exit);
		
		menu = new JButton("Main menu");
		menu.setPreferredSize(new Dimension(100,40));
		menu.addActionListener(this);
		menu.setPreferredSize(new Dimension(100,60));
		
		menuPanel = new JPanel();
		menuPanel.add(menu);
		menuPanel.setBorder(new EmptyBorder(5,0,0,22));
		add(menuPanel, BorderLayout.EAST);
		
		
		confirmation= new JPanel();
		confirmation.setLayout(new BorderLayout());
				
		yes = new JButton("yes");
		yes.addActionListener(this);
		yes.setPreferredSize(new Dimension(60,20));
		
		no = new JButton("no");
		no.addActionListener(this);
		no.setPreferredSize(new Dimension(60,20));

		JLabel conf = new JLabel("Are you sure ?", JLabel.CENTER);
		conf.setFont(new Font("arial", Font.BOLD, 11));
		conf.setBorder(new EmptyBorder(10,0,10,0));
		
		confirmation.setBorder(new EmptyBorder(0,20,15,20));
		confirmation.add(conf, BorderLayout.NORTH);
		confirmation.add(yes, BorderLayout.WEST);
		confirmation.add(new JLabel("  "), BorderLayout.CENTER);
		confirmation.add(no, BorderLayout.EAST);
		
	}
	
	private void victory(boolean win) {
		JLabel text= new JLabel();
		text.setBorder(new EmptyBorder(20,20,20,20));
		text.setFont(new Font("arial", Font.BOLD, 16));

		end=true;
		if(win) 
			text.setText("Victory !");
		
		else {
			grid.discoverAll();
			text.setText("You loose !");
		}
		
		remove(flagPanel);
		add(text,BorderLayout.WEST);
		
		add(exitPanel,BorderLayout.CENTER);
		
		validate();
		repaint();
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==flag) 
			flag.setEnabled(! flag.getEnabled());
		
		else if(event.getSource()==menu && !end) {
			remove(menuPanel);
			add(confirmation, BorderLayout.EAST);
			validate();
			repaint();
		}
		
		else if(event.getSource()==no) {
			remove(confirmation);
			add(menuPanel, BorderLayout.EAST);
			validate();
			repaint();
		}
		
		else if(event.getSource()==yes || (event.getSource()==menu && end)) {
			motherWindow.dispose();
			new MenuWindow();
		}
		
		else if(event.getSource()==exit) {
			motherWindow.dispose();
		}
			
		else if(!end){
			Case pressed=(Case) event.getSource();

			if(!pressed.getDiscovered() &&(flag.getEnabled() && ((nbFlagAvailable==0 && pressed.getFlag()) || nbFlagAvailable!=0))) {
				pressed.setFlag(! pressed.getFlag());
				
				if(pressed.getFlag())
					nbFlagAvailable--;
				else
					nbFlagAvailable++;
				
				if(nbFlagAvailable == 0 && grid.checkVictory()) {
					end=true;
					victory(true);
				}
			}
			
			else if(!flag.getEnabled() && !pressed.getDiscovered()){
				nbFlagAvailable+=grid.discoverNear(pressed);

				if(grid.getNbCaseRemaining()==grid.getNbBomb()) {
					end=true;
					victory(true);
				}
				
				if(pressed.getBomb()) {
					end=true;
					victory(false);
				}
			}
			flagLabel.setText("Remaining bombs : " + nbFlagAvailable);
		}
	}
}
