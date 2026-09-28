import java.util.*;

interface Capability {
    String getName();
    boolean apply(String value);
}

class PowerCapability implements Capability {
    private boolean on;

    public String getName() {
        return "Power";
    }

    public boolean apply(String value) {
        if (!value.equalsIgnoreCase("ON") && !value.equalsIgnoreCase("OFF"))
            return false;

        on = value.equalsIgnoreCase("ON");
        return true;
    }

    public String getStatus() {
        return on ? "ON" : "OFF";
    }
}

class BrightnessCapability implements Capability {
    private int brightness;

    public String getName() {
        return "Brightness";
    }

    public boolean apply(String value) {
        int level = Integer.parseInt(value);

        if (level < 0 || level > 100)
            return false;

        brightness = level;
        return true;
    }

    public int getBrightness() {
        return brightness;
    }
}

class TemperatureCapability implements Capability {
    private int temperature;

    public String getName() {
        return "Temperature";
    }

    public boolean apply(String value) {
        int temp = Integer.parseInt(value);

        if (temp < 16 || temp > 30)
            return false;

        temperature = temp;
        return true;
    }

    public int getTemperature() {
        return temperature;
    }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities = new HashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);
        System.out.println(name + ": " + capability.getName() + " capability added.");
    }

    public boolean hasCapability(String name) {
        return capabilities.containsKey(name);
    }

    public boolean apply(String capabilityName, String value) {
        Capability capability = capabilities.get(capabilityName);

        if (capability == null)
            return false;

        return capability.apply(value);
    }

    public Capability getCapability(String name) {
        return capabilities.get(name);
    }
}

class SceneStep {
    private String capabilityName;
    private String value;

    public SceneStep(String capabilityName, String value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }

    public int apply(List<Device> devices) {
        int count = 0;

        for (Device device : devices) {
            if (device.hasCapability(capabilityName)) {
                if (device.apply(capabilityName, value)) {
                    System.out.println(device.getName() + ": " +
                            capabilityName.toLowerCase() + " set to " + value +
                            (capabilityName.equals("Brightness") ? "%" :
                            capabilityName.equals("Temperature") ? "°C" : ""));
                    count++;
                }
            }
        }

        return count;
    }
}

class Scene {
    private String name;
    private List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");

        int count = 0;

        for (SceneStep step : steps)
            count += step.apply(devices);

        System.out.println("Scene '" + name + "' completed: " +
                count + " actions applied.");
    }
}

public class P3_SmartLabControlPanel {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(ac, lights, projector);

        Scene lectureMode = new Scene("Lecture Mode");

        lectureMode.addStep(new SceneStep("Power", "ON"));
        lectureMode.addStep(new SceneStep("Brightness", "40"));
        lectureMode.addStep(new SceneStep("Temperature", "24"));

        lectureMode.execute(devices);

        if (!ac.apply("Temperature", "12"))
            System.out.println("Rejected: Lab AC temperature must be between 16°C and 30°C.");

        projector.addCapability(new BrightnessCapability());
        projector.apply("Brightness", "70");

        System.out.println("Projector: brightness set to 70%.");
    }
}