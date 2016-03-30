/**
 * Minesweeper Part 1
 * COP 3330-04 - Computer Programming 2
 * 2016-03-14
 * @authors Kamran Payne and Cody Brown
 *
 */

package minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Minesweeper {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
                
                // Gives short tutorial
                System.out.println("When prompted enter the x and y"
                                + " coordinates followed by an 'f' for flag or "
                                + "an 'n', all seperated by spaces. \n"
                                + "Flags will be cleared if no bomb is near and "
                                + "it is cleared\n"
                                + "Negative (and 0) coordinates won't work!\n"
                                + "Good luck!\n");
                
                // Prompts user for game type
		System.out.println("Select game type (beginner, intermediate, advanced, custom); ");
		
		String select = scan.nextLine();
		
                // For whichever mode the user inputed, starts the game
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
			
		default:
			try {
				System.out.println("Please enter the board height, width, and number of mines separated by spaces: ");
				new Board(scan.nextInt(), scan.nextInt(), scan.nextInt());
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			}
			break;
		}
		scan.close();
	}
}
