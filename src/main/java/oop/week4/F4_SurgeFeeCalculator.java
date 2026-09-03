class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double feePercent = 0.0;

        if (delayMinutes <= 5) {
            feePercent = delayMinutes * 0.5;
        } else if (delayMinutes <= 15) {
            feePercent = (5 * 0.5) + ((delayMinutes - 5) * 1.0);
        } else {
            feePercent = (5 * 0.5) + (10 * 1.0) + ((delayMinutes - 15) * 2.0);
        }

        if (feePercent < minimumSurgePercent) {
            feePercent = minimumSurgePercent;
        }

        return orderValue * feePercent / 100;
    }
}

public class F4_SurgeFeeCalculator {
    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0);

        System.out.println(calculator.calculateSurgeFee(500, 0));
        System.out.println(calculator.calculateSurgeFee(500, 1));
        System.out.println(calculator.calculateSurgeFee(500, 16));
    }
}