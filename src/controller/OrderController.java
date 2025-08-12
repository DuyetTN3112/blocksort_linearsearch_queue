package controller;

import java.util.Scanner;
import model.Book;
import model.Customer;
import model.Order;
import service.BookService;
import service.CustomerService;
import service.OrderService;
import view.OrderView;

// Controller điều phối luồng xử lý cho Order (rút gọn)
public class OrderController {
    private static Scanner scanner;
    
    public static void setScanner(Scanner scanner) {
        OrderController.scanner = scanner;
    }
    
    public static void createOrder() {
        if (OrderService.getOrderCount() >= 200) {
            OrderView.displayMessage("Maximum number of orders reached.");
            return;
        }
        if (CustomerService.getCustomerCount() == 0) {
            OrderView.displayMessage("No customers available. Please create a customer first.");
            return;
        }
        if (BookService.getBookCount() == 0) {
            OrderView.displayMessage("No books available. Please create a book first.");
            return;
        }
        OrderView.displayCreateOrderHeader();
        // chọn customer
        OrderView.displayAllCustomers(CustomerService.getAllCustomers());
        int customerIndex = OrderView.getCustomerIndex(scanner, CustomerService.getCustomerCount());
        if (customerIndex == -1) return;
        Customer selectedCustomer = CustomerService.findCustomerByIndex(customerIndex);
        if (selectedCustomer == null) {
            OrderView.displayMessage("Customer not found.");
            return;
        }
        // tạo order mới
        Order newOrder = new Order(OrderService.generateNewOrderNumber(), selectedCustomer);
        // thêm sách
        boolean addMore = true;
        while (addMore) {
            if (BookService.getBookCount() == 0) break;
            OrderView.displayAllBooks(BookService.getAllBooks());
            int bookIndex = OrderView.getBookIndex(scanner, BookService.getBookCount());
            if (bookIndex == -1) break;
            Book selectedBook = BookService.findBookByIndex(bookIndex);
            if (selectedBook == null) {
                OrderView.displayMessage("Book not found.");
                continue;
            }
            // hỏi quantity (không ràng buộc stock vì model rút gọn)
            int quantity = 1;
            try {
                System.out.print("Enter quantity: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) quantity = 1;
            } catch (Exception ignored) {}
            newOrder.addItem(selectedBook, quantity);
            OrderView.displayBookAddedToOrder(selectedBook, quantity);
            addMore = OrderView.askAddMoreBooks(scanner);
        }
        if (newOrder.getItemCount() > 0) {
            if (OrderService.addOrder(newOrder)) {
                OrderView.displayOrderCreated(newOrder);
            } else {
                OrderView.displayMessage("Failed to create order.");
            }
        } else {
            OrderView.displayMessage("Order must contain at least one book.");
        }
    }
    
    public static void updateOrder() {
        if (OrderService.getOrderCount() == 0) {
            OrderView.displayMessage("No orders to update.");
            return;
        }
        OrderView.displayUpdateOrderHeader();
        OrderView.displayAllOrders(OrderService.getAllOrders());
        int index = OrderView.getOrderIndex(scanner, OrderService.getOrderCount());
        if (index == -1) return;
        Order existing = OrderService.findOrderByIndex(index);
        if (existing == null) {
            OrderView.displayMessage("Order not found.");
            return;
        }
        OrderView.displayOrderToUpdate(existing);
        String newStatus = OrderView.getNewOrderStatus(scanner, existing.getStatus());
        if (OrderService.updateOrderStatus(existing.getOrderNumber(), newStatus)) {
            OrderView.displayOrderUpdated(existing);
        } else {
            OrderView.displayMessage("Failed to update order status.");
        }
    }
    
    public static void deleteOrder() {
        if (OrderService.getOrderCount() == 0) {
            OrderView.displayMessage("No orders to delete.");
            return;
        }
        OrderView.displayDeleteOrderHeader();
        OrderView.displayAllOrders(OrderService.getAllOrders());
        int index = OrderView.getOrderIndex(scanner, OrderService.getOrderCount());
        if (index == -1) return;
        Order toDelete = OrderService.findOrderByIndex(index);
        if (toDelete == null) {
            OrderView.displayMessage("Order not found.");
            return;
        }
        OrderView.displayOrderToDelete(toDelete);
        boolean confirm = OrderView.confirmDeletion(scanner);
        if (confirm) {
            if (OrderService.deleteOrderByIndex(index)) {
                OrderView.displayOrderDeleted();
            } else {
                OrderView.displayMessage("Failed to delete order.");
            }
        }
    }
    
    public static void listAllOrders() {
        if (OrderService.getOrderCount() == 0) {
            OrderView.displayMessage("No orders found.");
            return;
        }
        OrderView.displayAllOrders(OrderService.getAllOrders());
    }
    
    public static void searchOrdersByCustomer() {
        if (OrderService.getOrderCount() == 0) {
            OrderView.displayMessage("No orders to search.");
            return;
        }
        String customerName = OrderView.getSearchCustomerName(scanner);
        Order[] results = OrderService.searchOrdersByCustomerName(customerName);
        OrderView.displaySearchResults(results, "customer name", customerName);
    }
    
    public static void searchOrdersByStatus() {
        if (OrderService.getOrderCount() == 0) {
            OrderView.displayMessage("No orders to search.");
            return;
        }
        String status = OrderView.getSearchStatus(scanner);
        Order[] results = OrderService.searchOrdersByStatus(status);
        OrderView.displaySearchResults(results, "status", status);
    }
    
    public static void loadSampleOrders() {
        OrderService.loadSampleOrders();
        OrderView.displayMessage("Sample orders loaded successfully!");
    }
}
