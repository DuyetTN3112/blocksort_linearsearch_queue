package view;

import java.util.Scanner;
import model.Book;
import model.Order;
import service.BookService;
import service.BookSortService;
import service.OrderService;

public class BookSortMenu {
    
    public static void showSortMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=== BOOK SORTING MENU ===");
            System.out.println("1. Sort by Title");
            System.out.println("2. Sort by Author");
            System.out.println("3. Sort Order Books");
            System.out.println("4. Benchmark Sorting");
            System.out.println("5. Test Sorting Patterns");
            System.out.println("6. Back to Main Menu");
            
            System.out.print("Enter your choice (1-6): ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    sortByTitle(scanner);
                    break;
                case "2":
                    sortByAuthor(scanner);
                    break;
                case "3":
                    sortOrderBooks(scanner);
                    break;
                case "4":
                    benchmarkSorting(scanner);
                    break;
                case "5":
                    testSortingPatterns(scanner);
                    break;
                case "6":
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
    
    private static void sortByTitle(Scanner scanner) {
        System.out.println("\n=== SORT BY TITLE ===");
        Book[] books = BookService.getAllBooks();
        int bookCount = BookService.getBookCount();
        
        if (bookCount == 0) {
            System.out.println("No books to sort.");
            return;
        }
        
        System.out.println("Original order:");
        displayBooks(books, bookCount);
        
        BookSortService.sortByTitle(books, bookCount);
        
        System.out.println("\nSorted by title:");
        displayBooks(books, bookCount);
        
        System.out.println("Sorting completed using Block Sort algorithm.");
    }
    
    private static void sortByAuthor(Scanner scanner) {
        System.out.println("\n=== SORT BY AUTHOR ===");
        Book[] books = BookService.getAllBooks();
        int bookCount = BookService.getBookCount();
        
        if (bookCount == 0) {
            System.out.println("No books to sort.");
            return;
        }
        
        System.out.println("Original order:");
        displayBooks(books, bookCount);
        
        BookSortService.sortByAuthor(books, bookCount);
        
        System.out.println("\nSorted by author:");
        displayBooks(books, bookCount);
        
        System.out.println("Sorting completed using Block Sort algorithm.");
    }
    
    private static void sortOrderBooks(Scanner scanner) {
        System.out.println("\n=== SORT ORDER BOOKS ===");
        
        if (OrderService.getOrderCount() == 0) {
            System.out.println("No orders available.");
            return;
        }
        
        // list orders
        Order[] orders = OrderService.getAllOrders();
        for (int i = 0; i < orders.length; i++) {
            System.out.println((i + 1) + ". " + orders[i].getOrderNumber() + " - " + orders[i].getCustomer().getName());
        }
        System.out.println("Enter the number of the order to sort books (1-" + OrderService.getOrderCount() + "):");
        
        int orderIndex = -1;
        boolean validIndex = false;
        while (!validIndex) {
            try {
                orderIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (orderIndex >= 0 && orderIndex < OrderService.getOrderCount()) {
                    validIndex = true;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + OrderService.getOrderCount());
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        Order selectedOrder = orders[orderIndex];
        
        if (selectedOrder.getItemCount() == 0) {
            System.out.println("This order has no books to sort.");
            return;
        }
        
        System.out.println("Original order books:");
        displayOrderBooks(selectedOrder);
        
        System.out.println("\nChoose sorting criteria:");
        System.out.println("1. Sort by book title");
        System.out.println("2. Sort by quantity");
        
        int sortChoice;
        try {
            sortChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            sortChoice = 1;
        }
        
        String sortBy = "title";
        switch (sortChoice) {
            case 1:
                sortBy = "title";
                break;
            case 2:
                sortBy = "quantity";
                break;
            default:
                System.out.println("Invalid choice. Using title sorting.");
                sortBy = "title";
        }
        
        BookSortService.sortOrderBooks(selectedOrder, sortBy);
        
        System.out.println("\nSorted order books:");
        displayOrderBooks(selectedOrder);
        
        System.out.println("Sorting completed using Block Sort algorithm.");
    }
    
    private static void benchmarkSorting(Scanner scanner) {
        System.out.println("\n=== BENCHMARK SORTING ===");
        
        Book[] books = BookService.getAllBooks();
        int bookCount = BookService.getBookCount();
        
        if (bookCount == 0) {
            System.out.println("No books to benchmark.");
            return;
        }
        
        System.out.println("Choose sorting criteria for benchmark:");
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by author");
        
        int sortChoice;
        try {
            sortChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            sortChoice = 1;
        }
        
        String sortBy = "title";
        switch (sortChoice) {
            case 1:
                sortBy = "title";
                break;
            case 2:
                sortBy = "author";
                break;
            default:
                System.out.println("Invalid choice. Using title sorting.");
                sortBy = "title";
        }
        
        System.out.print("Enter number of iterations for benchmark: ");
        int iterations;
        try {
            iterations = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid number of iterations.");
            return;
        }
        
        if (iterations <= 0) {
            System.out.println("Invalid number of iterations.");
            return;
        }
        
        BookSortService.benchmarkSort(books, bookCount, sortBy, iterations);
    }
    
    private static void testSortingPatterns(Scanner scanner) {
        System.out.println("\n=== TEST SORTING PATTERNS ===");
        
        Book[] books = BookService.getAllBooks();
        int bookCount = BookService.getBookCount();
        
        if (bookCount == 0) {
            System.out.println("No books to test patterns.");
            return;
        }
        
        BookSortService.testSortingPatterns(books, bookCount);
    }
    
    private static void displayBooks(Book[] books, int count) {
        for (int i = 0; i < count; i++) {
            Book book = books[i];
            System.out.println((i + 1) + ". " + book.getTitle() + 
                               " by " + book.getAuthor());
        }
    }
    
    private static void displayOrderBooks(Order order) {
        Order.BookItem[] items = order.getItems();
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i].getBook().getTitle() + 
                               " by " + items[i].getBook().getAuthor() + 
                               " (Qty: " + items[i].getQuantity() + ")");
        }
    }
}
