package card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import FinalProjectUI.MusicPlayer;
import Game.SpecialLoc;
import card.component.CardCircleButton;
import card.component.CardLabel;

// 機會命運抽牌效果卡
class SlotCard extends Card implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final String PREFIX = "Resources/card/slot/";
	private static final String[] PATH = {"receipt.jpg",
										  "scratch-off_lottery_ticket.jpg",
										  "movie.jpg",
										  "shit.jpg",
										  "wallet_lost.jpg",
										  "crossing_street.jpg",
										  "bad_driver.jpg",
										  "beach_upset.jpg",
										  "art_gallery.jpg",
										  "bike_thief.jpg",
										  "car_accident.jpg",
										  "train_ticket.jpg"};
	private static ImageIcon[] icons; 	// 隨機效果的圖片
	private JLabel iconLabel; 			// 圖片label
	private CardLabel titleLabel; 		// 「命運 / 機會 抽抽樂」
	private CardLabel contentLabel; 	// 「↑↑ 點選按鈕，決定你的命運吧 ↑↑」
	private int destIconID; 			// 要停留的圖片index
	private CardCircleButton btn; 		// 機會命運點選按鈕，使用者點選後，開始隨機的視覺效果
	private TxtCard lifeChanceCard; 	// 機會命運結果卡片
	
	SlotCard() {
		// empty
	}
	
	SlotCard(SpecialLoc loc) {
		super();
		
		// title
		titleLabel = new CardLabel(CardLabel.Tag.LIFE_CHANCE_TITLE, "命運 / 機會 抽抽樂");
		
		// contentLabel
		contentLabel = new CardLabel(CardLabel.Tag.SLOT_TXT, "↑↑ 點選按鈕，決定你的命運吧 ↑↑");
		
		// 圖片，先不用加入面板中，等到使用者點擊按鈕後再加入並展示隨機的視覺效果
		icons = new ImageIcon[PATH.length];
		for (int i = 0; i < PATH.length; i++) {
			icons[i] = new ImageIcon(PREFIX + PATH[i]);
		}
		int width = icons[0].getIconWidth();
		int height = icons[0].getIconHeight();
		iconLabel = new JLabel();
		iconLabel.setBounds((getWidth() - width) / 2, (getHeight() - height) / 2, width, height);
		
		// 按鈕
		btn = new CardCircleButton("Go!", 108);
		btn.addActionListener(this);
		
		// 抽卡結果卡：new出一張空白卡，之後只要透過setMessage(int, String)即可將該次的抽卡結果更新
		lifeChanceCard = new TxtCard(loc);
		
		resetSlotCard();
	}

	// 點擊按鈕後
	public void actionPerformed(ActionEvent e) {
		// 移除所有物件，顯示iconLabel
		removeAll();
		MusicPlayer.playBackgroundMusic("Resources/Music/lotterySound.wav"); 
		add(iconLabel);
		repaint();
		// 展示隨機視覺效果
		slotEffect();
	}
	
	// gameCard執行setInfoCard時使用，會停在拉霸畫面
	void setSlotCard(int destIconID, String lifeChanceDescription) {
		// 設定機會命運結果卡
		this.destIconID = destIconID;
		lifeChanceCard.setMessage(destIconID, lifeChanceDescription);
		// 重製卡片
		resetSlotCard();
	}
	
	// 移除所有components，加入title、button以及說明文字(contentLabel)，進入拉霸畫面
	private void resetSlotCard() {
		removeAll();
		add(titleLabel);
		add(contentLabel);
		add(btn);
		//repaint();
	}
	
	// 隨機視覺效果：前面圖片會不停輪流，直到最後一次要停在destIconID
	private void slotEffect() {
		Timer timer = new Timer(75, null);
		ActionListener taskPerformer = new ActionListener() {
			
			int i = 0; // 起始圖片，同時也是計數
			int delay = timer.getInitialDelay();

			public void actionPerformed(ActionEvent e) {
				if (i > 19) {
					if (i > 20) {
						displayLifeChanceCard(); // 更新成機會命運結果卡
						timer.stop();
					}
					i++;
					MusicPlayer.clip.stop();
					return; // 不再更新圖片
				}
				
				iconLabel.setIcon(icons[i % icons.length]); // 換圖片
				//repaint();
				
				switch (i) {
					case 12:
						delay *= 2;
						timer.setDelay(delay);
						break;
					case 16:
						delay *= 2;
						timer.setDelay(delay);
						break;
					case 19:
						iconLabel.setIcon(icons[destIconID]); // 停在後台選擇的圖片
						//repaint();
						delay = 1500;
						timer.setDelay(delay);
						break;
				}
				i++;
			}
		};
		timer.addActionListener(taskPerformer);
		timer.start();
	}
	
	private void displayLifeChanceCard() {
		removeAll();
		add(lifeChanceCard);
		repaint();
	}

}
