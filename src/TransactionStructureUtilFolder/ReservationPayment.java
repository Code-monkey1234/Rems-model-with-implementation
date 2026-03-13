/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureUtilFolder;

public class ReservationPayment implements PaymentStrategy {

    private double reservationFee;

    // Initializes reservation payment with just the fee amount.
    // This is a one-time payment to hold a lot — not the full contract price.
    public ReservationPayment(double reservationFee) {
        this.reservationFee = reservationFee;
        // Simple single-field initialization — reservation payments are a flat fee.
    }

    // Returns the reservation fee as the amount due.
    // This fee is typically deducted from the down payment later.
    @Override
    public double calculateAmount() {
        System.out.println("Reservation Fee: " + reservationFee);
        return reservationFee;
        // Returns only the flat reservation fee — no calculation needed.
    }

    // Records the reservation fee payment and prints a confirmation.
    // Does not change lot status to SOLD — lot is only RESERVED at this stage.
    @Override
    public void processPayment() {
        System.out.println("Reservation fee of " + reservationFee + " processed.");
        // Confirms the fee is collected — lot status is handled separately by Reservation.approve().
    }

    // Returns a summary string showing this is a reservation payment and the fee amount.
    // Used in receipts and payment tracking panels.
    @Override
    public String getPaymentDetails() {
        return "Reservation Payment | Fee: " + reservationFee;
        // Simple one-liner — reservation payments have no installment or balance tracking.
    }

    // Getters and Setters
    public double getReservationFee() { return reservationFee; }
    public void setReservationFee(double fee) { this.reservationFee = fee; }
}