
package oop.week12.practice;

public class P1_OrderWalkthrough {

    static class Order {

        static class LineItem {
            String productName;
            int quantity;
            double unitPrice;

            LineItem(String productName, int quantity, double unitPrice) {
                this.productName = productName;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
            }

            double total() {
                return quantity * unitPrice;
            }
        }

        LineItem[] items = new LineItem[100];
        int count = 0;

        void addItem(LineItem item) {
            items[count] = item;
            count++;
        }

        class Cursor {
            int position = 0;

            boolean hasNext() {
                return position < count;
            }

            LineItem next() {
                return items[position++];
            }

            void reset() {
                position = 0;
            }
        }

        double orderTotal() {
            double total = 0;

            for (int i = 0; i < count; i++) {
                total += items[i].total();
            }

            return total;
        }
    }

    public static void main(String[] args) {

        Order order = new Order();

        order.addItem(new Order.LineItem("Pen", 3, 10));
        order.addItem(new Order.LineItem("Book", 2, 250));
        order.addItem(new Order.LineItem("Bag", 1, 700));

        Order.Cursor cursor = order.new Cursor();

        while (cursor.hasNext()) {
            Order.LineItem item = cursor.next();

            System.out.println(item.productName + " " + item.total());
        }

        System.out.println("Total " + order.orderTotal());

        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
    }
}
