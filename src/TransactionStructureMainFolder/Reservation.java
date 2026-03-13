/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureMainFolder;

import UserStructureMainFolder.Client;
import TransactionStructureEnumFolder.ReservationStatus;

public class Reservation {

    private int reservationID;
    private Client client;
    private int block;
    private int lot;
    private ReservationStatus reservationStatus;

    // Initializes the reservation with the client's chosen block and lot.
    // Status defaults to PENDING — awaiting agent forwarding and admin approval.
    public Reservation(int reservationID, Client client, int block, int lot) {
        this.reservationID = reservationID;
        this.client = client;
        this.block = block;
        this.lot = lot;
        this.reservationStatus = ReservationStatus.PENDING;
        // All reservations start as PENDING — admin must explicitly approve or reject.
    }

    // Sets status to APPROVED and notifies all involved parties.
    // Called by RealEstate (admin) after reviewing the reservation.
    public void approve() {
        this.reservationStatus = ReservationStatus.APPROVED;
        notifyParties();
        System.out.println("Reservation " + reservationID + " approved.");
        // Updates status and triggers notification — client and agent are informed.
    }

    // Sets status to REJECTED with a reason and notifies all involved parties.
    // Called by admin when the reservation cannot be processed.
    public void reject(String reason) {
        this.reservationStatus = ReservationStatus.REJECTED;
        notifyParties();
        System.out.println("Reservation " + reservationID + " rejected. Reason: " + reason);
        // Updates status with rejection reason and triggers notification to all parties.
    }

    // Sets status to CANCELLED — called by the client when they withdraw their request.
    // Does not notify parties since the client is the one initiating the cancellation.
    public void cancel() {
        this.reservationStatus = ReservationStatus.CANCELLED;
        System.out.println("Reservation " + reservationID + " cancelled by client.");
        // Client-initiated cancellation — updates status without forwarding to admin.
    }

    // Prints a status update notification for all parties involved in this reservation.
    // In JFrame, this will trigger Notification objects sent to client, agent, and admin.
    public void notifyParties() {
        System.out.println("Notifying parties: Reservation " + reservationID + " is now " + reservationStatus);
        // Broadcasts the current reservation status — client, agent, and admin are updated.
    }

    // Getters and Setters
    public int getReservationID() { return reservationID; }
    public Client getClient() { return client; }
    public int getBlock() { return block; }
    public int getLot() { return lot; }
    public ReservationStatus getReservationStatus() { return reservationStatus; }

    public void setReservationStatus(ReservationStatus status) { this.reservationStatus = status; }
}