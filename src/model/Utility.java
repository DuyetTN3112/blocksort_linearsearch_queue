package model;

import java.util.Scanner;

public class Utility {
    private static long randomSeed = 123456789L;

    public static String getValidInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty or whitespace.");
            }
        } while (input.isEmpty());
        return input;
    }

    // sqrt floor for non-negative n
    public static int sqrtInt(int n) {
        if (n <= 0) return 0;
        int r = 0;
        while ((r + 1) * (r + 1) <= n) {
            r++;
        }
        return r;
    }

    // Simple LCG random in [0, bound)
    public static int randomInt(int bound) {
        if (bound <= 0) return 0;
        randomSeed = (randomSeed * 1103515245L + 12345L) & 0x7fffffffL;
        return (int) (randomSeed % bound);
    }

    public static String formatDouble(double value, int decimals) {
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
            while (frac.length() < decimals) {
                frac = "0" + frac;
            }
            sb.append(frac);
        }
        return sb.toString();
    }

    public static String padLeftZeros(int number, int width) {
        String s = String.valueOf(number);
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < width; i++) sb.append('0');
        sb.append(s);
        return sb.toString();
    }

    public static int minInt(int a, int b) {
        return (a < b) ? a : b;
    }

    // Very simple fixed date (no java.time)
    public static String getCurrentDate() {
        return "2024-01-15";
    }
}