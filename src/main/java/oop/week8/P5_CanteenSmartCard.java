import java.util.*;

interface PricingPlan {
    double calculatePrice(double price);
    String getName();
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price;
    }

    public String getName() {
        return "Day Scholar";
    }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.90;
    }

    public String getName() {
        return "Hosteller";
    }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.80;
    }

    public String getName() {
        return "Staff";
    }
}

class Transaction {
    private double amount;
    private String description;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

class Purchase {
    private String item;
    private double chargedAmount;
    private boolean refunded;

    public Purchase(String item, double chargedAmount) {
        this.item = item;
        this.chargedAmount = chargedAmount;
        this.refunded = false;
    }

    public String getItem() {
        return item;
    }

    public double getChargedAmount() {
        return chargedAmount;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void markRefunded() {
        refunded = true;
    }
}

class SmartCard {
    private String cardId;
    private PricingPlan plan;
    private double balance;
    private boolean blocked;
    private List<Transaction> transactions = new ArrayList<>();

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
        this.balance = 0;
        this.blocked = false;
    }

    private void recordTransaction(double amount, String description) {
        balance += amount;
        transactions.add(new Transaction(amount, description));
    }

    public void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println("Top-up rejected: Minimum top-up is ₹100.");
            return;
        }

        if (balance + amount > 5000) {
            System.out.println("Top-up rejected: Maximum balance is ₹5000.");
            return;
        }

        recordTransaction(amount, "Top-up");

        System.out.printf("Top-up successful: +₹%.0f, balance ₹%.0f.%n",
                amount, balance);
    }

    public Purchase purchase(String item, double originalPrice) {
        if (blocked) {
            System.out.println("Purchase rejected: Card is blocked.");
            return null;
        }

        double chargedAmount = plan.calculatePrice(originalPrice);

        if (balance < chargedAmount) {
            System.out.printf(
                    "Purchase failed: required ₹%.0f, available ₹%.0f.%n",
                    chargedAmount, balance
            );
            return null;
        }

        recordTransaction(-chargedAmount, item);

        System.out.printf("%s purchased for ₹%.0f, balance ₹%.0f.%n",
                item, chargedAmount, balance);

        return new Purchase(item, chargedAmount);
    }

    public void refund(Purchase purchase) {
        if (purchase == null) {
            System.out.println("Refund rejected: Invalid purchase.");
            return;
        }

        if (purchase.isRefunded()) {
            System.out.println("Refund rejected: Purchase has already been refunded.");
            return;
        }

        recordTransaction(
                purchase.getChargedAmount(),
                "Refund - " + purchase.getItem()
        );

        purchase.markRefunded();

        System.out.printf("Refund successful: +₹%.0f, balance ₹%.0f.%n",
                purchase.getChargedAmount(), balance);
    }

    public void block() {
        blocked = true;
        System.out.println("Card " + cardId + " blocked.");
    }

    public void unblock() {
        blocked = false;
        System.out.println("Card " + cardId + " unblocked.");
    }

    public void miniStatement() {
        System.out.println("\nMini Statement - " + cardId);

        for (Transaction transaction : transactions) {
            System.out.printf("%s: %+.0f%n",
                    transaction.getDescription(),
                    transaction.getAmount());
        }

        System.out.printf("Balance: ₹%.0f%n", balance);
    }
}

public class P5_CanteenSmartCard {
    public static void main(String[] args) {
        SmartCard card = new SmartCard(
                "C-2045",
                new HostellerPlan()
        );

        card.topUp(500);

        Purchase vegThali = card.purchase("Veg Thali", 120);
        Purchase coldCoffee = card.purchase("Cold Coffee", 60);

        card.purchase("Large Meal", 400);

        card.refund(vegThali);
        card.refund(vegThali);

        card.miniStatement();
    }
}