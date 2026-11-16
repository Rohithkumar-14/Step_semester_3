
package oop.week12.assignment;

public class P5_TokenMirrorCheck {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverse(Node head) {

        Node previous = null;
        Node current = head;

        while (current != null) {

            Node next = current.next;

            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }

    public static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(slow.next);

        Node first = head;
        Node second = secondHalf;

        boolean result = true;

        while (second != null) {

            if (first.data != second.data) {
                result = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        slow.next = reverse(secondHalf);

        return result;
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

        Node head = new Node(4);
        head.next = new Node(7);
        head.next.next = new Node(9);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(4);

        System.out.println("Palindrome: " + isPalindrome(head));

        System.out.print("List after check: ");
        printList(head);
    }
}
