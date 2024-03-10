package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Game {
    //parameters
    private int happyGoal=100;    //final score threshold
    private int initialMoney=1200;
    private ArrayList<Player> players=new ArrayList<Player>(4);
    private ArrayList<Location> locations=new ArrayList<Location>(24);
    private Player presentPlayer; //record who is acting in this round
    private int presentPlayerID=0;//if 4 player, id=0-3
    private Location presentLoc;//record after moving the player, the place the player get
    private int presentLocID=0;
    private int specialEventNum=0; //for the fate and chance;
    private boolean arrived = false;

    //methods
    //initial the game , including location, initial money, final score threshold, players and all locations
    public Game(String name1,String name2,String name3,String name4){
        //initialize all the locations
        for (int i = 0; i < 24; i++) {
           FoodLoc initial =new FoodLoc("initial",0,"","initial",0,0);
           locations.add(initial);
        }

        //add all the locations
        initializeLoc();

        //via input from player to call addPlayer()
        addPlayer(name1);
        addPlayer(name2);
        addPlayer(name3);
        addPlayer(name4);

        presentPlayer=players.get(0);
        presentLoc=locations.get(0);
    } 

    //locations
    public void initializeLoc(){
        //create food location
        addFoodLoc("第三代虱目魚丸湯", 17, "第三代虱目魚丸是一間擁有將近80年歷史的老店，秉持虱目魚的傳統味道外，並致力於改良及研發虱目魚的相關產品。","綜合虱目魚丸湯：110元", 110,33);
        addFoodLoc("莉莉冰菓店", 16, "一甲子歲月的台南老字號「莉莉水果店」(Lily Fruits Store)，店攤上的豔麗果物彷彿施展化妝品之效，讓應該像個老奶奶的冰果室還是保有一股青春氣質！","芒果香蕉牛奶冰：90元", 90,27);
        addFoodLoc("花園夜市", 18, "純香的鮮奶茶，搭配彈Q有嚼勁的珍珠，恰到好處的台灣風味。","珍珠鮮奶茶：65元", 65,20);
        addFoodLoc("五妃廟豆腐冰", 14, "懷舊小棧杏仁豆腐冰，加上湯圓、黑芝麻醬更好吃！","五妃廟豆腐冰：50元", 50,15);
        addFoodLoc("文章牛肉湯", 15, "只用現宰的優質溫體牛肉下水川燙，約五分熟的牛腹肉油脂豐富、鮮甜嫩口略帶細筋。","招牌牛肉湯：160元", 160,48);	//30
        
        //create normal location
        addNormalLoc("司法博物館", 1, "建於1912年，已有超過百年的歷史，由森山松之助所設計，原為台南地方法院。", 150,30 );
        addNormalLoc("奇美博物館", 2, "西洋古典建築外觀，館內藏納豐富的西洋繪畫雕塑", 500,100);
        addNormalLoc("台南美術二館", 3, "由石昭永建築師及日本坂茂建築設計事務所共同打造，是由普立茲克建築獎得主所規劃設計的建築物", 450, 90);
        addNormalLoc("全美戲院", 5, "臺灣知名電影導演李安提及過他就讀高中時，最常到此觀賞電影。", 250, 50);
        addNormalLoc("南紡購物中心", 6, "購物中心", 300, 60);
        addNormalLoc("赤崁樓", 8, "赤崁樓乃荷蘭人於西元1653年創建，原稱普羅民遮城（Provintia荷文為永恆之意）。", 200, 40);
        addNormalLoc("孔廟", 9, "全臺首學，臺灣的第一座孔子廟臺南孔子廟創建於明永曆19年。", 200, 40);
        addNormalLoc("林百貨", 10, "1932年由日本商人林方一所建立，在當時是南台灣規模最大的百貨公司。", 300, 60);
        addNormalLoc("安平古堡", 11, "荷蘭人於西元1624年，在安平建造了臺灣第一座城堡「熱蘭遮城」，也就是現在的安平古堡。", 250, 50);
        addNormalLoc("漁光島", 23, "島上一半以上是森林，林下步道為許多新人拍攝婚紗的景點，外側沙灘港灣亦吸引許多遊客踏浪。", 350, 70);
        addNormalLoc("四草綠色隧道", 20, "四草濕地是臺灣紅樹林的家鄉，四草大眾廟後方水道，沿途可欣賞台江地區3種原生紅樹林。", 200, 40);
        addNormalLoc("七股鹽山", 22, "七股鹽場曾為是台灣最大的曬鹽場", 100, 20);
        addNormalLoc("關子嶺溫泉", 21, "關子嶺溫泉由地層的石縫中流出，泉質屬鹼性泉。", 100, 20);

        //add special location
        addSpecialLoc("起點", 0, "剛好抵達起點，無法獲得回合獎勵。", 100,6,0);
        addSpecialLoc("台南火車站",7,"(這段敘述和獎勵是舊版的，不應該顯示)傳送至隨機地點，並支付車票50元。", -50,0,0);
        addSpecialLoc("成大醫院",19,"騎腳踏車被紅燈右轉的機車撞擊，住院暫停一回合。", 0,0,12);
        addSpecialLoc("育才派出所",12,"因為遺失現金200元，停留一回合做筆錄。",-200, 0,19);
        addSpecialLoc("機會",4,"機會",0,0,4);
        addSpecialLoc("命運",13,"命運",0,0,18);
    }
    public void addFoodLoc(String name, int locationIndex, String description,String foodName,int foodPrice,int foodHappiness){
        Location foodLoc=new FoodLoc(name,locationIndex,description,foodName,foodPrice,foodHappiness);
        this.locations.set(locationIndex,foodLoc);
    }
    public void addSpecialLoc(String name, int locationIndex, String description,int specialMoney, int specialHappiness, int destinationID ){
        Location specialLoc=new SpecialLoc(name, locationIndex, description, specialMoney, specialHappiness, destinationID);
        this.locations.set(locationIndex,specialLoc);
    }
    public void addNormalLoc(String name, int locationIndex, String description, int soldPrice, int toll){
        Location normalLoc=new NormalLoc(name, locationIndex, description, soldPrice, toll);
        this.locations.set(locationIndex,normalLoc);
    }

    //initialize players
    public void addPlayer(String name){
        ///initialize players
        Player player =new Player(name,initialMoney);
        players.add(player);
    }

    //---------------------------------------------------------------
    //get whatever you want
    public int getHappyGoal() {
        return happyGoal;
    }
    public int getInitialMoney() {
        return initialMoney;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public int getPresentPlayerID() {
        return presentPlayerID;
    }

    public Player getPresentPlayer() {
        return presentPlayer;
    }
    public ArrayList<Location> getLocations() {
        return locations;
    }
    public int getPresentLocID() {
        return presentLocID;
    }
    public Location getPresentLoc() {
        return presentLoc;
    }
    public boolean getArrived() {
    	return arrived;
    }

    //actions of player
    public void throwDiceMove(int randomNum){
        if(randomNum+presentLocID>24){
            changeMoney(100);
            changeHappiness(10);
            move(randomNum+presentLocID-24);
        }else if((randomNum+presentLocID)==24){
            move(0);
        }else {
            move(randomNum+presentLocID);
        }
    }
    public void move(int destinationID){ // go to specific place
        presentPlayer.setMyPastLocID(presentLocID);
        presentPlayer.setMyLocID(destinationID);
        presentLocID=destinationID;
        presentLoc=locations.get(destinationID);
    }
    public void changeMoney(int money){ //can be negative
        presentPlayer.setMoney(presentPlayer.getMoney()+money);
    }
    public void changeHappiness(int happiness){
        //can be negative
        presentPlayer.setHappiness(presentPlayer.getHappiness()+happiness);
    }
    public  void turnNext(){
        arrived = false;
        //let the present player and location change to next player and wait for throwing dice
        if(presentPlayerID==(3)){
            presentPlayerID=0; //after the last player, back to the first player
        }else {
            presentPlayerID=presentPlayerID+1;
        }
        presentPlayer=players.get(presentPlayerID);
        presentLocID=presentPlayer.getMyLocID();
        presentLoc=locations.get(presentLocID);

        //when the player is not allow to move, just turn next
        if(presentPlayer.getStopStatus()){
            presentPlayer.setStopStatus(false);// stop for once once
            turnNext();
        }
    }
    public boolean checkBankStatus(){
        //after all action in this round, check whether the player is bankrupt, if out of money,then return true.
        if(presentPlayer.getMoney()<0){
            //判斷該回合是否破產
            presentPlayer.setBankStatus(true);
        }
        return presentPlayer.getBankStatus();
    }
    public boolean checkWin(){
        return presentPlayer.getHappiness()>=happyGoal;
    }
    public int checkWinBankrupt(){
        //when someone bankrupt, return the winner
        int max=0;
        int maxPlayIndex=0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getHappiness()>=max){
                max= players.get(i).getHappiness();
                maxPlayIndex=i;
            }
        }
        return maxPlayIndex;
    }
    public ArrayList<Player> getRankPlayers(){
        //return sorted players by the happiness rank
        // initiate the new list;
        ArrayList<Player> rankPlayers = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            rankPlayers.add(players.get(i));
        }
        // Sorting the list based on name
        Collections.sort(rankPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p1.getHappiness(), p2.getHappiness());
            }
        });
