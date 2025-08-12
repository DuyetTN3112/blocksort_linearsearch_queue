package view;

import java.util.Scanner;
import model.Book;

// View hiển thị giao diện cho Book (rút gọn)
public class BookView {
    
    public static void displayMessage(String message) {
        System.out.println(message);
    }
    
    public static void displayCreateBookHeader() {
        System.out.println("\n=== CREATE NEW BOOK ===");
    }
    
    public static void displayUpdateBookHeader() {
        System.out.println("\n=== UPDATE BOOK ===");
    }
    
    public static void displayDeleteBookHeader() {
        System.out.println("\n=== DELETE BOOK ===");
    }
    
    public static void displayAllBooks(Book[] books) {
        if (books == null || books.length == 0) {
            System.out.println("No books found.");
            return;
        }
        System.out.println("\nCurrent Books:");
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println("ID: " + book.getId() + " - " + book.getTitle() + 
                               " by " + book.getAuthor());
        }
    }
    
    public static void displayBookCreated(Book book) {
        System.out.println("Book created successfully!");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
    }
    
    public static void displayBookUpdated(Book book) {
        System.out.println("Book updated successfully!");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
    }
    
    public static void displayBookToUpdate(Book book) {
        System.out.println("Updating book: " + book.getTitle());
    }
    
    public static void displayBookToDelete(Book book) {
        System.out.println("Are you sure you want to delete this book?");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
    }
    
    public static void displayBookDeleted() {
        System.out.println("Book deleted successfully!");
    }
    
    public static void displaySearchResults(Book[] results, String searchType, String searchTerm) {
        System.out.println("\nFound " + results.length + " book(s) with " + searchType + ": " + searchTerm);
        displayAllBooks(results);
    }
    
    // Input methods
    public static String getBookTitle(Scanner scanner) {
        System.out.print("Enter book title: ");
        return scanner.nextLine().trim();
    }
    
    public static String getBookAuthor(Scanner scanner) {
        System.out.print("Enter author name: ");
        return scanner.nextLine().trim();
    }
    
    // [ĐÃ XÓA] getBookIndex - Chỉ sử dụng getBookId
    
    public static String getNewBookTitle(Scanner scanner, String currentTitle) {
        System.out.print("Enter new title (current: " + currentTitle + "): ");
        String newTitle = scanner.nextLine().trim();
        return newTitle.isEmpty() ? currentTitle : newTitle;
    }
    
    public static String getNewBookAuthor(Scanner scanner, String currentAuthor) {
        System.out.print("Enter new author (current: " + currentAuthor + "): ");
        String newAuthor = scanner.nextLine().trim();
        return newAuthor.isEmpty() ? currentAuthor : newAuthor;
    }
    
    public static boolean confirmDeletion(Scanner scanner) {
        System.out.print("Enter 'yes' to confirm deletion, or any other key to cancel: ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        return confirmation.equals("yes");
    }
    
    public static String getSearchTitle(Scanner scanner) {
        System.out.print("Enter title to search: ");
        return scanner.nextLine().trim();
    }
    
    public static String getSearchAuthor(Scanner scanner) {
        System.out.print("Enter author to search: ");
        return scanner.nextLine().trim();
    }
    
    // Phương thức mới để nhập ID (dễ sử dụng hơn)
    public static int getBookId(Scanner scanner) {
        int id = -1;
        boolean validId = false;
        while (!validId) {
            System.out.print("Enter Book ID: ");
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                if (id > 0) {
                    validId = true;
                } else {
                    System.out.println("Please enter a valid ID (greater than 0).");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return id;
    }
}
