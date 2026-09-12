class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

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

    static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}