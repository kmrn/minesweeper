package minesweeper;

public class Bomb extends Tile {
	
	public Bomb() {
		super();
	}
	
	public void click() {
		open = true;
		symbol = '*';
		
	}
}
