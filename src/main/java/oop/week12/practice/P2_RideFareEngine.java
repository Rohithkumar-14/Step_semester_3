
package oop.week12.practice;

abstract class Ride {
    double distance;

    Ride(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();
}

class Auto extends Ride {

    Auto(double distance) {
        super(distance);
    }

    double calculateFare() {
        return 30 + 15 * distance;
    }
}

class Cab extends Ride {

    double surge;

    Cab(double distance, double surge) {
        super(distance);
        this.surge = surge;
    }

    double calculateFare() {
        return (50 + 20 * distance) * surge;
    }
}

class Bike extends Ride {

    int waitingMinutes;

    Bike(double distance, int waitingMinutes) {
        super(distance);
        this.waitingMinutes = waitingMinutes;
    }

    double calculateFare() {
        return 20 + 10 * distance + 2 * waitingMinutes;
    }
}

public class P2_RideFareEngine {

    public static void main(String[] args) {

        Ride[] rides = new Ride[3];

        rides[0] = new Auto(4);
        rides[1] = new Cab(6, 1.5);
        rides[2] = new Bike(3, 5);

        double total = 0;

        for (int i = 0; i < rides.length; i++) {

            double fare = rides[i].calculateFare();

            if (rides[i] instanceof Auto) {
                System.out.println("Auto: " + fare);
            }
            else if (rides[i] instanceof Cab) {
                System.out.println("Cab: " + fare);
            }
            else {
                System.out.println("Bike: " + fare);
            }

            total += fare;
        }

        System.out.println("Total: " + total);
    }
}
