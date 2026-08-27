class Car {
    String registrationNumber;
    String ownerName;

    Car(String registrationNumber, String ownerName) {
        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
    }
}

class ParkingSlot {
    int slotNumber;
    Car parkedCar;

    ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    void parkCar(Car car) {
        parkedCar = car;
    }

    void removeCar() {
        parkedCar = null;
    }

    void showStatus() {
        if (parkedCar == null) {
            System.out.println("Slot " + slotNumber + " is empty");
        } else {
            System.out.println("Slot " + slotNumber + " occupied by " + parkedCar.registrationNumber + " - " + parkedCar.ownerName);
        }
    }
}

public class F3_ParkingSlot {
    public static void main(String[] args) {
        ParkingSlot slot = new ParkingSlot(101);
        Car car = new Car("TN07MS0007", "Rohith");

        slot.showStatus();
        slot.parkCar(car);
        slot.showStatus();
        slot.removeCar();
        slot.showStatus();
    }
}