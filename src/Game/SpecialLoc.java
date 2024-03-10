package Game;

public class SpecialLoc extends Location{
    private int specialMoney;
    private int specialHappiness;
    private int destinationID;



    public SpecialLoc(String name, int locationIndex, String description,int specialMoney, int specialHappiness, int destinationID ) {
        super(name, locationIndex,description);
        this.specialMoney=specialMoney;
        this.specialHappiness=specialHappiness;
        this.destinationID=destinationID;
    }

    public int getSpecialHappiness() {
        return specialHappiness;
    }

    public int getSpecialMoney() {
        return specialMoney;
    }

    public int getDestinationID() {
        return destinationID;
    }


}
