import java.util.*;

interface Discountable {
    double applyDiscount(double total);
    String getDiscountDetails();
}

abstract class FoodItem implements Discountable {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String name,double price,int qty){
        this.itemName=name; this.price=price; this.quantity=qty;
    }
    public String getItemName(){ return itemName; }
    public double getPrice(){ return price; }
    public int getQuantity(){ return quantity; }

    public abstract double calculateTotalPrice();

    public void getItemDetails(){
        System.out.println(itemName+" | Qty:"+quantity+" | Price:"+price);
    }
}

class VegItem extends FoodItem {
    public VegItem(String name,double price,int qty){ super(name,price,qty); }
    public double calculateTotalPrice(){ return getPrice()*getQuantity(); }
    public double applyDiscount(double total){ return total*0.9; } // 10% off
    public String getDiscountDetails(){ return "10% Veg Discount"; }
}

class NonVegItem extends FoodItem {
    public NonVegItem(String name,double price,int qty){ super(name,price,qty); }
    public double calculateTotalPrice(){ return (getPrice()*getQuantity())+50; } // extra charge
    public double applyDiscount(double total){ return total*0.95; } // 5% off
    public String getDiscountDetails(){ return "5% Non-Veg Discount"; }
}

public class OnlineFoodDeliverySystem {
    public static void main(String[] args){
        List<FoodItem> order=Arrays.asList(
            new VegItem("Paneer Curry",200,2),
            new NonVegItem("Chicken Biryani",300,1)
        );

        for(FoodItem item:order){
            item.getItemDetails();
            double total=item.calculateTotalPrice();
            double finalAmount=item.applyDiscount(total);
            System.out.println("Total:"+total+" | Final:"+finalAmount+" | "+item.getDiscountDetails());
            System.out.println("---------------------------------");
        }
    }
}
