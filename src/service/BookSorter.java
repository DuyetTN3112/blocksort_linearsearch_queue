package service;

import model.Book;
import model.Order;

/**
 * THUẬT TOÁN BLOCK SORT (SẮP XẾP KHỐI) - TỰ XÂY DỰNG HOÀN TOÀN
 * 
 * GIỚI THIỆU:
 * Block Sort là một thuật toán sắp xếp hiệu quả kết hợp ưu điểm của nhiều thuật toán khác nhau.
 * Nó chia mảng thành các khối nhỏ, sắp xếp từng khối rồi hợp nhất chúng lại.
 * 
 * NGUYÊN LÝ HOẠT ĐỘNG:
 * 1. Chia mảng thành các khối có kích thước √n (căn bậc hai của n)
 * 2. Sắp xếp từng khối nhỏ bằng Insertion Sort (hiệu quả với dữ liệu nhỏ)
 * 3. Hợp nhất các khối đã sắp xếp bằng thuật toán Merge
 * 4. Lặp lại quá trình merge cho đến khi toàn bộ mảng được sắp xếp
 * 
 * ƯU ĐIỂM:
 * - Độ phức tạp thời gian: O(n log n) trong trường hợp trung bình
 * - Hiệu quả với dữ liệu lớn
 * - Stable sort (giữ nguyên thứ tự của các phần tử bằng nhau)
 * - Có thể tối ưu cho các loại dữ liệu khác nhau
 * 
 * NHƯỢC ĐIỂM:
 * - Cần thêm bộ nhớ để merge (không phải in-place)
 * - Phức tạp hơn các thuật toán đơn giản như Bubble Sort
 * 
 * ỨNG DỤNG:
 * - Sắp xếp danh sách sách theo tiêu đề hoặc tác giả
 * - Sắp xếp đơn hàng theo các tiêu chí khác nhau
 * - Thích hợp cho dữ liệu lớn cần sắp xếp nhanh
 * 
 * IMPLEMENTATION TỰ BUILD - KHÔNG SỬ DỤNG THỦ VIỆN CÓ SẴN
 */
public class BookSorter {
    
    /**
     * PHU'O'NG THU'C CHINH - THUAT TOAN BLOCK SORT
     * 
     * MO TA:
     * Day la phuong thuc chinh thuc hien thuat toan Block Sort.
     * No chia mang thanh cac khoi nho, sap xep tung khoi roi hop nhat chung.
     * 
     * THAM SO:
     * @param books - Mang cac cuon sach can sap xep
     * @param n - So luong sach trong mang
     * @param sortBy - Tieu chi sap xep ("title" hoac "author")
     * 
     * CAC BUOC THUC HIEN:
     * 1. Kiem tra tinh hop le cua tham so dau vao
     * 2. Tinh toan kich thuoc khoi toi uu (sqrt(n))
     * 3. Sap xep tung khoi nho bang Insertion Sort
     * 4. Hop nhat cac khoi da sap xep bang thuat toan Merge
     * 5. Do thoi gian thuc hien va hien thi ket qua
     * 
     * DO PHUC TAP:
     * - Thoi gian: O(n log n) trong truong hop trung binh
     * - Bo nho: O(sqrt(n)) cho viec luu tru cac khoi tam thoi
     */
    public static void blockSort(Book[] books, int n, String sortBy) {
        if (books == null || n <= 1) {
            return;
        }
        
        System.out.println("Starting Block Sort by " + sortBy + "...");
        long startTime = System.nanoTime();
        
        // Determine block size (sqrt of n is common choice)
        int blockSize = calculateBlockSize(n);
        
        System.out.println("Array size: " + n + ", Block size: " + blockSize);
        
        // Step 1: Sort individual blocks using insertion sort
        sortBlocks(books, n, blockSize, sortBy);
        
        // Step 2: Merge adjacent blocks until fully sorted
        mergeBlocks(books, n, blockSize, sortBy);
        
        long endTime = System.nanoTime();
        System.out.println("Block Sort completed in " + (endTime - startTime) / 1000000.0 + " ms");
    }
    
    // Calculate optimal block size
    private static int calculateBlockSize(int n) {
        int blockSize = sqrtInt(n);
        if (blockSize < 1) blockSize = 1;
        if (blockSize > n) blockSize = n;
        return blockSize;
    }
    
