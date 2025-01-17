package PartIV;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollDice extends JFrame {
	
	private JPanel resultPanel;
	private JPanel buttonPanel;
	private JPanel wrapperPanel;
	private JPanel dicePanel;
	private ImagePanel imagePanel1;
	private ImagePanel ImagePanel2;
	private JLabel textLabel;
	
	private int dice1 = 1;
	private int dice2 = 1;
	private int result = dice1+dice2;
	
	class DiceListener extends MouseAdapter {
		private int diceNum;
		public DiceListener(int dice) {
			diceNum = dice;
		}
		public void mouseClicked(MouseEvent event) {
			roll(diceNum);
			RollDice.this.repaint();
		}
	}
	
	public RollDice() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		
		wrapperPanel = new JPanel(new BorderLayout());
		dicePanel = new JPanel(new GridLayout(1, 2));
		resultPanel = new JPanel();
		buttonPanel = new JPanel();
		textLabel = new JLabel();
		resultPanel.add(textLabel);

		this.roll(1);
		this.roll(2);
		this.repaint();
		
		JButton rollButton = new JButton("Roll Dice");
		rollButton.addActionListener((e) -> {
			this.roll(1);
			this.roll(2);
			this.repaint();
		});
		
		buttonPanel.add(rollButton);
		wrapperPanel.add(dicePanel, BorderLayout.NORTH);
		wrapperPanel.add(resultPanel, BorderLayout.CENTER);
		wrapperPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(wrapperPanel);
		this.setSize(600, 300);
	    this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void roll(int diceNum) {
		if (diceNum == 1) {
			this.dice1 = (int)(Math.random() * 6) + 1;
		} else if (diceNum == 2) {
			this.dice2 = (int)(Math.random() * 6) + 1;
		}
	}

	public ImagePanel getDiceImage(int number, int diceIndex) {
		ImagePanel img = new ImagePanel("die" + number + ".png");
		img.addMouseListener(new DiceListener(diceIndex));
		return img;
	}

	public void repaint() {
		this.result = dice1 + dice2;
		textLabel.setText("Result: " + this.result);
		
		dicePanel.removeAll();
		imagePanel1 = getDiceImage(dice1, 1);
		ImagePanel2 = getDiceImage(dice2, 2);
		dicePanel.add(imagePanel1);
		dicePanel.add(ImagePanel2);
	}

	public static void main(String[] args) {
		RollDice rollDice = new RollDice();
	
	}
}