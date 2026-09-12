class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    private final int entryCode;
    private static int bibCounter = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4)
            throw new IllegalArgumentException("Invalid bib number");
        if (entryFee <= 0)
            throw new IllegalArgumentException("Invalid entry fee");

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.entryCode = ++bibCounter;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    void pay(double amount, String mode) {
        pay(amount);
        System.out.println("Paying via " + mode);
    }

    double getBalanceDue() {
        return entryFee - amountPaid;
    }

    static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'M'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    static int getBibCounter() {
        return bibCounter;
    }

    static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry)
                relay++;
            else
                individual++;
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + relay + " relay | " + individual + " individual";
    }
}

class RunnerEntry extends RaceEntry {
    public RunnerEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}

class EliteRunnerEntry extends RunnerEntry {
    public EliteRunnerEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}

class RelayTeamEntry extends RaceEntry {
    public RelayTeamEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}