package model;

/**
 * CẤU TRÚC DỮ LIỆU QUEUE (HÀNG ĐỢI) - TỰ XÂY DỰNG HOÀN TOÀN
 * 
 * GIỚI THIỆU:
 * Queue là một cấu trúc dữ liệu tuyến tính hoạt động theo nguyên tắc FIFO
 * (First In First Out - Vào trước ra trước). Phần tử được thêm vào đầu tiên
 * sẽ được lấy ra đầu tiên.
 * 
 * NGUYÊN LÝ HOẠT ĐỘNG:
 * - Front (đầu): Vị trí lấy phần tử ra (dequeue)
 * - Rear (cuối): Vị trí thêm phần tử vào (enqueue)
 * - Enqueue: Thêm phần tử vào cuối hàng đợi
 * - Dequeue: Lấy phần tử từ đầu hàng đợi
 * 
 * IMPLEMENTATION: CIRCULAR ARRAY (MẢNG VÒNG TRÒN)
 * Sử dụng mảng vòng tròn để tối ưu việc sử dụng bộ nhớ:
 * - Khi rear đạt cuối mảng, nó quay về đầu mảng
 * - Tránh lãng phí bộ nhớ khi dequeue nhiều phần tử
 * - Sử dụng phép chia lấy dư (%) để tính toán vị trí
 * 
 * ƯU ĐIỂM:
 * - Thao tác enqueue và dequeue có độ phức tạp O(1)
 * - Phù hợp cho các hệ thống cần xử lý tuần tự
 * - Tiết kiệm bộ nhớ với circular array
 * - Thread-safe nếu implement đúng
 * 
 * ỨNG DỤNG TRONG PROJECT:
 * - Quản lý hàng đợi đơn hàng chờ xử lý
 * - Xử lý đơn hàng theo thứ tự vào trước ra trước
 * - Buffer cho các tác vụ bất đồng bộ
 * 
 * TỰ XÂY DỰNG - KHÔNG SỬ DỤNG java.util.Queue
 */
public class OrderQueue {
    private Order[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    // Constructor
    public OrderQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Order[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    public OrderQueue() { this(100); }
    
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }
    public int getSize() { return size; }
    public int getCapacity() { return capacity; }
    
    /**
     * ENQUEUE - THÊM PHẦN Tử VÀO CUỐI HÀNG ĐỢI
     * 
     * MÔ TẢ:
     * Phương thức này thêm một đơn hàng vào cuối hàng đợi.
     * Sử dụng kỹ thuật Circular Array để tối ưu bộ nhớ.
     * 
     * THAM SỐ:
     * @param order - Đơn hàng cần thêm vào hàng đợi
     * @return boolean - true nếu thêm thành công, false nếu hàng đợi đầy
     * 
     * THUẬT TOÁN (CIRCULAR ARRAY):
     * 1. Kiểm tra hàng đợi có đầy không (size == capacity)
     * 2. Nếu đầy, trả về false và thông báo lỗi
     * 3. Tăng rear lên 1 với phép chia lấy dư: rear = (rear + 1) % capacity
     *    - Điều này đảm bảo rear quay về 0 khi đạt cuối mảng
     *    - Tạo thành mô hình mảng vòng tròn
     * 4. Đặt order vào vị trí rear mới
     * 5. Tăng size lên 1
     * 6. Hiển thị thông báo thành công
     * 
     * ĐỘ PHỨC TẠP: O(1) - Thời gian hằng số
     * 
     * VÍ DỤ: Nếu capacity=5, rear=4, phép tính (4+1)%5=0 sẽ đưa rear về đầu
     */
    public boolean enqueue(Order order) {
        // Bước 1: Kiểm tra hàng đợi có đầy không
        if (isFull()) {
            System.out.println("Queue is full! Cannot add order: " + order.getOrderNumber());
            return false;
        }
        
        // Bước 2-3: Cập nhật rear theo mô hình circular array
        rear = (rear + 1) % capacity; // Circular array: quay về 0 khi đạt cuối
        
        // Bước 4: Đặt order vào vị trí mới
        queue[rear] = order;
        
        // Bước 5: Tăng kích thước hàng đợi
        size++;
        
        // Bước 6: Thông báo thành công
        System.out.println("Order " + order.getOrderNumber() + " added to queue. Queue size: " + size);
        return true;
    }
    
    /**
     * DEQUEUE - LẤY PHẦN Tử TỪ ĐẦU HÀNG ĐỢI
     * 
     * MÔ TẢ:
     * Phương thức này lấy và loại bỏ đơn hàng đầu tiên ra khỏi hàng đợi.
     * Thực hiện nguyên tắc FIFO - phần tử vào trước sẽ ra trước.
     * 
     * @return Order - Đơn hàng được lấy ra, hoặc null nếu hàng đợi rỗng
     * 
     * THUẬT TOÁN (CIRCULAR ARRAY):
     * 1. Kiểm tra hàng đợi có rỗng không (size == 0)
     * 2. Nếu rỗng, trả về null và thông báo lỗi
     * 3. Lưu lại đơn hàng ở vị trí front để trả về
     * 4. Xóa tham chiếu tại vị trí front (giải phóng bộ nhớ)
     * 5. Di chuyển front tới vị trí tiếp theo: front = (front + 1) % capacity
     *    - Circular array: front quay về 0 khi đạt cuối mảng
     * 6. Giảm size xuống 1
     * 7. Hiển thị thông báo và trả về đơn hàng
     * 
     * ĐỘ PHỨC TẠP: O(1) - Thời gian hằng số
     * 
     * VÍ DỤ: Nếu capacity=5, front=4, phép tính (4+1)%5=0 sẽ đưa front về đầu
     */
    public Order dequeue() {
        // Bước 1: Kiểm tra hàng đợi có rỗng không
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue.");
            return null;
        }
        
        // Bước 2-3: Lưu lại đơn hàng cần trả về
        Order order = queue[front];
        
        // Bước 4: Xóa tham chiếu để giải phóng bộ nhớ (tránh memory leak)
        queue[front] = null;
        
        // Bước 5: Di chuyển front theo mô hình circular array
        front = (front + 1) % capacity; // Circular array: quay về 0 khi đạt cuối
        
        // Bước 6: Giảm kích thước hàng đợi
        size--;
        
        // Bước 7: Thông báo và trả về kết quả
        System.out.println("Order " + order.getOrderNumber() + " processed from queue. Queue size: " + size);
        return order;
    }
    
