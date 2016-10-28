package minesweeper;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class MainLayoutPanel extends JPanel {
	private DifficultyPanel difficulty;
	private GameLayoutPanel gameLayout;
	private CardLayout cardLayout;
        

	private JPanel cards;
	
	public MainLayoutPanel() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		difficulty = new DifficultyPanel();
		
		ButtonHandler handler = new ButtonHandler();
		JButton play = new JButton("Play Game");
		play.addActionListener(handler);
		
		difficulty.add(play);
		add(difficulty, "Difficulty");
                
		
		
		cardLayout.show(this, "Difficulty");
		
	}
	
        public void startGame(){
            gameLayout = new GameLayoutPanel(difficulty.getDifficulty());
            add(gameLayout, "Game Layout");
            cardLayout.show(this, "Game Layout");
        }
        
        
        
	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			startGame();
		}
	}
}
