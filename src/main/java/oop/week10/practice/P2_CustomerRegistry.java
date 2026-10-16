package oop.week10.practice;

import java.util.HashSet;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer c = (Customer) obj;
        return id == c.id && name.equals(c.name);
    }

    public int hashCode() {
        return id + name.hashCode();
    }
}

public class P2_CustomerRegistry {
    public static void main(String[] args) {
        HashSet<Customer> customers = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println(customers.add(c1));

        if (!customers.add(c2)) {
            System.out.println("false (duplicate rejected)");
        } else {
            System.out.println("true");
        }

        System.out.println(customers.add(c3));
        System.out.println("unique count " + customers.size());
        System.out.println("contains: " + customers.contains(c1));
    }
}