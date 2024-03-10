package card;

import javax.swing.JPanel;

import FinalProjectUI.UIColor;

public class Card extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 540; 	// 540
	public static final int HEIGHT = 260;	// 260
	
	Card() {
		setBounds(0, 0, WIDTH, HEIGHT);
		setLayout(null);
		setBackground(UIColor._8D0801);
	}

}
