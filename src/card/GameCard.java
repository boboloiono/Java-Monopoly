package card;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import FinalProjectUI.EnterFrame;
import FinalProjectUI.MusicPlayer;
import FinalProjectUI.UIColor;
import Game.FoodLoc;
import Game.Game;
import Game.Location;
import Game.NormalLoc;
import Game.SpecialLoc;
import card.component.CardFont;
import card.component.CardLabel;

public class GameCard {
	
	private Game game; 					// 後台
	private Card[][] mapCards; 			// 各地點的卡牌，normalLoc有兩張(空地 + 有主地)，其他地方都是1張(機會命運結果卡包在slotCard裡)
	private Card infoCard; 				// 現在顯示的mapCards
	private Card resultCard; 			// 顯示(1)誰的回合；(2)玩家操作完的結果，
	private CardLabel playerLabel; 		// 右上角顯示目前玩家：1P, 2P, 3P, 4P
	private JLayeredPane displayPane; 	// 要給ui的卡：會動態加入/移除infoCard及resultCard、並顯示目前玩家
	private String resultMessage; 		// resultCard要顯示的文字：購買成功、餘額不足、破產
	
	
	
	/*****************************************************
	 * 					  CONSTRUCTOR					 *
	 *****************************************************/
	
	public GameCard() {
		// empty
	}
	
	public GameCard(Game _game) {
		game = _game;
		
		// assign static value
		CardFont.InitCardFont();
		ButtonListener.setGameCard(this);
		
		// create mapCards
		ArrayList<Location> locs = game.getLocations();
		int size = locs.size();
		mapCards = new Card[size][2];
		for (int i = 0; i < size; i++) {
			Location loc = locs.get(i);
			// 1. 美食區
			// 2. 一般景點
			// 3. 特殊地點 / 機會命運
			if (loc instanceof FoodLoc) {
				mapCards[i][0] = new FoodCard((FoodLoc)loc);
			} else if (loc instanceof NormalLoc) {
				mapCards[i][0] = new BuyCard((NormalLoc)loc); 							// 空地卡
				mapCards[i][1] = new TollCard((NormalLoc)loc, (BuyCard)mapCards[i][0]); // 過路費卡
			} else if (loc instanceof SpecialLoc) {
				switch (i) {
					case 0, 7, 12, 19: 	// 4個特殊地點
						mapCards[i][0] = new TxtCard((SpecialLoc)loc);
						break;
					case 4:				// 第1個機會命運
						mapCards[i][0] = new SlotCard((SpecialLoc)loc);
						break;
					case 13: 			// 第2個機會命運，與第1個機會命運共用同張卡
						mapCards[i][0] = mapCards[4][0];
						break;
					default:
						System.out.println("wrong locationIndex in \"card.GameCard(Game)\"");
						System.exit(1);
				}
			}
		}
		
		// create infoCard
		infoCard = new Card(); 		// 1張全空白卡，會動態加入/移除mapCard
		
		// create resultCard
		resultCard = new TxtCard(); // 含有label的空白卡，之後可透過setMessage(String)來更新目前為誰的回合 / 使用者的操作結果
		
		// create playerLabel
		playerLabel = new CardLabel(CardLabel.Tag.PLAYER_LABEL, "");
		
		// create displayPane
		displayPane = new JLayeredPane();
		displayPane.setSize(Card.WIDTH, Card.HEIGHT);
		displayPane.setLayout(null);
		displayPane.setOpaque(true);
		displayPane.setBackground(UIColor._8D0801);
		displayPane.add(resultCard, JLayeredPane.DEFAULT_LAYER); 	// 將卡片內容顯示在最下層
		displayPane.add(playerLabel, JLayeredPane.PALETTE_LAYER); 	// 將player label顯示在最上層
	}
	
	
	
	/*****************************************************
	 * 					  PUBLIC METHOD					 *
	 *****************************************************/
	
