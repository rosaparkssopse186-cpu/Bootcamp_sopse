package org.example;

import java.util.Scanner;

public class NeighborhoodLibrary {
    static Scanner scanner = new Scanner(System.in);

    static Book[] books = {
                new Book(1, "9780140449136", "The Odyssey"),
                new Book(2, "9780743273565", "The Great Gatsby"),
                new Book(3, "9780451524935", "1984"),
                new Book(4, "9780061120084", "To Kill a Mockingbird"),
                new Book(5, "9780316769488", "The Catcher in the Rye"),
                new Book(6, "9780618260300", "The Hobbit"),
                new Book(7, "9780544003415", "The Lord of the Rings"),
                new Book(8, "9780439554930", "Harry Potter"),
                new Book(9, "9780307277671", "The Road"),
                new Book(10, "9780141439600", "Pride and Prejudice"),
                new Book(11, "9780142437230", "Moby Dick"),
                new Book(12, "9780140449266", "The Iliad"),
                new Book(13, "9781503280786", "Frankenstein"),
                new Book(14, "9780486280615", "Dracula"),
                new Book(15, "9780140186390", "Of Mice and Men"),
                new Book(16, "9780060850524", "Brave New World"),
                new Book(17, "9780553213119", "Jane Eyre"),
                new Book(18, "9780143105428", "Wuthering Heights"),
                new Book(19, "9780140283334", "Animal Farm"),
                new Book(20, "9780307949486", "The Book Thief")
    };

    public static void start() {

        boolean running = true;

        while (running) {

            displayHomeScreen();
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    showAvailableBooks();
                    break;

                case "2":
                    showCheckedOutBooks();
                    break;

                case "3":
                    running = false;
                    System.out.println("Thank you for using Neighborhood Library.");
                    break;

                default:

                    System.out.println("invalid option. please try again. ");
            }
        }

    }


  public static void displayHomeScreen() {

      System.out.println("NEIGHBORHOOD LIBRARY");
      System.out.println("1 Show available books");
      System.out.println("2 Show Checked out books");
      System.out.println("3 Exit");
      System.out.println("Choose an option:");

  }

    public static void showAvailableBooks() {
        System.out.println("\nAVAILIABLE Books");

        boolean foundBooks = false;

        for (Book book : books) {
            if (!book.isCheckedOut()) {
            System.out.println(book);
            foundBooks = true;
        }
    }
    if (!foundBooks) {
        System.out.println("No books are currently available.");
        return;
    }
    System.out.println("\nEnter a book id to check out or X to return:");

    String input = scanner.nextLine();

    if (input.equalsIgnoreCase("x")) {
        return;
    }

    try

    {

        int id = Integer.parseInt(input);

        Book selectedBook = findBookById(id);

        if (selectedBook == null) {
            System.out.println("Book ID not foud");

            return;
        }
        if (selectedBook.isCheckedOut()) {
            System.out.println("That book is already checked out.");
            return;
        }

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        selectedBook.checkOut(name);
    } catch (NumberFormatException e){
        System.out.println("please enter a valid number");
    }
  }

public static void showCheckedOutBooks() {

    System.out.println("\nCHECKED OUT BOOKS");

    boolean foundBooks = false;

    for (Book book : books) {
        if (book.isCheckedOut()) {
            System.out.println(
                    book.getId() + " | " +
                            book.getIsbn() + " | " +
                            book.getTitle() + " | Checked out to: " +
                            book.getCheckedOutTo());

            foundBooks = true;
        }
    }

    if (!foundBooks) {
        System.out.println("No books are currently checked out.");
        return;
    }
    System.out.println("\nC - Checked In Book | X - return:");

    String choice = scanner.nextLine();
    if (choice.equals("c")) {
        checkInBook();
    }
}

public static void checkInBook () {
    System.out.println("Enter Book ID: ");

    try {
        int id = Integer.parseInt(scanner.nextLine());

        Book book = findBookById(id);

        if (book == null) {
            System.out.println("book Id not found");
            return;
        }


    } catch (NumberFormatException e) {
        System.out.println("Invalid ID.");
    }
}

public static Book findBookById(int id) {

    for (Book book : books) {
        if (book.getId() == id) {
            return book;
        }
    }

    return null;

   }

}
