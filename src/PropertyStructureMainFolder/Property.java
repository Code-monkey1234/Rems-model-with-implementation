/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PropertyStructureMainFolder;

import PropertyStructureEnumFolder.LotStatus;
import PropertyStructureEnumFolder.LotType;

public class Property {

    private int lotID;
    private int blockID;
    private double lotArea;
    private double size;
    private LotType lotType;
    private double price;
    private LotStatus status;
    private Prices prices;

    // Initializes the property with all physical and pricing details.
    // Status defaults to AVAILABLE — no reservation or sale has been made yet.
    public Property(int lotID, int blockID, double lotArea, double size, LotType lotType, double price) {
        this.lotID = lotID;
        this.blockID = blockID;
        this.lotArea = lotArea;
        this.size = size;
        this.lotType = lotType;
        this.price = price;
        this.status = LotStatus.AVAILABLE;
        // All lots start as AVAILABLE — status changes through reserveLot() and sellLot().
    }

    // Returns true if the lot's current status is AVAILABLE.
    // Used by search methods and budget filters to skip unavailable lots.
    public boolean checkAvailability() {
        return status == LotStatus.AVAILABLE;
        // Simple status check — any status other than AVAILABLE returns false.
    }

    // Changes the lot status to RESERVED.
    // Called when a reservation is approved — lot is held for the client.
    public void reserveLot() {
        this.status = LotStatus.RESERVED;
        System.out.println("Lot " + lotID + " is now RESERVED.");
        // Status update only — client and agent are notified separately via Reservation.notifyParties().
    }

    // Changes the lot status to SOLD.
    // Called when full payment is confirmed — either via cash or completed installment.
    public void sellLot() {
        this.status = LotStatus.SOLD;
        System.out.println("Lot " + lotID + " is now SOLD.");
        // Marks ownership as transferred — deed generation is triggered after this.
    }

    // Returns a formatted string showing the lot's key details and current status.
    // In JFrame, this populates a property detail panel or table row.
    public String showPropertyDetails() {
        return "Lot ID: " + lotID +
               " | Block: " + blockID +
               " | Area: " + lotArea + " sqm" +
               " | Type: " + lotType +
               " | Price: " + price +
               " | Status: " + status;
        // Full property summary — all info a client or agent needs to evaluate a lot.
    }

    // Updates the price and status of the property.
    // Called by admin when repricing a lot or manually changing its availability.
    public void updatePropertyDetails(double price, LotStatus status) {
        this.price = price;
        this.status = status;
        System.out.println("Lot " + lotID + " updated — Price: " + price + " | Status: " + status);
        // Admin-level update — allows price adjustments and manual status overrides.
    }

    // Getters and Setters
    public int getLotID() { return lotID; }
    public int getBlockID() { return blockID; }
    public double getLotArea() { return lotArea; }
    public double getSize() { return size; }
    public LotType getLotType() { return lotType; }
    public double getPrice() { return price; }
    public LotStatus getStatus() { return status; }
    public Prices getPrices() { return prices; }

    public void setLotID(int lotID) { this.lotID = lotID; }
    public void setBlockID(int blockID) { this.blockID = blockID; }
    public void setLotArea(double lotArea) { this.lotArea = lotArea; }
    public void setSize(double size) { this.size = size; }
    public void setLotType(LotType lotType) { this.lotType = lotType; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(LotStatus status) { this.status = status; }
    public void setPrices(Prices prices) { this.prices = prices; }
}