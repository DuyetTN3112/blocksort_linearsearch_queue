package view;

import java.util.Scanner;
import model.Customer;

// View hiển thị giao diện cho Customer (rút gọn)
public class CustomerView {
    
    public static void displayMessage(String message) {
        System.out.println(message);
    }
    
    public static void displayCreateCustomerHeader() {
        System.out.println("\n=== CREATE NEW CUSTOMER ===");
    }
    
    public static void displayUpdateCustomerHeader() {
        System.out.println("\n=== UPDATE CUSTOMER ===");
    }
    
    public static void displayDeleteCustomerHeader() {
        System.out.println("\n=== DELETE CUSTOMER ===");
    }
    
    public static void displayAllCustomers(Customer[] customers) {
        if (customers == null || customers.length == 0) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("\nCurrent Customers:");
        for (int i = 0; i < customers.length; i++) {
            Customer c = customers[i];
            System.out.println((i + 1) + ". " + c.getName() + " - Address: " + c.getShippingAddress());
        }
    }
    
    public static void displayCustomerCreated(Customer customer) {
        System.out.println("Customer created successfully!");
        System.out.println("Name: " + customer.getName());
        System.out.println("Shipping Address: " + customer.getShippingAddress());
    }
    
    public static void displayCustomerUpdated(Customer customer) {
        System.out.println("Customer updated successfully!");
        System.out.println("Name: " + customer.getName());
        System.out.println("Shipping Address: " + customer.getShippingAddress());
    }
    
    public static void displayCustomerToUpdate(Customer customer) {
        System.out.println("Updating customer: " + customer.getName());
    }
    
    public static void displayCustomerToDelete(Customer customer) {
        System.out.println("Are you sure you want to delete this customer?");
        System.out.println("Name: " + customer.getName());
        System.out.println("Shipping Address: " + customer.getShippingAddress());
    }
    
    public static void displayCustomerDeleted() {
        System.out.println("Customer deleted successfully!");
    }
    
    public static void displaySearchResults(Customer[] results, String searchType, String searchTerm) {
        System.out.println("\nFound " + results.length + " customer(s) with " + searchType + ": " + searchTerm);
        displayAllCustomers(results);
    }
    
    // Input methods
    public static String getCustomerName(Scanner scanner) {
        System.out.print("Enter customer name: ");
        return scanner.nextLine().trim();
    }
    
    public static String getCustomerAddress(Scanner scanner) {
        System.out.print("Enter shipping address: ");
        return scanner.nextLine().trim();
    }
    
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
    
    public static String getNewCustomerName(Scanner scanner, String currentName) {
        System.out.print("Enter new name [" + currentName + "]: ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? currentName : input;
    }
    
    public static String getNewCustomerAddress(Scanner scanner, String currentAddress) {
        System.out.print("Enter new shipping address [" + currentAddress + "]: ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? currentAddress : input;
    }
    
    public static boolean confirmDeletion(Scanner scanner) {
        System.out.print("Are you sure? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
    
    public static String getSearchName(Scanner scanner) {
        System.out.print("Enter name to search: ");
        return scanner.nextLine().trim();
    }
}
