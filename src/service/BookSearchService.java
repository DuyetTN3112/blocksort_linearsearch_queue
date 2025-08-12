package service;

import model.Book;

/**
 * THUẬT TOÁN LINEAR SEARCH (TÌM KIẾM TUYẾN TÍNH) - TỰ XÂY DỰNG HOÀN TOÀN
 * 
 * GIỚI THIỆU:
 * Linear Search là thuật toán tìm kiếm đơn giản nhất, duyệt từng phần tử
 * trong mảng một cách tuần tự từ đầu đến cuối để tìm phần tử mong muốn.
 * 
 * NGUYÊN LÝ HOẠT ĐỘNG:
 * 1. Bắt đầu từ phần tử đầu tiên của mảng
 * 2. So sánh từng phần tử với giá trị cần tìm
 * 3. Nếu tìm thấy, trả về vị trí hoặc phần tử đó
 * 4. Nếu duyệt hết mảng mà không tìm thấy, trả về kết quả "không tìm thấy"
 * 
 * ƯU ĐIỂM:
 * - Đơn giản, dễ hiểu và dễ cài đặt
 * - Không yêu cầu dữ liệu phải được sắp xếp trước
 * - Hoạt động tốt với mảng nhỏ
 * - Độ phức tạp không gian: O(1) - chỉ cần bộ nhớ hằng số
 * 
 * NHƯỢC ĐIỂM:
 * - Độ phức tạp thời gian: O(n) - trong trường hợp xấu nhất phải duyệt hết mảng
 * - Không hiệu quả với dữ liệu lớn
 * - Không tận dụng được tính chất sắp xếp của dữ liệu (nếu có)
 * 
 * ỨNG DỤNG TRONG PROJECT:
 * - Tìm kiếm sách theo tiêu đề (có thể tìm một phần)
 * - Tìm kiếm sách theo tác giả (có thể tìm một phần)
 * - Tìm kiếm đơn hàng theo mã số, tên khách hàng, trạng thái
 * 
 * IMPLEMENTATION TỰ BUILD - KHÔNG SỬ DỤNG THỦ VIỆN CÓ SẴN
 */
public class BookSearchService {
    /**
     * TÌM KIẾM SÁCH THEO TIÊU ĐỀ - LINEAR SEARCH ALGORITHM
     * 
     * MÔ TẢ:
     * Phương thức này sử dụng thuật toán Linear Search để tìm tất cả các cuốn sách
     * có tiêu đề chứa cụm từ khóa người dùng nhập vào (tìm kiếm mờ - partial match).
     * 
     * THAM SỐ:
     * @param title - Cụm từ khóa cần tìm trong tiêu đề sách
     * @return Book[] - Mảng chứa tất cả sách phù hợp
     * 
     * CÁC BƯỚC THỰC HIỆN (LINEAR SEARCH ALGORITHM):
     * 1. Kiểm tra tính hợp lệ của tham số đầu vào
     * 2. Lấy toàn bộ danh sách sách từ BookService
     * 3. Chuẩn bị mảng kết quả tạm thời
     * 4. Chuyển từ khóa tìm kiếm về chữ thường (lowercase) - tự build
     * 5. DUYỆT TUẦN TỰ từng cuốn sách trong danh sách (Linear Search):
     *    - Với mỗi cuốn sách tại vị trí i (i = 0 đến n-1)
     *    - Chuyển tiêu đề sách về chữ thường
     *    - Kiểm tra xem tiêu đề có chứa từ khóa không (substring matching)
     *    - Nếu có, thêm vào mảng kết quả
     * 6. Tạo mảng kết quả cuối cùng với đúng kích thước
     * 7. Sao chép các kết quả từ mảng tạm sang mảng cuối cùng
     * 
     * ĐỘ PHỨC TẠP:
     * - Thời gian: O(n) - phải duyệt hết n sách trong trường hợp xấu nhất
     * - Không gian: O(k) - với k là số kết quả tìm được
     * 
     * VÍ DỤ:
     * - Nếu tìm "Java", sẽ trả về tất cả sách có tiêu đề chứa "Java"
     * - Nếu tìm "prog", sẽ trả về sách "Programming", "Java Programming", etc.
     */
    public static Book[] searchBooksByTitle(String title) {
        // Bước 1: Kiểm tra tính hợp lệ của tham số
        if (title == null || title.trim().isEmpty()) {
            return new Book[0]; // Trả về mảng rỗng nếu không có từ khóa
        }
        
        // Bước 2: Lấy toàn bộ dữ liệu sách
        Book[] all = BookService.getAllBooks();
        int n = BookService.getBookCount();
        
        // Bước 3: Chuẩn bị mảng kết quả tạm thời (kích thước tối đa là n)
        Book[] results = new Book[n];
        int resultCount = 0; // Đếm số kết quả tìm được
        
        // Bước 4: Chuẩn hóa từ khóa tìm kiếm (chuyển về chữ thường)
        String needle = toLowerCase(title);
        
        // Bước 5: LINEAR SEARCH - Duyệt tuần tự từng phần tử
        for (int i = 0; i < n; i++) {
            // So sánh tiêu đề sách (lowercase) với từ khóa
            if (toLowerCase(all[i].getTitle()).contains(needle)) {
                results[resultCount++] = all[i]; // Thêm vào kết quả nếu khớp
            }
        }
        
        // Bước 6-7: Tạo mảng kết quả cuối cùng với đúng kích thước
        Book[] finalResults = new Book[resultCount];
        for (int i = 0; i < resultCount; i++) {
            finalResults[i] = results[i];
        }
        
        return finalResults;
    }

