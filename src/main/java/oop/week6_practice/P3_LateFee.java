import java.util.Arrays;

class EventTicket {
    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    protected void applyLateFee(double amount) {
        basePrice += amount;
        lateFeeHistory[lateFeeCount++] = amount;
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class WorkshopTicket extends EventTicket {
    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}