import java.util.*;

interface Taxable {
    double calculateTax();
    String getTaxDetails();
}

abstract class Product {
    private int productId; private String name; private double price;
    public Product(int productId, String name, double price) {
        this.productId=productId; this.name=name; this.price=price;
    }
    public int getProductId(){ return productId; }
    public String getName(){ return name; }
    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price=price; }

    public abstract double calculateDiscount();
    public void displayFinalPrice() {
        double discount=calculateDiscount();
        double tax=(this instanceof Taxable)?((Taxable)this).calculateTax():0;
        double finalPrice=getPrice()+tax-discount;
        System.out.println(name+" | Price: "+price+" | Discount: "+discount+" | Tax: "+tax+" | Final: "+finalPrice);
    }
}

class Electronics extends Product implements Taxable {
    public Electronics(int id,String name,double price){ super(id,name,price); }
    public double calculateDiscount(){ return getPrice()*0.1; } // 10% off
    public double calculateTax(){ return getPrice()*0.18; }    // 18% GST
    public String getTaxDetails(){ return "18% GST"; }
}


class Clothing extends Product implements Taxable {
    public Clothing(int id,String name,double price){ super(id,name,price); }
    public double calculateDiscount(){ return getPrice()*0.2; } // 20% off
    public double calculateTax(){ return getPrice()*0.05; }     // 5% GST
    public String getTaxDetails(){ return "5% GST"; }
}

class Groceries extends Product {
    public Groceries(int id,String name,double price){ super(id,name,price); }
    public double calculateDiscount(){ return getPrice()*0.05; } // 5% off
}

public class ECommercePlatform {
    public static void main(String[] args) {
        List<Product> products=Arrays.asList(
            new Electronics(1,"Laptop",50000),
            new Clothing(2,"T-Shirt",1000),
            new Groceries(3,"Apples",200)
        );

        for(Product p:products) {
            p.displayFinalPrice();
        }
    }
}
