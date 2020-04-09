package Vues;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import java.util.Random;

import Modeles.Case;

public class GridPanel extends JPanel implements ActionListener{
	private Case [][] grid;
	private int nbBomb;
	private int nbCaseRemaining;
	private int nbCaseX;
	private int nbCaseY;
	
	public GridPanel(String difficulty, GamePanel motherPanel) {
		nbBomb=0;
		
		if(difficulty=="easy") {
			nbCaseX=10;
			nbCaseY=15;
		}
		else if (difficulty=="medium") {
			nbCaseX=15;
			nbCaseY=20;
		}
		else {
			nbCaseX=20;
			nbCaseY=25;
		}
		
		nbCaseRemaining=nbCaseX*nbCaseY;
		
		setLayout(new GridLayout(nbCaseX,nbCaseY));
		
		grid = new Case[nbCaseX][nbCaseY];
				
		for(int i=0; i<nbCaseX; i++) {
			for(int j=0; j<nbCaseY; j++) {
				grid[i][j]=new Case(false);
				grid[i][j].setPreferredSize(new Dimension(33,33));
				grid[i][j].addActionListener(motherPanel);
				add(grid[i][j],i,j);
			}
		}
		
		int x, y;
		Random rdm = new Random();
		
		for(int i=0; i<nbCaseX*nbCaseY*0.18; i++) {
			x=rdm.nextInt(nbCaseX);
			y=rdm.nextInt(nbCaseY);
			
			if(grid[x][y].getBomb())
				i--;
			else {
				grid[x][y].setBomb(true);
				nbBomb++;
			}
		}
		
		for(int i=0; i<nbCaseX; i++) {
			for(int j=0;j<nbCaseY;j++) {
				searchBombNear(i,j);
			}
		}
	}

	public int getNbBomb() {
		return nbBomb;
	}
	
	public int getNbCaseRemaining() {
		return nbCaseRemaining;
	}
	
	public void discoverAll() {
		for(int i=0; i<nbCaseX; i++) {
			for(int j=0; j<nbCaseY; j++) {
				grid[i][j].discoverBomb();
			}
		}
	}
	
	public void searchBombNear(int x, int y){
		int bombCount=0;
		if(!grid[x][y].getBomb()) {
			for(int inc=-1; inc<2; inc+=2) {
				if(x+inc<nbCaseX && x+inc>=0 && grid[x+inc][y].getBomb())
					bombCount+=1;
				if(y+inc<nbCaseY && y+inc>=0 && grid[x][y+inc].getBomb())
					bombCount+=1;
				if(x+inc<nbCaseX && x+inc>=0 && y+inc<nbCaseY && y+inc>=0 && grid[x+inc][y+inc].getBomb())
					bombCount+=1;
				if(x-inc<nbCaseX && x-inc>=0 && y+inc<nbCaseY && y+inc>=0 && grid[x-inc][y+inc].getBomb())
					bombCount+=1;
			}
		grid[x][y].setBombNear(bombCount);
		}
	}
	
	public int discoverNear(Case pressed) {
		int i=0, j=0, compteur=0;
		while(grid[i][j]!=pressed) {
			i=compteur/nbCaseY;
			j=compteur%nbCaseY;
			compteur++;
		}
		
		return discoverRec(i,j);
	}
	
	public int discoverRec(int i, int j) {
		int nbFlagRemoved=0;
		if(grid[i][j].getFlag())
			nbFlagRemoved++;
		grid[i][j].discover();
		nbCaseRemaining--;
		System.out.println(nbCaseRemaining);

		if(grid[i][j].getBombNear()==0 && !grid[i][j].getBomb()) {
			if(i+1<nbCaseX && !grid[i+1][j].getDiscovered())
				nbFlagRemoved+=discoverRec(i+1,j);
			
			if(i-1>=0 && !grid[i-1][j].getDiscovered())
				nbFlagRemoved+=discoverRec(i-1,j);
			
			if(j+1<nbCaseY && !grid[i][j+1].getDiscovered())
				nbFlagRemoved+=discoverRec(i,j+1);
			
			if(j-1>=0 && !grid[i][j-1].getDiscovered())
				nbFlagRemoved+=discoverRec(i,j-1);
			
			if(i+1<nbCaseX && j+1<nbCaseY && !grid[i+1][j+1].getDiscovered())
				nbFlagRemoved+=discoverRec(i+1,j+1);
			
			if(i-1>=0 && j+1<nbCaseY && !grid[i-1][j+1].getDiscovered())
				nbFlagRemoved+=discoverRec(i-1,j+1);
			
			if(i+1<nbCaseX && j-1>=0 && !grid[i+1][j-1].getDiscovered())
				nbFlagRemoved+=discoverRec(i+1,j-1);
			
			if(i-1>=0 && j-1>=0 && !grid[i-1][j-1].getDiscovered())
				nbFlagRemoved+=discoverRec(i-1,j-1);
		}
		return nbFlagRemoved;
	}
	
	public boolean checkVictory(){
		int compt=0;
		for(int i=0; i<nbCaseX; i++) {
			for(int j=0; j<nbCaseY; j++) {
				if(grid[i][j].getFlag() && grid[i][j].getBomb())
					compt++;
			}
		}
		if(compt==nbBomb)
			return true;
		return false;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
