package FinalProjectUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class EnterFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public static final String JF_FONT_STYLE = "Resources/font/jf-openhuninn-1.0.ttf";	// 粉圓體
	public static final String BANCO_FONT_STYLE = "Resources/font/Banco.ttf";					// 活力粗斜體
	private JProgressBar loadingBar;
	private JLabel titleLabel;
	private JButton startButton;
	
	// GameCard關閉背景音樂
	public static Clip clip;
	public static AudioInputStream ais;
	
	public EnterFrame() {
		// 設置窗口大小和標題
		this.setSize(MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
		this.setTitle("NCKU MONOPOLY");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		// 創建面板並設置背景圖片
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("Resources/element/Monopoly.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(null); // 使用自定義佈局
        
        // 加入開始遊戲的背景音效 
        MusicPlayer.playBackgroundMusic("Resources/Music/startMusic.wav");
        MusicPlayer.controlVolume(0.8);	// 音量調半
        
        // 創建對話框標籤並設置樣式
        titleLabel = new JLabel("這是一款只有臺南人才懂的大富翁...");
        titleLabel.setOpaque(true);
        titleLabel.setFont(UIFont.createFont(JF_FONT_STYLE, Font.BOLD, 60));
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(new Color(0x00FF00));
		titleLabel.setBounds(0, MainFrame.FRAME_HEIGHT/2-100, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT/11);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titleLabel);
        
        // 設置打字機效果的文本動畫效果
		animateTitle(titleLabel);
		
		// 創建"Start Game"按鈕並設置樣式
		startButton = new UIButton("Start Game!", UIColor._BF0603, UIColor._8D0801);
        startButton.setForeground(Color.BLACK);
        startButton.setFont(UIFont.createFont(BANCO_FONT_STYLE, Font.BOLD, 75));
        startButton.setBounds(MainFrame.FRAME_WIDTH/3, MainFrame.FRAME_HEIGHT/2+100, MainFrame.FRAME_WIDTH/3+60, MainFrame.FRAME_HEIGHT/5);
        startButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                // 啟動加載線程
                startLoading();
                startButton.setEnabled(false);
                MusicPlayer.clip.stop();
                MusicPlayer.clip.close();
            }
        });
        panel.add(startButton);
        
        // 創建加載狀態條
        loadingBar = new JProgressBar();
        loadingBar.setString("Loading");
        loadingBar.setForeground(Color.WHITE);
        loadingBar.setFont(UIFont.createFont(BANCO_FONT_STYLE, Font.BOLD, 40));
        loadingBar.setStringPainted(true);
        loadingBar.setBounds(150, 460, 820, 40);
        loadingBar.setVisible(false);
        panel.add(loadingBar);
        
        // 將面板添加到當前窗口
        add(panel);
        
    }
	
	private void animateTitle(JLabel titleLabel) {
        String text = "這是一款只有臺南人才懂的大富翁......";
        final int delay = 150; // 打字機效果的延遲時間，單位為毫秒
        
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < text.length(); i++) {
                    publish(text.substring(0, i + 1));
                    Thread.sleep(delay);
                }
                // 啟動startButton閃爍
                startButtonBlink();
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                String animatedText = chunks.get(chunks.size() - 1);
                titleLabel.setText(animatedText);
            }
        };
        
        worker.execute();
    }
	
	private void startButtonBlink() {
        Timer timer = new Timer(500, new ActionListener() {
            private boolean isVisible = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                startButton.setVisible(isVisible);
            }
        });
        timer.start();
        
        // 停止閃爍
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                startButton.setVisible(true);
            }
        });
    }

	private void startLoading() {
        // 顯示加載狀態條
        loadingBar.setVisible(true);
        
        // 模擬加載過程
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int progress = 0; progress <= 100; progress++) {
                    Thread.sleep(20);
                    publish(progress);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int progress = chunks.get(chunks.size() - 1);
                loadingBar.setValue(progress);
            }

            @Override
            protected void done() {
                // 進入 PlayerSettingGui
            	new PlayerSetFrame(1).setVisible(true);
                MusicPlayer.controlVolume(0.5);
                MusicPlayer.playBackgroundMusic("Resources/Music/playingMusic.wav"); 
                clip = MusicPlayer.clip;
                ais = MusicPlayer.audioInputStream;
                dispose(); // 關閉當前窗口
            }
        };
        
        worker.execute();
	}
}
