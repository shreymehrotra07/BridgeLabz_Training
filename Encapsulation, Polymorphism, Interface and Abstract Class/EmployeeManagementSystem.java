import java.util.*;

interface Department {
    void assignDepartment(String deptName);
    String getDepartmentDetails();
}

abstract class Employee implements Department {
    private int id; private String name; private double baseSalary; private String dept;
    public Employee(int id, String name, double baseSalary){this.id=id;this.name=name;this.baseSalary=baseSalary;}
    public int getId(){return id;} public String getName(){return name;} public double getBaseSalary(){return baseSalary;}
    public void assignDepartment(String deptName){this.dept=deptName;}
    public String getDepartmentDetails(){return dept;}
    public abstract double calculateSalary();
    public void displayDetails(){
        System.out.println(id+" | "+name+" | Dept: "+dept+" | Salary: "+calculateSalary());
    }
}

class FullTimeEmployee extends Employee {
    private double allowance;
    public FullTimeEmployee(int id,String name,double base,double allowance){super(id,name,base);this.allowance=allowance;}
    public double calculateSalary(){return getBaseSalary()+allowance;}
}

class PartTimeEmployee extends Employee {
    private int hours; private double rate;
    public PartTimeEmployee(int id,String name,double base,int hours,double rate){super(id,name,base);this.hours=hours;this.rate=rate;}
    public double calculateSalary(){return getBaseSalary()+hours*rate;}
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        List<Employee> list=Arrays.asList(
            new FullTimeEmployee(101,"Alice",30000,5000),
            new PartTimeEmployee(102,"Bob",10000,40,200),
            new FullTimeEmployee(103,"Charlie",40000,8000)
        );
        list.get(0).assignDepartment("IT");
        list.get(1).assignDepartment("HR");
        list.get(2).assignDepartment("Finance");
        for(Employee e:list) e.displayDetails();
    }
}
