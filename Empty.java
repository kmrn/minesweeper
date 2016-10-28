package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Tile{
        
        private static LoadClass loader = new LoadClass();
        // Constructor that just calls parents constructor
	Empty() {
		super();
	}
            
        // Overrides parents click method
        @Override
	public void click() {
                // Checks to see if the space has already been clicked and clears
                // any spaces adjacent to it if a bomb is not adjacent recursively
		if (!open && !flagged) {
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
						
						if (tempx >= 0 && tempy >= 0 && tempy < Board.getH() && tempx < Board.getW())
							adjacent.add(board[tempy][tempx]);
					} 
				}
			}
			
			int num = 0;
			
                        // Checks how many bombs occupy the adjacent spaces
			for (Tile tile : adjacent) {
				if (tile instanceof Bomb)
					num++;
			}	
			
                        // Shows a blank space if no adjacent bombs but shows
                        // the number of adjacent bombs otherwise
			if (num == 0 && !flagged) {
				setIcon(loader.empty);
				for (Tile tile : adjacent) {
					tile.click();
				}
			} else if (num == 1 && !flagged){
				setIcon(loader.one);
			} else if (num == 2 && !flagged){
                                setIcon(loader.two);
                        } else if (num == 3 && !flagged){
				setIcon(loader.three);
			} else if (num == 4 && !flagged){
                                setIcon(loader.four);
                        } else if (num == 5 && !flagged){
                                setIcon(loader.five);
                        } else if (num == 6 && !flagged){
				setIcon(loader.six);
			} else if (num == 7 && !flagged){
                                setIcon(loader.seven);
                        } else if (num == 8 && !flagged){
                                setIcon(loader.eight);
                        }
		}
	}
}
