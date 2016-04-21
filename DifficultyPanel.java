package minesweeper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class DifficultyPanel extends JPanel {
	private JLabel label;
	private JRadioButton beginner, intermediate, advanced;
	private ButtonGroup select;
	private int difficultySetting;
	
	public DifficultyPanel() {
		setLayout(new FlowLayout());
		
		label = new JLabel("Difficulty");
		label.setFont(new Font("Helvetica", Font.BOLD, 12));
		beginner = new JRadioButton("Beginner");
		intermediate = new JRadioButton("Intermediate");
		advanced = new JRadioButton("Advanced");
		
		select = new ButtonGroup();
		select.add(beginner);
		select.add(intermediate);
		select.add(advanced);
		
		ButtonHandler handler = new ButtonHandler();
		beginner.addActionListener(handler);
		intermediate.addActionListener(handler);
		advanced.addActionListener(handler);
		
		add(label);
		add(beginner);
		add(intermediate);
		add(advanced);
		
	}
	
	public int getDifficulty() {
		return difficultySetting;
	}
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == beginner) {
				difficultySetting = 0;
			} else if (e.getSource() == intermediate) {
				difficultySetting = 1;
			} else if (e.getSource() == advanced) {
				difficultySetting = 2;
			}
		}
	}
}