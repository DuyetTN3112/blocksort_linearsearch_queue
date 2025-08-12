package model;

//  hình như đề bảo là chỉ demo. idk maybe 
public class BookStack {
    private Book[] stack;
    private int top;
    private int capacity;
    
    // Constructor
    public BookStack(int capacity) {
        this.capacity = capacity;
        this.stack = new Book[capacity];
        this.top = -1;
    }
    
    // Default constructor with capacity of 50
    public BookStack() {
        this(50);
    }
    
    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }
    
    // Check if stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }
    
    // Get current size of stack
    public int getSize() {
        return top + 1;
    }
    
    // Get capacity of stack
    public int getCapacity() {
        return capacity;
    }
    
    // Push operation - Add book to top of stack
    public boolean push(Book book) {
        if (isFull()) {
            System.out.println("Stack is full! Cannot add book: " + book.getTitle());
            return false;
        }
        stack[++top] = book;
        System.out.println("Book '" + book.getTitle() + "' pushed to stack. Stack size: " + getSize());
        return true;
    }
    
    // Pop operation - Remove and return book from top of stack
    public Book pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Cannot pop.");
            return null;
        }
        Book book = stack[top];
        stack[top] = null;
        top--;
        System.out.println("Book '" + book.getTitle() + "' popped from stack. Stack size: " + getSize());
        return book;
    }
    
    // Peek operation - View book at top without removing
    public Book peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! No book to peek.");
            return null;
        }
        return stack[top];
    }
    
    // Search for book by title in the stack
    public Book findBookByTitle(String title) {
        if (isEmpty()) return null;
        for (int i = top; i >= 0; i--) {
            if (equalsIgnoreCase(stack[i].getTitle(), title)) return stack[i];
        }
        return null;
    }
    
    // Get book at specific position (0-based index from top)
    public Book getBookAt(int index) {
        if (index < 0 || index > top) return null;
        return stack[top - index];
    }
    
    // Display all books in stack
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.println("\n=== BOOK STACK (Top to Bottom) ===");
        System.out.println("Stack Size: " + getSize() + "/" + capacity);
        for (int i = top; i >= 0; i--) {
            Book book = stack[i];
            System.out.println((top - i + 1) + ". " + book.getTitle() + 
                             " by " + book.getAuthor());
        }
    }
    
    // Get all books as array (from top to bottom)
    public Book[] getAllBooks() {
        if (isEmpty()) return new Book[0];
        Book[] books = new Book[getSize()];
        for (int i = 0; i < getSize(); i++) books[i] = stack[top - i];
        return books;
    }
    
    // Clear all books from stack
    public void clear() {
        while (!isEmpty()) pop();
        top = -1;
        System.out.println("Stack cleared!");
    }
    
    // Check if stack contains a specific book by title
    public boolean contains(String title) {
        return findBookByTitle(title) != null;
    }
    
    // Get stack statistics (avoid String.format)
    public String getStackStats() {
        String utilization = formatDouble((getSize() * 100.0 / capacity), 2);
        return "Stack Statistics:\n" +
               "- Size: " + getSize() + "/" + capacity + "\n" +
               "- Utilization: " + utilization + "%\n" +
               "- Is Full: " + isFull() + "\n" +
               "- Is Empty: " + isEmpty();
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