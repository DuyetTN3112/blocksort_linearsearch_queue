package model;

// Model khách hàng với ID để quản lý đơn giản hơn
public class Customer {
    private static int nextId = 1; // Bộ đếm ID tự động tăng
    private int id;                // ID duy nhất cho mỗi khách hàng
    private String name;           // customer name
    private String shippingAddress; // shipping address

    public Customer(String name, String shippingAddress) {
        this.id = nextId++; // Tự động sinh ID
        this.name = name;
        this.shippingAddress = shippingAddress;
    }

    public Customer() {
        this.id = nextId++; // Tự động sinh ID
        this.name = "";
        this.shippingAddress = "";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", shippingAddress='" + shippingAddress + '\'' +
               '}';
    }

    public Customer copy() {
        return new Customer(name, shippingAddress);
    }
}
