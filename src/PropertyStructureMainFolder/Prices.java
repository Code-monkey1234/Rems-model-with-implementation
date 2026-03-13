/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PropertyStructureMainFolder;

public class Prices {

    private double totalContractPrice;
    private Property property;
    private double reservationFee;
    private double discount;
    private double downPaymentPercent;
    private int installmentMonths;
    private double interestRate;
    private double netDP;
    private double monthlyAmort;

    // Initializes all pricing parameters for a property.
    // These values are used to compute reservation fee, down payment, and monthly amortization.
    public Prices(double totalContractPrice, double reservationFee, double discount,
                  double downPaymentPercent, int installmentMonths, double interestRate) {
        this.totalContractPrice = totalContractPrice;
        this.reservationFee = reservationFee;
        this.discount = discount;
        this.downPaymentPercent = downPaymentPercent;
        this.installmentMonths = installmentMonths;
        this.interestRate = interestRate;
        // All pricing variables set at creation — calculations are done via the compute methods below.
    }

    // Returns the total cash price after deducting any discount from the TCP.
    // Used for CashPayment — this is the final amount a cash buyer pays.
    public double calculateTotalCashPrice() {
        double tcp = totalContractPrice - discount;
        System.out.println("Total Cash Price: " + tcp);
        return tcp;
        // Discount is subtracted from TCP — result is the full amount due for cash buyers.
    }

    // Returns the flat reservation fee for holding the lot.
    // This amount is later deducted from the net down payment.
    public double calculateReservationFee() {
        System.out.println("Reservation Fee: " + reservationFee);
        return reservationFee;
        // Reservation fee is a fixed amount — no calculation needed, just returned as-is.
    }

    // Calculates the net down payment by taking the down payment percentage of TCP
    // and subtracting the already-paid reservation fee.
    public double calculateDownPayment() {
        netDP = (totalContractPrice * downPaymentPercent / 100) - reservationFee;
        System.out.println("Net Down Payment: " + netDP);
        return netDP;
        // Reservation fee is credited toward down payment — client pays the remaining net DP.
    }

    // Calculates the monthly amortization for the installment balance remaining after down payment.
    // Applies interest to the remaining balance and divides by the number of installment months.
    public double calculateMonthlyAmortization() {
        double balance = totalContractPrice - (totalContractPrice * downPaymentPercent / 100);
        monthlyAmort = (balance * (1 + interestRate)) / installmentMonths;
        System.out.println("Monthly Amortization: " + monthlyAmort);
        return monthlyAmort;
        // Balance after DP is computed, interest is applied, then divided evenly over all months.
    }

    // Getters and Setters
    public double getTotalContractPrice() { return totalContractPrice; }
    public double getReservationFee() { return reservationFee; }
    public double getDiscount() { return discount; }
    public double getDownPaymentPercent() { return downPaymentPercent; }
    public int getInstallmentMonths() { return installmentMonths; }
    public double getInterestRate() { return interestRate; }
    public double getNetDP() { return netDP; }
    public double getMonthlyAmort() { return monthlyAmort; }

    public void setTotalContractPrice(double tcp) { this.totalContractPrice = tcp; }
    public void setReservationFee(double fee) { this.reservationFee = fee; }
    public void setDiscount(double discount) { this.discount = discount; }
}