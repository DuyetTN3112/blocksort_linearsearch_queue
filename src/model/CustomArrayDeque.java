package model;

/**
 * CẤU TRÚC DỮ LIỆU ARRAYDEQUE (HÀNG ĐỢI HAI ĐẦU) - TỰ XÂY DỰNG HOÀN TOÀN
 * 
 * GIỚI THIỆU:
 * ArrayDeque (Double-ended Queue) là cấu trúc dữ liệu cho phép thêm và xóa
 * phần tử ở cả hai đầu (đầu và cuối) một cách hiệu quả. Nó kết hợp
 * ưu điểm của cả Stack và Queue.
 * 
 * NGUYÊN LÝ HOẠT ĐỘNG:
 * - Có thể thêm/xóa phần tử ở đầu (head) hoặc cuối (tail)
 * - Sử dụng mảng vòng tròn (circular array) để tối ưu hiệu suất
 * - Head và tail có thể di chuyển theo cả hai hướng
 * - Tự động mở rộng kích thước khi cần thiết
 * 
 * CÁC THAO TÁC CHÍNH:
 * - addFirst(T): Thêm vào đầu
 * - addLast(T): Thêm vào cuối  
 * - removeFirst(): Lấy từ đầu
 * - removeLast(): Lấy từ cuối
 * - getFirst(): Xem phần tử đầu
 * - getLast(): Xem phần tử cuối
 * 
 * ƯU ĐIỂM:
 * - Thao tác ở cả hai đầu có độ phức tạp O(1)
 * - Linh hoạt hơn Stack và Queue thông thường
 * - Sử dụng bộ nhớ hiệu quả với circular array
 * - Có thể mở rộng động khi cần
 * 
 * ỨNG DỤNG:
 * - Implement Stack (sử dụng addFirst/removeFirst)
 * - Implement Queue (sử dụng addLast/removeFirst)
 * - Sliding Window algorithms
 * - Undo/Redo functionality
 * - BFS và DFS trong graph algorithms
 * 
 * IMPLEMENTATION TỰ BUILD - KHÔNG SỬ DỤNG java.util.ArrayDeque
 */
public class CustomArrayDeque<T> {
    private T[] elements;
    private int head;
    private int tail;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    
    @SuppressWarnings("unchecked")
    public CustomArrayDeque() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    
    @SuppressWarnings("unchecked")
    public CustomArrayDeque(int initialCapacity) {
        this.elements = (T[]) new Object[initialCapacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    
    // Thêm phần tử vào đầu
    public void addFirst(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        
        ensureCapacity();
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = element;
        size++;
    }
    
    // Thêm phần tử vào cuối
    public void addLast(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }
    
    // Lấy và xóa phần tử đầu
    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        
        T element = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        
        return element;
    }
    
    // Lấy và xóa phần tử cuối
    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        
        tail = (tail - 1 + elements.length) % elements.length;
        T element = elements[tail];
        elements[tail] = null;
        size--;
        
        return element;
    }
    
    // Lấy phần tử đầu (không xóa)
    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return elements[head];
    }
    
    // Lấy phần tử cuối (không xóa)
    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return elements[(tail - 1 + elements.length) % elements.length];
    }
    
    // Kiểm tra deque có rỗng không
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Lấy kích thước deque
    public int size() {
        return size;
    }
    
    // Xóa tất cả phần tử
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        head = 0;
        tail = 0;
        size = 0;
    }
    
    // Kiểm tra xem có chứa phần tử không
    public boolean contains(T element) {
        if (element == null) {
            return false;
        }
        
        for (int i = 0; i < size; i++) {
            int index = (head + i) % elements.length;
            if (element.equals(elements[index])) {
                return true;
            }
        }
        return false;
    }
    
    // Chuyển đổi thành mảng
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = elements[(head + i) % elements.length];
        }
        return result;
    }
    
    // Đảm bảo đủ dung lượng
    private void ensureCapacity() {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
    }
    
    // Thay đổi kích thước mảng
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newElements = (T[]) new Object[newCapacity];
        
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(head + i) % elements.length];
        }
        
        elements = newElements;
        head = 0;
        tail = size;
    }
    
    // Hiển thị thông tin deque
    public void displayInfo() {
        System.out.println("Deque Info:");
        System.out.println("Size: " + size);
        System.out.println("Capacity: " + elements.length);
        System.out.println("Head: " + head);
        System.out.println("Tail: " + tail);
        System.out.println("Is Empty: " + isEmpty());
        
        if (!isEmpty()) {
            System.out.println("Elements:");
            for (int i = 0; i < size; i++) {
                int index = (head + i) % elements.length;
                System.out.println("  [" + index + "]: " + elements[index]);
            }
        }
    }
    
    // Phương thức toString
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i = 0; i < size; i++) {
            int index = (head + i) % elements.length;
            sb.append(elements[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        
        sb.append("]");
        return sb.toString();
    }
}
