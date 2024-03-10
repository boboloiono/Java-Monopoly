package FinalProjectUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LocationUI extends JLabel{
	
	private static final long serialVersionUID = 1L;

	String[] locationName = {"起點", "司法博物館", "奇美博物館","台南美術館", "機會", "全美戲院", "南紡購物中心", "台南車站", 
			"赤崁樓", "孔廟", "林百貨", "安平古堡","育才派出所","命運", "五妃廟豆腐冰", "文章牛肉湯", "莉莉冰果室", "第三代虱目魚丸", "花園夜市", "成大醫院",
			"四草綠色隧道", "關子嶺溫泉", "七股鹽山", "漁光島"};
	String[] locationPath = {"Resources/Loc/start.png", "Resources/Loc/law museum.png", "Resources/Loc/chiMe museum.png","Resources/Loc/art museum.png", 
			"Resources/Loc/chance.png", "Resources/Loc/theatre.png", "Resources/Loc/tainan spinning.png", "Resources/Loc/station.png",
			"Resources/Loc/chikan tower.png", "Resources/Loc/confucian temple.png", "Resources/Loc/hayashi.png", "Resources/Loc/anping castle.png", 
			"Resources/Loc/police station.png", "Resources/Loc/fate.png", "Resources/Loc/temple ice.png", "Resources/Loc/beef soup.png", "Resources/Loc/fruit ice.png", 
			"Resources/Loc/milkfish balls.png", "Resources/Loc/flower night market.png", "Resources/Loc/NCKU hospital.png", 
			"Resources/Loc/green tunnel.png", "Resources/Loc/hot spa.png", "Resources/Loc/salt mountain.png", "Resources/Loc/yuguang island.png"};
	
	// 24個Location的GUI
	public LocationUI(int index){
		this.setText(locationName[index]);
		this.setHorizontalTextPosition(JLabel.CENTER); // 設定文字在圖片的上方(TOP, CENTER, BOTTOM)
		this.setVerticalTextPosition(JLabel.TOP); // 設定文字在圖片的上方(TOP, CENTER, BOTTOM)
		this.setForeground(Color.white); // 設定文字顏色
		this.setFont(UIFont.createFont("Resources/font/jf-openhuninn-1.0.ttf", Font.PLAIN, 12)); // 設定文字字型、大小
		this.setIconTextGap(10); // 設定gap讓text跟icon靠近一點
		this.setBounds(32, -7, 145, 145); // x, y, wide, height
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		// 新增Location的圖片
		ImageIcon image = new ImageIcon(locationPath[index]);
		this.setIcon(image);
	}	
}
