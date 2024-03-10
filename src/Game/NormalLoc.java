package Game;

public class NormalLoc extends Location{
    private int soldPrice;
    private int toll;
    private Player owner;//not be included in constructor, hence it may be null


    public NormalLoc(String name, int locationIndex, String description, int soldPrice, int toll) {
        super(name, locationIndex, description);
        this.soldPrice=soldPrice;
        this.toll=toll;
    }

    public int getSoldPrice(){
        return soldPrice;
    }

    public int getToll() {
        return toll;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player buyer){
        this.owner=buyer;
    }

    public boolean checkSoldStatus(){ //true implies that has not been sold
        return this.owner == null;
    }//true implies there is no owner
}
