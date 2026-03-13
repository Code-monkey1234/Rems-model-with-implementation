/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PropertyStructureMainFolder;

import java.util.ArrayList;
import java.util.List;
import PropertyStructureEnumFolder.LotStatus;

public class Block {

    private int blockID;
    private String blockName;
    private List<Property> lots = new ArrayList<>();
    private List<Property> availableLot = new ArrayList<>();

    // Initializes the block with an ID and name.
    // Lots are added separately via getLots().add() or a dedicated addLot() method.
    public Block(int blockID, String blockName) {
        this.blockID = blockID;
        this.blockName = blockName;
        // Lot lists start empty — populated when the subdivision is set up.
    }

    // Adds a property (lot) to this block's master lot list.
    // Called during subdivision setup to populate the block with its lots.
    public void addLot(Property property) {
        lots.add(property);
        System.out.println("Lot " + property.getLotID() + " added to Block " + blockName);
        // Registers the lot under this block — available for search and filtering.
    }

    // Clears the available list and rebuilds it by filtering only AVAILABLE lots.
    // Returns an up-to-date list of lots that clients can reserve.
    public List<Property> getAvailableLots() {
        availableLot.clear();
        for (Property p : lots) {
            if (p.getStatus() == LotStatus.AVAILABLE) {
                availableLot.add(p);
            }
        }
        return availableLot;
        // Rebuilds on every call to reflect the latest status — avoids stale availability data.
    }

    // Getters and Setters
    public int getBlockID() { return blockID; }
    public String getBlockName() { return blockName; }
    public List<Property> getLots() { return lots; }

    public void setBlockID(int blockID) { this.blockID = blockID; }
    public void setBlockName(String blockName) { this.blockName = blockName; }
}