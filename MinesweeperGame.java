/**
 * Minesweeper Part 1
 * COP 3330-04 - Computer Programming 2
 * 2016-03-14
 * @authors Kamran Payne and Cody Brown
 *
 */

package minesweeper;

import java.awt.FlowLayout;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MinesweeperGame extends JFrame {	
	
	private MainLayoutPanel mainLayout;
	
	public MinesweeperGame() {
		setTitle("Minesweeper");
		setLayout(new FlowLayout());
		
		add(new MainLayoutPanel());
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(930, 600);
		setVisible(true);
	} 
	
	
	public static void main(String[] args) {
		new MinesweeperGame();
	}
}
