class LibraryMember {
    String name;
    int memberId;
    static int totalMembers = 0;

    LibraryMember(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        totalMembers++;
    }

    void showDetails() {
        System.out.println(name + " - Member ID: " + memberId);
    }

    static void showTotalMembers() {
        System.out.println("Total members: " + totalMembers);
    }
}

public class F4_LibraryMember {
    public static void main(String[] args) {
        LibraryMember member1 = new LibraryMember("Rohith", 101);
        LibraryMember member2 = new LibraryMember("Rahul", 102);
        LibraryMember member3 = new LibraryMember("Arun", 103);

        member1.showDetails();
        member2.showDetails();
        member3.showDetails();

        LibraryMember.showTotalMembers();
    }
}