    /**
     * TÌM KIẾM SÁCH THEO TÁC GIẢ - LINEAR SEARCH ALGORITHM
     * 
     * MÔ TẢ:
     * Tương tự như tìm kiếm theo tiêu đề, phương thức này sử dụng Linear Search
     * để tìm tất cả các cuốn sách có tên tác giả chứa cụm từ khóa.
     * 
     * THAM SỐ:
     * @param author - Cụm từ khóa cần tìm trong tên tác giả
     * @return Book[] - Mảng chứa tất cả sách của tác giả phù hợp
     * 
     * THUẬT TOÁN GIỐNG HET NHƯ TÌM KIẾM THEO TIÊU ĐỀ:
     * 1. Chuẩn hóa từ khóa tìm kiếm
     * 2. Duyệt tuần tự toàn bộ danh sách sách
     * 3. So sánh tên tác giả với từ khóa (substring matching)
     * 4. Thu thập các kết quả khớp
     * 
     * VÍ DỤ:
     * - Tìm "nguyen" sẽ trả về sách của "Nguyen Van A", "Tran Nguyen B"
     * - Tìm "smith" sẽ trả về sách của "John Smith", "Smith Johnson"
     */
    public static Book[] searchBooksByAuthor(String author) {
        // Kiểm tra tính hợp lệ của tham số đầu vào
        if (author == null || author.trim().isEmpty()) {
            return new Book[0];
        }
        
        // Lấy toàn bộ dữ liệu sách
        Book[] all = BookService.getAllBooks();
        int n = BookService.getBookCount();
        
        // Chuẩn bị mảng kết quả tạm thời
        Book[] results = new Book[n];
        int resultCount = 0;
        
        // Chuẩn hóa từ khóa (chuyển về chữ thường)
        String needle = toLowerCase(author);
        
        // LINEAR SEARCH: Duyệt tuần tự từng cuốn sách
        for (int i = 0; i < n; i++) {
            // So sánh tên tác giả (lowercase) với từ khóa
            if (toLowerCase(all[i].getAuthor()).contains(needle)) {
                results[resultCount++] = all[i]; // Thêm vào kết quả
            }
        }
        
        // Tạo mảng kết quả cuối cùng
        Book[] finalResults = new Book[resultCount];
        for (int i = 0; i < resultCount; i++) {
            finalResults[i] = results[i];
        }
        
        return finalResults;
    }

    private static String toLowerCase(String s) {
        if (s == null) return null;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) if (c[i] >= 'A' && c[i] <= 'Z') c[i] = (char)(c[i] + 32);
        return new String(c);
    }
}
