package org.example;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = false;
        this.checkedOutTo = "";
    }
    public int getId() {
        return id;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public boolean isCheckedOut() {
        return isCheckedOut;
    }
    public String getCheckedOutTo() {
        return checkedOutTo;
   }
  public void checkOut(String name) {
       if (isCheckedOut) {
           System.out.println("This book is already checked out.");
       }
       isCheckedOut = true;
       checkedOutTo = name;
    System.out.println("Book checked out successfully.");
}
    public void checkin() {
        if (!isCheckedOut) {
            System.out.println("this book is already checked in.");
            return;
        }
        isCheckedOut = false;
        checkedOutTo = "";
        System.out.println("Book checked in sucessfully");
       }
        @Override
        public String toString() {
            return String.format("%d | %s| %s", id, isbn, title);
        }
}
