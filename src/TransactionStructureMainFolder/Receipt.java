/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureMainFolder;

public class Receipt {

    private int receiptID;
    private double amount;
    private Payment payment;

    // Initializes the receipt with an ID, the amount paid, and a reference to the payment.
    // Created automatically by Payment.generateReceipt() after a successful transaction.
    public Receipt(int receiptID, double amount, Payment payment) {
        this.receiptID = receiptID;
        this.amount = amount;
        this.payment = payment;
        // Stores a back-reference to the payment so receipt details can pull transaction info.
    }

    // Returns a formatted string summary of the receipt including ID, amount, and payment date.
    // In JFrame, this will populate a receipt detail panel or be printed as a PDF.
    public String getDetails() {
        return "Receipt ID: " + receiptID +
               " | Amount Paid: " + amount +
               " | Client: " + payment.getClient().getName() +
               " | Lot ID: " + payment.getProperty().getLotID() +
               " | Date: " + payment.getPaidAt();
        // Pulls client name, lot ID, and date from the referenced payment for a full summary.
    }

    // Getters
    public int getReceiptID() { return receiptID; }
    public double getAmount() { return amount; }
    public Payment getPayment() { return payment; }
}