interface Exportable {
    String exportData();
}

class ExportCounter {
    private static int totalExports = 0;

    static void increment() {
        totalExports++;
    }

    static int getTotalExports() {
        return totalExports;
    }
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportCounter.increment();
        String result = "Exported report: " + reportName;
        System.out.println(result);
        return result;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportCounter.increment();
        String result = "Exported profile: " + username;
        System.out.println(result);
        return result;
    }
}

class ExportManager {
    static void exportAll(Exportable[] items) {
        for (Exportable item : items)
            item.exportData();
    }

    static int getTotalExports() {
        return ExportCounter.getTotalExports();
    }
}

public class P2_DataExport {
    public static void main(String[] args) {
        ReportGenerator ref = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        ref.exportData();
        u.exportData();

        ExportManager.exportAll(new Exportable[]{ref, u});

        System.out.println("Total exports: " + ExportManager.getTotalExports());
    }
}