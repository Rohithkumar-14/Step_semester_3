class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(String attendeeId, double basePrice) {
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " + getBalanceDue());
    }

    static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket)
            return "Multilevel descendant (3 generations deep)";
        if (ticket instanceof HackathonTicket)
            return "Hierarchical sibling (independent branch)";
        return "Standard Event Ticket";
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0;

        for (EventTicket ticket : tickets)
            total += ticket.getBalanceDue();

        return total;
    }
}

class WorkshopTicket extends EventTicket {
    protected String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue());
    }
}

class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    void printTicket() {
        System.out.println("Premium Workshop Ticket | Track: " + track + " | Kit Fee: " + kitFee + " | Balance Due: " + getBalanceDue());
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;

    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    void printTicket() {
        System.out.println("Hackathon Ticket | Team: " + teamName + " | Balance Due: " + getBalanceDue());
    }
}