package FinalProjectUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Game.Player;

public class PlayerSetFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton btn;
	private JTextField textField;
	private int i = 0;
	protected List<Player> players;
	public static String[] playerName = {"player1Name", "player2Name", "player3Name", "player4Name"};
	
	public PlayerSetFrame(int i){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		btn = new JButton("Submit");
		btn.addActionListener(this);

		ImageIcon image = new ImageIcon("Resources/Player/superhero"+i+".png");
		JLabel heroImg = new JLabel();
		heroImg.setIcon(image);
		
		textField = new JTextField();
		textField.setSize(250, 40);
		textField.setFont(UIFont.createFont("Resources/font/jf-openhuninn-1.0.ttf", Font.PLAIN, 50));
		textField.setForeground(new Color(0x00FF00));
		textField.setBackground(Color.black);
		textField.setCaretColor(Color.white);
		textField.setText("player"+i+"name");
		
		this.i = i;
		this.add(btn);
		this.add(heroImg);
		this.add(textField);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn){

			switch (i) {
				case 1:
					PlayerSetFrame.playerName[0] = textField.getText(); break;
				case 2:
					PlayerSetFrame.playerName[1] = textField.getText(); break;
				case 3:
					PlayerSetFrame.playerName[2] = textField.getText(); break;
				case 4:
					PlayerSetFrame.playerName[3] = textField.getText(); break;
			}
			
			if(i==4) {
				btn.setEnabled(false);
				textField.setEditable(false);
				this.dispose();
				new MainFrame().setVisible(true);
			}
			else {
				new PlayerSetFrame(i+1);
				this.dispose();
			}
		}
	}
}