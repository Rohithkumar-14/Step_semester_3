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

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}

public class P2_FeeAccount {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA001", 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("RA002", 200000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA003", 180000, 20);

        plain.pay(150000);
        hostel.payInTwoInstallments(120000);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount account = (ScholarshipFeeAccount) accounts[i];
                System.out.println("Scholarship account effective due: Rs " + account.effectiveDue());
            } else if (accounts[i] instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + accounts[i].getDue());
            } else {
                System.out.println("Plain account due: Rs " + accounts[i].getDue());
            }
        }
    }
}