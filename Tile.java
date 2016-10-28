package minesweeper;

import javax.swing.JButton;

public abstract class Tile extends JButton {
	
	// Declaration of public variables that are available to the child classes
	public int borderNum;
	public boolean open, flagged, isBomb;
	public int x;
	public int y;
           
    // Constructor that declares the tile to be not opened and the default
    // symbol 
	Tile () {
		open = false;
                flagged = false;
                isBomb = false;
	}
	
    // Abstract click method
	public abstract void click();

}