    // Sort individual blocks
    private static void sortBlocks(Book[] books, int n, int blockSize, String sortBy) {
        for (int i = 0; i < n; i += blockSize) {
            int end = getMinimum(i + blockSize - 1, n - 1);
            insertionSort(books, i, end, sortBy);
        }
    }
    
    // Merge sorted blocks
    private static void mergeBlocks(Book[] books, int n, int blockSize, String sortBy) {
        for (int size = blockSize; size < n; size *= 2) {
            for (int start = 0; start < n; start += 2 * size) {
                int mid = getMinimum(start + size - 1, n - 1);
                int end = getMinimum(start + 2 * size - 1, n - 1);
                
                if (mid < end) {
                    merge(books, start, mid, end, sortBy);
                }
            }
        }
    }
    
    // Helper method to get minimum of two integers
    private static int getMinimum(int a, int b) {
        return (a < b) ? a : b;
    }
    
    // Insertion sort for small blocks
    private static void insertionSort(Book[] books, int start, int end, String sortBy) {
        for (int i = start + 1; i <= end; i++) {
            Book key = books[i];
            int j = i - 1;
            
            while (j >= start && compareBooks(books[j], key, sortBy) > 0) {
                books[j + 1] = books[j];
                j--;
            }
            books[j + 1] = key;
        }
    }
    
