
package oop.week12.assignment;

public class P4_LibraryReturnsByDueDay {

    static class Node {
        String title;
        int day;
        Node next;

        Node(String title, int day) {
            this.title = title;
            this.day = day;
        }
    }

    public static Node sortByDueDay(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = slow.next;
        slow.next = null;

        Node left = sortByDueDay(head);
        Node right = sortByDueDay(second);

        return merge(left, right);
    }

    static Node merge(Node left, Node right) {

        Node dummy = new Node("", 0);
        Node current = dummy;

        while (left != null && right != null) {

            if (left.day <= right.day) {
                current.next = left;
                left = left.next;
            }
            else {
                current.next = right;
                right = right.next;
            }

            current = current.next;
        }

        if (left != null) {
            current.next = left;
        }

        if (right != null) {
            current.next = right;
        }

        return dummy.next;
    }

    static void printList(Node head) {

        Node current = head;

        while (current != null) {
            System.out.print(current.title + ":" + current.day + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        Node head = new Node("Java", 15);
        head.next = new Node("DSA", 3);
        head.next.next = new Node("OS", 9);
        head.next.next.next = new Node("DBMS", 3);
        head.next.next.next.next = new Node("CN", 12);
        head.next.next.next.next.next = new Node("AI", 1);

        head = sortByDueDay(head);

        printList(head);
    }
}
