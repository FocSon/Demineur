package Modeles;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Case extends JButton{
	private boolean bomb;
	private int bombNear;

	private boolean flag;

	private boolean discovered;
	private String discoveredSprite;
	
	public Case(boolean parBomb) {
		bomb=parBomb;
		bombNear=0;
		
		flag=false;
		
		discovered=false;
		discoveredSprite="sprites" + File.separator + "case_discovered_sprite.png";
		
		setIcon(new ImageIcon("sprites" + File.separator + "case_undiscovered_sprite.png"));
	}
	
	public void setFlag(boolean flagged) {
		if(discovered==false) {
			flag=flagged;
			
			if(flagged)
				setIcon(new ImageIcon("sprites" + File.separator + "case_flag_sprite.png"));
			else
				setIcon(new ImageIcon("sprites" + File.separator + "case_undiscovered_sprite.png"));
		}
	}
	
	public void discover() {
		discovered=true;
		setIcon(new ImageIcon(discoveredSprite));
	}
	
	public void discoverBomb() {
		if(getBomb() && !getDiscovered()) {
			setIcon(new ImageIcon("sprites" + File.separator + "case_bomb_undiscovered_sprite.png"));
		}
	}
	
	public void setBombNear(int parBombNear) {
		bombNear=parBombNear;
		if(bombNear==0)
			discoveredSprite="sprites" + File.separator + "case_discovered_sprite.png";
		else
			discoveredSprite="sprites" + File.separator + "case_discovered_" + bombNear + "_sprite.png";
	}
	
	public int getBombNear() {
		return bombNear;
	}
	
	public String getDiscoveredSprite() {
		return discoveredSprite;
	}
	
	public boolean getBomb() {
		return bomb;
	}
	
	public void setBomb(boolean parBomb) {
		bomb=parBomb;
		discoveredSprite="sprites" + File.separator + "case_bomb_sprite.png";
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public boolean getDiscovered() {
		return discovered;
	}
}
