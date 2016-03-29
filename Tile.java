package minesweeper;

public abstract class Tile {
		
	public int borderNum;
	public boolean open;
	public char symbol;
	public int x;
	public int y;

	Tile () {
		open = false;
		symbol = '█';
	}
	
	public abstract void click();
	
	public void flag() {
		if (symbol == 'F')
			symbol = '█';
		else
			symbol = 'F';
	}

}
