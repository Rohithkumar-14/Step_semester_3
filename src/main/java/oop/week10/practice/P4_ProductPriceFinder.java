package oop.week10.practice;

public class P4_ProductPriceFinder {
    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int low = 150;
        int high = 300;

        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (prices[mid] < low) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int lower = left;

        left = 0;
        right = prices.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (prices[mid] <= high) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int upper = left;

        System.out.println("Lower bound index " + lower);
        System.out.println("Upper bound index " + upper);
        System.out.println("Count " + (upper - lower));
    }
}