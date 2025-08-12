package service;

import model.Order;

public class OrderSearchService {
    public static Order searchOrderById(String orderNumber) {
        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            return null;
        }
        return OrderService.searchOrderByNumber(orderNumber);
    }

    public static Order[] searchOrdersByCustomerName(String name) {
        return OrderService.searchOrdersByCustomerName(name);
    }

    public static Order[] searchOrdersByStatus(String status) {
        return OrderService.searchOrdersByStatus(status);
    }
}
