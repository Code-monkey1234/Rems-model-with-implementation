/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserStructureMainFolder;

import java.util.ArrayList;
import java.util.List;

import TransactionStructureMainFolder.Payment;
import TransactionStructureMainFolder.Inquiry;
import TransactionStructureMainFolder.Reservation;
import TransactionStructureEnumFolder.PaymentOptions;
import TransactionStructureMainFolder.Document;
import PropertyStructureMainFolder.Property;
import PropertyStructureMainFolder.Block;

public class Client extends User {

    private double budget;
    private double amount;
    private PaymentOptions paymentMode;
    private Agent agent;
    private Payment payment;
    private Reservation reservation;
    private List<Inquiry> myInquiries = new ArrayList<>();
    private List<Reservation> myReservations = new ArrayList<>();
    private List<Payment> myPayments = new ArrayList<>();

    //pag nag register, gagawa ng client gamit ung constructor ng user:
    // Passes all registration fields up to User's constructor via super().
    // Client has no extra fields at registration — budget/amount are set later via setters.
    public Client(int userID, String name, String email, int phoneNumber, String password) {
        super(userID, name, email, phoneNumber, password);
    }

    // dko sure dito. Parang search filter na dropdown ng sizes then pag click, ung size na 
    //nakalagay don sa button is ung magiging input sa (double lotSize)
    public Property searchLot(double lotSize) {
        if (agent == null || agent.getRealEstate() == null) return null;
        for (Block block : agent.getRealEstate().getSubdivision().getAllBlocks()) {
            for (Property p : block.getLots()) {
                if (p.checkAvailability() && p.getLotArea() == lotSize) {
                    return p;
                }
            }
        }
        return null;
        // Loops through all blocks and lots to find an available property matching the size.
    }

    
    //ito ganon din sa search filter ng searchLot. Can be anything, dropdown, para multiple choice pero isa lang pwedeng ma check
    public Block searchLocation(int blockNum) {
        if (agent == null || agent.getRealEstate() == null) return null;
        for (Block block : agent.getRealEstate().getSubdivision().getAllBlocks()) {
            if (block.getBlockID() == blockNum) {
                return block;
            }
        }
        return null;
        // Iterates all blocks in the subdivision to match by blockID.
    }

    //dito range, tayo na mag set,
    // Filters and returns all available properties whose price is within the client's budget.
    public List<Property> budgetFilter() {
        List<Property> affordable = new ArrayList<>();
        if (agent == null || agent.getRealEstate() == null) return affordable;
        for (Block block : agent.getRealEstate().getSubdivision().getAllBlocks()) {
            for (Property p : block.getLots()) {
                if (p.checkAvailability() && p.getPrice() <= budget) {
                    affordable.add(p);
                }
            }
        }
        return affordable;
        // Collects all available properties priced at or below the client's set budget.
    }

    // Prints the details of the client's currently active reservation and payment.
    // Sa JFrame, mag populate ito ng details panel
    public void viewProperty() {
        if (reservation != null) {
            System.out.println("Block: " + reservation.getBlock() + " | Lot: " + reservation.getLot());
        }
        if (payment != null) {
            System.out.println(payment.getStrategy().getPaymentDetails());
        }
        // Displays the reserved property and associated payment details for the client.
    }

    // Creates and submits a new Reservation for the specified block and lot,
    // then adds it to the client's reservation list and notifies the agent.
    public void sendRequest(int reservationID, int block, int lot) {
        Reservation newReservation = new Reservation(reservationID, this, block, lot);
        myReservations.add(newReservation);
        this.reservation = newReservation;
        System.out.println("Reservation request sent for Block " + block + " Lot " + lot);
        // Creates a PENDING reservation and stores it — agent will then process it.
    }

    // Executes the client's active payment through the assigned payment strategy.
    // Triggers amount calculation, payment processing, receipt generation, and party notification.
    public void makePayment() {
        if (payment != null) {
            payment.executePayment();
            myPayments.add(payment);
        }
        // Delegates to Payment.executePayment() which runs the full payment workflow.
    }

    // Returns a formatted string showing the client's name and email.
    // Used for display in tables or detail panels in the JFrame layer.
    public String showClientDetails() {
        return "Client: " + getName() + " | Email: " + getEmail();
        // Simple summary of client identity — can be expanded with budget or payment mode.
    }

    // Sets the client's active reservation status to CANCELLED and removes it from the list.
    // Prints a confirmation of the cancellation.
    public void cancelReservation() {
        if (reservation != null) {
            reservation.cancel();
            myReservations.remove(reservation);
            this.reservation = null;
        }
        // Cancels the active reservation and cleans up the client's reservation reference.
    }

    // Prints current payment progress for installment clients —
    // shows months paid, remaining balance, and whether fully paid.
    public void trackPaymentProgress() {
        if (payment == null) {
            System.out.println("No active payment.");
            return;
        }
        System.out.println("Payment Progress for " + getName() + ":");
        System.out.println(payment.getStrategy().getPaymentDetails());
        // Delegates to the payment strategy's getPaymentDetails() for current progress info.
    }

    // Simulates downloading a document by printing its type and file path.
    // In JFrame, this will open a file dialog or trigger a file download action.
    public void downloadDocument(Document document) {
        System.out.println("Downloading: " + document.getType() + " from " + document.getFilePath());
        // Prints document info — JFrame layer will handle actual file access.
    }

    // Getters and Setters
    public double getBudget() { return budget; }
    public double getAmount() { return amount; }
    public PaymentOptions getPaymentMode() { return paymentMode; }
    public Agent getAgent() { return agent; }
    public Payment getPayment() { return payment; }
    public Reservation getReservation() { return reservation; }
    public List<Inquiry> getMyInquiries() { return myInquiries; }
    public List<Reservation> getMyReservations() { return myReservations; }
    public List<Payment> getMyPayments() { return myPayments; }

    public void setBudget(double budget) { this.budget = budget; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setPaymentMode(PaymentOptions paymentMode) { this.paymentMode = paymentMode; }
    public void setAgent(Agent agent) { this.agent = agent; }
    public void setPayment(Payment payment) { this.payment = payment; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }
}