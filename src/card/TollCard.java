package card;

import javax.swing.ImageIcon;

import Game.NormalLoc;
import card.component.CardButton;
import card.component.CardButton.Tag;
import card.component.CardLabel;

// 過路費：和同地點的buyCard共用圖片
class TollCard extends Card {
	
	private static final long serialVersionUID = 1L;
	private String tollInfo; 	// 紀錄過路費字串："支付過路費XX元"
	private CardLabel descriptionLabel; 	/** 顯示：
	 							 			  *  "這是「玩家XXX」的土地\n" + 
	 							 			  *  "支付過路費 XX 元"
	 							 			  */
	
	TollCard(){
		// empty
	}
	
	TollCard(NormalLoc loc, BuyCard bc){
		// this.panel
		super();
		
		// 地名
		CardLabel locName = new CardLabel(CardLabel.Tag.LOC_TITLE, loc.getName());
		add(locName);
		
		// 照片
		ImageIcon icon = new ImageIcon(bc.getIconImage());
		CardLabel iconLabel = new CardLabel(icon);
		add(iconLabel);
		
		// 過路費
		tollInfo = "<br>支付過路費&nbsp;&nbsp;NT$" + loc.getToll() + "</html>"; // 「&nbsp;」:html空白 
		descriptionLabel = new CardLabel(CardLabel.Tag.TOLL_TXT, "");
		setMessage("XXXX");
		add(descriptionLabel);
		
		// button
		CardButton btnContinue = new CardButton(Tag.CONTINUE);
		add(btnContinue);
	}
	
	void setMessage(String ownerName) {
		String description = "<html>這是「" + ownerName + "」的土地" + tollInfo;
		descriptionLabel.setText(description);
		//repaint();
	}

}
