/**
 * Minesweeper Part 1
 * COP 3330-04 - Computer Programming 2
 * 2016-03-14
 * @author Kamran Payne
 *
 */


import java.util.Scanner;

public abstract class Minesweeper {

	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Select game type (beginner, intermediate, advanced, custom); ");
//		String select = scan.nextLine();
//		
//		System.out.println();
		String select = "beginner";
		
		switch (select) {
		case "Beginner":
		case "beginner":
			new Board(9, 9, 10);
			break;
			
		case "Intermediate":
		case "intermediate":
			new Board(16, 16, 40);
			break;
			
		case "Advanced":
		case "advanced":
			new Board(16, 30, 99);
			break;
			
		case "Custom":
		case "custom":
			System.out.println("Please enter the board height, width, and number of mines separated by spaces: ");
			//new Board(scan.nextInt(), scan.nextInt(), scan.nextInt());
			break;
			
		default:
			System.out.println("ERROR: Invalid input");
			break;
		}
		
		//scan.close();
	}
}
