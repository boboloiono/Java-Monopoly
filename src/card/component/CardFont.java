package card.component;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class CardFont {
	
	private static final String CARD_FONT_STYLE = "Resources/font/jf-openhuninn-1.0.ttf"; // 粉圓體
	private static Font fontTemplate;
	private static Font title;
	private static Font normal;
	private static Font button;
	private static Font player;
	
	// 在gameCard的constructor呼叫，給定初始值
	public static void InitCardFont() {
		File fontFile = new File(CARD_FONT_STYLE);
		try {
			fontTemplate = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (Exception e) {
			System.out.println("error for createFont in package card.font, class CardFont, method InitCardFont");
			System.out.println("check \'NotoSansTC-Regular.otf\' relative location");
			System.out.println("can test with \'System.out.println(System.getProperty(\"user.dir\"));\'");
			System.exit(1);
		}
		
		title = fontTemplate.deriveFont(Font.BOLD, 32f);
		normal = fontTemplate.deriveFont(Font.BOLD, 22f);
		button = fontTemplate.deriveFont(Font.BOLD, 16f);
		player = fontTemplate.deriveFont(Font.BOLD, 30f);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(title);
		ge.registerFont(normal);
		ge.registerFont(button);
	}
	
	static Font getTitleFont() {
		return title;
	}
	
	static Font getNormalFont() {
		return normal;
	}
	
	static Font getButtonFont() {
		return button;
	}
	
	static Font getPlayerFont() {
		return player;
	}

}
