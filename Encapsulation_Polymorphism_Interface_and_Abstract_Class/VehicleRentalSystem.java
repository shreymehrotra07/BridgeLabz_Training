import java.util.*;

interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

abstract class Vehicle implements Insurable {
    private String vehicleNumber, type, policyNumber;
    private double rentalRate;
    
    public Vehicle(String number,String type,double rate,String policy){
        this.vehicleNumber=number; this.type=type; this.rentalRate=rate; this.policyNumber=policy;
    }
    public String getVehicleNumber(){ return vehicleNumber; }
    public String getType(){ return type; }
    public double getRentalRate(){ return rentalRate; }
    protected String getPolicyNumber(){ return policyNumber; } // encapsulated

    public abstract double calculateRentalCost(int days);

    public void displayDetails(int days){
        double rent=calculateRentalCost(days);
        double ins=calculateInsurance();
        System.out.println(type+" ("+vehicleNumber+") | Rent:"+rent+" | Insurance:"+ins+" | Policy:"+getInsuranceDetails());
    }
}

class Car extends Vehicle {
    public Car(String num,double rate,String policy){ super(num,"Car",rate,policy); }
    public double calculateRentalCost(int days){ return getRentalRate()*days; }
    public double calculateInsurance(){ return getRentalRate()*0.1; }
    public String getInsuranceDetails(){ return "Car Policy:"+getPolicyNumber(); }
}

class Bike extends Vehicle {
    public Bike(String num,double rate,String policy){ super(num,"Bike",rate,policy); }
    public double calculateRentalCost(int days){ return getRentalRate()*days*0.8; } // cheaper
    public double calculateInsurance(){ return getRentalRate()*0.05; }
    public String getInsuranceDetails(){ return "Bike Policy:"+getPolicyNumber(); }
}

class Truck extends Vehicle {
    public Truck(String num,double rate,String policy){ super(num,"Truck",rate,policy); }
    public double calculateRentalCost(int days){ return getRentalRate()*days*1.5; } // higher load
    public double calculateInsurance(){ return getRentalRate()*0.2; }
    public String getInsuranceDetails(){ return "Truck Policy:"+getPolicyNumber(); }
}

public class VehicleRentalSystem {
    public static void main(String[] args){
        List<Vehicle> list=Arrays.asList(
            new Car("CAR101",2000,"P123"),
            new Bike("BIKE55",800,"P456"),
            new Truck("TRUCK9",5000,"P789")
        );
        for(Vehicle v:list) v.displayDetails(5); // 5 days rental
    }
}
