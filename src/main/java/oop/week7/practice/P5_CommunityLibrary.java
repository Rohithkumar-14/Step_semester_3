abstract class LibraryItem {
    private final String itemId;
    private static int counter = 1000;

    protected LibraryItem() {
        itemId = "LIB-" + (++counter);
    }

    public abstract int getLoanPeriodDays();

    String getItemId() {
        return itemId;
    }
}

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

class Textbook extends LibraryItem implements Renewable, Reservable {
    private String title;

    public Textbook(String title) {
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }

    @Override
    public String reserve() {
        return title + " reserved";
    }
}

class Magazine extends LibraryItem implements Renewable {
    private String title;

    public Magazine(String title) {
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }
}

class DigitalPass implements Renewable {
    private String resourceName;

    public DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }
}

class LibraryController {
    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items)
            System.out.println(item.getLoanPeriodDays());
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            Reservable r = (Reservable) o;
            return r.reserve();
        }

        return "Reservation not supported";
    }
}

public class P5_CommunityLibrary {
    public static void main(String[] args) {
        Textbook t = new Textbook("Java Fundamentals");
        Magazine m = new Magazine("Tech Monthly");
        DigitalPass d = new DigitalPass("E-Journal Access");

        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());

        System.out.println(LibraryController.reserveIfSupported(m));
        System.out.println(LibraryController.reserveIfSupported(d));

        LibraryItem ref = t;
        System.out.println(LibraryController.reserveIfSupported(ref));

        LibraryController.processCheckouts(new LibraryItem[]{t, m});
    }
}