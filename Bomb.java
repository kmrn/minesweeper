
public class Bomb {
	
	public int borderNum;
	public boolean open;
	public boolean bomb;
	public char symbol;
	
	private int x;
	private int y;
	private char bombType;
	
	public Bomb() {
		bomb = false;
		open = false;
		symbol = '?';
	}
	
	public void click() {
		if (bomb)
			symbol = '*';
		else
			symbol = ' ';
		
	}
}
