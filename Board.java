package minesweeper;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Board {
	
	private static Tile[][] board;
	private static int height;
	private static int width;
	private static int gameState;
	private static int numMines;
	
	public Board(int boardHeight, int boardWidth, int mines) {
		
		height = boardHeight;
		width = boardWidth;
		numMines = mines;
		gameState = 1;
		
		board = new Tile[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = new Empty();
				board[i][j].x = j;
				board[i][j].y = i;
			}
		}
		
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
		updateBoard(0, 0, '0');
	}
	
	public static void updateBoard(int x, int y, char flag) {
		x--;
		y--;
		
		if (x >= 0 && x < width && y >= 0 && y < height) {
			if (flag == 'f' || flag == 'F') {
				if (board[y][x].symbol == 'F') {
					board[y][x].flag();
				} else if (board[y][x].symbol != '█') {
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
		
		
		if (gameState == 1)
			checkWin();
		
		if (gameState == 0) {
			showAllBombs();
			System.out.println("\nGAME OVER \n YOU LOOOOOOOOSE");
		}
		
		
		
		System.out.print("\n\t");
		for (int i = 1; i <= width; i++) {
			if (i < 10)
				System.out.print(" " + i + " ");
			else
				System.out.print(i + " ");
		}
		
		System.out.print("\n");
		
		for (int i = 1; i <= height; i++) {
			System.out.print(" " + i + "\t");
			for (int j = 1; j <= width; j++) {
				System.out.print("[" + board[i-1][j-1].symbol + "]");
			}
			System.out.print("\n");
		}
		
		if (gameState == 1)
			getInput();
		
		if (gameState == 2)
			System.out.println("\nGAME OVER \n YOU WIN \n  YOU ARE A COOL GUY");
		
		if (gameState == 0 || gameState == 2)
			System.exit(0);
	}
	
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
	
	public static Tile[][] getBoard() { return board; }
	public static int getHeight() { return height; }
	public static int getWidth() { return width; }
	
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
		}
	}
}
