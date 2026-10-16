package oop.week10.practice;

class Resource {
    String id;
    String type;
    boolean canReserve;
    boolean canDownload;

    Resource(String id, String type, boolean canReserve, boolean canDownload) {
        this.id = id;
        this.type = type;
        this.canReserve = canReserve;
        this.canDownload = canDownload;
    }
}

public class P5_LibraryPlatform {
    static Resource[] resources = new Resource[100];
    static int count = 0;

    static void add(Resource r) {
        int i = 0;

        while (i < count && resources[i].id.compareTo(r.id) < 0) {
            i++;
        }

        if (i < count && resources[i].id.equals(r.id)) {
            System.out.println("duplicate rejected");
            return;
        }

        for (int j = count; j > i; j--) {
            resources[j] = resources[j - 1];
        }

        resources[i] = r;
        count++;
        System.out.println(r.id + " added");
    }

    static int find(String id) {
        for (int i = 0; i < count; i++) {
            if (resources[i].id.equals(id)) {
                return i;
            }
        }

        return -1;
    }

    static void reserve(String id, String member) {
        int index = find(id);

        if (index == -1) {
            System.out.println(id + " not found");
        } else if (resources[index].canReserve) {
            System.out.println(id + " reserved for " + member);
        } else {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    static void download(String id) {
        int index = find(id);

        if (index == -1) {
            System.out.println(id + " not found");
        } else if (resources[index].canDownload) {
            System.out.println(id + " downloaded");
        } else {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    public static void main(String[] args) {
        add(new Resource("B1", "Book", true, false));
        add(new Resource("B1", "Book", true, false));
        add(new Resource("E1", "EBook", true, true));

        reserve("B1", "M1");
        download("B1");
        download("E1");

        int index = find("E1");

        if (index != -1) {
            System.out.println("E1 found at index " + index);
        } else {
            System.out.println("E1 not found");
        }
    }
}