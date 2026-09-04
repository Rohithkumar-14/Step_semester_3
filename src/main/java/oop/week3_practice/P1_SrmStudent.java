class SrmStudent {
    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    static double classAverage(SrmStudent[] students) {
        int total = 0;

        for (int i = 0; i < students.length; i++) {
            total += students[i].attendance;
        }

        return (double) total / students.length;
    }
}

public class P1_SrmStudent {
    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA231100301011", 82),
            new SrmStudent("Anitha", "RA231100301012", 68),
            new SrmStudent("Karthik", "RA231100301013", 91),
            new SrmStudent("Meera", "RA231100301014", 74),
            new SrmStudent("Suresh", "RA231100301015", 60)
        };

        for (int i = 0; i < students.length; i++) {
            if (students[i].isEligible()) {
                System.out.println(students[i].name + " - " + students[i].attendance + "% - Eligible");
            } else {
                System.out.println(students[i].name + " - " + students[i].attendance + "% - Detained");
            }
        }

        System.out.println("Class average: " + SrmStudent.classAverage(students) + "%");
    }
}