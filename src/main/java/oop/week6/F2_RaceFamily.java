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

    void announce() {
        System.out.print("Race Entry: " + bibNumber);
    }

    static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry)
            return "Multilevel descendant (3 generations deep)";
        if (entry instanceof RelayTeamEntry)
            return "Hierarchical sibling (independent branch)";
        return "Base RaceEntry";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;

        for (RaceEntry entry : entries)
            total += entry.getBalanceDue();

        return total;
    }
}

class RunnerEntry extends RaceEntry {
    public RunnerEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }

    @Override
    void announce() {
        System.out.print("Runner Entry: " + bibNumber);
    }
}

class EliteRunnerEntry extends RunnerEntry {
    public EliteRunnerEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }

    @Override
    void announce() {
        System.out.print("Elite Runner Entry: " + bibNumber);
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    int getTeamSize() {
        return teamSize;
    }

    @Override
    void announce() {
        System.out.print("Relay Team Entry: " + bibNumber);
    }
}