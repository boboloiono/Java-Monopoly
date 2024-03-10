package FinalProjectUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

public class DiceImageService {
	
	// 在Dice的面板新增Dice圖片
	public static JLabel loadImage(String filePath) {
		BufferedImage image;
		JLabel imageContainer;
		try {
			InputStream inputStream = new FileInputStream(filePath);
			image = ImageIO.read(inputStream);
			imageContainer = new JLabel(new ImageIcon(image));
			return imageContainer;
		} catch (Exception e) {
			System.out.println("Error: "+ e);
			return null;
		}
	}
	// 更新Dice圖片
	public static void updateImage(JLabel imageContainer, String filePath) {
		BufferedImage image;
		try {
			InputStream inputStream = new FileInputStream(filePath);
			image = ImageIO.read(inputStream);
			imageContainer.setIcon(new ImageIcon(image));
		} catch (Exception e) {
			System.out.println("Error: "+ e);
		}
	}
}
