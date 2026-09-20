abstract class ServiceableVehicle {
    private double mileage;

    public abstract String performMaintenance();

    double getMileage() {
        return mileage;
    }

    void addMileage(double km) {
        if (km < 0)
            throw new IllegalArgumentException("Mileage cannot be negative");

        mileage += km;
    }
}

interface Insurable {
    String getInsuranceInfo();
}

class Forklift extends ServiceableVehicle implements Insurable {
    private String assetTag;

    public Forklift(String assetTag) {
        this.assetTag = assetTag;
    }

    @Override
    public String performMaintenance() {
        return "Forklift " + assetTag + ": hydraulic and fork inspection complete";
    }

    @Override
    public String getInsuranceInfo() {
        return "Insured under fleet policy - Asset " + assetTag;
    }
}

class HeavyDutyForklift extends Forklift {
    public HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }

    @Override
    public String performMaintenance() {
        return super.performMaintenance() + " | high pressure hydraulic check complete";
    }
}

class FleetManager {
    static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            Insurable i = (Insurable) v;
            return i.getInsuranceInfo();
        }

        return "No insurance record exists";
    }
}

public class P3_FleetMaintenance {
    public static void main(String[] args) {
        Forklift forklift = new Forklift("FL-22");
        forklift.addMileage(120);

        System.out.println(forklift.getMileage());
        System.out.println(forklift.performMaintenance());
        System.out.println(FleetManager.getInsuranceIfApplicable(forklift));

        HeavyDutyForklift heavy = new HeavyDutyForklift("HD-9");

        System.out.println(heavy.performMaintenance());
        System.out.println(FleetManager.getInsuranceIfApplicable(heavy));
    }
}