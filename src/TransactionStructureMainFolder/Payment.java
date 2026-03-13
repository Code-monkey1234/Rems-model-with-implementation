/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureMainFolder;

import UserStructureMainFolder.Client;
import PropertyStructureMainFolder.Property;
import TransactionStructureUtilFolder.PaymentStrategy;
import TransactionStructureEnumFolder.PaymentOptions;

public class Payment {

    private int paymentID;
    private double amountPaid;
    private PaymentOptions paymentType;
    private Client client;
    private Property property;
    private PaymentStrategy strategy;
    private String paidAt;

    // Initializes payment with client, property, and the chosen payment strategy.
    // paidAt is auto-set to today's date — amountPaid is calculated during executePayment().
    public Payment(int paymentID, Client client, Property property, PaymentStrategy strategy) {
        this.paymentID = paymentID;
        this.client = client;
        this.property = property;
        this.strategy = strategy;
        this.paidAt = java.time.LocalDate.now().toString();
        // Strategy pattern — the actual payment behavior depends on the strategy injected here.
    }

    // Runs the full payment workflow: calculates amount, processes payment, generates receipt, notifies parties.
    // This is the main method called when a client makes a payment.
    public void executePayment() {
        this.amountPaid = strategy.calculateAmount();
        strategy.processPayment();
        generateReceipt();
        notifyParties();
        // Delegates calculation and processing to the strategy — then handles receipt and notification.
    }

    // Prints a notification confirming payment details were sent to relevant parties.
    // In JFrame, this will send Notification objects to client, agent, and admin.
    public void notifyParties() {
        System.out.println("Payment ID " + paymentID + " processed for " + client.getName() +
                           " | Amount: " + amountPaid + " | Date: " + paidAt);
        // Broadcasts payment confirmation — all parties are informed of the transaction.
    }

    // Creates and returns a Receipt object containing payment summary details.
    // Called automatically at the end of executePayment() to record the transaction.
    public Receipt generateReceipt() {
        Receipt receipt = new Receipt(
            (int)(Math.random() * 9000) + 1000,
            amountPaid,
            this
        );
        System.out.println("Receipt generated for payment ID: " + paymentID);
        return receipt;
        // Generates a receipt with a random ID, the paid amount, and this payment as reference.
    }

    // Getters and Setters
    public int getPaymentID() { return paymentID; }
    public double getAmountPaid() { return amountPaid; }
    public PaymentOptions getPaymentType() { return paymentType; }
    public Client getClient() { return client; }
    public Property getProperty() { return property; }
    public PaymentStrategy getStrategy() { return strategy; }
    public String getPaidAt() { return paidAt; }

    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
    public void setPaymentType(PaymentOptions type) { this.paymentType = type; }
    public void setStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
}