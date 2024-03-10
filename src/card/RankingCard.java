package card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Game.Player;
import card.component.CardButton;
import card.component.CardButton.Tag;
import card.component.CardLabel;
import card.component.RoundPanel;

class RankingCard extends Card {
	
	private static final long serialVersionUID = 1L;
	
	private CardLabel title;
	private RoundPanel[] results;
	private CardButton exitBtn;
	
	RankingCard() {
		// empty
		// automatically call super()
	}
	
	RankingCard(ArrayList<Player> players) {
		this();
		
		final int size = players.size();
		
		// title
		title = new CardLabel(CardLabel.Tag.RANK_TITLE, "排行榜");
		add(title);
		
		// players result
		results = new RoundPanel[size];
		for (int i = 0, ranking = 0; i < size; i++) {
			if (i != 0 && players.get(i).getHappiness() != players.get(i - 1).getHappiness()) {
				ranking++;
			}
			results[i] = new RoundPanel(i, ranking, players.get(i).getName());
			add(results[i]);
		}
		
		// btn
		exitBtn = new CardButton(Tag.EXIT);
	}
	
	void playAnimation() {
		titleSliding();
		for (int i = 0; i < results.length; i++) {
			panelSliding(i, results[i]);
		}
		add(exitBtn);
		repaint();
	}
	
	private void titleSliding() {		
		final int FINAL_Y = 10;
		Timer timer = new Timer(16, null);
		
		ActionListener taskPerformer = new ActionListener() {
			
			int y = title.getY();
			
			public void actionPerformed(ActionEvent e) {
				// 1. 向下移動
				// 2. 向上移動
				if (y < FINAL_Y) {
					title.setLocation(0, y += 4);
				} else if (y > FINAL_Y) {
					title.setLocation(0, y -= 1);
				} else{
					timer.stop();
					unlock();
				}
			}
		};
		
		timer.addActionListener(taskPerformer);
		timer.start();
		locking(0l);
	}
	
	private void panelSliding(int i, RoundPanel result) {
		final int FINAL_X = (Card.WIDTH - RoundPanel.WIDTH) / 2;
		final int Y = result.getY();
		Timer timer = new Timer(16, null);
		
		ActionListener taskPerformer = new ActionListener() {
			
			int x = result.getX();

			public void actionPerformed(ActionEvent e) {
				if (x > FINAL_X) {
					result.setLocation(x -= 8, Y);
				} else if (x < FINAL_X) {
					result.setLocation(x += 1, Y);
				} else {
					timer.stop();
					if (i + 1 == results.length) {
						unlock();
					}
				}
			}
		};
		
		timer.addActionListener(taskPerformer);
		timer.start();
		
		long delay = (i + 1 == results.length) ? 0l : 250l;
		locking(delay);
	}
	
	private void locking(long delay) {
		synchronized (this) {
			try {
				this.wait(delay);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void unlock() {
		synchronized (this) {
			this.notify();
		}
	}

}
