/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PropertyStructureMainFolder;

import java.util.ArrayList;
import java.util.List;

public class Subdivision {

    private int totalBlocks;
    private int totalLots;
    private List<Block> allBlocks = new ArrayList<>();
    private List<Property> sold = new ArrayList<>();

    // Initializes the subdivision with the expected total number of blocks and lots.
    // Actual Block objects are added separately via addBlock().
    public Subdivision(int totalBlocks, int totalLots) {
        this.totalBlocks = totalBlocks;
        this.totalLots = totalLots;
        // Counts are set at creation — used for reporting and capacity tracking.
    }

    // Adds a Block to the subdivision's block list.
    // Called during system setup to build out the full subdivision structure.
    public void addBlock(Block block) {
        allBlocks.add(block);
        System.out.println("Block " + block.getBlockName() + " added to subdivision.");
        // Registers the block so it can be searched and iterated by clients and agents.
    }

    // Returns all available lots across every block in the subdivision.
    // Used by Client.budgetFilter() and Client.searchLot() to scan all blocks at once.
    public List<Property> getAllAvailableLots() {
        List<Property> available = new ArrayList<>();
        for (Block block : allBlocks) {
            available.addAll(block.getAvailableLots());
        }
        return available;
        // Aggregates available lots from all blocks — gives a subdivision-wide availability view.
    }

    // Records a property as sold by adding it to the sold list.
    // Called when a lot's status is updated to SOLD after full payment.
    public void recordSale(Property property) {
        sold.add(property);
        System.out.println("Lot " + property.getLotID() + " recorded as sold in subdivision.");
        // Tracks all sold lots for admin reporting and subdivision inventory management.
    }

    // Returns a summary count of available vs sold lots across the whole subdivision.
    // In JFrame, this will populate a dashboard stats panel for the admin.
    public void showSubdivisionSummary() {
        int available = getAllAvailableLots().size();
        System.out.println("=== Subdivision Summary ===");
        System.out.println("Total Blocks: " + totalBlocks);
        System.out.println("Total Lots: " + totalLots);
        System.out.println("Available Lots: " + available);
        System.out.println("Sold Lots: " + sold.size());
        // Prints a full inventory overview — useful for admin monitoring and reporting.
    }

    // Getters and Setters
    public int getBlocks() { return totalBlocks; }
    public int getLots() { return totalLots; }
    public List<Block> getAllBlocks() { return allBlocks; }
    public List<Property> getSold() { return sold; }

    public void setBlocks(int blocks) { this.totalBlocks = blocks; }
    public void setLots(int lots) { this.totalLots = lots; }
}