//        for (int i = 0; i < 4; i++) {
//            System.out.println(rankPlayers.get(3-i).getName() + " - " + rankPlayers.get(3-i).getHappiness()+ " ");
//        }
        ArrayList<Player> inverseRankPlayers =new ArrayList<Player>();
        for (int i = 0; i < 4; i++) {
            inverseRankPlayers.add(rankPlayers.get(3-i));//since the rankPlayers is sorted in low to high
        }
        return inverseRankPlayers;
    }
    
    //用於最後排名時，回傳按照分數大小排列的玩家串
//    public int checkWinBankrupt(){ //因為有了getRankPlayers
//        //when someone bankrupt, return the winner
//        int max=0;
//        int maxPlayIndex=0;
//        for (int i = 0; i < players.size(); i++) {
//            if (players.get(i).getHappiness()>=max){
//                max= players.get(i).getHappiness();
//                maxPlayIndex=i;
//            }
//        }
//        return maxPlayIndex;
//    }
    
    public boolean buyFood(){
        //if buy food successfully return true, if have not enough money return false
        if(presentLoc instanceof FoodLoc){
            if((presentPlayer.getMoney()-((FoodLoc) presentLoc).getFoodPrice())>=0){
                changeMoney(-((FoodLoc) presentLoc).getFoodPrice());//pay for the food
                changeHappiness(((FoodLoc) presentLoc).getFoodHappiness());
                return true;
            }else{ //have no enough money
                return false;
            }
        }else{
            return false; //this should not happen
        }
    }

    public String specialEvent(){
        //execute all the special event, including gain money and happiness
        // and change location.return the description of the special event for the info card can show
        //for fate and chances

        if(presentLoc instanceof SpecialLoc){
            Random random=new Random(); //there are 14 kinds of special event
            switch (presentLocID){
                case 4, 13:
                    //when get into chance or fate
                    specialEventNum=(random.nextInt(12) + 1);
                    switch (specialEventNum){
                        case 1:
                            changeMoney(100);
                            return "撿到中獎100元發票。資產＋100元";
                        case 2:
                            changeHappiness(15);
                            return "做善心買刮刮樂，花費200元，中獎200元。快樂分數+15。";
                        case 3:
                            changeMoney(300);
                            changeHappiness(20);
                            return "幫暗戀對象填問卷，獲得300元電影票禮券，藉機約學姊看電影。快樂分數+20：資產+300元";
                        case 4:
                            changeHappiness(-5);
                            return "腳踏車坐墊滴到鳥屎。快樂分數-5";
                        case 5:
                            changeMoney(-200);
                            return "學生證遺失重辦，工本費200元。資產-200。";
                        case 6:
                            changeHappiness(10);
                            return "扶老奶奶過馬路。快樂分數+10";
                        case 7:
                            changeMoney(-300);
                            return "騎機車未禮讓行人，開罰300元。資產-300";
                        case 8:
                            changeHappiness(-5);
                            return "漁光島踏浪鞋子、襪子進沙。快樂分數-5";
                        case 9:
                            changeHappiness(-15);
                            return "在安靜的美術館，不小心放響屁，尷尬分數破表。快樂分數-15";
                        case 10:
                            changeHappiness(-10);
                            return "腳踏車被陌生人「有借無還」。快樂分數-10";
                        case 11:
                            move(19);//移動到醫院
                        	arrived = true;
                            presentPlayer.setStopStatus(true);
                            return "騎腳踏車出車禍，移往醫院休養一回合。";
                        case 12:
                            move(7);
                        	arrived = true;
                            return "獲得火車票一張，傳送往台南火車站。";
                    }
                case 12,19:
                    //change stop status
                    changeMoney(((SpecialLoc) presentLoc).getSpecialMoney());
                    changeHappiness(((SpecialLoc) presentLoc).getSpecialHappiness());
                    presentPlayer.setStopStatus(true);
                    return presentLoc.getDescription();
                case 7: //train station
                	arrived = true;
                    changeMoney(((SpecialLoc) presentLoc).getSpecialMoney());
                    Random rd =new Random();
                    int randNum=rd.nextInt(24);
                    if(randNum==7){ //arrive at station
                        move(randNum+1);
                    }else {
                        move(randNum);
                    }
                    return "從火車站隨機傳送至"+getPresentLoc().getName()+"，並支付車票50元";
//                    below is the old version
//                    move(((Game.SpecialLoc) presentLoc).getDestinationID());//random move
//                    return presentLoc.getDescription();
                case 0:
                    return "剛好抵達起點，無法獲得回合獎勵。";
//                    below is the old version
//                    changeMoney(((Game.SpecialLoc) presentLoc).getSpecialMoney());
//                    changeHappiness(((Game.SpecialLoc) presentLoc).getSpecialHappiness());
//                    return presentLoc.getDescription();

            }
        }
        return "wrong";//this should not happen
    }
    public int getSpecialEventNum(){
        return specialEventNum-1;
    }
    public boolean buyLocation(){
        if(presentLoc instanceof NormalLoc){
            //true implies buy successfully, false implies not have enough money
            if (presentPlayer.getMoney()>=((NormalLoc) presentLoc).getSoldPrice()){
                changeMoney(-((NormalLoc) presentLoc).getSoldPrice());
                presentPlayer.addOwnLocList(presentLoc);
                ((NormalLoc) presentLoc).setOwner(presentPlayer);
                return true;
            }else {
                return false;
            }
        }else{
            //this should not happen
            return false;
        }
    }
    public boolean normalEvent(){
        if(presentLoc instanceof NormalLoc){
            if (((NormalLoc) presentLoc).checkSoldStatus()){
                //true implies ask player whether want to buyLoc, false implies pay Tool
                return true;
            }else {
                //presentPlayer pay for toll
                changeMoney(-((NormalLoc) presentLoc).getToll());
                ((NormalLoc) presentLoc).getOwner().setMoney(((NormalLoc) presentLoc).getOwner().getMoney()+((NormalLoc) presentLoc).getToll());
                return false;
            }
        }else {
            return false; //this should not happen
        }


    }
    public boolean checkNormalOwner(){
        if(presentLoc instanceof NormalLoc){
            return presentPlayer==((NormalLoc) presentLoc).getOwner();
        }else{
            return false; //this should not happen
        }
    }

}
