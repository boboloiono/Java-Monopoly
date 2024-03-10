package card.component;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import FinalProjectUI.UIColor;
import card.Card;

public class RoundPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 360;
	public static final int HEIGHT = 36;
	
	private static final String PREFIX = "Resources/card/ranking/";
	private static final String[] PATH = { "in-love.png",
										   "happy.png",
										   "confused.png",
										   "sad.png"
										 };
	
	public RoundPanel(){
		// empty
	}
	
	public RoundPanel(int yIdx, int rankingIdx, String name) {
		// style
		setBounds(Card.WIDTH, 70 + 45 * yIdx, WIDTH, HEIGHT);
		setLayout(null);
		setBackground(UIColor._8D0801);
		
		// name
		CardLabel nameLabel = new CardLabel(CardLabel.Tag.RANK_TXT, name);
		add(nameLabel);
		
		// icon
		ImageIcon icon = new ImageIcon(PREFIX + PATH[rankingIdx]);
		CardLabel iconLabel = new CardLabel(icon);
		iconLabel.setLocation(10, (getHeight() - iconLabel.getHeight()) / 2);
		add(iconLabel);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(UIColor._BF0603);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
	}

}
