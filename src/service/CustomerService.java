package service;

import model.Customer;
import seed.CustomerSeedData;

// Service chứa business logic cho Customer (rút gọn)
public class CustomerService {
    private static final int MAX_CUSTOMERS = 100;
    private static Customer[] customers = new Customer[MAX_CUSTOMERS];
    private static int customerCount = 0;

    public static boolean addCustomer(Customer customer) {
        if (customer == null) return false;
        if (customerCount >= MAX_CUSTOMERS) return false;
        // Tránh trùng (name + shippingAddress)
        for (int i = 0; i < customerCount; i++) {
            if (equalsIgnoreCase(customers[i].getName(), customer.getName()) &&
                equalsIgnoreCase(customers[i].getShippingAddress(), customer.getShippingAddress())) {
                return false;
            }
        }
        customers[customerCount++] = customer;
        return true;
    }

    public static boolean updateCustomer(int index, Customer updated) {
        if (index < 0 || index >= customerCount || updated == null) return false;
        customers[index] = updated;
        return true;
    }

    public static boolean deleteCustomerByIndex(int index) {
        if (index < 0 || index >= customerCount) return false;
        for (int i = index; i < customerCount - 1; i++) customers[i] = customers[i + 1];
        customers[customerCount - 1] = null;
        customerCount--;
        return true;
    }

    public static Customer findCustomerByIndex(int index) {
        if (index < 0 || index >= customerCount) return null;
        return customers[index];
    }

    public static Customer[] getAllCustomers() {
        Customer[] result = new Customer[customerCount];
        for (int i = 0; i < customerCount; i++) result[i] = customers[i];
        return result;
    }

    public static int getCustomerCount() { return customerCount; }

    public static void loadSampleCustomers() {
        CustomerSeedData.loadSampleCustomers();
    }

    private static boolean equalsIgnoreCase(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(i);
            if (c1 >= 'A' && c1 <= 'Z') c1 = (char)(c1 + 32);
            if (c2 >= 'A' && c2 <= 'Z') c2 = (char)(c2 + 32);
            if (c1 != c2) return false;
        }
        return true;
    }
}
