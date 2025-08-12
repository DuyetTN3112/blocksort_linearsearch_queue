package view;

import java.util.Scanner;
import model.Order;
import service.OrderSearchService;

public class OrderSearchMenu {
    
    public static void showSearchMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=== ORDER SEARCH MENU ===");
            System.out.println("1. Search by Order Number");
            System.out.println("2. Search by Customer Name");
            System.out.println("3. Search by Order Status");
            System.out.println("4. Back to Main Menu");
            
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    searchByOrderNumber(scanner);
                    break;
                case "2":
                    searchByCustomerName(scanner);
                    break;
                case "3":
                    searchByStatus(scanner);
                    break;
                case "4":
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
    
    private static void searchByOrderNumber(Scanner scanner) {
        System.out.println("\n=== SEARCH BY ORDER NUMBER ===");
        System.out.print("Enter Order Number to search: ");
        String orderNumber = scanner.nextLine().trim();
        
        if (orderNumber.isEmpty()) {
            System.out.println("Order Number cannot be empty.");
            return;
        }
        
        Order result = OrderSearchService.searchOrderById(orderNumber);
        
        if (result != null) {
            System.out.println("\nOrder found:");
            displayOrderDetails(result);
        } else {
            System.out.println("No order found with Number: " + orderNumber);
        }
    }
    
    private static void searchByCustomerName(Scanner scanner) {
        System.out.println("\n=== SEARCH BY CUSTOMER NAME ===");
        System.out.print("Enter customer name to search: ");
        String customerName = scanner.nextLine().trim();
        
        if (customerName.isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }
        
        Order[] results = OrderSearchService.searchOrdersByCustomerName(customerName);
        
        if (results.length > 0) {
            System.out.println("\nFound " + results.length + " order(s):");
            for (Order order : results) {
                displayOrderDetails(order);
                System.out.println("---");
            }
        } else {
            System.out.println("No orders found for customer: " + customerName);
        }
    }
    
    private static void searchByStatus(Scanner scanner) {
        System.out.println("\n=== SEARCH BY ORDER STATUS ===");
        System.out.println("Available statuses: PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED");
        System.out.print("Enter order status to search: ");
        String status = scanner.nextLine().trim().toUpperCase();
        
        if (status.isEmpty()) {
            System.out.println("Status cannot be empty.");
            return;
        }
        
        Order[] results = OrderSearchService.searchOrdersByStatus(status);
        
        if (results.length > 0) {
            System.out.println("\nFound " + results.length + " order(s):");
            for (Order order : results) {
                displayOrderDetails(order);
                System.out.println("---");
            }
        } else {
            System.out.println("No orders found with status: " + status);
        }
    }
    
    private static void displayOrderDetails(Order order) {
        System.out.println("Order Number: " + order.getOrderNumber());
        System.out.println("Customer: " + (order.getCustomer() != null ? order.getCustomer().getName() : "Unknown"));
        System.out.println("Status: " + order.getStatus());
        System.out.println("Items: " + order.getItemCount());
        
        Order.BookItem[] items = order.getItems();
        if (items.length > 0) {
            System.out.println("Books:");
            for (int i = 0; i < items.length; i++) {
                System.out.println("  - " + items[i].getBook().getTitle() + 
                                 " by " + items[i].getBook().getAuthor() + 
                                 " (Qty: " + items[i].getQuantity() + ")");
            }
        }
    }
}