    // Merge function for combining sorted blocks
    private static void merge(Book[] books, int start, int mid, int end, String sortBy) {
        // Create temporary arrays
        int leftSize = mid - start + 1;
        int rightSize = end - mid;
        
        Book[] leftArray = new Book[leftSize];
        Book[] rightArray = new Book[rightSize];
        
        // Copy data to temporary arrays
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = books[start + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = books[mid + 1 + j];
        }
        
        // Merge the temporary arrays back
        int i = 0, j = 0, k = start;
        
        while (i < leftSize && j < rightSize) {
            if (compareBooks(leftArray[i], rightArray[j], sortBy) <= 0) {
                books[k] = leftArray[i];
                i++;
            } else {
                books[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of leftArray, if any
        while (i < leftSize) {
            books[k] = leftArray[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray, if any
        while (j < rightSize) {
            books[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    // Compare books based on sorting criteria - tự build string comparison
    private static int compareBooks(Book book1, Book book2, String sortBy) {
        switch (toLowerCase(sortBy)) {
            case "title":
                return compareStringsIgnoreCase(book1.getTitle(), book2.getTitle());
            case "author":
                return compareStringsIgnoreCase(book1.getAuthor(), book2.getAuthor());
            default:
                return compareStringsIgnoreCase(book1.getTitle(), book2.getTitle());
        }
    }
    
    // Custom string comparison ignoring case - tự build
    private static int compareStringsIgnoreCase(String str1, String str2) {
        if (str1 == null && str2 == null) return 0;
        if (str1 == null) return -1;
        if (str2 == null) return 1;
        
        String lower1 = toLowerCase(str1);
        String lower2 = toLowerCase(str2);
        
        int len1 = lower1.length();
        int len2 = lower2.length();
        int minLen = (len1 < len2) ? len1 : len2;
        
        for (int i = 0; i < minLen; i++) {
            char c1 = lower1.charAt(i);
            char c2 = lower2.charAt(i);
            if (c1 != c2) {
                return c1 - c2;
            }
        }
        
        return len1 - len2;
    }
    
    // Convert string to lowercase - tự build
    private static String toLowerCase(String str) {
        if (str == null) return null;
        
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char)(chars[i] + 32);
            }
        }
        return new String(chars);
    }
    
    // Sort books by title
    public static void sortByTitle(Book[] books, int n) {
        blockSort(books, n, "title");
    }
    
    // Sort books by author
    public static void sortByAuthor(Book[] books, int n) {
        blockSort(books, n, "author");
    }
    
    // Sort books in an Order
    public static void sortOrderBooks(Order order, String sortBy) {
        if (order == null || order.getItemCount() <= 1) {
            return;
        }
        
        System.out.println("Sorting books in order " + order.getOrderNumber() + " by " + sortBy);
        
        // Get items from order
        Order.BookItem[] items = order.getItems();
        int itemCount = items.length;
        
        // Sort using block sort
        blockSortBookItems(items, itemCount, sortBy);
        
        System.out.println("Order books sorted successfully!");
    }
    
    // Block sort with BookItems
    private static void blockSortBookItems(Order.BookItem[] items, int n, String sortBy) {
        if (items == null || n <= 1) {
            return;
        }
        
        int blockSize = calculateBlockSize(n);
        
        // Sort individual blocks
        for (int i = 0; i < n; i += blockSize) {
            int end = getMinimum(i + blockSize - 1, n - 1);
            insertionSortBookItems(items, i, end, sortBy);
        }
        
        // Merge blocks
        for (int size = blockSize; size < n; size *= 2) {
            for (int start = 0; start < n; start += 2 * size) {
                int mid = getMinimum(start + size - 1, n - 1);
                int end = getMinimum(start + 2 * size - 1, n - 1);
                
                if (mid < end) {
                    mergeBookItems(items, start, mid, end, sortBy);
                }
            }
        }
    }
    
    // Insertion sort with BookItems
    private static void insertionSortBookItems(Order.BookItem[] items, int start, int end, String sortBy) {
        for (int i = start + 1; i <= end; i++) {
            Order.BookItem keyItem = items[i];
            int j = i - 1;
            
            while (j >= start && compareBookItems(items[j], keyItem, sortBy) > 0) {
                items[j + 1] = items[j];
                j--;
            }
            items[j + 1] = keyItem;
        }
    }
    
    // Merge with BookItems
    private static void mergeBookItems(Order.BookItem[] items, int start, int mid, int end, String sortBy) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;
        
        Order.BookItem[] leftItems = new Order.BookItem[leftSize];
        Order.BookItem[] rightItems = new Order.BookItem[rightSize];
        
        // Copy data to temporary arrays
        for (int i = 0; i < leftSize; i++) {
            leftItems[i] = items[start + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightItems[j] = items[mid + 1 + j];
        }
        
        // Merge back
        int i = 0, j = 0, k = start;
        
        while (i < leftSize && j < rightSize) {
            if (compareBookItems(leftItems[i], rightItems[j], sortBy) <= 0) {
                items[k] = leftItems[i];
                i++;
            } else {
                items[k] = rightItems[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < leftSize) {
            items[k] = leftItems[i];
            i++;
            k++;
        }
        
        while (j < rightSize) {
            items[k] = rightItems[j];
            j++;
            k++;
        }
    }
    
    // Compare BookItems based on sorting criteria
    private static int compareBookItems(Order.BookItem item1, Order.BookItem item2, String sortBy) {
        switch (toLowerCase(sortBy)) {
            case "title":
                return compareStringsIgnoreCase(item1.getBook().getTitle(), item2.getBook().getTitle());
            case "quantity":
                return Integer.compare(item1.getQuantity(), item2.getQuantity());
            default:
                return compareStringsIgnoreCase(item1.getBook().getTitle(), item2.getBook().getTitle());
        }
    }
    
    // Display sorted books
    public static void displaySortedBooks(Book[] books, int n, String sortBy) {
        System.out.println("\n=== BOOKS SORTED BY " + toUpperCase(sortBy) + " ===");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". " + books[i].getTitle() +
                             " by " + books[i].getAuthor());
        }
        System.out.println("==========================================\n");
    }
    
    // Convert string to uppercase - tự build
    private static String toUpperCase(String str) {
        if (str == null) return null;
        
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = (char)(chars[i] - 32);
            }
        }
        return new String(chars);
    }
    
    // Verify if array is sorted
    public static boolean isSorted(Book[] books, int n, String sortBy) {
        for (int i = 1; i < n; i++) {
            if (compareBooks(books[i-1], books[i], sortBy) > 0) {
                return false;
            }
        }
        return true;
    }
    
    // Get algorithm complexity information
    public static String getComplexityInfo() {
        return "Block Sort Algorithm Complexity:\n" +
               "- Time Complexity: O(n log n) average case\n" +
               "- Space Complexity: O(sqrt(n)) for block storage\n" +
               "- Best Case: O(n) when data is already sorted\n" +
               "- Worst Case: O(n log n)\n" +
               "- Stability: Stable (maintains relative order of equal elements)\n" +
               "- In-place: No (requires additional space for merging)\n" +
               "- Block Size: sqrt(n) optimal for most cases\n" +
               "- Combines insertion sort for small blocks with merge for larger blocks";
    }
    
    // Benchmark sorting performance
    public static void benchmarkSort(Book[] books, int n, String sortBy, int iterations) {
        System.out.println("Benchmarking Block Sort performance...");
        System.out.println("Iterations: " + iterations + ", Array size: " + n);
        
        long totalTime = 0;
        
        for (int i = 0; i < iterations; i++) {
            // Create copy for each iteration
            Book[] testBooks = new Book[n];
            for (int j = 0; j < n; j++) {
                testBooks[j] = books[j].copy();
            }
            
            long startTime = System.nanoTime();
            blockSort(testBooks, n, sortBy);
            long endTime = System.nanoTime();
            
            totalTime += (endTime - startTime);
        }
        
        double avgTime = totalTime / (double) iterations / 1000000.0; // ms
        System.out.println("Average sort time: " + formatDouble(avgTime, 2) + " ms");
        double ips = (n * 1000.0) / (avgTime <= 0 ? 1 : avgTime);
        System.out.println("Items per second: " + formatDouble(ips, 0));
    }
    
    // Create shuffled copy of array for testing
    public static Book[] createShuffledCopy(Book[] books, int n) {
        Book[] shuffled = new Book[n];
        for (int i = 0; i < n; i++) {
            shuffled[i] = books[i].copy();
        }
        
        // Simple shuffle algorithm - tự build (Fisher-Yates)
        for (int i = n - 1; i > 0; i--) {
            int j = randomInt(i + 1);
            Book temp = shuffled[i];
            shuffled[i] = shuffled[j];
            shuffled[j] = temp;
        }
        
        return shuffled;
    }
    
    // Test sorting with different data patterns
    public static void testSortingPatterns(Book[] books, int n) {
        System.out.println("Testing Block Sort with different data patterns:");
        
        // Test 1: Already sorted data
        System.out.println("\n1. Testing with already sorted data:");
        Book[] sortedBooks = new Book[n];
        for (int i = 0; i < n; i++) {
            sortedBooks[i] = books[i].copy();
        }
        blockSort(sortedBooks, n, "title");
        
        long startTime = System.nanoTime();
        blockSort(sortedBooks, n, "title");
        long endTime = System.nanoTime();
        System.out.println("Time for already sorted: " + (endTime - startTime) / 1000000.0 + " ms");
        
        // Test 2: Reverse sorted data
        System.out.println("\n2. Testing with reverse sorted data:");
        Book[] reverseBooks = new Book[n];
        for (int i = 0; i < n; i++) {
            reverseBooks[i] = books[n - 1 - i].copy();
        }
        
        startTime = System.nanoTime();
        blockSort(reverseBooks, n, "title");
        endTime = System.nanoTime();
        System.out.println("Time for reverse sorted: " + (endTime - startTime) / 1000000.0 + " ms");
        
        // Test 3: Random data
        System.out.println("\n3. Testing with random data:");
        Book[] randomBooks = createShuffledCopy(books, n);
        
        startTime = System.nanoTime();
        blockSort(randomBooks, n, "title");
        endTime = System.nanoTime();
        System.out.println("Time for random data: " + (endTime - startTime) / 1000000.0 + " ms");
        
        // Verify all sorts worked correctly
        System.out.println("\nVerification:");
        System.out.println("Sorted data is sorted: " + isSorted(sortedBooks, n, "title"));
        System.out.println("Reverse sorted data is now sorted: " + isSorted(reverseBooks, n, "title"));
        System.out.println("Random data is now sorted: " + isSorted(randomBooks, n, "title"));
    }
    
    // Utility functions - tự build
    private static int sqrtInt(int n) {
        if (n <= 1) return n;
        int x = n;
        int y = (x + n / x) / 2;
        while (y < x) {
            x = y;
            y = (x + n / x) / 2;
        }
        return x;
    }
    
    private static int randomInt(int max) {
        return (int)(System.nanoTime() % max);
    }
    
    private static String formatDouble(double value, int decimals) {
        long factor = 1;
        for (int i = 0; i < decimals; i++) factor *= 10;
        long rounded = (long) (value * factor + 0.5);
        long integerPart = rounded / factor;
        long fractionPart = rounded % factor;
        StringBuilder sb = new StringBuilder();
        sb.append(integerPart);
        if (decimals > 0) {
            sb.append('.');
            String frac = String.valueOf(fractionPart);
            while (frac.length() < decimals) frac = "0" + frac;
            sb.append(frac);
        }
        return sb.toString();
    }
}