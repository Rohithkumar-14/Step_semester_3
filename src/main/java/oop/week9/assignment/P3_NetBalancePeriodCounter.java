import java.util.*;

public class P3_NetBalancePeriodCounter {

    static int countPeriods(int[] transactions, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int sum = 0;
        int count = 0;

        for (int value : transactions) {
            sum += value;

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {

        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        int k = 7;

        System.out.println(countPeriods(transactions, k));
    }
}