package Modeles;

import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FlagButton extends JButton{
	private boolean enabled;
	
	public FlagButton(boolean activate) {
		setEnabled(activate);
		setVisible(true);
		setPreferredSize(new Dimension(66,66));
	}
	
	public void setEnabled(boolean parEnabled) {
		enabled=parEnabled;
		if(parEnabled)
			setIcon(new ImageIcon("sprites" + File.separator + "case_flag_enabled_sprite.png"));
		else
			setIcon(new ImageIcon("sprites" + File.separator + "case_flag_disabled_sprite.png"));
	}
	
	public boolean getEnabled() {
		return enabled;
	}

}
