import java.util.*;

interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLoc);
}

abstract class Vehicle implements GPS {
    private String vehicleId, driverName, location;
    private double ratePerKm;

    public Vehicle(String id,String driver,double rate,String loc){
        this.vehicleId=id; this.driverName=driver; this.ratePerKm=rate; this.location=loc;
    }
    public String getVehicleId(){ return vehicleId; }
    public String getDriverName(){ return driverName; }
    public double getRatePerKm(){ return ratePerKm; }

    public String getCurrentLocation(){ return location; }
    public void updateLocation(String newLoc){ this.location=newLoc; }

    public abstract double calculateFare(double distance);

    public void getVehicleDetails(){
        System.out.println(driverName+" | ID:"+vehicleId+" | Rate:"+ratePerKm+" | Loc:"+location);
    }
}

class Car extends Vehicle {
    public Car(String id,String driver,double rate,String loc){ super(id,driver,rate,loc); }
    public double calculateFare(double dist){ return dist*getRatePerKm()+50; } // base charge
}
class Bike extends Vehicle {
    public Bike(String id,String driver,double rate,String loc){ super(id,driver,rate,loc); }
    public double calculateFare(double dist){ return dist*getRatePerKm(); }
}
class Auto extends Vehicle {
    public Auto(String id,String driver,double rate,String loc){ super(id,driver,rate,loc); }
    public double calculateFare(double dist){ return dist*getRatePerKm()+20; }
}

public class RideHailingApp {
    public static void main(String[] args){
        List<Vehicle> rides=Arrays.asList(
            new Car("C101","Alice",15,"Downtown"),
            new Bike("B202","Bob",8,"Station"),
            new Auto("A303","Charlie",10,"Mall")
        );

        double distance=12; 
        for(Vehicle v:rides){
            v.getVehicleDetails();
            System.out.println("Fare for "+distance+" km: "+v.calculateFare(distance));
            System.out.println("---------------------------------");
        }
    }
}
