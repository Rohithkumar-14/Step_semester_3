class DeliverySlot {
    String orderId;
    String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00") ||
               timeSlot.equals("13:00-14:00") ||
               timeSlot.equals("19:00-20:00") ||
               timeSlot.equals("20:00-21:00");
    }
}

public class F2_DeliverySlot {
    public static void main(String[] args) {
        DeliverySlot order1 = new DeliverySlot("ORD101", "13:00-14:00");
        DeliverySlot order2 = new DeliverySlot("ORD102");

        System.out.println(order1.orderId + " " + order1.timeSlot + " " + order1.isPeakHour());
        System.out.println(order2.orderId + " " + order2.timeSlot + " " + order2.isPeakHour());
    }
}