	public void test(int i, int j) {
		Component[] cs =  displayPane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER);
		displayPane.remove(cs[0]);
		displayPane.add(mapCards[i][j], JLayeredPane.DEFAULT_LAYER);
		displayPane.repaint();
	}
	
	// 給ui呼叫：取得要顯示在視窗中的卡片
	public JLayeredPane getDisplayPane() {
		return displayPane;
	}
	
	// 給ui在擲骰子前呼叫：顯示目前玩家
	public void setPlayerLabel() {
		// 中央顯示「???的回合」
		((TxtCard)resultCard).setMessage("「" + game.getPresentPlayer().getName() + "」的回合");
		setDisplayPane(resultCard);
		
		// 右上角label
		//AnimationEffect.playerLabelEffect(playerLabel, game.getPresentPlayerID());
		playerLabel.sliding(game.getPresentPlayerID());
	}
	
	// ui在每次擲完骰子且移動玩家後，進行呼叫
	// 根據目前位置決定要顯示的卡片內容
	public void setInfoCard() {
		// 位在自己買下的地，則直接更新resultCard
		if (game.checkNormalOwner()) {
			resultMessage = "您位在自己的土地上";
			return;
		}
		
		// 位在機會命運、特殊地點、美食區、空地、或其他人的地
		Location loc = game.getPresentLoc();
		int locID = loc.getLocationIndex();
		
		// 判斷目前位置：
		// 1. 其他人的地：第2張卡(過路費)
		// 2. 空地、美食區、特殊地點、機會命運：第1張卡
		if ( (loc instanceof NormalLoc) && !((NormalLoc)loc).checkSoldStatus() ) {
			game.normalEvent(); 			// 後臺收取過路費
			infoCard = mapCards[locID][1]; 	// 過路費卡
		} else {
			infoCard = mapCards[locID][0]; 	// 空地卡、食物卡、特殊地點卡、拉霸卡
			
			if (loc instanceof SpecialLoc) {
				String message = game.specialEvent(); // 後臺設定隨機事件及對應數字，並回傳文字描述
				System.out.println("機會命運: " + message);
				switch (locID) {
					case 4, 13: // 機會命運
						System.out.println("num: " + game.getSpecialEventNum());
						((SlotCard)infoCard).setSlotCard(game.getSpecialEventNum(), message); // 設定拉霸卡中的機會命運結果卡
						break;
					case 7: 	// 台南車站傳送至隨機地點
						((TxtCard)infoCard).setMessage(message, "，");
						break;
				}
			}
		}
		
		setDisplayPane(infoCard);
		
		// lock
		locking(0l);
	}
	
	// ui在更新操作結果後呼叫，
	// 顯示resultCard (玩家操作結果 及 贏家)
	public void setResultCard() {
		// 顯示買完食物/空地後的結果
		if (!resultMessage.equals("")) {
			if (resultMessage.equals("購買成功")) {
				MusicPlayer.playSFX("Resources/Music/cash_register_sound_effects.wav");
			}
			setResultCard(1500l);
			MusicPlayer.closeSFX();
		}
		
		// 1. 玩家獲勝
		// 2. 玩家破產
		if (game.checkWin()) {
			resultMessage = "<html>您的幸福感爆表<br>公布遊戲結果</html>";
			setRankingCard();
		} else if (game.checkBankStatus()) {
			resultMessage = "<html>您已破產<br>公布遊戲結果</html>";
			setRankingCard();
		} else {
			MusicPlayer.playSFX("Resources/Music/buttonClick.wav");
		}
	}
	
	
	
	/*****************************************************
	 * 					 PACKAGE METHOD					 *
	 *****************************************************/
	
	// ButtonListener.actionPerformed(ActionEvent)呼叫
	// 玩家選擇購買食物或空地
	void checkBuy() {
		boolean success; // 購買是否成功
		
		// 1. 食物
		// 2. 空地
		if (game.getPresentLoc() instanceof FoodLoc) {
			success = game.buyFood();
		} else {
			success = game.buyLocation();
			// 若成功，更新卡片上的地主名稱
			if (success) {
				TollCard tc = (TollCard)mapCards[game.getPresentLoc().getLocationIndex()][1];
				tc.setMessage(game.getPresentPlayer().getName());
			}
		}

		resultMessage = success ? "購買成功" : "餘額不足";
		
		unlock();
	}
	
	// ButtonListener.actionPerformed(ActionEvent)呼叫
	// 在空地 / 美食區按下「略過」時
	// 收過路費、特殊地點、機會命運按下「繼續」時
	void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
		unlock();
	}
	
	
	
	/*****************************************************
	 * 					 PRIVATE METHOD					 *
	 *****************************************************/
	
	private void playMusic() {
		// close playing music
		try {
			EnterFrame.clip.stop();
			EnterFrame.clip.close();
			EnterFrame.ais.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// open start music
		MusicPlayer.playBackgroundMusic("Resources/Music/startMusic.wav");
	}
	
	private void setResultCard(long delay) {
		((TxtCard)resultCard).setMessage(resultMessage);
		setDisplayPane(resultCard);
		
		locking(delay);
	}
	
	void setRankingCard() {		
		// create card
		RankingCard rankingCard = new RankingCard(game.getRankPlayers());
		
		// show player's result
		playMusic();
		setResultCard(1500l);

		// add card
		displayPane.removeAll();
		displayPane.add(rankingCard, JLayeredPane.DEFAULT_LAYER);
		//displayPane.repaint();
		
		rankingCard.playAnimation();
		locking(0l);
	}
	
	// this.setInfoCard(), this.setResultCard(String)呼叫
	// 移除displayCard舊有的卡片，加入要顯示的卡片
	private void setDisplayPane(Component added) {
		Component[] cs =  displayPane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER);
		for (Component c : cs) {
			displayPane.remove(c);
		}
		displayPane.add(added, JLayeredPane.DEFAULT_LAYER);
		//displayPane.repaint();
	}
	
	private void locking(long delay) {
		synchronized (game) {
			try {
				game.wait(delay);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void unlock() {
		synchronized (game) {
			game.notify();
		}
	}

}
