/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureUtilFolder;

import UserStructureMainFolder.Client;
import PropertyStructureMainFolder.Property;

public class InstallmentPayment implements PaymentStrategy {

    private double monthlyAmortization;
    private int monthsPaid;
    private int totalMonths;
    private double remainingBalance;
    private Client client;
    private Property property;

    // Initializes installment payment with monthly amount, total duration, total balance, buyer, and property.
    // monthsPaid starts at 0 — incremented each time processPayment() is called.
    public InstallmentPayment(double monthlyAmortization, int totalMonths,
                               double totalBalance, Client client, Property property) {
        this.monthlyAmortization = monthlyAmortization;
        this.totalMonths = totalMonths;
        this.remainingBalance = totalBalance;
        this.monthsPaid = 0;
        this.client = client;
        this.property = property;
        // Starting state — no payments made yet, full balance outstanding.
    }

    // Returns the monthly amortization amount due for this installment period.
    // This is what gets recorded as amountPaid in Payment each time a payment is made.
    @Override
    public double calculateAmount() {
        System.out.println("Monthly Amortization: " + monthlyAmortization);
        return monthlyAmortization;
        // Each call returns the same monthly amount — consistent across all installment periods.
    }

    // Deducts one month's amortization from the remaining balance and increments monthsPaid.
    // If fully paid, marks the property as SOLD and triggers deed generation.
    @Override
    public void processPayment() {
        remainingBalance -= monthlyAmortization;
        monthsPaid++;
        System.out.println("Payment processed. Months paid: " + monthsPaid + "/" + totalMonths);
        System.out.println("Remaining balance: " + remainingBalance);
        if (isFullyPaid()) {
            triggerDeed();
        }
        // Each call represents one monthly payment — tracks progress until fully paid.
    }

    // Returns a full progress summary including monthly amount, months paid, and remaining balance.
    // Used by Agent.viewClientProgress() and Client.trackPaymentProgress().
    @Override
    public String getPaymentDetails() {
        return "Installment | Monthly: " + monthlyAmortization +
               " | Paid: " + monthsPaid + "/" + totalMonths +
               " | Remaining: " + remainingBalance;
        // Shows current payment standing — all info needed to track installment progress.
    }

    // Returns true if the client has paid all months or the balance is zero or below.
    // Checked after every processPayment() call to trigger deed when complete.
    public boolean isFullyPaid() {
        return monthsPaid >= totalMonths || remainingBalance <= 0;
        // Two conditions — either all months completed or balance fully cleared.
    }

    // Marks the property as SOLD and prints a deed generation notice for the client.
    // Called automatically when isFullyPaid() returns true after the final payment.
    public void triggerDeed() {
        property.sellLot();
        System.out.println("Fully paid! Deed of Absolute Sale will be generated for " + client.getName());
        // Property ownership officially transfers — admin will generate and send the deed.
    }

    // Getters and Setters
    public double getMonthlyAmortization() { return monthlyAmortization; }
    public int getMonthsPaid() { return monthsPaid; }
    public int getTotalMonths() { return totalMonths; }
    public double getRemainingBalance() { return remainingBalance; }

    public void setMonthlyAmortization(double ma) { this.monthlyAmortization = ma; }
    public void setTotalMonths(int months) { this.totalMonths = months; }
}