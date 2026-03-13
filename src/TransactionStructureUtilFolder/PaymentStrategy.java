/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TransactionStructureUtilFolder;

// Interface that defines the contract for all payment types.
// CashPayment, InstallmentPayment, and ReservationPayment all implement this.
// Allows Payment.java to work with any payment type without knowing the specifics.
public interface PaymentStrategy {

    // Returns the calculated amount for this payment type.
    // Cash returns TCP, Installment returns monthly amount, Reservation returns the fee.
    double calculateAmount();

    // Executes the actual payment processing logic for this type.
    // Updates property status, tracks months paid, or records reservation fee.
    void processPayment();

    // Returns a formatted summary string of this payment's current details.
    // Used by viewClientProgress() and trackPaymentProgress() to display status.
    String getPaymentDetails();
}