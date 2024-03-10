package card;

import java.awt.BorderLayout;

import Game.SpecialLoc;
import card.component.CardButton;
import card.component.CardButton.Tag;
import card.component.CardLabel;

// 特殊地點、機會命運結果、resultCard
class TxtCard extends Card {

	private static final long serialVersionUID = 1L;

	// 不同機會命運、特殊地點的文字內容量不同，因此contentLabel也有不同的長寬及位置
	private enum StringFormat {
		TWO_LINES,
		FOUR_LINES,
		FIVE_LINES();
	}
	
	private CardLabel descriptionLabel; // 顯示文字內容
	private StringFormat format; 		// descriptionLabel的文字切割
	
	// resultCard
	TxtCard() {
		super();
		
		// label
		descriptionLabel = new CardLabel(CardLabel.Tag.RESULT, "");
		
		// layout
		setLayout(new BorderLayout());
		add(descriptionLabel, BorderLayout.CENTER);
	}
	
	// 特殊地點、機會命運
	TxtCard(SpecialLoc loc) {
		super();
		
		int locId = loc.getLocationIndex();
		
		// 指定descriptionLabel文字切割格式 及 地名label
		String description = loc.getDescription();
		switch (locId) {
			case 7, 19:
				format = StringFormat.TWO_LINES;
				description = stringFormatting(description, "，"); // 切成兩行
			case 0, 12:
				CardLabel name = new CardLabel(CardLabel.Tag.LOC_TITLE, loc.getName());
				add(name);
				break;
		}
		
		// 文字內容
		descriptionLabel = new CardLabel(CardLabel.Tag.SPECIAL_TXT, description);
		add(descriptionLabel);
		
		// 按鈕
		CardButton btnContinue = new CardButton(Tag.CONTINUE);
		add(btnContinue);
	}
	
	// resultCard
	void setMessage(String message) {
		descriptionLabel.setText(message);
		//repaint();
	}
	
	// 當玩家走到火車站，要被傳送至隨機地點時
	void setMessage(String message, String delimiter) {
		message = stringFormatting(message, delimiter);
		setMessage(message);
	}
	
	// 機會命運更改label格式 及 文字
	void setMessage(int lifeChanceID, String description) {
		switch (lifeChanceID) {
			case 0, 3, 5, 9, 10, 11: // 不切割
				break;
			case 1, 4, 6, 7:
				format = StringFormat.TWO_LINES;
				description = stringFormatting(description, "。");
				break;
			case 2:
				format = StringFormat.FIVE_LINES;
				description = stringFormatting(description, "，|。|：");
				break;
			case 8:
				format = StringFormat.FOUR_LINES;
				description = stringFormatting(description, "，|。");
				break;
			default:
				System.out.println("wrong lifeChanceID in \"card.TxtCard.setMessage(int, String)\"");
				System.exit(1);
		}
		descriptionLabel.setText(description);
		//repaint();
	}
	
	// 文字內容切割成數行
	private String stringFormatting(String description, String delimiter) {
		String[] tokens = description.split(delimiter);
		
		// 根據不同的特殊地點 或 機會命運，每行後面要把delimiter補回去，除了最後一行
		switch (format) {
			// 共兩行
			case TWO_LINES:
				tokens[0] += delimiter + "<br>";
				break;
			case FIVE_LINES:
				tokens[3] += "，"; 		// 快樂分數+20，
			case FOUR_LINES:
				tokens[0] += "，<br>"; 	// 幫暗戀的學姊填問卷，  | 在安靜的美術館，
				tokens[1] += "，<br>"; 	// 獲得300元電影票禮券， | 不小心放響屁，
				tokens[2] += "。<br>"; 	// 藉機約學姊看電影。    | 尷尬分數破表。
				break;
			default:
				System.out.println("wrong LabelFormat in \"card.TxtCard.stringFormatting(String, String)\"");
				System.exit(1);
		}
		
		// 全部串接在一起，並以html格式打包
		description = "<html>";
		for (int i = 0; i < tokens.length; i++) {
			description += tokens[i];
		}
		description += "</html>";
		
		return description;
	}

}
