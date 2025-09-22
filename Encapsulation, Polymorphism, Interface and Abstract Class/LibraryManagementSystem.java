import java.util.*;

interface Reservable {
    void reserveItem(String borrower);
    boolean checkAvailability();
}

abstract class LibraryItem implements Reservable {
    private String itemId, title, author, borrower;
    public LibraryItem(String id,String title,String author){
        this.itemId=id; this.title=title; this.author=author;
    }
    public String getItemId(){ return itemId; }
    public String getTitle(){ return title; }
    public String getAuthor(){ return author; }
    protected String getBorrower(){ return borrower; } // encapsulated
    
    public void reserveItem(String borrower){ this.borrower=borrower; }
    public boolean checkAvailability(){ return borrower==null; }

    public abstract int getLoanDuration(); // in days

    public void getItemDetails(){
        System.out.println(title+" by "+author+" | ID:"+itemId+" | Loan:"+getLoanDuration()+" days | Available:"+(checkAvailability()?"Yes":"No"));
    }
}

class Book extends LibraryItem {
    public Book(String id,String title,String author){ super(id,title,author); }
    public int getLoanDuration(){ return 21; } // 3 weeks
}
class Magazine extends LibraryItem {
    public Magazine(String id,String title,String author){ super(id,title,author); }
    public int getLoanDuration(){ return 7; } // 1 week
}
class DVD extends LibraryItem {
    public DVD(String id,String title,String author){ super(id,title,author); }
    public int getLoanDuration(){ return 14; } // 2 weeks
}

public class LibraryManagementSystem {
    public static void main(String[] args){
        List<LibraryItem> items=Arrays.asList(
            new Book("B101","Java Basics","James"),
            new Magazine("M202","Tech Today","EditorX"),
            new DVD("D303","Inception","Nolan")
        );
    
        items.get(0).reserveItem("Alice");

        for(LibraryItem item:items){
            item.getItemDetails();
        }
    }
}
