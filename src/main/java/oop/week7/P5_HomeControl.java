abstract class HomeDevice {
    private final String serialNumber;
    private static int counter = 1000;

    protected HomeDevice() {
        serialNumber = "HD-" + (++counter);
    }

    public abstract String activate();

    String getSerialNumber() {
        return serialNumber;
    }
}

interface RemoteControllable {
    String connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

class WashingMachine extends HomeDevice implements RemoteControllable, EnergyTrackable {
    private double consumptionWatts;

    public WashingMachine(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Washing machine " + getSerialNumber() + " started a cycle";
    }

    @Override
    public String connect(String appId) {
        return getSerialNumber() + " connected to " + appId;
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class Refrigerator extends HomeDevice implements EnergyTrackable {
    private double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Refrigerator " + getSerialNumber() + " activated";
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class MobileApp implements RemoteControllable {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}

class HomeController {
    static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable item : items)
            System.out.println(item.connect(appId));
    }

    static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable e = (EnergyTrackable) d;
            return e.getConsumptionWatts();
        }

        return 0.0;
    }
}

public class P5_HomeControl {
    public static void main(String[] args) {
        WashingMachine washingMachine = new WashingMachine(500);
        Refrigerator refrigerator = new Refrigerator(150);
        MobileApp mobileApp = new MobileApp("HomeConnect");

        System.out.println(washingMachine.getSerialNumber());
        System.out.println(washingMachine.activate());
        System.out.println(washingMachine.connect("HomeConnect"));

        System.out.println(refrigerator.getSerialNumber());
        System.out.println(refrigerator.activate());
        System.out.println(HomeController.getConsumptionIfTrackable(refrigerator));

        System.out.println(mobileApp.connect("HomeConnect"));

        RemoteControllable[] devices = {washingMachine, mobileApp};
        HomeController.connectAll(devices, "HomeConnect");

        System.out.println(washingMachine.getConsumptionWatts());
    }
}