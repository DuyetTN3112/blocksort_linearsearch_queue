package controller;

import java.util.Scanner;
import model.Book;
import service.BookSearchService;
import service.BookService;
import view.BookView;

// Controller điều phối luồng xử lý cho Book (rút gọn)
public class BookController {
    private static Scanner scanner;
    
    public static void setScanner(Scanner scanner) {
        BookController.scanner = scanner;
    }
    
    public static void createBook() {
        if (BookService.getBookCount() >= 50) {
            BookView.displayMessage("Hết slot rùi. Ko đút thêm đc nữa đâu");
            return;
        }
        BookView.displayCreateBookHeader();
        String title = BookView.getBookTitle(scanner);
        String author = BookView.getBookAuthor(scanner);
        Book newBook = new Book(title, author);
        if (BookService.addBook(newBook)) {
            BookView.displayBookCreated(newBook);
        } else {
            BookView.displayMessage("Ko thêm đc đou, chắc là có lỗi j đấy, thử lại xiem");
        }
    }
    
    // Cập nhật sách theo ID - Đơn giản và dễ sử dụng
    public static void updateBook() {
        if (BookService.getBookCount() == 0) {
            BookView.displayMessage("Chả có j mới cả");
            return;
        }
        BookView.displayUpdateBookHeader();
        BookView.displayAllBooks(BookService.getAllBooks());
        
        int id = BookView.getBookId(scanner);
        Book existing = BookService.findBookById(id);
        if (existing == null) {
            BookView.displayMessage("Khum thấy sách đâu bạn ơiii");
            return;
        }
        
        BookView.displayBookToUpdate(existing);
        String newTitle = BookView.getNewBookTitle(scanner, existing.getTitle());
        String newAuthor = BookView.getNewBookAuthor(scanner, existing.getAuthor());
        
        if (BookService.updateBookById(id, newTitle, newAuthor)) {
            BookView.displayBookUpdated(existing); // existing đã được cập nhật
        } else {
            BookView.displayMessage("Lỗi rùi bạn ơiii, tớ cx kobt lỗi j");
        }
    }
    
    // Xóa sách theo ID - Đơn giản và dễ sử dụng
    public static void deleteBook() {
        if (BookService.getBookCount() == 0) {
            BookView.displayMessage("Làm đíu có j mà đòi xóa");
            return;
        }
        BookView.displayDeleteBookHeader();
        BookView.displayAllBooks(BookService.getAllBooks());
        
        int id = BookView.getBookId(scanner);
        Book bookToDelete = BookService.findBookById(id);
        if (bookToDelete == null) {
            BookView.displayMessage("Lỗi rùi, nhm ko pahir lỗi tui đâu");
            return;
        }
        
        BookView.displayBookToDelete(bookToDelete);
        boolean confirm = BookView.confirmDeletion(scanner);
        if (confirm) {
            if (BookService.deleteBookById(id)) {
                BookView.displayBookDeleted();
            } else {
                BookView.displayMessage("Hong be ơi, ko phải lỗi của tui, app lỏ ló thế");
            }
        } else {
            BookView.displayMessage("Khum đc đâu pé ơiii");
        }
    }
    
    public static void listAllBooks() {
        Book[] books = BookService.getAllBooks();
        if (books.length == 0) BookView.displayMessage("Hong be oi , ko có sách nào");
        else BookView.displayAllBooks(books);
    }
    
    public static void searchBooksByTitle() {
        String title = BookView.getSearchTitle(scanner);
        Book[] results = BookSearchService.searchBooksByTitle(title);
        if (results.length == 0) BookView.displayMessage("Chả thấy sách nào có tên như này đouuu: " + title);
        else BookView.displaySearchResults(results, "title", title);
    }
    
    public static void searchBooksByAuthor() {
        String author = BookView.getSearchAuthor(scanner);
        Book[] results = BookSearchService.searchBooksByAuthor(author);
        if (results.length == 0) BookView.displayMessage("Có chắc là tác giả tên như thế này ko: " + author);
        else BookView.displaySearchResults(results, "author", author);
    }
    
    public static void loadSampleBooks() {
        BookService.loadSampleBooks();
        BookView.displayMessage("Chắc là ko tạch đâuuu");
    }
}
