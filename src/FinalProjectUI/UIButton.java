package FinalProjectUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

class UIButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	private Shape shape;
	private Color normalColor;
	private Color hoverColor;
	
	UIButton(){
		// empty
	}
	
	UIButton(String text, Color normalColor, Color hoverColor){
		super(text);
		this.normalColor = normalColor;
		this.hoverColor = hoverColor; 
		
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}
	
	public boolean contains(int x, int y) {
		if ( (shape == null) || (!shape.getBounds().equals(getBounds())) ) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
	
	protected void paintComponent(Graphics g) {
		if (getModel().isRollover() && hoverColor != null) {
			g.setColor(hoverColor);
		} else {
			g.setColor(normalColor);
		}
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
		super.paintComponent(g);
	}

}
