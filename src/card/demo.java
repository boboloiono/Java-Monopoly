package card;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import FinalProjectUI.UIColor;
import Game.FoodLoc;
import Game.Game;
import Game.Location;
import Game.NormalLoc;
import Game.SpecialLoc;
import card.component.CardFont;

public class demo extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 800, 600);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//frame.getContentPane().setBackground(UIColor._FAEDCD);
		
		Game game = new Game(null, null, null, null);
		
		// font
		CardFont.InitCardFont();
		GameCard gc = new GameCard(game);
		frame.add(gc.getDisplayPane());
		
		// food
		/*Location[] fls = new Location[5];		
		Card[] fcs = new Card[5];
		createFood(fls, fcs);
		frame.add(fcs[0]);*/
		
		// normal loc
		/*Location[] nls = new Location[13];
		Card[][] ncs = new Card[13][2];
		createNormal(nls, ncs);
		frame.add(ncs[12][1]);
		((TollCard)ncs[12][1]).setMessage("哇哈哈");*/
		
		// special loc
		/*Location[] sls = new Location[6];
		Card[] scs = new Card[6];
		createSpecial(sls, scs);
		frame.add(scs[4]);
		((SlotCard)scs[4]).setSlotCard(7, "漁光島踏浪鞋子、襪子進沙。快樂分數-5");*/
		
		// resultCard
		/*Card resultCard = new TxtCard();*/
		//frame.add(resultCard);
		//resultCard.setMessage("破產");
		
		// test gameCard
		
		//System.out.println(System.getProperty("user.dir"));
		
		frame.setVisible(true);
		
		//gc.setRankingCard();
	}
	
	private static void  createFood(Location[] fls, Card[] fcs) {
		fls[0] = new FoodLoc("五妃廟豆腐冰", 14, "懷舊小棧杏仁豆腐冰，加上湯圓、黑芝麻醬更好吃！","五妃廟豆腐冰：50元", 50,15);
		fls[1] = new FoodLoc("文章牛肉湯", 15, "只用現宰的優質溫體牛肉下水川燙，約五分熟的牛腹肉油脂豐富、鮮甜嫩口略帶細筋。","招牌牛肉湯：160元", 160,48);
		fls[2] = new FoodLoc("莉莉冰菓店", 16, "一甲子歲月的台南老字號「莉莉水果店」(Lily Fruits Store)，店攤上的豔麗果物彷彿施展化妝品之效，讓應該像個老奶奶的冰果室還是保有一股青春氣質！","芒果香蕉牛奶冰：90元", 90,27);
		fls[3] = new FoodLoc("第三代虱目魚丸湯", 17, "第三代虱目魚丸是一間擁有將近80年歷史的老店，秉持虱目魚的傳統味道外，並致力於改良及研發虱目魚的相關產品。","綜合虱目魚丸湯：110元", 110,33);
		fls[4] = new FoodLoc("花園夜市", 18, "純香的鮮奶茶，搭配彈Q有嚼勁的珍珠，恰到好處的台灣風味。","珍珠鮮奶茶：65元", 65,20);
		
		for (int i = 0; i < 5; i++) {
			fcs[i] = new FoodCard((FoodLoc)fls[i]);
		}
	}
	
	
	private static void createNormal(Location[] nls, Card[][] ncs) {
		nls[0] = new NormalLoc("司法博物館", 1, "建於1912年，已有超過百年的歷史，由森山松之助所設計，原為台南地方法院。", 150,30 );
		nls[1] = new NormalLoc("奇美博物館", 2, "西洋古典建築外觀，館內藏納豐富的西洋繪畫雕塑", 500,100);
		nls[2] = new NormalLoc("台南美術二館", 3, "由石昭永建築師及日本坂茂建築設計事務所共同打造，是由普立茲克建築獎得主所規劃設計的建築物", 450, 90);
		nls[3] = new NormalLoc("全美戲院", 5, "臺灣知名電影導演李安提及過他就讀高中時，最常到此觀賞電影。", 250, 50);
		nls[4] = new NormalLoc("南紡購物中心", 6, "購物中心", 300, 60);
		nls[5] = new NormalLoc("赤崁樓", 8, "赤崁樓乃荷蘭人於西元1653年創建，原稱普羅民遮城（Provintia荷文為永恆之意）。", 200, 40);
		nls[6] = new NormalLoc("孔廟", 9, "全臺首學，臺灣的第一座孔子廟臺南孔子廟創建於明永曆19年。", 200, 40);
		nls[7] = new NormalLoc("林百貨", 10, "1932年由日本商人林方一所建立，在當時是南台灣規模最大的百貨公司。", 300, 60);
		nls[8] = new NormalLoc("安平古堡", 11, "荷蘭人於西元1624年，在安平建造了臺灣第一座城堡「熱蘭遮城」，也就是現在的安平古堡。", 250, 50);
		nls[9] = new NormalLoc("四草綠色隧道", 20, "四草濕地是臺灣紅樹林的家鄉，四草大眾廟後方水道，沿途可欣賞台江地區3種原生紅樹林。", 200, 40);
		nls[10] = new NormalLoc("關子嶺溫泉", 21, "關子嶺溫泉由地層的石縫中流出，泉質屬鹼性泉。", 100, 20);
		nls[11] = new NormalLoc("七股鹽山", 22, "七股鹽場曾為是台灣最大的曬鹽場", 100, 20);
		nls[12] = new NormalLoc("漁光島", 23, "島上一半以上是森林，林下步道為許多新人拍攝婚紗的景點，外側沙灘港灣亦吸引許多遊客踏浪。", 350, 70);
		
		for (int i = 0; i < 13; i++) {
			ncs[i][0] = new BuyCard((NormalLoc)nls[i]);
			ncs[i][1] = new TollCard((NormalLoc)nls[i], (BuyCard)ncs[i][0]);
		}
	}
	
	private static void createSpecial(Location[] sls, Card[] scs) {
		sls[0] = new SpecialLoc("起點", 0, "剛好抵達起點，無法獲得回合獎勵。", 100,6,0);
		sls[1] = new SpecialLoc("台南火車站",7,"(這段敘述和獎勵是舊版的，不應該顯示)傳送至隨機地點，並支付車票50元。", -50,0,0);
		sls[2] = new SpecialLoc("成大醫院",19,"騎腳踏車被紅燈右轉的機車撞擊，住院暫停一回合。", 0,0,12);
		sls[3] = new SpecialLoc("育才派出所",12,"因為遺失現金200元，停留一回合做筆錄。",-200, 0,19);
		sls[4] = new SpecialLoc("機會",4,"機會",0,0,4);
		sls[5] = new SpecialLoc("命運",13,"命運",0,0,18);
		
		for (int i = 0; i < 4; i++) {
			scs[i] = new TxtCard((SpecialLoc)sls[i]);
		}
		for (int i = 4; i < 6; i++) {
			scs[i] = new SlotCard((SpecialLoc)sls[i]);
		}
	}

}
