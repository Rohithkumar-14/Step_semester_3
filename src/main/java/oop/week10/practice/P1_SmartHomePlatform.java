package oop.week10.practice;

class Device {
    String id;
    String type;
    boolean on;
    boolean dim;
    boolean schedule;
    boolean energy;
    int units;

    Device(String id, String type, boolean dim, boolean schedule, boolean energy, int units) {
        this.id = id;
        this.type = type;
        this.dim = dim;
        this.schedule = schedule;
        this.energy = energy;
        this.units = units;
        this.on = false;
    }
}

public class P1_SmartHomePlatform {
    public static void main(String[] args) {
        Device[] devices = {
            new Device("L1", "light", true, true, false, 0),
            new Device("F1", "fan", false, true, false, 0),
            new Device("P1", "plug", false, false, true, 12)
        };

        String[] commands = {
            "ON L1",
            "DIM L1 40",
            "DIM F1 30",
            "SCHEDULE F1 22:00",
            "ENERGY P1",
            "ENERGY L1"
        };

        for (String command : commands) {
            String[] parts = command.split(" ");
            String action = parts[0];
            String id = parts[1];
            Device d = null;

            for (Device device : devices) {
                if (device.id.equals(id)) {
                    d = device;
                    break;
                }
            }

            if (d == null) {
                System.out.println(id + " not found");
            } else if (action.equals("ON")) {
                d.on = true;
                System.out.println(id + " is ON");
            } else if (action.equals("DIM")) {
                if (d.dim) {
                    System.out.println(id + " dimmed to " + parts[2]);
                } else {
                    System.out.println(id + " rejected: DIM unsupported");
                }
            } else if (action.equals("SCHEDULE")) {
                if (d.schedule) {
                    System.out.println(id + " scheduled " + parts[2]);
                } else {
                    System.out.println(id + " rejected: SCHEDULE unsupported");
                }
            } else if (action.equals("ENERGY")) {
                if (d.energy) {
                    System.out.println(id + " energy " + d.units + " kWh");
                } else {
                    System.out.println(id + " rejected: ENERGY unsupported");
                }
            }
        }
    }
}