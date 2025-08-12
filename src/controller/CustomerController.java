package controller;

import java.util.Scanner;
import model.Customer;
import service.CustomerService;
import view.CustomerView;

// Controller điều phối luồng xử lý cho Customer (rút gọn)
public class CustomerController {
    private static Scanner scanner;
    
    public static void setScanner(Scanner scanner) {
        CustomerController.scanner = scanner;
    }
    
    public static void createCustomer() {
        if (CustomerService.getCustomerCount() >= 50) {
            CustomerView.displayMessage("Ko bt gi j ahihihi");
            return;
        }
        CustomerView.displayCreateCustomerHeader();
        String name = CustomerView.getCustomerName(scanner);
        String address = CustomerView.getCustomerAddress(scanner);
        Customer newCustomer = new Customer(name, address);
        if (CustomerService.addCustomer(newCustomer)) {
            CustomerView.displayCustomerCreated(newCustomer);
        } else {
            CustomerView.displayMessage("Khum tạo đc");
        }
    }
    
    public static void updateCustomer() {
        if (CustomerService.getCustomerCount() == 0) {
            CustomerView.displayMessage("Khum sửa đc lun");
            return;
        }
        CustomerView.displayUpdateCustomerHeader();
        CustomerView.displayAllCustomers(CustomerService.getAllCustomers());
        int index = CustomerView.getCustomerIndex(scanner, CustomerService.getCustomerCount());
        if (index == -1) return;
        Customer existing = CustomerService.findCustomerByIndex(index);
        if (existing == null) {
            CustomerView.displayMessage("Đố bt lỗi j");
            return;
        }
        CustomerView.displayCustomerToUpdate(existing);
        String newName = CustomerView.getNewCustomerName(scanner, existing.getName());
        String newAddress = CustomerView.getNewCustomerAddress(scanner, existing.getShippingAddress());
        Customer updated = new Customer(newName, newAddress);
        if (CustomerService.updateCustomer(index, updated)) {
            CustomerView.displayCustomerUpdated(updated);
        } else {
            CustomerView.displayMessage("Đố bt lỗi j");
        }
    }
    
    public static void deleteCustomer() {
        if (CustomerService.getCustomerCount() == 0) {
            CustomerView.displayMessage("Lại đây tớ nói nhỏ cho lỗi là gì nhé");
            return;
        }
        CustomerView.displayDeleteCustomerHeader();
        CustomerView.displayAllCustomers(CustomerService.getAllCustomers());
        int index = CustomerView.getCustomerIndex(scanner, CustomerService.getCustomerCount());
        if (index == -1) return;
        Customer toDelete = CustomerService.findCustomerByIndex(index);
        if (toDelete == null) {
            CustomerView.displayMessage("Yêu tớ ko, yêu thì tớ lói lỗi");
            return;
        }
        CustomerView.displayCustomerToDelete(toDelete);
        boolean confirm = CustomerView.confirmDeletion(scanner);
        if (confirm) {
            if (CustomerService.deleteCustomerByIndex(index)) {
                CustomerView.displayCustomerDeleted();
            } else {
                CustomerView.displayMessage("Đau đầu quá lỗi j zậy");
            }
        } else {
            CustomerView.displayMessage("Nay mệt òi, ngủ đi thui");
        }
    }
    
    public static void listAllCustomers() {
        Customer[] customers = CustomerService.getAllCustomers();
        if (customers.length == 0) CustomerView.displayMessage("Ngất đây, chán quá rùi");
        else CustomerView.displayAllCustomers(customers);
    }
    
    public static void searchCustomersByName() {
        String name = CustomerView.getSearchName(scanner);
        // Tìm kiếm tuyến tính đơn giản trong service rút gọn
        Customer[] base = CustomerService.getAllCustomers();
        int n = CustomerService.getCustomerCount();
        Customer[] results = new Customer[n];
        int resultCount = 0;
        String needle = toLowerCase(name);
        for (int i = 0; i < n; i++) {
            if (toLowerCase(base[i].getName()).contains(needle)) results[resultCount++] = base[i];
        }
        Customer[] finalResults = new Customer[resultCount];
        for (int i = 0; i < resultCount; i++) finalResults[i] = results[i];
        CustomerView.displaySearchResults(finalResults, "name", name);
    }
    
    public static void loadSampleCustomers() {
        CustomerService.loadSampleCustomers();
        CustomerView.displayMessage("Tớ yêu cậu ");
    }

    private static String toLowerCase(String s) {
        if (s == null) return null;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) if (c[i] >= 'A' && c[i] <= 'Z') c[i] = (char)(c[i] + 32);
        return new String(c);
    }
}
