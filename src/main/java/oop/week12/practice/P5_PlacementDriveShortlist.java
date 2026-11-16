
package oop.week12.practice;

import java.util.Scanner;

public class P5_PlacementDriveShortlist {

    static class Candidate {
        String name;
        int[] scores;
        int total;
        int bestGain;
        int startTest;
        int endTest;

        Candidate(String name, int[] scores) {
            this.name = name;
            this.scores = scores;

            for (int i = 0; i < 6; i++) {
                total += scores[i];
            }

            findBestClimb();
        }

        void findBestClimb() {

            int current = 0;
            int best = 0;

            int currentStart = 1;
            startTest = 1;
            endTest = 1;

            for (int i = 1; i < 6; i++) {

                int change = scores[i] - scores[i - 1];

                if (current + change < change) {
                    current = change;
                    currentStart = i;
                } else {
                    current += change;
                }

                if (current > best) {
                    best = current;
                    startTest = currentStart;
                    endTest = i + 1;
                }
            }

            bestGain = best;
        }
    }

    static void mergeSort(Candidate[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    static void merge(Candidate[] arr, int left, int mid, int right) {

        Candidate[] temp = new Candidate[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {

            if (arr[i].total >= arr[j].total) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    static int cutoff(Candidate[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;
        int answer = arr.length;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (arr[mid].total >= target) {
                low = mid + 1;
            } else {
                answer = mid;
                high = mid - 1;
            }
        }

        return answer;
    }

    static void pair(Candidate[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            int sum = arr[left].total + arr[right].total;

            if (sum == target) {
                System.out.println("PAIR " + target + ": "
                        + arr[left].name + " + " + arr[right].name);
                return;
            }
            else if (sum > target) {
                left++;
            }
            else {
                right--;
            }
        }

        System.out.println("PAIR " + target + ": no pair");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Candidate[] arr = new Candidate[n];

        for (int i = 0; i < n; i++) {

            String name = sc.next();

            int[] scores = new int[6];

            for (int j = 0; j < 6; j++) {
                scores[j] = sc.nextInt();
            }

            arr[i] = new Candidate(name, scores);
        }

        mergeSort(arr, 0, n - 1);

        System.out.println("Shortlist:");

        for (int i = 0; i < n; i++) {

            System.out.println((i + 1) + ". " + arr[i].name
                    + " " + arr[i].total
                    + " | best climb +" + arr[i].bestGain
                    + " (test " + arr[i].startTest
                    + " to " + arr[i].endTest + ")");
        }

        while (sc.hasNext()) {

            String command = sc.next();

            int target = sc.nextInt();

            if (command.equals("CUTOFF")) {
                int count = cutoff(arr, target);
                System.out.println("CUTOFF " + target
                        + ": " + count + " candidates");
            }
            else if (command.equals("PAIR")) {
                pair(arr, target);
            }
        }

        sc.close();
    }
}
