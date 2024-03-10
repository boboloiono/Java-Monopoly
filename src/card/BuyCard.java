package card;

import java.awt.Image;

import javax.swing.ImageIcon;

import Game.NormalLoc;
import card.component.CardButton;
import card.component.CardButton.Tag;
import card.component.CardLabel;

// 空地卡
class BuyCard extends Card {

	private static final long serialVersionUID = 1L;
	private static final String PREFIX = "Resources/card/normal_location/";
	private static final String[] PATH = { "judicial_museum.jpg",
										   "chimei_museum.jpg",
										   "tainan_art_museum_building_2.jpg",
										   "chuan_mei_theater.jpg",
										   "T.S._mall.jpg",
										   "chikan_tower.jpg",
										   "taiwan's_first_school.jpg",
										   "hayashi_hyakkaten_02.jpg",
										   "anping_fort.jpg",
										   "sicao_green_tunnel.jpg",
										   "guanziling_hot_spring.jpg",
										   "salt_mountain.jpg",
										   "yuguang_island.jpg",
										 };
	private Image img; // icon的圖片，和TollCard共用
	
	BuyCard(){
		// empty
	}
	
	BuyCard(NormalLoc loc){
		// this.panel
		super();
		
		// 地名
		CardLabel locName = new CardLabel(CardLabel.Tag.LOC_TITLE, loc.getName());
		add(locName);
		
		// 照片
		ImageIcon icon = new ImageIcon(PREFIX + PATH[getIndex(loc.getLocationIndex())]);
		img = icon.getImage();
		CardLabel iconLabel = new CardLabel(icon);
		add(iconLabel);
		
		// 過路費 + 購買價
		String msg = "<html>過路費：NT$" + loc.getToll() + "<br>" + "購買價：NT$" + loc.getSoldPrice() + "</html>";
		CardLabel description = new CardLabel(CardLabel.Tag.IMG_TXT, msg);
		add(description);
		
		// button
		CardButton btnBuy = new CardButton(Tag.BUY);
		add(btnBuy);
		CardButton btnSkip = new CardButton(Tag.SKIP);
		add(btnSkip);
	}
	
	Image getIconImage() {
		return img;
	}
	
	private int getIndex(int locID) {
		switch (locID) {
			case 1, 2, 3:
				return locID - 1;
			case 5, 6:
				return locID - 2;
			case 8, 9, 10, 11:
				return locID - 3;
			case 20, 21, 22, 23:
				return locID - 11;
			default:
				System.out.println("Wrong food locID in \"card.BuyCard.getIndex(int)\"");
				System.exit(1);
		}
		return -1;
	}

}
