package minesweeper;

import javax.swing.JPanel;
import javax.swing.JLabel;

class GameLayoutPanel extends JPanel {
	
	private Board board;
	
	public GameLayoutPanel(int difficulty) {	
		
		switch (difficulty) {
		case 0:
			board = new Board(9, 9, 10);
			break;
			
		case 1:
			board = new Board(16, 16, 40);
			break;
			
		case 2:
			board = new Board(16, 30, 99);
			break;
			
		default:
			break;
		}
		
		add(board);
	}
}
