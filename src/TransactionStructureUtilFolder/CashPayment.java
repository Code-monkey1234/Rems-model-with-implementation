/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureUtilFolder;

import UserStructureMainFolder.Client;
import PropertyStructureMainFolder.Property;

public class CashPayment implements PaymentStrategy {

    private double totalContractPrice;
    private Client client;
    private Property property;

    // Initializes cash payment with the full contract price, buyer, and property.
    // TCP is the total amount due — no installments, paid in full at once.
    public CashPayment(double totalContractPrice, Client client, Property property) {
        this.totalContractPrice = totalContractPrice;
        this.client = client;
        this.property = property;
        // Full amount is set at creation — no calculations needed beyond discount.
    }

    // Returns the total contract price as the amount due for cash payment.
    // No monthly breakdown — the full TCP is what the client owes.
    @Override
    public double calculateAmount() {
        System.out.println("Total Contract Price (Cash): " + totalContractPrice);
        return totalContractPrice;
        // Cash payment amount is always the full TCP — no splitting or amortization.
    }

    // Marks the property as SOLD and triggers deed generation for the client.
    // Cash payment immediately transfers ownership — no waiting for installments.
    @Override
    public void processPayment() {
        property.sellLot();
        System.out.println("Cash payment of " + totalContractPrice + " processed for " + client.getName());
        triggerDeed();
        // Property is sold immediately on full cash payment — deed is generated right after.
    }

    // Returns a summary string showing the payment type and total amount.
    // Used in receipt generation and payment progress tracking.
    @Override
    public String getPaymentDetails() {
        return "Cash Payment | TCP: " + totalContractPrice + " | Client: " + client.getName();
        // One-liner summary — cash has no installment tracking, just the total amount.
    }

    // Prints a deed trigger notice for the client after full cash payment.
    // In the full system, this would call RealEstate.generateDeed(client, property).
    public void triggerDeed() {
        System.out.println("Deed of Absolute Sale will be generated for " + client.getName());
        // Signals that the admin should now generate and send the deed document.
    }

    // Getters and Setters
    public double getTotalContractPrice() { return totalContractPrice; }
    public void setTotalContractPrice(double tcp) { this.totalContractPrice = tcp; }
}