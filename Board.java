
import java.util.Random;
import java.util.Scanner;

public class Board {
	
	private static Bomb[][] board;
	private static int height;
	private static int width;
	
	public Board(int boardHeight, int boardWidth, int mines) {
		
		height = boardHeight;
		width = boardWidth;
		
		board = new Bomb[height][width];
		
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				board[i][j] = new Bomb();
		
		while (mines > 0) {
			Random randX = new Random();
			Random randY = new Random();
			int x = randX.nextInt(width);
			int y = randY.nextInt(height);
			board[y][x].bomb = true;
			mines--;
		}
		updateBoard(0, 0);
	}
	
	public static void updateBoard(int x, int y) {
		x--;
		y--;
                
		if (x >= 0 && x < width && y >= 0 && y < height)
			board[y][x].click();
                
                
		System.out.print("\n   ");
		for (int i = 1; i <= width; i++)
			System.out.print(" " + i + " ");
		
		System.out.print("\n");
		
		for (int i = 1; i <= height; i++) {
			System.out.print(" " + i + " ");
			for (int j = 1; j <= width; j++) {
				System.out.print("[" + board[i-1][j-1].symbol + "]");
			}
			System.out.print("\n");
		}
		
		getInput();
	}
	
	public static void getInput() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\nEnter x and y coordinates separated by a space: ");
		updateBoard(scan.nextInt(), scan.nextInt());
		scan.close();
	}
}
