package service;

import model.Customer;
import model.Order;
import seed.OrderSeedData;

// Service chứa business logic cho Order (rút gọn)
public class OrderService {
    private static final int MAX_ORDERS = 200;
    private static Order[] allOrders = new Order[MAX_ORDERS];
    private static int orderCount = 0;
    private static int nextOrderSeq = 1;

    public static boolean createOrder(Order order) {
        if (order == null) return false;
        if (orderCount >= MAX_ORDERS) return false;
        if (findOrderByNumber(order.getOrderNumber()) != null) return false;
        if (order.getCustomer() == null) return false;
        allOrders[orderCount++] = order;
        return true;
    }

    public static boolean addOrder(Order order) { return createOrder(order); }

    public static boolean updateOrder(int index, Order updatedOrder) {
        if (index < 0 || index >= orderCount || updatedOrder == null) return false;
        allOrders[index] = updatedOrder;
        return true;
    }

    public static boolean updateOrderStatus(String orderNumber, String newStatus) {
        Order o = findOrderByNumber(orderNumber);
        if (o == null) return false;
        o.setStatus(newStatus);
        return true;
    }

    public static boolean deleteOrderByIndex(int index) {
        if (index < 0 || index >= orderCount) return false;
        for (int i = index; i < orderCount - 1; i++) allOrders[i] = allOrders[i + 1];
        allOrders[orderCount - 1] = null;
        orderCount--;
        return true;
    }

    public static Order findOrderByNumber(String orderNumber) {
        if (orderNumber == null) return null;
        for (int i = 0; i < orderCount; i++) {
            if (equalsIgnoreCase(allOrders[i].getOrderNumber(), orderNumber)) return allOrders[i];
        }
        return null;
    }

    public static Order findOrderByIndex(int index) {
        if (index < 0 || index >= orderCount) return null;
        return allOrders[index];
    }

    public static Order[] getAllOrders() {
        Order[] result = new Order[orderCount];
        for (int i = 0; i < orderCount; i++) result[i] = allOrders[i];
        return result;
    }

    public static int getOrderCount() { return orderCount; }

    public static String generateNewOrderNumber() {
        // ORD-001 style
        String seq = padLeftZeros(nextOrderSeq++, 3);
        return "ORD-" + seq;
    }

    // Search helpers
    public static Order searchOrderByNumber(String orderNumber) {
        return findOrderByNumber(orderNumber);
    }

    public static Order[] searchOrdersByCustomerName(String name) {
        if (name == null || name.trim().isEmpty()) return new Order[0];
        Order[] results = new Order[orderCount];
        int resultCount = 0;
        String needle = toLowerCase(name);
        for (int i = 0; i < orderCount; i++) {
            Customer c = allOrders[i].getCustomer();
            if (c != null && toLowerCase(c.getName()).contains(needle)) {
                results[resultCount++] = allOrders[i];
            }
        }
        Order[] finalResults = new Order[resultCount];
        for (int i = 0; i < resultCount; i++) finalResults[i] = results[i];
        return finalResults;
    }

    public static Order[] searchOrdersByStatus(String status) {
        if (status == null || status.trim().isEmpty()) return new Order[0];
        Order[] results = new Order[orderCount];
        int resultCount = 0;
        String needle = toLowerCase(status);
        for (int i = 0; i < orderCount; i++) {
            if (toLowerCase(allOrders[i].getStatus()).contains(needle)) results[resultCount++] = allOrders[i];
        }
        Order[] finalResults = new Order[resultCount];
        for (int i = 0; i < resultCount; i++) finalResults[i] = results[i];
        return finalResults;
    }

    // Sample data
    public static void loadSampleOrders() {
        OrderSeedData.loadSampleOrders();
    }

    // Utils (local)
    private static String toLowerCase(String s) {
        if (s == null) return null;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) if (c[i] >= 'A' && c[i] <= 'Z') c[i] = (char)(c[i] + 32);
        return new String(c);
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

    private static String padLeftZeros(int number, int width) {
        String s = String.valueOf(number);
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < width; i++) sb.append('0');
        sb.append(s);
        return sb.toString();
    }
}
