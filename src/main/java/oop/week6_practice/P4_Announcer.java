class EventTicket {
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    String printTicket() {
        return "Standard | Balance: " + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    String getTrack() {
        return track;
    }

    @Override
    String printTicket() {
        return "Workshop | Track: " + track + " | Balance: " + getBalanceDue();
    }
}

class TicketAnnouncer {
    static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();

        for (EventTicket ticket : tickets) {
            sb.append(ticket.printTicket()).append(" ");

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshop = (WorkshopTicket) ticket;
                sb.append("[Track via downcast: ")
                  .append(workshop.getTrack())
                  .append("] | ");
            }
        }

        return sb.toString();
    }
}