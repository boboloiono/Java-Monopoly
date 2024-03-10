package card;

import javax.swing.ImageIcon;

import Game.FoodLoc;
import card.component.CardButton;
import card.component.CardButton.Tag;
import card.component.CardLabel;

// 美食區
class FoodCard extends Card {

	private static final long serialVersionUID = 1L;
	private static final String PREFIX = "Resources/card/food/";
	private static final String[] PATH = { "tofu_ice.jpg",
										   "beef_soup.jpg",
										   "fruit_milk_ice.jpg",
										   "fish_ball_soup.jpg",
										   "bubble_tea.jpg"
										 };

	FoodCard(){
		// empty
	}
	
	FoodCard(FoodLoc loc){
		// this.panel: set size & layout
		super();
		
		// 店名
		CardLabel locName = new CardLabel(CardLabel.Tag.LOC_TITLE, loc.getName());
		add(locName);
		
		// 照片
		String str = PREFIX + PATH[loc.getLocationIndex() - 14];
		ImageIcon icon = new ImageIcon(str);
		CardLabel iconLabel = new CardLabel(icon);
		add(iconLabel);
		
		// 品項&價錢 + 心情
		str = "<html>" + loc.getFoodName() + "<br>" + "快樂指數&nbsp;+" + loc.getFoodHappiness() + "</html>"; // html語法：「<br>」換行、「&nbsp;」空格
		CardLabel description = new CardLabel(CardLabel.Tag.IMG_TXT, str);
		add(description);
		
		// button
		CardButton btnBuy = new CardButton(Tag.BUY);
		add(btnBuy);
		CardButton btnSkip = new CardButton(Tag.SKIP);
		add(btnSkip);
	}

}
