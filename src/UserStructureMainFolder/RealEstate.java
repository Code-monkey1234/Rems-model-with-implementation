/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserStructureMainFolder;

import PropertyStructureMainFolder.Subdivision;
import PropertyStructureMainFolder.Property;
import PropertyStructureEnumFolder.LotStatus;
import TransactionStructureMainFolder.Reservation;
import TransactionStructureMainFolder.Payment;
import TransactionStructureMainFolder.Document;
import TransactionStructureEnumFolder.DocumentType;

public class RealEstate extends User {

    private Subdivision subdivision;
    private String subdivName;

    // Passes all registration fields up to User's constructor via super().
    // RealEstate acts as the Admin role — no extra fields at registration.
    public RealEstate(int userID, String name, String email, int phoneNumber, String password) {
        super(userID, name, email, phoneNumber, password);
    }

    // Sets the reservation status to APPROVED and notifies all involved parties.
    // Called by admin after reviewing a reservation forwarded by the agent.
    public void approveReservation(Reservation reservation) {
        reservation.approve();
        System.out.println("Reservation ID " + reservation.getReservationID() + " approved by admin.");
        // Delegates to Reservation.approve() which sets status and triggers notifications.
    }

    // Sets the reservation status to REJECTED with a reason and notifies all parties.
    // Called by admin when a reservation does not meet requirements.
    public void rejectReservation(Reservation reservation, String reason) {
        reservation.reject(reason);
        System.out.println("Reservation ID " + reservation.getReservationID() + " rejected. Reason: " + reason);
        // Delegates to Reservation.reject() which sets status, stores reason, and notifies.
    }

    // Marks a property sale as officially approved by admin.
    // Called after full payment is confirmed, before deed generation.
    public void approveSale(Property property) {
        property.sellLot();
        System.out.println("Sale approved by admin. Lot " + property.getLotID() + " is now SOLD.");
        // Calls sellLot() to update property status to SOLD — triggers deed workflow next.
    }

    // Verifies and confirms a payment submitted by the agent.
    // Updates the property status to RESERVED and notifies all parties.
    public void confirmPayment(Payment payment) {
        payment.getProperty().setStatus(LotStatus.RESERVED);
        payment.notifyParties();
        System.out.println("Admin confirmed payment ID " + payment.getPaymentID() + ". Lot set to RESERVED.");
        // Admin then generates deed separately when ready
    }         

    // Generates a Deed of Absolute Sale document for a fully paid client and property.
    // Returns the Document object which can then be sent to the client via agent.
    public Document generateDeed(Client client, Property property) {
        Document deed = new Document(
            (int)(Math.random() * 9000) + 1000,
            DocumentType.DEED_OF_ABSOLUTE_SALE,
            java.time.LocalDate.now().toString(),
            client,
            property,
            "/docs/deed_" + client.getUserID() + "_" + property.getLotID() + ".pdf"
        );
        deed.generate();
        System.out.println("Deed of Absolute Sale generated for " + client.getName());
        return deed;
        // Creates and generates a deed document — stored at a file path for download.
    }

    // Sends a document to the agent or client by printing send confirmation.
    // In JFrame, this will trigger a file notification or download prompt.
    public void sendDoc(Document document, User recipient) {
        System.out.println("Sending " + document.getType() + " to " + recipient.getName());
        recipient.addNotification(new TransactionStructureMainFolder.Notification(
            document.getDocumentID(),
            "Document available: " + document.getType(),
            this,
            recipient,
            TransactionStructureEnumFolder.NotificationType.DOCUMENT,
            document.getDocumentID()
        ));
        // Notifies the recipient that a document has been sent — they can then download it.
    }

    // Returns a formatted string showing the subdivision name and admin name.
    // Used for display in admin dashboard panels in the JFrame layer.
    public String showRealEstateDetails() {
        return "RealEstate: " + subdivName + " | Admin: " + getName();
        // Simple admin identity summary — can be expanded with subdivision stats.
    }

    // Getters and Setters
    public Subdivision getSubdivision() { return subdivision; }
    public String getSubdivName() { return subdivName; }

    public void setSubdivision(Subdivision subdivision) { this.subdivision = subdivision; }
    public void setSubdivName(String subdivName) { this.subdivName = subdivName; }
}