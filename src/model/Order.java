package model;

// Model đơn hàng (rút gọn theo đề bài)
public class Order {
    public static class BookItem {
        private Book book;      // title, author
        private int quantity;   // số lượng

        public BookItem(Book book, int quantity) {
            this.book = book;
            this.quantity = quantity;
        }

        public Book getBook() { return book; }
        public int getQuantity() { return quantity; }
        public void setBook(Book book) { this.book = book; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    private String orderNumber;   // order number
    private Customer customer;    // name, shippingAddress
    private BookItem[] items;     // danh sách sách với số lượng
    private int itemCount;
    private static final int MAX_ITEMS = 20;
    private String status;        // current status

    public Order(String orderNumber, Customer customer) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.items = new BookItem[MAX_ITEMS];
        this.itemCount = 0;
        this.status = "PENDING";
    }

    public Order() {
        this("", null);
    }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BookItem[] getItems() {
        BookItem[] result = new BookItem[itemCount];
        for (int i = 0; i < itemCount; i++) result[i] = items[i];
        return result;
    }
    public int getItemCount() { return itemCount; }

    public boolean addItem(Book book, int quantity) {
        if (book == null || quantity <= 0) return false;
        // gộp nếu cùng sách (so sánh theo title+author)
        for (int i = 0; i < itemCount; i++) {
            BookItem bi = items[i];
            if (equalsIgnoreCase(bi.book.getTitle(), book.getTitle()) &&
                equalsIgnoreCase(bi.book.getAuthor(), book.getAuthor())) {
                bi.quantity += quantity;
                return true;
            }
        }
        if (itemCount >= MAX_ITEMS) return false;
        items[itemCount++] = new BookItem(book, quantity);
        return true;
    }

    public boolean removeItemByIndex(int index) {
        if (index < 0 || index >= itemCount) return false;
        for (int j = index; j < itemCount - 1; j++) items[j] = items[j + 1];
        items[itemCount - 1] = null;
        itemCount--;
        return true;
    }

    private boolean equalsIgnoreCase(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(i);
            if (c1 >= 'A' && c1 <= 'Z') c1 = (char) (c1 + 32);
            if (c2 >= 'A' && c2 <= 'Z') c2 = (char) (c2 + 32);
            if (c1 != c2) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" +
               "orderNumber='" + orderNumber + '\'' +
               ", customer=" + (customer != null ? customer.getName() : "null") +
               ", status='" + status + '\'' +
               ", items=" + itemCount +
               '}';
    }
}
