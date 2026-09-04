class BrokenStudent {
    static String name;
    static String regNo;
    static int attendance;

    BrokenStudent(String name, String regNo, int attendance) {
        BrokenStudent.name = name;
        BrokenStudent.regNo = regNo;
        BrokenStudent.attendance = attendance;
    }
}

class SrmStudentFixed {
    String name;
    String regNo;
    int attendance;

    static String university = "SRMIST";
    static int admissionCount = 0;

    SrmStudentFixed(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class P4_SrmStudentStatic {
    public static void main(String[] args) {
        BrokenStudent student1 = new BrokenStudent("Ravi", "RA231100301011", 82);
        BrokenStudent student2 = new BrokenStudent("Meera", "RA231100301012", 74);

        System.out.println("Broken version:");
        System.out.println(student1.name);
        System.out.println(student2.name);

        SrmStudentFixed student3 = new SrmStudentFixed("Ravi", 82);
        SrmStudentFixed student4 = new SrmStudentFixed("Meera", 74);

        System.out.println("Fixed version:");
        student3.printIdCard();
        student4.printIdCard();

        SrmStudentFixed.printTotalAdmissions();
    }
}