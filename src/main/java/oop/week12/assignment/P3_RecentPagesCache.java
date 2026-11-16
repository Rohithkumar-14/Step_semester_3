
package oop.week12.assignment;

import java.util.HashMap;

public class P3_RecentPagesCache {

    static class Node {
        int key;
        String page;
        Node prev;
        Node next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    static class RecentCache {

        int capacity;
        HashMap<Integer, Node> map;

        Node head;
        Node tail;

        int lastEvicted = -1;
        boolean lastUpdated = false;

        RecentCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();

            head = new Node(0, "");
            tail = new Node(0, "");

            head.next = tail;
            tail.prev = head;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        void addToFront(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        String get(int key) {

            if (!map.containsKey(key)) {
                return "-1";
            }

            Node node = map.get(key);

            remove(node);
            addToFront(node);

            return node.page;
        }

        void put(int key, String page) {

            lastEvicted = -1;
            lastUpdated = false;

            if (map.containsKey(key)) {

                Node node = map.get(key);
                node.page = page;

                remove(node);
                addToFront(node);

                lastUpdated = true;
                return;
            }

            if (map.size() == capacity) {

                Node leastRecent = tail.prev;

                lastEvicted = leastRecent.key;

                remove(leastRecent);
                map.remove(leastRecent.key);
            }

            Node newNode = new Node(key, page);

            addToFront(newNode);
            map.put(key, newNode);
        }

        void printCache() {

            System.out.print("Cache (most recent first): ");

            Node current = head.next;

            while (current != tail) {

                System.out.print(current.key + "=" + current.page);

                current = current.next;

                if (current != tail) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        RecentCache cache = new RecentCache(2);

        cache.put(1, "Home");
        System.out.println("put(1,Home)");

        cache.put(2, "Notes");
        System.out.println("put(2,Notes)");

        System.out.println("get(1) -> " + cache.get(1));

        cache.put(3, "Quiz");

        System.out.println("put(3,Quiz) evicted "
                + cache.lastEvicted);

        System.out.println("get(2) -> " + cache.get(2));

        cache.put(1, "Home2");

        System.out.println("put(1,Home2) updated");

        cache.put(4, "Mail");

        System.out.println("put(4,Mail) evicted "
                + cache.lastEvicted);

        System.out.println("get(3) -> " + cache.get(3));
        System.out.println("get(1) -> " + cache.get(1));

        cache.printCache();
    }
}
