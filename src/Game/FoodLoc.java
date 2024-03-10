package Game;

public class FoodLoc extends Location {
    private String foodName;//食物名稱
    private int foodPrice;
    private int foodHappiness;

    //constructor of foodLocation
    public FoodLoc(String name, int locationIndex, String description,String foodName,int foodPrice,int foodHappiness) {
        super(name, locationIndex, description);

        this.foodName=foodName;
        this.foodPrice=foodPrice;
        this.foodHappiness=foodHappiness;
    }


    public String getFoodName(){
        return foodName;
    }

    public int getFoodPrice(){
        return foodPrice;
    }

    public int getFoodHappiness(){
        return foodHappiness;
    }

}
