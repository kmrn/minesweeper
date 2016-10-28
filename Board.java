package minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {
	
    // Declaration of private variables
	private static Tile[][] board;
        private static LoadClass loader = new LoadClass();
        private static boolean[][] bombs;
	private static int height;
	private static int width;
	private static int gameState;
	private static int numMines;
        
	
    // Board constructor that accepts the height, width, and amount of mines
    // as parameters
	public Board(int boardHeight, int boardWidth, int mines) {
		setLayout(new GridLayout(boardHeight, boardWidth));
		
		height = boardHeight;
		width = boardWidth;
		numMines = mines;
		gameState = 1;
                
                bombs = new boolean[height][width];
                
                while(mines > 0) {
                    Random randX = new Random();
                    Random randY = new Random();
                    int x = randX.nextInt(width);
                    int y = randX.nextInt(height);
                    
                    if(!bombs[y][x]){
                        bombs[y][x] = true;
                        mines--;
                    }
                }
		
		board = new Tile[height][width];
		
		ButtonHandler handler = new ButtonHandler();
        // Instantiates board objects
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
                                if(!bombs[i][j]){
                                    board[i][j] = new Empty();
                                    board[i][j].setIcon(loader.blank);
                                    board[i][j].setPreferredSize(new Dimension(30,30));
                                    board[i][j].x = j;
                                    board[i][j].y = i;
                                    board[i][j].addMouseListener(handler);
                                    board[i][j].isBomb = false;
                                    add(board[i][j]);
                                } else {
                                    board[i][j] = new Bomb();
                                    board[i][j].setIcon(loader.blank);
                                    board[i][j].setPreferredSize(new Dimension(30,30));
                                    board[i][j].addMouseListener(handler);
                                    board[i][j].isBomb = true;
                                    add(board[i][j]);
                                    
                                }
                                
			}
		}
	}
	
	private class ButtonHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {}
                public void mousePressed(MouseEvent e) {
                    for(int i = 0; i < height; i++){
                        for(int j = 0; j < width; j++){
                            if(!(board[i][j] instanceof Bomb) && board[i][j] == e.getSource() && board[i][j].getIcon() == loader.blank && e.getButton() == MouseEvent.BUTTON1){
                                board[i][j].click();
                                checkWin();
                            }
                            
                            if((board[i][j] instanceof Bomb) && board[i][j] == e.getSource() && board[i][j].getIcon() == loader.blank && e.getButton() == MouseEvent.BUTTON1){
                                gameState = 0;
                                showAllBombs();
                            }
                            
                            if(e.getButton() == MouseEvent.BUTTON3 && board[i][j] == e.getSource() && board[i][j].getIcon() == loader.blank){
                                board[i][j].setIcon(loader.flagIcon);
                                board[i][j].flagged = true;
                            } else if(e.getButton() == MouseEvent.BUTTON3 && board[i][j] == e.getSource() && board[i][j].getIcon() == loader.flagIcon){
                                board[i][j].setIcon(loader.blank);
                                board[i][j].flagged = false;
                            }
                                
                        }
                    }
                    
                    
                }
                public void mouseReleased(MouseEvent e) {}
                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
		
	}
	
    // Accessor methods used by the Empty class
	public static Tile[][] getBoard() { return board; }
	public static int getH() { return height; }
	public static int getW() { return width; }
	
	// Method that either reveals all bombs or flags all bombs depending on
	// whether the user won or lost
	public void showAllBombs() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[j][i] instanceof Bomb) {
					if (gameState == 0)
						board[j][i].setIcon(loader.nuclear);
					if (gameState == 2)
						board[j][i].setIcon(loader.flagIcon);
				}
			}
		}
                if(gameState == 0){
                    loader.playMusic();
                    JOptionPane.showMessageDialog(this, "YOU LOSE!!!!");
                    System.exit(0);
                }
	}
	
	// Checks if user has won by checking if every tile other than a bomb
    // has been uncovered
	public void checkWin() {
		int emptyTiles = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[j][i] instanceof Empty && board[j][i].open) {
					emptyTiles++;
				}
			}
		}
		if (emptyTiles == ((height*width) - numMines)) {
			gameState = 2;
			showAllBombs();
                        JOptionPane.showMessageDialog(this, "YOU WIN!!!");
                        System.exit(0);
		}
	}
}