package seed;

import model.Book;
import model.Customer;
import model.Order;
import service.BookService;
import service.CustomerService;
import service.OrderService;

public class OrderSeedData {
    
    public static void loadSampleOrders() {
        // Đảm bảo có customers và books trước
        if (CustomerService.getCustomerCount() == 0) {
            CustomerSeedData.loadSampleCustomers();
        }
        if (BookService.getBookCount() == 0) {
            BookSeedData.loadSampleBooks();
        }
        
        Customer[] customers = CustomerService.getAllCustomers();
        Book[] books = BookService.getAllBooks();
        
        if (customers.length > 0 && books.length > 0) {
            // Tạo order 1
            Order order1 = new Order(OrderService.generateNewOrderNumber(), customers[0]);
            order1.addItem(books[0], 2);
            order1.addItem(books[2], 1);
            OrderService.addOrder(order1);
            
            // Tạo order 2
            Order order2 = new Order(OrderService.generateNewOrderNumber(), customers[1]);
            order2.addItem(books[1], 3);
            order2.addItem(books[4], 1);
            OrderService.addOrder(order2);
            
            // Tạo order 3
            Order order3 = new Order(OrderService.generateNewOrderNumber(), customers[2]);
            order3.addItem(books[3], 1);
            order3.addItem(books[5], 2);
            order3.addItem(books[7], 1);
            OrderService.addOrder(order3);
            
            // Tạo order 4
            Order order4 = new Order(OrderService.generateNewOrderNumber(), customers[3]);
            order4.addItem(books[6], 1);
            OrderService.addOrder(order4);
            
            // Tạo order 5
            Order order5 = new Order(OrderService.generateNewOrderNumber(), customers[4]);
            order5.addItem(books[8], 2);
            order5.addItem(books[9], 1);
            OrderService.addOrder(order5);
        }
    }
}
