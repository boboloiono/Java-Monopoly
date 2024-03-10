package card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import card.component.CardButton;

public class ButtonListener implements ActionListener {
	
	private static GameCard gameCard;
	
	static void setGameCard(GameCard _gameCard) {
		gameCard = _gameCard;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardButton btn =  (CardButton)(e.getSource()); // 取得被觸發的物件
		switch (btn.getTag()) {
			case BUY: 		// 點擊「購買」按鈕
				gameCard.checkBuy();
				break;
			case SKIP: 		// 點擊「略過」按鈕
				gameCard.setResultMessage(""); // 空白結果
				break;
			case CONTINUE: 	// 點擊「繼續」按鈕
				gameCard.setResultMessage("");
				break;
			case EXIT: 		// close windows
				System.exit(0);
			default: // error
				System.out.println("Wrong CardButton.tag in \"card.ButtonListener.actionPerformed(ActionEvent)\"");
				System.exit(1);
		}
	}

}
