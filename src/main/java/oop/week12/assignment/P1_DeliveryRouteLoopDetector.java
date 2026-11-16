
package oop.week12.assignment;

public class P1_DeliveryRouteLoopDetector {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static String detectLoop(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {

                int length = 1;
                Node temp = slow.next;

                while (temp != slow) {
                    length++;
                    temp = temp.next;
                }

                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return "Loop: yes, starts at stop "
                        + slow.data + ", length " + length;
            }
        }

        return "Loop: no";
    }

    public static void main(String[] args) {

        Node a = new Node(10);
        Node b = new Node(20);
        Node c = new Node(30);
        Node d = new Node(40);
        Node e = new Node(50);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        e.next = c;

        System.out.println(detectLoop(a));
    }
}
