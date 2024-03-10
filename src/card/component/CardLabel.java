package card.component;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import FinalProjectUI.UIColor;
import card.Card;

public class CardLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	public enum Tag {
		LOC_TITLE(23, 15, 55, LEFT, CardFont.getTitleFont()),
		LIFE_CHANCE_TITLE(0, 20, 55, CENTER, CardFont.getTitleFont()), 				// 機會命運
		RANK_TITLE(0, -55, 55, CENTER, CardFont.getTitleFont()),
		IMG_TXT(250, 90, 180 - 90, LEFT, CardFont.getNormalFont()),
		TOLL_TXT(220, 75, 180 - 90, LEFT, CardFont.getNormalFont()),
		SPECIAL_TXT(0, 20, Card.HEIGHT - 20, CENTER, CardFont.getNormalFont()),
		SLOT_TXT(0, 205, 40, CENTER, CardFont.getNormalFont()),
		RESULT(0, 0, Card.HEIGHT, CENTER, CardFont.getTitleFont()),
		RANK_TXT(0, 0, RoundPanel.WIDTH, RoundPanel.HEIGHT, CENTER, CardFont.getNormalFont()),
		PLAYER_LABEL(Card.WIDTH - 70, 0, 40, CENTER, CardFont.getPlayerFont());
		
		Tag(int x, int y, int height, int align, Font font){
			this.x = x;
			this.y = y;
			width = Card.WIDTH - x;
			this.height = height;
			this.align = align;
			this.font = font;
		}
		
		Tag(int x, int y, int width, int height, int align, Font font){
			this(x, y, height, align, font);
			this.width = width;
		}

		int x;
		int y;
		int width;
		int height;
		int align;
		Font font;
	}
	
	public CardLabel() {
		// empty
	}
	
	public CardLabel(Tag tag, String txt) {
		setText(txt);
		setFont(tag.font);
		setBounds(tag.x, tag.y, tag.width, tag.height);
		setHorizontalAlignment(tag.align);
		
		switch (tag) {
			case PLAYER_LABEL:
				setOpaque(true);
				setForeground(UIColor._8D0801);
				setBackground(UIColor._F4D58D);
				break;
			case IMG_TXT:
				setVerticalAlignment(TOP);
			default:
				setForeground(UIColor._FEFAE0);
		}
	}
	
	// 圖片label
	public CardLabel(ImageIcon icon) {
		setIcon(icon);
		setBounds(40, 75, icon.getIconWidth(), icon.getIconHeight());
	}
	
	// only for player label
	public void sliding(int i) {
		Timer timer = new Timer(16, null);
		
		ActionListener taskPerformer = new ActionListener() {
			
			final int finalX = getX();
			int currentX = finalX;
			boolean moveRight = true; // playerLabel是否向右移動
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// playerLabel一開始向右移動
				// 右移到底後，更換文字內容
				// 再向左移回原位
				if (moveRight) {
					setLocation(currentX += 8, 0);
					if (currentX >= Card.WIDTH) {
						moveRight = false;
						setText("P" + (i + 1)); // 改成「P?」
						//playerLabel.repaint();
					}
				} else {
					setLocation(currentX -= 8, 0);
					if (currentX <= finalX) {
						timer.stop();
						setLocation(finalX, 0);
					}
				}
				//displayPane.repaint();
			}
		};
		
		timer.addActionListener(taskPerformer);
		timer.start();
	}
}
