package minesweeper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
//import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Board extends JPanel {
	
    // Declaration of private variables
	private static Tile[][] board;
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
		
		board = new Tile[height][width];
		
		ButtonHandler handler = new ButtonHandler();
        // Instantiates board objects
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = new Empty();
				board[i][j].x = j;
				board[i][j].y = i;
				board[i][j].addActionListener(handler);
				add(board[i][j]);
			}
		}
		
        // Places mines in random positions in the board array
		while (mines > 0) {
			Random randX = new Random();
			Random randY = new Random();
			int x = randX.nextInt(width);
			int y = randY.nextInt(height);
			
			if (!(board[y][x] instanceof Bomb)) {
				board[y][x] = new Bomb();
				mines--;
			}
		}
		
		//updateBoard(0, 0, '0');
	}
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() instanceof Bomb) {
				if (e.getSource() instanceof Nuclear) {
				
				} else if (e.getSource() instanceof Poison) {
					
				} else if (e.getSource() instanceof Rainbow) {
				
				} else if (e.getSource() instanceof Health) {
					
				}	
			} else {
				
			}
		}
		
	}
	
    // Method that updates the board and prints it out
	public static void updateBoard(int x, int y, char flag) {
        
		// User inputs number >= 1 and arrays first values are 0
		x--;
		y--;
		
        // Applies user input after the first time
		if (x >= 0 && x < width && y >= 0 && y < height) {
			if (flag == 'f' || flag == 'F') {
				if (board[y][x].symbol == 'F') {
					board[y][x].flag();
				} else if (board[y][x].symbol != '-') {
					System.out.println("You can only flag uncovered tiles!");		
				} else {
					board[y][x].flag();
				}
			} else {
				if (board[y][x].symbol == 'F') {
					System.out.println("Unflag a square before clicking it! (flag it again to unflag it)");
				} else {
					board[y][x].click();
					if (board[y][x] instanceof Bomb)
						gameState = 0;
				}
			}
		}
		
		// Checks if the user has won
		if (gameState == 1)
			checkWin();
		
        // Shows all the bombs if user clicked a bomb and they lose
		if (gameState == 0) {
			showAllBombs();
			System.out.println(
                        " _______  _______  __   __  _______    _______  __   __  _______  ______    __  \n" +
                        "|       ||   _   ||  |_|  ||       |  |       ||  | |  ||       ||    _ |  |  | \n" +
                        "|    ___||  |_|  ||       ||    ___|  |   _   ||  |_|  ||    ___||   | ||  |  | \n" +
                        "|   | __ |       ||       ||   |___   |  | |  ||       ||   |___ |   |_||_ |  | \n" +
                        "|   ||  ||       ||       ||    ___|  |  |_|  ||       ||    ___||    __  ||__| \n" +
                        "|   |_| ||   _   || ||_|| ||   |___   |       | |     | |   |___ |   |  | | __  \n" +
                        "|_______||__| |__||_|   |_||_______|  |_______|  |___|  |_______||___|  |_||__| ");
		}
		
		
		// Prints top row of coordinates
		System.out.print("\n\t");
		for (int i = 1; i <= width; i++) {
			if (i < 10)
				System.out.print(" " + i + " ");
			else
				System.out.print(i + " ");
		}
		
		System.out.print("\n");
		
        // Prints the vertical row of coordinates and prints the board
		for (int i = 1; i <= height; i++) {
			System.out.print(" " + i + "\t");
			for (int j = 1; j <= width; j++) {
				System.out.print("|" + board[i-1][j-1].symbol + "|");
			}
			System.out.print("\n");
		}
		
        // Calls method to get user input
		if (gameState == 1)
			getInput();
		
        // Exits the game if user has won or lost
		if (gameState == 0 || gameState == 2)
			System.exit(0);
	}
	
    // Method that gets the users input
	public static void getInput() {
		Scanner scan = new Scanner(System.in);
		try{
			System.out.print("\nInput > ");
			updateBoard(scan.nextInt(), scan.nextInt(), scan.next().charAt(0));
			scan.close();
		} catch (InputMismatchException e){
			System.out.println("Invalid Input");
			getInput();
		}
	}
	
    // Accessor methods used by the Empty class
	public static Tile[][] getBoard() { return board; }
	public static int getH() { return height; }
	public static int getW() { return width; }
	
	// Method that either reveals all bombs or flags all bombs depending on
	// whether the user won or lost
	public static void showAllBombs() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[j][i] instanceof Bomb) {
					if (gameState == 0)
						board[j][i].click();
					if (gameState == 2)
						board[j][i].symbol = 'F';
				}
			}
		}
	}
	
	// Checks if user has won by checking if every tile other than a bomb
    // has been uncovered
	public static void checkWin() {
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
                        System.out.println(
                        " __   __  _______  __   __    _     _  ___   __    _  __  \n" +
                        "|  | |  ||       ||  | |  |  | | _ | ||   | |  |  | ||  | \n" +
                        "|  |_|  ||   _   ||  | |  |  | || || ||   | |   |_| ||  | \n" +
                        "|       ||  | |  ||  |_|  |  |       ||   | |       ||  | \n" +
                        "|_     _||  |_|  ||       |  |       ||   | |  _    ||__| \n" +
                        "  |   |  |       ||       |  |   _   ||   | | | |   | __  \n" +
                        "  |___|  |_______||_______|  |__| |__||___| |_|  |__||__| ");
		}
	}
}