    public Order peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty! No order to peek.");
            return null;
        }
        return queue[front];
    }
    
    public Order findOrderByNumber(String orderNumber) {
        if (isEmpty()) return null;
        int current = front;
        for (int i = 0; i < size; i++) {
            if (equalsIgnoreCase(queue[current].getOrderNumber(), orderNumber)) return queue[current];
            current = (current + 1) % capacity;
        }
        return null;
    }
    
    public Order getOrderAt(int index) {
        if (index < 0 || index >= size) return null;
        int position = (front + index) % capacity;
        return queue[position];
    }
    
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.println("\n=== ORDER QUEUE (Front to Rear) ===");
        System.out.println("Queue Size: " + size + "/" + capacity);
        int current = front;
        for (int i = 0; i < size; i++) {
            Order order = queue[current];
            System.out.println((i + 1) + ". " + order.getOrderNumber() + 
                             " - Customer: " + (order.getCustomer() != null ? order.getCustomer().getName() : "Unknown") + 
                             " - Status: " + order.getStatus() +
                             " - Items: " + order.getItemCount());
            current = (current + 1) % capacity;
        }
    }
    
    public Order[] getAllOrders() {
        if (isEmpty()) return new Order[0];
        Order[] orders = new Order[size];
        int current = front;
        for (int i = 0; i < size; i++) {
            orders[i] = queue[current];
            current = (current + 1) % capacity;
        }
        return orders;
    }
    
    public void clear() {
        while (!isEmpty()) dequeue();
        front = 0;
        rear = -1;
        size = 0;
        System.out.println("Queue cleared!");
    }
    
    public boolean contains(String orderNumber) { return findOrderByNumber(orderNumber) != null; }
    
    public String getQueueStats() {
        String utilization = formatDouble((size * 100.0 / capacity), 2);
        return "Queue Statistics:\n" +
               "- Size: " + size + "/" + capacity + "\n" +
               "- Utilization: " + utilization + "%\n" +
               "- Is Full: " + isFull() + "\n" +
               "- Is Empty: " + isEmpty();
    }
    
    public boolean processNextOrder() {
        Order order = dequeue();
        if (order != null) {
            System.out.println("Processing completed for order: " + order.getOrderNumber() + 
                             " - Status: " + order.getStatus());
            return true;
        }
        return false;
    }
    
    public void processAllOrders() {
        System.out.println("Processing all orders in queue...");
        int processed = 0;
        while (!isEmpty()) if (processNextOrder()) processed++;
        System.out.println("Total orders processed: " + processed);
    }
    
    private String formatDouble(double value, int decimals) {
        long factor = 1;
        for (int i = 0; i < decimals; i++) factor *= 10;
        long rounded = (long) (value * factor + 0.5);
        long integerPart = rounded / factor;
        long fractionPart = rounded % factor;
        StringBuilder sb = new StringBuilder();
        sb.append(integerPart);
        if (decimals > 0) {
            sb.append('.') ;
            String frac = String.valueOf(fractionPart);
            while (frac.length() < decimals) frac = "0" + frac;
            sb.append(frac);
        }
        return sb.toString();
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
}