package FinalProjectUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import card.GameCard;

import Game.Game;
import Game.Player;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final String ENGLISH_FONT_STYLE = "Resources/font/Banco.ttf";
	public final static int FRAME_WIDTH = 1085;
	public final static int FRAME_HEIGHT = 850;
	public final static int LOC_WIDTH = 140;
	public final static int LOC_HEIGHT = 140;
	public final static int GAP = 10;
	private List<PlayerInfoUI> playerInfo =  new ArrayList<>();
	private List<PlayerUI> playerUI = new ArrayList<>();
	private List<JPanel> panel_loc;
	private JLabel dice;
	private JButton rollButton;
	private int diceNumber;
	private Game game;
	private GameCard playerInfoCard;
	
	public MainFrame() {
		List<Player> players = new ArrayList<>();
		List<LocationUI> locDiagrams = new ArrayList<LocationUI>();	
		ImageIcon titleImage = new ImageIcon("Frame_logo.png");
		this.setIconImage(titleImage.getImage());
		this.setTitle("NCKU FOOD MONOPOLY");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		

		/********************************************************
		 *                          							*	
		 *                     Setting Panels!                	*
		 *                          							*
		 ********************************************************/
		
		// ----------------- Border Panels -----------------
		
		JPanel panel_top = new JPanel((new FlowLayout(FlowLayout.LEFT, 5, 10)));	// //向左切齊，水平間隙 5 pixels, 垂直間隙 10 pixels
		JPanel panel_right = new JPanel((new FlowLayout(FlowLayout.LEFT, 5, 5)));
		JPanel panel_bottom = new JPanel((new FlowLayout(FlowLayout.LEFT, 5, 5)));
		JPanel panel_left = new JPanel((new FlowLayout(FlowLayout.RIGHT, 5, 5)));
		JPanel panel_mid = new JPanel((new FlowLayout()));
		
		panel_top.setPreferredSize(new Dimension(FRAME_WIDTH, LOC_HEIGHT));								// 1085 X 140
		panel_right.setPreferredSize(new Dimension(LOC_WIDTH, FRAME_HEIGHT-LOC_HEIGHT*2));				// 140 X 570 
		panel_bottom.setPreferredSize(new Dimension(FRAME_WIDTH, LOC_HEIGHT));							// 1090 X 140
		panel_left.setPreferredSize(new Dimension(LOC_WIDTH, FRAME_HEIGHT-LOC_HEIGHT*2));				// 140 X 570
		panel_mid.setPreferredSize(new Dimension(FRAME_WIDTH-LOC_WIDTH*2, FRAME_HEIGHT-LOC_HEIGHT*2));	// 805 X 570
		
		panel_top.setBackground(UIColor._708D81);	// 每個上,下,左,右,中部的大區塊都設定為綠色，最後綠色會以邊界形式呈現
		panel_right.setBackground(UIColor._708D81);
		panel_bottom.setBackground(UIColor._708D81);
		panel_left.setBackground(UIColor._708D81);
		panel_mid.setBackground(UIColor._708D81);
		
		// ----------------- Location Panels -----------------
		
		panel_loc = new ArrayList<JPanel>();
		
		for(int i=0; i<24; i++) {
			panel_loc.add(new JPanel());
			panel_loc.get(i).setLayout(null);
			if(i==0) panel_loc.get(i).setBackground(UIColor._104911);							// 起點：草綠色
			else if(i==4 || i==13) panel_loc.get(i).setBackground(UIColor._023047);				// 機會命運：寶藍色
			else panel_loc.get(i).setBackground(UIColor._001427);								// 其他Location：深藍色
			panel_loc.get(i).setPreferredSize(new Dimension(LOC_WIDTH-GAP, LOC_HEIGHT-GAP));	// 每個Location的長寬都是 130 X 130
		}
		
		// ------------------ Player Panels ------------------
		
		JPanel panel_mid_north = new JPanel(new BorderLayout());										// 中部區塊 - 上半部：玩家資訊Panel
		panel_mid_north.setPreferredSize(new Dimension(FRAME_WIDTH-LOC_WIDTH*2, LOC_HEIGHT-GAP));		// 大小 805 X 130
		panel_mid_north.setBackground(UIColor._708D81);
		JPanel panel_mid_south = new JPanel(new BorderLayout());										// 中部區塊 - 下半部：玩家資訊Panel
		panel_mid_south.setPreferredSize(new Dimension(FRAME_WIDTH-LOC_WIDTH*2, LOC_HEIGHT-GAP));		// 大小 805 X 130
		panel_mid_south.setBackground(UIColor._708D81);

		// ------------------- Info Panels -------------------

		JPanel panel_mid_center = new JPanel();		// 中部區塊－中間：左邊切給 Dice，右邊切給 Card
		panel_mid_center.setPreferredSize(new Dimension(FRAME_WIDTH-LOC_WIDTH*2, FRAME_HEIGHT-LOC_HEIGHT*4-GAP*3+GAP/2));	//大小 805 X 260
		panel_mid_center.setLayout(new BorderLayout());
		panel_mid_center.setBackground(UIColor._708D81);		// 設定中部區塊為綠色
		
		JPanel panel_dice = new JPanel();
		panel_dice.setPreferredSize(new Dimension(260, 260));	// 放在中部區塊的左，大小 260 X 260
		panel_dice.setBackground(UIColor._7F4F24);				// 設定 Dice 背景顏色是黃色
		panel_dice.setLayout(null);
		
		JPanel panel_card = new JPanel();						
		panel_card.setPreferredSize(new Dimension(540, 260));	// 放在中部區塊的右邊，大小 540 X 260
		panel_card.setLayout(new BorderLayout());

		// ----------------- initialize player -----------------
		
		players.add(new Player(PlayerSetFrame.playerName[0], 0));	// 在PlayerSetFrame輸入四個玩家的名字之後，就可以建立四個玩家物件
		players.add(new Player(PlayerSetFrame.playerName[1], 0));
		players.add(new Player(PlayerSetFrame.playerName[2], 0));
		players.add(new Player(PlayerSetFrame.playerName[3], 0));
		
		try {	// 此時要等待一秒，才能完全新增好玩家物件，否則會因為讀不到玩家名字而出錯
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			
		}
		// 得到家的名字之後，就可以建立Game物件
		game =  new Game(PlayerSetFrame.playerName[0],PlayerSetFrame.playerName[1],PlayerSetFrame.playerName[2],PlayerSetFrame.playerName[3]);
		playerInfoCard = new GameCard(game);				// 把後端的game物件傳送到Card，卡片大小: 540 X 260
		panel_card.add(playerInfoCard.getDisplayPane());	// 將 Card 加入 panel_card 的版面
		playerInfoCard.setPlayerLabel();
		
		// ----------------- set player Info -----------------
		
		// 把每位玩家的姓名、快樂指數、財產總額的版面 加到 playerInfo 的主畫面版面
		playerInfo.add(new PlayerInfoUI(1, game.getPlayers().get(0).getName(), game.getPlayers().get(0).getHappiness(), game.getPlayers().get(0).getMoney()));
		playerInfo.add(new PlayerInfoUI(2, game.getPlayers().get(1).getName(), game.getPlayers().get(1).getHappiness(), game.getPlayers().get(1).getMoney()));
		playerInfo.add(new PlayerInfoUI(3, game.getPlayers().get(2).getName(), game.getPlayers().get(2).getHappiness(), game.getPlayers().get(2).getMoney()));
		playerInfo.add(new PlayerInfoUI(4, game.getPlayers().get(3).getName(), game.getPlayers().get(3).getHappiness(), game.getPlayers().get(3).getMoney()));
		
		
		// ---------------- initial position ----------------
		
		playerUI.add(new PlayerUI(1, panel_loc.get(0)));
		playerUI.add(new PlayerUI(2, panel_loc.get(0)));
		playerUI.add(new PlayerUI(3, panel_loc.get(0)));
		playerUI.add(new PlayerUI(4, panel_loc.get(0)));
		
		playerUI.get(0).initializeLoc(1);
		playerUI.get(1).initializeLoc(2);
		playerUI.get(2).initializeLoc(3);
		playerUI.get(3).initializeLoc(4);
		
		// ------------ add Diagram in each Location -------------
		
		for(int i=0; i<24; i++) {
			locDiagrams.add(new LocationUI(i));	// 實作 24 個 locDiagram
			locDiagrams.get(i).setLayout(new FlowLayout(FlowLayout.CENTER)); 
			panel_loc.get(i).add(locDiagrams.get(i));	// 各別加到 24個 panel_loc 的版面
		}
		
		// -------------- add border Panels to Frame --------------
		
		this.add(panel_top, BorderLayout.NORTH);
		this.add(panel_mid, BorderLayout.CENTER);
		this.add(panel_right, BorderLayout.EAST);
		this.add(panel_bottom, BorderLayout.SOUTH);
		this.add(panel_left, BorderLayout.WEST);
		
		// --------- add Location Panels to Border Panels ----------
		// 大富翁的 Location 要從左上、順時針圍成一個圈
		for(int i=0; i<8; i++) { panel_top.add(panel_loc.get(i));}		// 從左上到右上
		for(int i=8; i<12; i++) { panel_right.add(panel_loc.get(i));}	// 從右上到右下
		for(int i=19; i>11; i--) { panel_bottom.add(panel_loc.get(i));}	// 從右下到左下
		for(int i=23; i>19; i--) { panel_left.add(panel_loc.get(i));}	// 從左下到左上	
		
		// ---------- add Info Panes to Center Panels ----------
		
		panel_mid.add(panel_mid_north);
		panel_mid.add(panel_mid_center);
		panel_mid.add(panel_mid_south);
		panel_mid_north.add(playerInfo.get(0), BorderLayout.WEST);
		panel_mid_north.add(playerInfo.get(1), BorderLayout.EAST);
		panel_mid_south.add(playerInfo.get(2), BorderLayout.WEST);
		panel_mid_south.add(playerInfo.get(3), BorderLayout.EAST);
		
		// ----------- add Dice and Card Panels to Location Panels -----------
		panel_mid_center.add(panel_dice, BorderLayout.WEST);
		panel_mid_center.add(panel_card, BorderLayout.EAST);
		
		// ----------------------- add Dice -------------------------
		
		// 1. Banner
		JLabel bannerImage = DiceImageService.loadImage("Resources/Dice/banner.png");
		bannerImage.setBounds(10, -20, 260, 160);
		panel_dice.add(bannerImage);
		
		// 2. Dice
		dice = DiceImageService.loadImage("Resources/Dice/Dice1.png");
		dice.setBounds(23, 25, 220, 220);
		panel_dice.add(dice);
		
		// 3. Roll Button
		rollButton = new UIButton("Roll", UIColor._F4D58D, UIColor._E9C46A); // 原本為淡黃色，鼠標移進時變為深黃色
		rollButton.setBounds(75, 190, 118, 45);
		rollButton.setFont(UIFont.createFont(ENGLISH_FONT_STYLE, Font.ITALIC, 37));
		rollButton.setHorizontalAlignment(JButton.CENTER);
		rollButton.setVerticalAlignment(JButton.CENTER);
		rollButton.setRolloverEnabled(true);
		rollButton.addActionListener(e -> System.out.println("Roll"));
		panel_dice.add(rollButton);
		
		/********************************************************
		 *                          							*	
		 *                       Game Start!                	*
		 *                          							*
		 ********************************************************/
		
		// 使用隨機數生成器生成一个骰子點数
		Random rand = new Random();
		rollButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// roll for 3 seconds
                rollButton.setEnabled(false);
	            long startTime = System.currentTimeMillis();
	            Thread rollThread = new Thread(new Runnable() {
					@Override
					 public void run() {
	                    long endTime = System.currentTimeMillis();
	                    try{
	                    	MusicPlayer.playSFX("Resources/Music/diceRollingSound.wav");	// 開啟擲骰子的音樂
							while((endTime - startTime)/1000F < 1){	// 躑的時間設定為一秒
								// rolling dice !
								diceNumber = rand.nextInt(1, 7);
								DiceImageService.updateImage(dice, "Resources/Dice/Dice"+ diceNumber +".png");	// 更新骰子圖像
	                            repaint();		// 重繪Dice面板
	                            revalidate();	// 重新驗證介面
	                            Thread.sleep(60);	// 方法在每次循環之間添加一定的延遲，以控制動畫的速度
	                            endTime = System.currentTimeMillis();
	                        }
	                        MusicPlayer.closeSFX();	// 躑完結束後，關閉擲骰子的音樂
	                    }catch(InterruptedException e){
	                        System.out.println("Threading Error: " + e);
	                    }
	                    System.out.println("dice:"+diceNumber);
	            		game.throwDiceMove(diceNumber);	// 後端 game 讀入躑到的骰子數值
	            		playerMove();	// 躑玩骰子後，進入PlayerUI的動作
					}
	            });
	            rollThread.start();
			}
		});
		this.setVisible(true);
	}
	
	private void playerMove() {
        for(int i = 0; i < diceNumber; i ++) {
        	MusicPlayer.playSFX("Resources/Music/moveSound.wav");	// 在移動時，加入移動音樂
    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i)%24).remove(playerUI.get(game.getPresentPlayerID()));	// 將 PlayerUI 從原本的位置移除
    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i+1)%24).add(playerUI.get(game.getPresentPlayerID()));	// 將 PlayerUI 新增到新位置的panel
    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i+1)%24).setComponentZOrder(playerUI.get(game.getPresentPlayerID()), 0); // 設定 PlayerUI 在 Location 的最上層
    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i)%24).repaint();	// 將 Player 原本所屬的位置重繪
    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i+1)%24).repaint(); // 將 Player 更新後的位置重繪
    		try {	// 需暫停一下，等待新舊位置位置重繪完成
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		if(i == diceNumber-1) {		// 最後一步的前一步就先關閉移動音樂（前一步就關掉的目的是避免玩家停止移動，移動音樂卻還沒結束的情況
    			MusicPlayer.clip.stop();
    			MusicPlayer.clip.close();
    			try {
					MusicPlayer.audioInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
        }
		// 移動到目標地點後，顯示目標地點對應的資訊卡牌（例：購買土地、付過路費、火車站...）
		playerInfoCard.setInfoCard();
		
		// 如果到達火車站需前往下個目的地，或要抽到需前往醫院的卡，就再次移動 PayerUI
		if(game.getArrived()){
			int distance = 0;
			// 計算需要走的距離
			// 如果新位置<舊位置，代表有經過起點，故距離計算需用 24減去舊位置再加上新位置；反知則直接就位置減新位置即可得欲移動的距離。
			if (game.getPresentPlayer().getMyLocID() < game.getPresentPlayer().getMyPastLocID()) {
				distance = (24 - game.getPresentPlayer().getMyPastLocID()) + game.getPresentPlayer().getMyLocID();
			}else{
				distance = game.getPresentPlayer().getMyLocID() - game.getPresentPlayer().getMyPastLocID();
			}
			
			for(int i = 0; i < distance; i ++) {
				MusicPlayer.playSFX("Resources/Music/moveSound.wav");	// 撥放移動音樂
				panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i)%24).remove(playerUI.get(game.getPresentPlayerID()));
	    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i+1)%24).add(playerUI.get(game.getPresentPlayerID()));
	    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i+1)%24).setComponentZOrder(playerUI.get(game.getPresentPlayerID()), 0);
	    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i)%24).repaint();
	    		panel_loc.get((game.getPresentPlayer().getMyPastLocID()+i+1)%24).repaint();
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		if(i == distance-1) {		// 最後一步的前一步就先關閉移動音樂
	    			MusicPlayer.clip.stop();
	    			MusicPlayer.clip.close();
	    		}
			}
		}
		System.out.println("id:"+game.getPresentPlayerID()+" Past:"+game.getPresentPlayer().getMyPastLocID() +" "+ "Present:"+game.getPresentPlayer().getMyLocID());

		// 印出更新後的Location UI, 以及現在玩家ID,快樂指數和財產總額
		System.out.println("UI: " + game.getPresentLoc().getName() + " ID:" + game.getPresentPlayerID() + " M:" + game.getPresentPlayer().getMoney() + " H:" + game.getPresentPlayer().getHappiness());
		// 重設四個玩家面板上的快樂指數和金錢總額
		for(int i = 0; i<4; i++) {
			playerInfo.get(i).setHappiness(game.getPlayers().get(i).getHappiness());
			playerInfo.get(i).setMoney(game.getPlayers().get(i).getMoney());
			playerInfo.get(i).repaint();
		}
		// 在卡片區顯示結果，包含判斷財產是否破產 或快樂指數是否爆表
		playerInfoCard.setResultCard();
		// 後端進入下一局
        game.turnNext();
		// 重設面板資訊：「輪到ＸＸＸ的回合」
        playerInfoCard.setPlayerLabel();
		// 打開 rollButton
		rollButton.setEnabled(true);
	}
}
