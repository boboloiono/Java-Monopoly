package FinalProjectUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Player;

public class PlayerInfoUI extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel right_happiness;
	private JLabel right_money;
	protected Player players;
	
	PlayerInfoUI(int i, String name, int happiness, int money){
		this.setPreferredSize(new Dimension(400, 130));
		this.setLayout(new BorderLayout());
		
		JPanel info_left = new JPanel();	// 左邊面板給 PlayerUI 和 PlayerName
		JPanel info_right = new JPanel();	// 右邊面板給 快樂指數 和 財產總額
		info_left.setBackground(UIColor._F4D58D);	// 背景為淡黃色
		info_right.setBackground(UIColor._F4D58D);	// 背景為淡黃色
		
		info_left.setPreferredSize(new Dimension(180, 120));
		info_right.setPreferredSize(new Dimension(220, 120));

		ImageIcon playerImg = new ImageIcon("Resources/Player/hero"+i+".png");
		JLabel left_playerUI = new JLabel();
		left_playerUI.setPreferredSize(new Dimension(180, 120));
		left_playerUI.setIcon(playerImg);
		left_playerUI.setText(name);
		left_playerUI.setFont(UIFont.createFont("Resources/font/jf-openhuninn-1.0.ttf", Font.BOLD, 20));
		left_playerUI.setForeground(UIColor._001427);	// 字體設為深藍色
		left_playerUI.setIconTextGap(5);
		left_playerUI.setVerticalTextPosition(JLabel.BOTTOM);
		left_playerUI.setHorizontalTextPosition(JLabel.CENTER);
		left_playerUI.setVerticalAlignment(JLabel.CENTER);
		left_playerUI.setHorizontalAlignment(JLabel.CENTER);
		
		info_right.setLayout(null);
		ImageIcon playerHappiness = new ImageIcon("Resources/element/happiness.png");
		ImageIcon playerMoney = new ImageIcon("Resources/element/money.png");
		right_happiness = new JLabel();
		right_money = new JLabel();
		
		right_happiness.setBounds(0, 20, 220, 40);
		right_happiness.setText("快樂指數："+happiness);
		right_happiness.setFont(UIFont.createFont("Resources/font/jf-openhuninn-1.0.ttf", Font.BOLD, 20));
		right_happiness.setForeground(UIColor._001427);	// 字體設為深藍色
		right_happiness.setIconTextGap(10);
		right_happiness.setIcon(playerHappiness);
		right_happiness.setHorizontalTextPosition(JLabel.RIGHT);
		right_happiness.setVerticalAlignment(JLabel.BOTTOM);
		right_happiness.setHorizontalAlignment(JLabel.LEFT);
		
		right_money.setBounds(0, 70, 220, 40);
		right_money.setText("財產總額："+money);
		right_money.setFont(UIFont.createFont("Resources/font/jf-openhuninn-1.0.ttf", Font.BOLD, 20));
		right_money.setForeground(UIColor._001427);	// 字體設為深藍色
		right_money.setIconTextGap(10);
		right_money.setIcon(playerMoney);
		right_money.setHorizontalTextPosition(JLabel.RIGHT);
		right_money.setVerticalAlignment(JLabel.TOP);
		right_money.setHorizontalAlignment(JLabel.LEFT);
		
		this.add(info_left, BorderLayout.WEST);
		this.add(info_right, BorderLayout.EAST);
		info_left.add(left_playerUI);
		info_right.add(right_happiness);
		info_right.add(right_money);
		
		this.setVisible(true);
	}
	public void setHappiness(int happiness) {
		right_happiness.setText("快樂指數："+happiness);
	}
	public void setMoney(int money) {
		right_money.setText("財產總額："+money);
	}
}
