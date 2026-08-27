class Employee {
    String name;
    double baseSalary;

    Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    double calculateBonus() {
        return baseSalary * 0.05;
    }

    void printDetails() {
        System.out.println(name + " - Salary: " + baseSalary + ", Bonus: " + calculateBonus());
    }
}

class Manager extends Employee {
    Manager(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    double calculateBonus() {
        return baseSalary * 0.10;
    }
}

class Developer extends Employee {
    Developer(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    double calculateBonus() {
        return baseSalary * 0.08;
    }
}

public class F2_Employee {
    public static void main(String[] args) {
        Employee manager = new Manager("Arun", 60000);
        Employee developer = new Developer("Ravi", 50000);

        manager.printDetails();
        developer.printDetails();

        System.out.println(manager instanceof Employee);
        System.out.println(manager instanceof Manager);
        System.out.println(developer instanceof Developer);
    }
}