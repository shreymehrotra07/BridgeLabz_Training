import java.util.*;

class Flight {
    int id, capacity;
    String src, dest, date;
    Flight(int id, String s, String d, String dt, int c) {
        this.id=id; src=s; dest=d; date=dt; capacity=c;
    }
}

class Passenger {
    int id; String name;
    Passenger(int id, String n) { this.id=id; name=n; }
}

class Booking {
    int id, fid, pid;
    Booking(int id,int f,int p){ this.id=id; fid=f; pid=p; }
}

public class FlightApp {
    public static void main(String[] args) {
        List<Flight> flights = Arrays.asList(
            new Flight(1,"Delhi","Mumbai","2025-09-20",2),
            new Flight(2,"Delhi","Goa","2025-09-21",3),
            new Flight(3,"Delhi","Mumbai","2025-09-22",2),
            new Flight(4,"Chennai","Pune","2025-09-23",3)
        );
        List<Passenger> passengers = Arrays.asList(
            new Passenger(10,"Aman"), new Passenger(11,"Ravi"),
            new Passenger(12,"Neha"), new Passenger(13,"Vikram"),
            new Passenger(14,"Sneha")
        );
        List<Booking> bookings = Arrays.asList(
            new Booking(100,1,10), new Booking(101,1,11), new Booking(102,1,12),
            new Booking(103,2,13), new Booking(104,2,14),
            new Booking(105,3,10), new Booking(106,3,10)
        );

        // 1
        System.out.println("Q1: Bookings per flight");
        for(Flight f:flights){
            int c=0; for(Booking b:bookings) if(b.fid==f.id) c++;
            System.out.println("Flight "+f.id+" "+f.src+"->"+f.dest+" has "+c+" bookings");
        }

        // 2
        System.out.println("\nQ2: Flights with no bookings");
        for(Flight f:flights){
            boolean booked=false;
            for(Booking b:bookings) if(b.fid==f.id) booked=true;
            if(!booked) System.out.println("Flight "+f.id+" "+f.src+"->"+f.dest+" has no bookings");
        }

        // 3
        System.out.println("\nQ3: Passengers with multiple flights same day");
        for(Passenger p:passengers){
            Map<String,Integer> dayCount=new HashMap<>();
            for(Booking b:bookings) if(b.pid==p.id){
                String date=""; for(Flight f:flights) if(f.id==b.fid) date=f.date;
                dayCount.put(date, dayCount.getOrDefault(date,0)+1);
            }
            for(String d:dayCount.keySet()) if(dayCount.get(d)>1)
                System.out.println(p.name+" booked multiple flights on "+d);
        }

        // 4
        System.out.println("\nQ4: Delhi->Mumbai flights next week");
        for(Flight f:flights)
            if(f.src.equals("Delhi") && f.dest.equals("Mumbai") &&
               f.date.compareTo("2025-09-18")>=0 && f.date.compareTo("2025-09-25")<=0)
                System.out.println("Flight "+f.id+" on "+f.date);

        // 5
        System.out.println("\nQ5: Overbooked flights");
        for(Flight f:flights){
            int c=0; for(Booking b:bookings) if(b.fid==f.id) c++;
            if(c>f.capacity) System.out.println("Flight "+f.id+" overbooked. Capacity "+f.capacity+" Booked "+c);
        }

        // 6
        System.out.println("\nQ6: Top 3 booked flights");
        Map<Integer,Integer> count=new HashMap<>();
        for(Booking b:bookings) count.put(b.fid,count.getOrDefault(b.fid,0)+1);
        List<Map.Entry<Integer,Integer>> list=new ArrayList<>(count.entrySet());
        list.sort((a,b)->b.getValue()-a.getValue());
        for(int i=0;i<Math.min(3,list.size());i++){
            int fid=list.get(i).getKey(), c=list.get(i).getValue();
            for(Flight f:flights) if(f.id==fid)
                System.out.println("Flight "+f.id+" "+f.src+"->"+f.dest+" with "+c+" bookings");
        }
    }
}