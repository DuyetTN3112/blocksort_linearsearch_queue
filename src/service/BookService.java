package service;

import model.Book;
import seed.BookSeedData;

// Service chứa business logic cho Book (rút gọn)
public class BookService {
    private static final int MAX_BOOKS = 100;
    private static Book[] books = new Book[MAX_BOOKS];
    private static int bookCount = 0;

    public static boolean addBook(Book book) {
        if (book == null) return false;
        if (bookCount >= MAX_BOOKS) return false;
        // Tránh trùng (title+author)
        for (int i = 0; i < bookCount; i++) {
            if (equalsIgnoreCase(books[i].getTitle(), book.getTitle()) &&
                equalsIgnoreCase(books[i].getAuthor(), book.getAuthor())) {
                return false;
            }
        }
        books[bookCount++] = book;
        return true;
    }

    // [ĐÃ XÓA] - Chỉ sử dụng ID, không còn index
    
    // Cập nhật sách theo ID (đơn giản hơn)
    public static boolean updateBookById(int id, String newTitle, String newAuthor) {
        Book book = findBookById(id);
        if (book == null) return false;
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        return true;
    }
    
    // Xóa sách theo ID (đơn giản hơn)
    public static boolean deleteBookById(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == id) {
                return deleteBookByIndexHelper(i);
            }
        }
        return false;
    }
    
    // Helper method private để xóa theo index
    private static boolean deleteBookByIndexHelper(int index) {
        if (index < 0 || index >= bookCount) return false;
        for (int i = index; i < bookCount - 1; i++) books[i] = books[i + 1];
        books[bookCount - 1] = null;
        bookCount--;
        return true;
    }

    // Tìm sách theo index (giữ lại cho tương thích)
    public static Book findBookByIndex(int index) {
        if (index < 0 || index >= bookCount) return null;
        return books[index];
    }
    
    // Tìm sách theo ID (cách mới, đơn giản hơn)
    public static Book findBookById(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == id) {
                return books[i];
            }
        }
        return null;
    }

    public static Book[] getAllBooks() {
        Book[] result = new Book[bookCount];
        for (int i = 0; i < bookCount; i++) result[i] = books[i];
        return result;
    }

    public static int getBookCount() { return bookCount; }

    public static void loadSampleBooks() {
        BookSeedData.loadSampleBooks();
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
