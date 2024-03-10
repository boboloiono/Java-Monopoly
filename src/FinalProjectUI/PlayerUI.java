package FinalProjectUI;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// for Player's Icon UI and present its move 

public class PlayerUI extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public PlayerUI(int playerIndex, JPanel jPanel){
		this.setPreferredSize(new Dimension(64,64));
		this.setLayout(null);
		ImageIcon player_image = new ImageIcon("Resources/Player/hero"+playerIndex+".png");
		this.setIcon(player_image);
		this.setVisible(true);
		
		jPanel.add(this);
	}
	
	// 初始化玩家位置，設定在 Location面板 第一格的不同位置
	public void initializeLoc(int playerIndex) {
		switch(playerIndex) {
			case(1): this.setBounds(1,0,64,64); break;
			case(2): this.setBounds(65,0,64,64); break;
			case(3): this.setBounds(1,65,64,64); break;
			case(4): this.setBounds(65,65,64,64); break;
		}
	}
}
