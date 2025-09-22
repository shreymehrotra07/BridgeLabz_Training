import java.util.*;

interface MedicalRecord {
    void addRecord(String record);
    List<String> viewRecords();
}

abstract class Patient implements MedicalRecord {
    private int patientId, age;
    private String name, diagnosis;
    private List<String> history = new ArrayList<>();

    public Patient(int id,String name,int age,String diag){
        this.patientId=id; this.name=name; this.age=age; this.diagnosis=diag;
    }
    public int getPatientId(){ return patientId; }
    public String getName(){ return name; }
    public int getAge(){ return age; }
    protected String getDiagnosis(){ return diagnosis; } // encapsulated

    public void addRecord(String record){ history.add(record); }
    public List<String> viewRecords(){ return history; }

    public abstract double calculateBill();

    public void getPatientDetails(){
        System.out.println("ID:"+patientId+" | "+name+" | Age:"+age+" | Bill:"+calculateBill());
    }
}

class InPatient extends Patient {
    private int days; private double dailyRate;
    public InPatient(int id,String name,int age,String diag,int days,double rate){
        super(id,name,age,diag); this.days=days; this.dailyRate=rate;
    }
    public double calculateBill(){ return days*dailyRate; }
}
class OutPatient extends Patient {
    private double consultationFee;
    public OutPatient(int id,String name,int age,String diag,double fee){
        super(id,name,age,diag); this.consultationFee=fee;
    }
    public double calculateBill(){ return consultationFee; }
}

public class HospitalManagement {
    public static void main(String[] args){
        List<Patient> patients=Arrays.asList(
            new InPatient(1,"Alice",30,"Flu",5,2000),
            new OutPatient(2,"Bob",45,"Checkup",500)
        );

        for(Patient p:patients){
            p.addRecord("Visited on: 22-Sep-2025");
            p.getPatientDetails();
            System.out.println("Records:"+p.viewRecords());
            System.out.println("--------------------------------");
        }
    }
}
