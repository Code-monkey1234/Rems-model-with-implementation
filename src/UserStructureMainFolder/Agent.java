
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserStructureMainFolder;

import TransactionStructureMainFolder.Inquiry;
import TransactionStructureMainFolder.Reservation;
import TransactionStructureMainFolder.Payment;
import TransactionStructureMainFolder.Document;

import java.util.ArrayList;
import java.util.List;

public class Agent extends User {

    private Client buyer;
    private RealEstate realEstate;
    private List<Inquiry> inquiryTable = new ArrayList<>();
    private List<Reservation> reservationTable = new ArrayList<>();
    private List<Payment> paymentsTable = new ArrayList<>();

    // Passes all registration fields up to User's constructor via super().
    // Agent has no extra unique fields at registration.
    public Agent(int userID, String name, String email, int phoneNumber, String password) {
        super(userID, name, email, phoneNumber, password);
    }

    // Returns the agent's full list of received inquiries from clients.
    // In JFrame, this list will populate an inquiry table for the agent to review.
    public List<Inquiry> viewInquiry() {
        return inquiryTable;
        // Returns the current inquiry list — agent can loop through and respond to each.
    }

    // Adds an inquiry to the agent's inquiry table when a client sends one.
    // Called when a client submits a new inquiry directed at this agent.
    public void receiveInquiry(Inquiry inquiry) {
        inquiryTable.add(inquiry);
        System.out.println("New inquiry received from " + inquiry.getClient().getName());
        // Stores the inquiry so the agent can later view and respond to it.
    }

    // Responds to a specific inquiry by setting its response text and printing confirmation.
    // The response is stored in the Inquiry object for the client to view later.
    public void sendDetails(Inquiry inquiry, String response) {
        inquiry.setResponse(response);
        System.out.println("Response sent to " + inquiry.getClient().getName() + ": " + response);
        // Sets the response on the inquiry — client calls viewResponse() to see it.
    }

    // Returns the agent's full list of reservation requests from clients.
    // In JFrame, this populates a reservation request table for the agent to process.
    public List<Reservation> viewRequests() {
        return reservationTable;
        // Returns all reservations assigned to this agent — pending, approved, or rejected.
    }

    // Forwards a reservation to the admin (RealEstate) for final approval.
    // Adds the reservation to the agent's table and notifies the admin.
    public void sendRequest(Reservation reservation) {
        reservationTable.add(reservation);
        System.out.println("Reservation ID " + reservation.getReservationID() + " forwarded to admin for approval.");
        // Agent acts as intermediary — receives from client, forwards to admin.
    }

    // Displays progress of all clients' payments handled by this agent.
    // Loops through the payments table and prints each payment's strategy details.
    public void viewClientProgress() {
        System.out.println("=== Client Payment Progress ===");
        for (Payment p : paymentsTable) {
            System.out.println(p.getClient().getName() + ": " + p.getStrategy().getPaymentDetails());
        }
        // Iterates all tracked payments and shows the current status for each client.
    }

    // Returns a formatted string showing the agent's name and email.
    // Used for display in tables or profile panels in the JFrame layer.
    public String showAgentDetails() {
        return "Agent: " + getName() + " | Email: " + getEmail();
        // Simple summary of agent identity — can be expanded with assigned clients.
    }

    // Sends a document to the assigned client by printing document info.
    // In JFrame, this will trigger a file transfer or notification to the client.
    public void sendDoc(Document document) {
        if (buyer != null) {
            System.out.println("Sending " + document.getType() + " to " + buyer.getName());
            buyer.addNotification(new TransactionStructureMainFolder.Notification(
                document.getDocumentID(), "Document sent: " + document.getType(),
                this, buyer,
                TransactionStructureEnumFolder.NotificationType.DOCUMENT,
                document.getDocumentID()
            ));
        }
        // Delivers the document to the client and sends a notification for confirmation.
    }

    // Adds the reservation to the agent's table and forwards it to admin for approval.
    // Also notifies the client that the reservation is being processed.
    public void processReservation(Reservation reservation) {
        reservationTable.add(reservation);
        reservation.notifyParties();
        System.out.println("Processing reservation ID " + reservation.getReservationID());
        // Records the reservation and triggers notification to all involved parties.
    }

    // Adds a confirmed payment to the agent's payment table and notifies admin.
    // Called after client makes payment — agent tracks it and informs admin.
    public void confirmPayment(Payment payment) {
    paymentsTable.add(payment);
    System.out.println("Agent " + getName() + " confirmed payment ID " + payment.getPaymentID());
    System.out.println("Forwarding to admin for final approval...");
    // In JFrame, this triggers a notification to admin
    realEstate.confirmPayment(payment);
    // calls admin's confirmPayment automatically after agent confirms
    }

    // Getters and Setters
    public Client getBuyer() { return buyer; }
    public RealEstate getRealEstate() { return realEstate; }
    public List<Inquiry> getInquiryTable() { return inquiryTable; }
    public List<Reservation> getReservationTable() { return reservationTable; }
    public List<Payment> getPaymentsTable() { return paymentsTable; }

    public void setBuyer(Client buyer) { this.buyer = buyer; }
    public void setRealEstate(RealEstate realEstate) { this.realEstate = realEstate; }
}