import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
    String getName();
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + 10 * weight;
    }

    public String getName() {
        return "Standard";
    }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 80 + 15 * weight;
    }

    public String getName() {
        return "Express";
    }
}

class FragileShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return new StandardShipping().calculateCharge(weight) + 50;
    }

    public String getName() {
        return "Fragile";
    }
}

interface NotificationChannel {
    void notify(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {
    public void notify(String parcelId, String status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String parcelId, String status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}

class Parcel {
    private String parcelId;
    private double weight;
    private ShippingType shippingType;
    private String status = "BOOKED";
    private List<NotificationChannel> channels = new ArrayList<>();

    public Parcel(String parcelId, double weight, ShippingType shippingType) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.shippingType = shippingType;
    }

    public double getCharge() {
        return shippingType.calculateCharge(weight);
    }

    public String getStatus() {
        return status;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    private void notifyChannels() {
        for (NotificationChannel channel : channels)
            channel.notify(parcelId, status);
    }

    public void book() {
        System.out.println("Parcel " + parcelId + " booked (" +
                shippingType.getName() + ", " + weight + " kg).");

        System.out.printf("Charge: ₹%.2f.%n", getCharge());

        notifyChannels();
    }

    public void updateStatus(String newStatus) {
        if (!isValidTransition(newStatus)) {
            System.out.println("Invalid transition: " + status + " → " + newStatus + " is not allowed.");
            return;
        }

        status = newStatus;
        notifyChannels();
    }

    private boolean isValidTransition(String newStatus) {
        if (status.equals("BOOKED") && newStatus.equals("PICKED_UP"))
            return true;

        if (status.equals("PICKED_UP") && newStatus.equals("IN_TRANSIT"))
            return true;

        if (status.equals("IN_TRANSIT") && newStatus.equals("OUT_FOR_DELIVERY"))
            return true;

        if (status.equals("OUT_FOR_DELIVERY") && newStatus.equals("DELIVERED"))
            return true;

        return false;
    }

    public void cancel() {
        if (!status.equals("BOOKED")) {
            System.out.println("Cancellation failed: " + parcelId +
                    " can be cancelled only while BOOKED.");
            return;
        }

        status = "CANCELLED";
        System.out.println("Parcel " + parcelId + " cancelled.");
        notifyChannels();
    }
}

class ParcelService {
    public void bookParcel(Parcel parcel) {
        parcel.book();
    }
}

public class P2_SwiftShipParcelTracker {
    public static void main(String[] args) {
        Parcel parcel = new Parcel(
                "P101",
                2,
                new ExpressShipping()
        );

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        ParcelService service = new ParcelService();
        service.bookParcel(parcel);

        parcel.updateStatus("PICKED_UP");

        parcel.cancel();

        parcel.updateStatus("IN_TRANSIT");

        parcel.updateStatus("DELIVERED");
    }
}