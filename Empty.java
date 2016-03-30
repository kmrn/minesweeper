package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Tile {
        
        // Constructor that just calls parents constructor
	Empty() {
		super();
	}
            
        // Overrides parents click method
	public void click() {
                // Checks to see if the space has already been clicked and clears
                // any spaces adjacent to it if a bomb is not adjacent recursively
		if (!open) {
			open = true;
			Tile[][] board = Board.getBoard();
			
			List<Tile> adjacent = new ArrayList<>();
			
                        // Creates a list of adjacent spaces to the selected tile
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
			
                        // Checks how many bombs occupy the adjacent spaces
			for (Tile tile : adjacent) {
				if (tile instanceof Bomb)
					num++;
			}	
			
                        // Shows a blank space if no adjacent bombs but shows
                        // the number of adjacent bombs otherwise
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
