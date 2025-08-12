package service;

import model.Book;
import model.Order;

public class BookSortService {
    public static void sortByTitle(Book[] books, int count) {
        BookSorter.sortByTitle(books, count);
    }
    public static void sortByAuthor(Book[] books, int count) {
        BookSorter.sortByAuthor(books, count);
    }
    public static void sortOrderBooks(Order order, String sortBy) {
        BookSorter.sortOrderBooks(order, sortBy);
    }
    public static void benchmarkSort(Book[] books, int count, String sortBy, int iterations) {
        BookSorter.benchmarkSort(books, count, sortBy, iterations);
    }
    public static void testSortingPatterns(Book[] books, int count) {
        BookSorter.testSortingPatterns(books, count);
    }
}
