package Game;

public abstract class Location {
    private String name;
    private int locationIndex; //there are 24 places, hence the index is 0~23
    private String description; //description about the place

    public Location(String name, int locationIndex,String description){
        this.name=name;
        this.locationIndex=locationIndex;
        this.description=description;
    }

    public String getName(){
        return name;
    }
    public int getLocationIndex(){
        return locationIndex;
    }
    public String getDescription(){return  description;}
}
