package card.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

import FinalProjectUI.UIColor;

/**
 * 重載一個適當的繪畫方法以畫出一個圓形。
 * 設置一些事件使得只有當點擊圓形按鈕的範圍時才會作出響應
 * https://www.twblogs.net/a/5b8910942b71775d1cdfa4c1
 * https://stackoverflow.com/questions/42855224/how-to-add-rgb-values-into-setcolor-in-java
 */
public class CardCircleButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	private Shape shape; // 用於保存按鈕形狀，有助於偵聽點擊事件
	
	public CardCircleButton() {
		// empty
	}
	
	public CardCircleButton(String txt, int size) {
		setText(txt);
		setFont(CardFont.getTitleFont());
		
		setBounds(216, 87, size, size);
		setRolloverEnabled(true);
		
		setContentAreaFilled(false);
		setBorderPainted(false); 	// 不繪製邊框
		setFocusPainted(false); 	// 不繪製焦點狀態
	}
	
	//判斷鼠標的x, y座標是否落在按鈕形狀內
	public boolean contains(int x, int y) {
		if ( (shape == null) || (!shape.getBounds().equals(getBounds())) ) {
			// 構造一個橢圓形對象
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		
		return shape.contains(x, y);
	}
	
	// 畫圓型按鈕的背景和標籤
	protected void paintComponent(Graphics g) {
		// mac對isRollover()無反應 -> 改用mouseListener
		// 1. 取得圓形按鈕的model，且滑鼠在按鈕上方
		// 2. 滑鼠不在按鈕上方
		if (getModel().isRollover()) {
			g.setColor(UIColor._DDA15E); 	// 給個深點的顏色
		} else {
			g.setColor(UIColor._F4D58D); 	// 淺一點的顏色 
		}
		
		// 減少邊緣鋸齒狀
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// 畫一個矩形的內切橢圓，並填充這個橢圓
		// 當矩形為正方形時，畫出的橢圓即是正圓
		g.fillOval(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

}
