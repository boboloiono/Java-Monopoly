package FinalProjectUI;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

class UIFont{
	
	private static Font font;
	
	static Font createFont(String filePath, int style, float size) {
		File fontFile = new File(filePath);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (Exception e) {
			System.out.println("error for createFont in package FinalProjectUI, class UIFont, method createFont");
			System.out.println("check \'" + filePath + "\' relative location");
			System.out.println("can test with \'System.out.println(System.getProperty(\"user.dir\"));\'");
			System.exit(1);
		}
		
		font = font.deriveFont(style, size);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		
		System.out.println("success");
		
		return font;
	}

}
