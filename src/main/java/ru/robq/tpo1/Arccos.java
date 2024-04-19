package ru.robq.tpo1;

public class Arccos {

    private static double double_fact(int n) {
        int start;
        if (n % 2 == 0) {
            start = 2;
        } else {
            start = 3;
        }
        double result = 1;
        for (int i = start; i <= n; i += 2) {
            result *= i;
        }
        return result;
    }

    public static double arccos(double x, int n) {
        double result = x;
        if (x < -1 || x > 1) {
            return Double.NaN;
        }
        for (int i = 1; i < n; i++) {
            double part = (double_fact(2 * i - 1) * Math.pow(x, 2 * i + 1)) / (double_fact(2 * i) * (2 * i + 1));
            result += part;
        }
        result =  Math.PI / 2 - result;
        return result;
    }
}
