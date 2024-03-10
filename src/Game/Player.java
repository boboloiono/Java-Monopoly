package Game;

import java.util.ArrayList;

public class Player {
    private String name;
    private int happiness=0;
    private int money;
    private int myLocID=0;//the initial place is 0, record the present location of the player
    private int myPastLocID=0; //to record the previous location of the player

    private boolean stopStatus=false; //when true, the player can't move
    private ArrayList<Location> ownLocList=new ArrayList<Location>(); //store the locations bought by player
    private boolean bankStatus=false;//check if the player is bankrupt, true implies bankrupt, and should be stop
    public Player(String name, int money){
        this.name=name;
        this.money=money;
    }
    public String getName() {
        return name;
    }

    public int getHappiness() {
        return happiness;
    }
    public int getMoney(){return money;}
    public int getMyLocID(){return myLocID;}
    public int getMyPastLocID(){return myPastLocID;}

    public boolean getBankStatus() {
        return bankStatus;
    }
    public boolean getStopStatus() {
        return stopStatus;
    }
    public void setBankStatus(boolean bankStatus) {
        this.bankStatus = bankStatus;
    }
    public void setStopStatus(boolean stopStatus) {
        this.stopStatus = stopStatus;
    }
    public void addOwnLocList(Location newLoc) {
        this.ownLocList.add(newLoc);
    }
    public ArrayList<Location> getOwnLocList() {
        return ownLocList;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public void setMyLocID(int locID) {
        this.myLocID = locID;
    }

    public void setMyPastLocID(int myPastLocID) {
        this.myPastLocID = myPastLocID;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
}
