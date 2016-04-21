package minesweeper;

import java.awt.Dimension;

import javax.swing.JButton;

public abstract class Tile extends JButton {
	
	// Declaration of public variables that are available to the child classes
	public int borderNum;
	public boolean open;
	public char symbol;
	public int x;
	public int y;
           
    // Constructor that declares the tile to be not opened and the default
    // symbol 
	Tile () {
		open = false;
		symbol = '-';
		setPreferredSize(new Dimension(30, 30));
	}
	
    // Abstract click method
	public abstract void click();
	
    // Concrete method that switches the flag symbol
	public void flag() {
		if (symbol == 'F')
			symbol = '-';
		else
			symbol = 'F';
	}

}
