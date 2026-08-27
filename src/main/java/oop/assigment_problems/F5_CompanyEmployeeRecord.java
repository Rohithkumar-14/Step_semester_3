class Company {
    String companyName;

    Company(String companyName) {
        this.companyName = companyName;
    }
}

class CompanyEmployee {
    String name;
    double salary;
    Company company;
    static int employeeCount = 0;

    CompanyEmployee(String name, double salary, Company company) {
        this.name = name;
        this.salary = salary;
        this.company = company;
        employeeCount++;
    }

    void showDetails() {
        if (company == null) {
            System.out.println(name + " - Salary: " + salary + " - No company");
        } else {
            System.out.println(name + " - Salary: " + salary + " - Company: " + company.companyName);
        }
    }

    static void showEmployeeCount() {
        System.out.println("Total employees: " + employeeCount);
    }
}

class ManagerEmployee extends CompanyEmployee {
    ManagerEmployee(String name, double salary, Company company) {
        super(name, salary, company);
    }

    void showManagerDetails() {
        System.out.println(name + " is a manager");
    }
}

public class F5_CompanyEmployeeRecord {
    public static void main(String[] args) {
        Company company = new Company("Tech Solutions");

        CompanyEmployee employee1 = new CompanyEmployee("Rohith", 50000, company);
        CompanyEmployee employee2 = new CompanyEmployee("Rahul", 55000, null);
        ManagerEmployee manager = new ManagerEmployee("Arun", 70000, company);

        employee1.showDetails();
        employee2.showDetails();
        manager.showDetails();
        manager.showManagerDetails();

        CompanyEmployee.showEmployeeCount();
    }
}