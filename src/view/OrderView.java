package view;

import java.util.Scanner;
import model.Book;
import model.Customer;
import model.Order;

// View hiển thị giao diện cho Order (rút gọn)
public class OrderView {
    
    public static void displayMessage(String message) {
        System.out.println(message);
    }
    
    public static void displayCreateOrderHeader() {
        System.out.println("\n=== CREATE NEW ORDER ===");
    }
    
    public static void displayUpdateOrderHeader() {
        System.out.println("\n=== UPDATE ORDER ===");
    }
    
    public static void displayDeleteOrderHeader() {
        System.out.println("\n=== DELETE ORDER ===");
    }
    
    public static void displayAllOrders(Order[] orders) {
        if (orders == null || orders.length == 0) {
            System.out.println("No orders found.");
            return;
        }
        System.out.println("\nCurrent Orders:");
        for (int i = 0; i < orders.length; i++) {
            Order order = orders[i];
            System.out.println((i + 1) + ". Order " + order.getOrderNumber() + 
                               " - " + (order.getCustomer() != null ? order.getCustomer().getName() : "Unknown") +
                               " - Status: " + order.getStatus() +
                               " - Items: " + order.getItemCount());
        }
    }
    
    public static void displayAllCustomers(Customer[] customers) {
        if (customers == null || customers.length == 0) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("\nSelect Customer:");
        for (int i = 0; i < customers.length; i++) {
            Customer customer = customers[i];
            System.out.println((i + 1) + ". " + customer.getName() + 
                               " - Address: " + customer.getShippingAddress());
        }
    }
    
    public static void displayAllBooks(Book[] books) {
        if (books == null || books.length == 0) {
            System.out.println("No books found.");
            return;
        }
        System.out.println("\nSelect Book:");
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println((i + 1) + ". " + book.getTitle() + 
                               " by " + book.getAuthor());
        }
    }
    
    public static void displayOrderCreated(Order order) {
        System.out.println("Order created successfully!");
        System.out.println("Order Number: " + order.getOrderNumber());
        System.out.println("Customer: " + (order.getCustomer() != null ? order.getCustomer().getName() : "Unknown"));
        System.out.println("Status: " + order.getStatus());
        System.out.println("Items: " + order.getItemCount());
    }
    
    public static void displayOrderUpdated(Order order) {
        System.out.println("Order updated successfully!");
        System.out.println("Order Number: " + order.getOrderNumber());
        System.out.println("Customer: " + (order.getCustomer() != null ? order.getCustomer().getName() : "Unknown"));
        System.out.println("Status: " + order.getStatus());
        System.out.println("Items: " + order.getItemCount());
    }
    
    public static void displayOrderToUpdate(Order order) {
        System.out.println("Updating order: " + order.getOrderNumber());
        System.out.println("Current status: " + order.getStatus());
    }
    
    public static void displayOrderToDelete(Order order) {
        System.out.println("Are you sure you want to delete this order?");
        System.out.println("Order Number: " + order.getOrderNumber());
        System.out.println("Customer: " + (order.getCustomer() != null ? order.getCustomer().getName() : "Unknown"));
        System.out.println("Status: " + order.getStatus());
        System.out.println("Items: " + order.getItemCount());
    }
    
    public static void displayOrderDeleted() {
        System.out.println("Order deleted successfully!");
    }
    
    public static void displayBookAddedToOrder(Book book, int quantity) {
        System.out.println("Added " + quantity + "x " + book.getTitle() + " to order.");
    }
    
    public static void displaySearchResults(Order[] results, String searchType, String searchTerm) {
        System.out.println("\nFound " + results.length + " order(s) with " + searchType + ": " + searchTerm);
        displayAllOrders(results);
    }
    
    // Input methods
    public static int getCustomerIndex(Scanner scanner, int maxCount) {
        System.out.print("Enter customer number (1-" + maxCount + ") or 0 to cancel: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return -1;
            if (choice >= 1 && choice <= maxCount) return choice - 1;
            System.out.println("Invalid choice. Please enter a number between 1 and " + maxCount);
            return getCustomerIndex(scanner, maxCount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getCustomerIndex(scanner, maxCount);
        }
    }
    
    public static int getBookIndex(Scanner scanner, int maxCount) {
        System.out.print("Enter book number (1-" + maxCount + ") or 0 to finish: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return -1;
            if (choice >= 1 && choice <= maxCount) return choice - 1;
            System.out.println("Invalid choice. Please enter a number between 1 and " + maxCount);
            return getBookIndex(scanner, maxCount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getBookIndex(scanner, maxCount);
        }
    }
    
    public static int getBookQuantity(Scanner scanner, int ignored) {
        System.out.print("Enter quantity: ");
        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity >= 1) return quantity;
            System.out.println("Invalid quantity. Please enter a number >= 1");
            return getBookQuantity(scanner, ignored);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getBookQuantity(scanner, ignored);
        }
    }
    
    public static boolean askAddMoreBooks(Scanner scanner) {
        System.out.print("Add more books? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
    
    public static int getOrderIndex(Scanner scanner, int maxCount) {
        System.out.print("Enter order number (1-" + maxCount + ") or 0 to cancel: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return -1;
            if (choice >= 1 && choice <= maxCount) return choice - 1;
            System.out.println("Invalid choice. Please enter a number between 1 and " + maxCount);
            return getOrderIndex(scanner, maxCount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getOrderIndex(scanner, maxCount);
        }
    }
    
    public static String getNewOrderStatus(Scanner scanner, String currentStatus) {
        System.out.println("Available statuses: PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED");
        System.out.print("Enter new status [" + currentStatus + "]: ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? currentStatus : input.toUpperCase();
    }
    
    public static boolean confirmDeletion(Scanner scanner) {
        System.out.print("Are you sure? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
    
    public static String getSearchCustomerName(Scanner scanner) {
        System.out.print("Enter customer name to search: ");
        return scanner.nextLine().trim();
    }
    
    public static String getSearchStatus(Scanner scanner) {
        System.out.print("Enter status to search: ");
        return scanner.nextLine().trim();
    }
}
