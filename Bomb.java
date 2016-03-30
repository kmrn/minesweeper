package minesweeper;

public class Bomb extends Tile {
	
        // Constructor that just calls parents constructor
	public Bomb() {
		super();
	}
	
        // Click method that overrides parents method
	public void click() {
		open = true;
		symbol = 'X';
		
	}
}
