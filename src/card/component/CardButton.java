package card.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

import card.ButtonListener;
import FinalProjectUI.UIColor;

public class CardButton extends JButton{
	
	private static final long serialVersionUID = 1L;

	public enum Tag {
		BUY(250, 180), SKIP(415, 180), CONTINUE(442, 198), EXIT(440, 10);
		
		private int x;
		private int y;
		
		Tag(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		int getX() {
			return x;
		}
		
		int getY() {
			return y;
		}
	}
	
	private static final int WIDTH = 80;
	private static final int HEIGHT = 45;
	private Tag tag; 	// 紀錄現在是哪種按鈕
	private Shape shape;
	
	
	
	/*****************************************************
	 * 					  CONSTRUCTOR					 *
	 *****************************************************/
	
	public CardButton(){
		// empty
	}
	
	public CardButton(Tag tag) {
		this.tag = tag;
		setText(toString());
		setFont(CardFont.getButtonFont());
		setBounds(tag.getX(), tag.getY(), WIDTH, HEIGHT);
		addActionListener(new ButtonListener());
		
		setRolloverEnabled(true);
		
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}
	
	
	
	/*****************************************************
	 * 					  PUBLIC METHOD					 *
	 *****************************************************/
	
	public Tag getTag() {
		return tag;
	}
	
	public String toString() {
		switch (tag) {
			case BUY:
				return "購買";
			case SKIP:
				return "略過";
			case CONTINUE:
				return "繼續";
			case EXIT:
				return "結束";
		}
		
		// error
		System.out.println("wrong CardButton.Txt type in package card.component, method toString()");
		System.exit(1);
		return "";
	}
	
	public boolean contains(int x, int y) {
		if ( (shape == null) || (!shape.getBounds().equals(getBounds())) ) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
	
	
	
	/*****************************************************
	 * 					PROTECTED METHOD				 *
	 *****************************************************/
	
	protected void paintComponent(Graphics g) {
		if (getModel().isRollover()) {
			g.setColor(UIColor._DDA15E);
		} else {
			g.setColor(UIColor._F4D58D);
		}
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRoundRect(0, 0, WIDTH, HEIGHT, 16, 16);
		super.paintComponent(g);
	}

}
