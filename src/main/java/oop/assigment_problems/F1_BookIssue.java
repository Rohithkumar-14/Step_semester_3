class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }
        return 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (int i = 0; i < issues.length; i++) {
            total += issues[i].fineAmount();
        }

        return total;
    }
}

 class F1_BookIssue {
    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Rohith", 18),
            new BookIssue("Effective Java", "Rohith", 5),
            new BookIssue("Refactoring", "Rohith", 0),
            new BookIssue("DSA Handbook", "Rohith", 21),
            new BookIssue("Design Patterns", "Rohith", 9)
        };

        for (int i = 0; i < issues.length; i++) {
            if (issues[i].isSeverelyOverdue()) {
                System.out.println(issues[i].title + " - " + issues[i].daysOverdue + " days - Severely overdue");
            } else {
                System.out.println(issues[i].title + " - " + issues[i].daysOverdue + " days - OK");
            }
        }

        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}