package model;

// Model sách với ID để quản lý đơn giản hơn
public class Book {
    private static int nextId = 1; // Bộ đếm ID tự động tăng
    private int id;                // ID duy nhất cho mỗi sách
    private String title;          // book title
    private String author;         // author name

    public Book(String title, String author) {
        this.id = nextId++; // Tự động sinh ID
        this.title = title;
        this.author = author;
    }

    public Book() {
        this.id = nextId++; // Tự động sinh ID
        this.title = "";
        this.author = "";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book copy() {
        return new Book(title, author);
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               '}';
    }
}
