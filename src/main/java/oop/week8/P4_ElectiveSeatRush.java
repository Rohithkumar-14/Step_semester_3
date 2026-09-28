import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
    String getName();
}

class RegularPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 24;
    }

    public String getName() {
        return "Regular";
    }
}

class HonorsPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 28;
    }

    public String getName() {
        return "Honors";
    }
}

class ExchangePolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 20;
    }

    public String getName() {
        return "Exchange";
    }
}

class Student {
    private String name;
    private CreditPolicy policy;
    private int currentCredits;

    public Student(String name, CreditPolicy policy, int currentCredits) {
        this.name = name;
        this.policy = policy;
        this.currentCredits = currentCredits;
    }

    public String getName() {
        return name;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCreditLimit() {
        return policy.getCreditLimit();
    }

    public String getPolicyName() {
        return policy.getName();
    }

    public boolean canAddCredits(int credits) {
        return currentCredits + credits <= getCreditLimit();
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Elective {
    private String name;
    private int credits;
    private int capacity;
    private List<Student> enrolled = new ArrayList<>();
    private Queue<Student> waitlist = new LinkedList<>();

    public Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public boolean isEnrolled(Student student) {
        return enrolled.contains(student);
    }

    public boolean isWaiting(Student student) {
        return waitlist.contains(student);
    }

    public void enroll(Student student) {
        if (isEnrolled(student) || isWaiting(student)) {
            System.out.println("Enrollment failed: " + student.getName() +
                    " is already enrolled or waiting.");
            return;
        }

        if (!student.canAddCredits(credits)) {
            System.out.println("Enrollment failed: " + student.getName() +
                    " would exceed the " + student.getPolicyName() +
                    " credit limit (" +
                    (student.getCurrentCredits() + credits) + "/" +
                    student.getCreditLimit() + ").");
            return;
        }

        if (enrolled.size() < capacity) {
            enrolled.add(student);
            student.addCredits(credits);

            System.out.println(student.getName() +
                    " enrolled in " + name +
                    " (credits: " +
                    student.getCurrentCredits() + "/" +
                    student.getCreditLimit() + ").");
        } else {
            waitlist.add(student);

            System.out.println(name + " is full.");
            System.out.println(student.getName() +
                    " added to waitlist (position " +
                    waitlist.size() + ").");
        }
    }

    public void drop(Student student) {
        if (!enrolled.remove(student)) {
            System.out.println("Drop failed: " + student.getName() +
                    " is not enrolled.");
            return;
        }

        student.removeCredits(credits);

        System.out.println(student.getName() +
                " dropped " + name +
                " (credits: " +
                student.getCurrentCredits() + "/" +
                student.getCreditLimit() + ").");

        promoteNext();
    }

    private void promoteNext() {
        if (enrolled.size() >= capacity)
            return;

        Student selected = null;
        int size = waitlist.size();

        for (int i = 0; i < size; i++) {
            Student student = waitlist.poll();

            if (selected == null && student.canAddCredits(credits)) {
                selected = student;
            } else {
                waitlist.add(student);
            }
        }

        if (selected != null) {
            enrolled.add(selected);
            selected.addCredits(credits);

            System.out.println(selected.getName() +
                    " promoted from waitlist and enrolled in " +
                    name + " (credits: " +
                    selected.getCurrentCredits() + "/" +
                    selected.getCreditLimit() + ").");
        }
    }
}

class EnrollmentService {
    public void enroll(Student student, Elective elective) {
        elective.enroll(student);
    }

    public void drop(Student student, Elective elective) {
        elective.drop(student);
    }
}

public class P4_ElectiveSeatRush {
    public static void main(String[] args) {
        Elective cloudComputing = new Elective(
                "Cloud Computing",
                4,
                2
        );

        Student asha = new Student(
                "Asha",
                new RegularPolicy(),
                20
        );

        Student ravi = new Student(
                "Ravi",
                new HonorsPolicy(),
                22
        );

        Student neha = new Student(
                "Neha",
                new ExchangePolicy(),
                12
        );

        Student kiran = new Student(
                "Kiran",
                new RegularPolicy(),
                22
        );

        EnrollmentService service = new EnrollmentService();

        service.enroll(asha, cloudComputing);
        service.enroll(ravi, cloudComputing);
        service.enroll(neha, cloudComputing);
        service.enroll(kiran, cloudComputing);

        service.drop(asha, cloudComputing);
    }
}