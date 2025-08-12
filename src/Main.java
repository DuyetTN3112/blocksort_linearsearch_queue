import controller.BookController;
import controller.CustomerController;
import controller.OrderController;
import java.util.Scanner;
import model.Book;
import model.BookStack;
import model.CustomArrayDeque;
import model.Order;
import service.BookService;
import service.CustomerService;
import service.OrderService;
import view.BookSortMenu;
import view.OrderSearchMenu;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        loadSampleData();
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\n Yêu cậu rất nhìu, đến với tớ nào");
            System.out.println("1. Vào khu sách thử đi, vui lắm");
            System.out.println("2. Tớ có nhìu bạn lắm, giao lưu khumm");
            System.out.println("3. Đặt sách hửm, yêu tớ đi, tớ tặng lunn");
            System.out.println("4. Cậu muốn tìm quyển sách nào, tớ dùng thuật toán Block sót, xịn lắm. À quên đây là sắp xếp");
            System.out.println("5. Đây mới là Tìm kím nè. Tìm đường vào tim tớ hửm. Tớ dùng thuật toán linear đó");
            System.out.println("6. Cấu Bạn Trúc và học giải thuật nè");
            System.out.println("7. Exit");
            
            System.out.print("Chọn một số đi nà: ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    showBookManagementMenu();
                    break;
                case "2":
                    showCustomerManagementMenu();
                    break;
                case "3":
                    showOrderManagementMenu();
                    break;
                case "4":
                    BookSortMenu.showSortMenu(scanner);
                    break;
                case "5":
                    OrderSearchMenu.showSearchMenu(scanner);
                    break;
                case "6":
                    demonstrateQueueOperations();
                    break;
                case "7":
                    System.out.println("Bái baiiiii");
                    exit = true;
                    break;
                default:
                    System.out.println("Đừng nhập linh tinh, nhập số đi nà");
            }
        }
        
        scanner.close();
    }
    
    private static void showBookManagementMenu() {
        BookController.setScanner(scanner);
        while (true) {
            System.out.println("\n Hí lu chào mừng đến tủ sách của tớ");
            System.out.println("1. List all");
            System.out.println("2. Thêm ");
            System.out.println("3. Sửa");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm tên");
            System.out.println("6. Tìm tác giả");
            System.out.println("7. Cái này cái j quên lun òi");
            System.out.println("8. Ra ngoài. Get outttttt. M chính thức bị ban");
            
            System.out.print("Chọn 1 đi cuuu");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    BookController.listAllBooks();
                    break;
                case "2":
                    BookController.createBook();
                    break;
                case "3":
                    BookController.updateBook();
                    break;
                case "4":
                    BookController.deleteBook();
                    break;
                case "5":
                    BookController.searchBooksByTitle();
                    break;
                case "6":
                    BookController.searchBooksByAuthor();
                    break;
                case "7":
                    BookController.loadSampleBooks();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Cáu rồi đấy, lại đi nhóc");
            }
        }
    }
    
    private static void showCustomerManagementMenu() {
        CustomerController.setScanner(scanner);
        while (true) {
            System.out.println("\n Chào mừng đến với không có gì");
            System.out.println("1. List all");
            System.out.println("2. sinh em bé");
            System.out.println("3. sửa người");
            System.out.println("4. ăn thịt người");
            System.out.println("5. Lùng, sục kiếm, người");
            System.out.println("6. Cái này cái j quên luôn r");
            System.out.println("7. Cook, không tiễn");
            
            System.out.print("Chọn 1 đi nỉ ga");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    CustomerController.listAllCustomers();
                    break;
                case "2":
                    CustomerController.createCustomer();
                    break;
                case "3":
                    CustomerController.updateCustomer();
                    break;
                case "4":
                    CustomerController.deleteCustomer();
                    break;
                case "5":
                    CustomerController.searchCustomersByName();
                    break;
                case "6":
                    CustomerController.loadSampleCustomers();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Láo nháo gõ u đầu bh, lại đi ");
            }
        }
    }
    
    private static void showOrderManagementMenu() {
        OrderController.setScanner(scanner);
        while (true) {
            System.out.println("\n=== ORDER MANAGEMENT MENU ===");
            System.out.println("1. List All Orders");
            System.out.println("2. Create New Order");
            System.out.println("3. Update Order Status");
            System.out.println("4. Delete Order");
            System.out.println("5. Search Orders by Customer");
            System.out.println("6. Search Orders by Status");
            System.out.println("7. Load Sample Orders");
            System.out.println("8. Back to Main Menu");
            
            System.out.print("Enter your choice (1-8): ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    OrderController.listAllOrders();
                    break;
                case "2":
                    OrderController.createOrder();
                    break;
                case "3":
                    OrderController.updateOrder();
                    break;
                case "4":
                    OrderController.deleteOrder();
                    break;
                case "5":
                    OrderController.searchOrdersByCustomer();
                    break;
                case "6":
                    OrderController.searchOrdersByStatus();
                    break;
                case "7":
                    OrderController.loadSampleOrders();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }
    
    private static void demonstrateQueueOperations() {
        System.out.println("\n=== QUEUE OPERATIONS DEMONSTRATION ===");
        System.out.println("Using Custom ArrayDeque implementation...");
        
        CustomArrayDeque<Order> orderQueue = new CustomArrayDeque<>(10);
        Order[] orders = OrderService.getAllOrders();
        int orderCount = OrderService.getOrderCount();
        
        if (orderCount > 0) {
            System.out.println("Adding orders to queue...");
            for (int i = 0; i < Math.min(3, orderCount); i++) {
                orderQueue.addLast(orders[i]);
                System.out.println("Added order: " + orders[i].getOrderNumber());
            }
            System.out.println("\nQueue status:");
            orderQueue.displayInfo();
            System.out.println("\nProcessing orders from queue...");
            while (!orderQueue.isEmpty()) {
                Order order = orderQueue.removeFirst();
                System.out.println("Processed order: " + order.getOrderNumber() + 
                                 " - Customer: " + order.getCustomer().getName());
            }
            System.out.println("Queue is now empty.");
        } else {
            System.out.println("No orders available for queue demonstration.");
        }
    }
    
    private static void demonstrateStackOperations() {
        System.out.println("\n=== STACK OPERATIONS DEMONSTRATION ===");
        System.out.println("Using BookStack implementation...");
        
        BookStack bookStack = new BookStack(10);
        Book[] books = BookService.getAllBooks();
        int bookCount = BookService.getBookCount();
        
        if (bookCount > 0) {
            System.out.println("Pushing books to stack...");
            for (int i = 0; i < Math.min(3, bookCount); i++) {
                bookStack.push(books[i]);
                System.out.println("Pushed book: " + books[i].getTitle());
            }
            System.out.println("\nStack status:");
            System.out.println("Stack size: " + bookStack.getSize());
            System.out.println("Is empty: " + bookStack.isEmpty());
            System.out.println("\nPopping books from stack...");
            while (!bookStack.isEmpty()) {
                Book book = bookStack.pop();
                System.out.println("Popped book: " + book.getTitle());
            }
            System.out.println("Stack is now empty.");
        } else {
            System.out.println("No books available for stack demonstration.");
        }
    }

    private static void loadSampleData() {
        System.out.println("Loading sample data...");
        BookService.loadSampleBooks();
        CustomerService.loadSampleCustomers();
        OrderService.loadSampleOrders();
        System.out.println("Sample data loaded successfully!");
    }
}