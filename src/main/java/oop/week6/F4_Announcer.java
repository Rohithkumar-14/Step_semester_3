class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return entryFee - amountPaid;
    }

    String announce() {
        return "Race Entry: " + bibNumber;
    }
}

class RunnerEntry extends RaceEntry {
    public RunnerEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }

    @Override
    String announce() {
        return "Runner Entry: " + bibNumber;
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
    String announce() {
        return "Relay Team Entry: " + bibNumber;
    }
}

class RaceAnnouncer {
    static String announceAll(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();

        for (RaceEntry entry : entries) {
            sb.append(entry.announce()).append(" | ");

            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                sb.append("[Team size via downcast: ")
                  .append(relay.getTeamSize())
                  .append("] | ");
            }
        }

        return sb.toString();
    }
}