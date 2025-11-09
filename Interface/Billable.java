package Interface;

public interface Billable {
    void calculateCharges();

    void generateBill();

    void processPayment(double amount);
}
