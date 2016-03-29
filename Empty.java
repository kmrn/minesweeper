package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Tile {

	Empty() {
		super();
	}

	public void click() {
		if (!open) {
			open = true;
			Tile[][] board = Board.getBoard();
			
			List<Tile> adjacent = new ArrayList<>();
			
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <=1; j++) {
					int tempx = x + j;
					int tempy = y + i;
					if (i == 0 && j == 0) {
						
					} else {
						
						if (tempx >= 0 && tempy >= 0 && tempy < Board.getHeight() && tempx < Board.getWidth())
							adjacent.add(board[tempy][tempx]);
					} 
				}
			}
			
			char num = '0';
			
			for (Tile tile : adjacent) {
				if (tile instanceof Bomb)
					num++;
			}	
			
			if (num == '0') {
				symbol = ' ';
				for (Tile tile : adjacent) {
					tile.click();
				}
			} else {
				symbol = num;
			}
		}
	}
}
