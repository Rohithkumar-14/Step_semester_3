
package oop.week12.assignment;

public class P2_ExamScriptBatches {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node reverseInGroups(Node head, int k) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node previousGroup = dummy;

        while (true) {

            Node kth = previousGroup;

            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            if (kth == null) {
                break;
            }

            Node nextGroup = kth.next;

            Node previous = nextGroup;
            Node current = previousGroup.next;

            while (current != nextGroup) {

                Node next = current.next;

                current.next = previous;
                previous = current;
                current = next;
            }

            Node oldStart = previousGroup.next;

            previousGroup.next = kth;
            previousGroup = oldStart;
        }

        return dummy.next;
    }

    static void printList(Node head) {

        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        head = reverseInGroups(head, 3);

        printList(head);
    }
}
