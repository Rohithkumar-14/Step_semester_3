class DeliveryAccount {
    static String systemName;

    String studentId;
    double orderValue;
    boolean premium;

    static {
        systemName = "Nightly Reconciliation System";
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
        this.premium = false;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes <= 0) {
            return 0.0;
        }

        double feePercent;

        if (delayMinutes <= 5) {
            feePercent = delayMinutes * 0.5;
        } else if (delayMinutes <= 15) {
            feePercent = 2.5 + (delayMinutes - 5);
        } else {
            feePercent = 12.5 + (delayMinutes - 15) * 2;
        }

        return orderValue * feePercent / 100;
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
        this.premium = true;
    }
}

class DeliveryProcessor {
    void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return;
        }

        double surgeFee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumDeliveryAccount) {
            System.out.println("Premium: " + account.studentId + " | Surge Fee: " + surgeFee);
        } else {
            System.out.println("Regular: " + account.studentId + " | Surge Fee: " + surgeFee);
        }
    }

    static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Mismatched array lengths");
        }

        DeliveryProcessor processor = new DeliveryProcessor();

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            processor.processAccount(accounts[i], amounts[i], delayMinutesArray[i]);

            processed++;
            grandTotal += accounts[i].calculateSurgeFee(delayMinutesArray[i]);

            if (accounts[i] instanceof PremiumDeliveryAccount) {
                premium++;
            } else {
                regular++;
            }
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + premium + " premium | " + regular + " regular | grand total surge fees = " + grandTotal);
    }
}

public class F5_DeliveryAccount {
    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delays = {10, 5, 0};

        DeliveryProcessor.processBatch(accounts, amounts, delays);
    }
}