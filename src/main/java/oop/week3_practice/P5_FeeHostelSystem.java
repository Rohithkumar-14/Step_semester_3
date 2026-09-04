class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {
    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot() {
        if (occupied < beds) {
            occupied++;
        }
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;
        totalStudents++;
    }

    String fullStatus() {
        String roomNumber;

        if (room == null) {
            roomNumber = "unallotted";
        } else {
            roomNumber = room.roomNo;
        }

        return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomNumber;
    }
}

public class P5_FeeHostelSystem {
    public static void main(String[] args) {
        HostelFeeAccount fee1 = new HostelFeeAccount("RA001", 200000);
        HostelFeeAccount fee2 = new HostelFeeAccount("RA002", 200000);
        HostelFeeAccount fee3 = new HostelFeeAccount("RA003", 200000);

        SrmStudent student1 = new SrmStudent("Ravi", "RA001", fee1);
        SrmStudent student2 = new SrmStudent("Anitha", "RA002", fee2);
        SrmStudent student3 = new SrmStudent("Karthik", "RA003", fee3);

        HostelRoom room1 = new HostelRoom("C-214", 3, 0);
        HostelRoom room2 = new HostelRoom("C-507", 2, 0);

        room1.allot();
        student1.room = room1;

        room2.allot();
        student2.room = room2;

        fee1.pay(60000);
        fee2.pay(20000);
        fee3.pay(-5000);

        System.out.println(student1.fullStatus());
        System.out.println(student2.fullStatus());
        System.out.println(student3.fullStatus());

        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}