import java.util.Arrays;

class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4)
            throw new IllegalArgumentException("Invalid bib number");
        if (entryFee <= 0)
            throw new IllegalArgumentException("Invalid entry fee");

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return entryFee - amountPaid;
    }

    protected void applyLateFee(double amount) {
        entryFee += amount;
        lateFeeHistory[lateFeeCount++] = amount;
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class RunnerEntry extends RaceEntry {
    public RunnerEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}