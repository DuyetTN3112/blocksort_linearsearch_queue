package seed;

import model.Book;
import service.BookService;

public class BookSeedData {
    
    public static Book[] getSampleBooks() {
        return new Book[] {
            // Văn học Việt Nam kinh điển
            new Book("Số Đỏ", "Vũ Trọng Phụng"),
            new Book("Tắt Đèn", "Ngô Tất Tố"),
            new Book("Dế Mèn Phiêu Lưu Ký", "Tô Hoài"),
            new Book("Chí Phèo", "Nam Cao"),
            new Book("Làng", "Kim Lân"),
            
            // Văn học hiện đại Việt Nam
            new Book("Lão Hạc", "Nam Cao"),
            new Book("Vợ Nhặt", "Kim Lân"),
            new Book("Những Đứa Con Trong Gia Đình", "Nguyễn Thi"),
            new Book("Người Lái Đò Sông Đà", "Nguyễn Tuân"),
            new Book("Tôi Thấy Hoa Vàng Trên Cỏ Xanh", "Nguyễn Nhật Ánh"),
            
            // Sách khoa học - kỹ thuật phổ biến tại VN
            new Book("Lập Trình Java Cơ Bản", "Nguyễn Văn Hiếu"),
            new Book("Cơ Sở Dữ Liệu", "Trần Minh Quang"),
            new Book("Thuật Toán Và Cấu Trúc Dữ Liệu", "Lê Minh Hoàng"),
            new Book("Lập Trình Web với PHP", "Phạm Huy Hoàng"),
            new Book("Mạng Máy Tính", "Võ Thanh Tù"),
            
            // Sách kinh tế - quản lý
            new Book("Quản Trị Kinh Doanh", "Nguyễn Khánh Trung"),
            new Book("Marketing Căn Bản", "Trương Đình Chiến"),
            new Book("Kế Toán Tài Chính", "Nguyễn Thị Phương"),
            new Book("Kinh Tế Vi Mô", "Trần Thọ Đạt"),
            new Book("Đầu Tư Chứng Khoán", "Lê Đạt Chí"),
            
            // Sách phát triển bản thân
            new Book("Đắc Nhân Tâm", "Dale Carnegie"),
            new Book("Nghĩ Giàu Và Làm Giàu", "Napoleon Hill"),
            new Book("7 Thói Quen Hiệu Quả", "Stephen Covey"),
            new Book("Quẳng Gánh Lo Đi Và Vui Sống", "Dale Carnegie"),
            new Book("Tôi Tài Giỏi, Bạn Cũng Thế", "Adam Khoo")
        };
    }
    
    public static void loadSampleBooks() {
        Book[] books = getSampleBooks();
        for (Book book : books) {
            BookService.addBook(book);
        }
    }
}
