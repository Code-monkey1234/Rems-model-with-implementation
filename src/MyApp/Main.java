/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyApp;

/**
 *
 * @author User
 */
import UserStructureMainFolder.*;
import UserStructureUtil.*;
import UserStructureEnum.Role;

import PropertyStructureMainFolder.*;
import PropertyStructureEnumFolder.*;

import TransactionStructureMainFolder.*;
import TransactionStructureUtilFolder.*;
import TransactionStructureEnumFolder.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   REMS - Real Estate Management System ");
        System.out.println("========================================\n");

        // ─────────────────────────────────────────
        // STEP 1: SETUP — Build the subdivision
        // ─────────────────────────────────────────
        System.out.println("--- [SETUP] Building Subdivision ---");

        Subdivision subdivision = new Subdivision(2, 6);

        Block block1 = new Block(1, "Block A");
        Block block2 = new Block(2, "Block B");

        Property lot1 = new Property(101, 1, 120.0, 120.0, LotType.CORNER, 2500000);
        Property lot2 = new Property(102, 1, 80.0,  80.0,  LotType.INNER,  1800000);
        Property lot3 = new Property(103, 1, 100.0, 100.0, LotType.INNER,  2000000);
        Property lot4 = new Property(201, 2, 150.0, 150.0, LotType.CORNER, 3000000);
        Property lot5 = new Property(202, 2, 90.0,  90.0,  LotType.INNER,  1900000);
        Property lot6 = new Property(203, 2, 110.0, 110.0, LotType.INNER,  2200000);

        // Attach prices to lot1 (for installment demo)
        Prices lot1Prices = new Prices(2500000, 20000, 50000, 20, 60, 0.12);
        lot1.setPrices(lot1Prices);

        block1.addLot(lot1);
        block1.addLot(lot2);
        block1.addLot(lot3);
        block2.addLot(lot4);
        block2.addLot(lot5);
        block2.addLot(lot6);

        subdivision.addBlock(block1);
        subdivision.addBlock(block2);

        subdivision.showSubdivisionSummary();
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 2: REGISTER USERS
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 2] Registering Users ---");

        UserDatabase db = new UserDatabase();

        User adminUser  = UserFactory.register(1, "Maria Santos",  "maria@rems.com",  12345678, "admin123",  Role.ADMIN);
        User agentUser  = UserFactory.register(2, "Jose Reyes",    "jose@rems.com",   87654213, "agent123",  Role.AGENT);
        User clientUser = UserFactory.register(3, "Anna Cruz",     "anna@rems.com",   91912345, "client123", Role.CLIENT);

        db.addUser(adminUser);
        db.addUser(agentUser);
        db.addUser(clientUser);

        // Cast to specific types for method access
        RealEstate admin  = (RealEstate) adminUser;
        Agent      agent  = (Agent)      agentUser;
        Client     client = (Client)     clientUser;

        // Link admin to subdivision
        admin.setSubdivision(subdivision);
        admin.setSubdivName("Villa Esperanza");

        // Link agent to admin's real estate
        agent.setRealEstate(admin);

        // Link client to agent
        client.setAgent(agent);
        client.setBudget(2600000);

        System.out.println(admin.showRealEstateDetails());
        System.out.println(agent.showAgentDetails());
        System.out.println(client.showClientDetails());
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 3: LOGIN
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 3] Logging In ---");

        User found = db.findByEmail("anna@rems.com");
        if (found != null && found.login("anna@rems.com", "client123")) {
            System.out.println("LOGIN SUCCESS: " + found.getName());
        } else {
            System.out.println("LOGIN FAILED.");
        }

        // Wrong password attempt
        if (found != null && found.login("anna@rems.com", "wrongpass")) {
            System.out.println("LOGIN SUCCESS");
        } else {
            System.out.println("LOGIN FAILED (wrong password) — correct behavior.\n");
        }

        // ─────────────────────────────────────────
        // STEP 4: CLIENT SEARCHES FOR A LOT
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 4] Client Searches for Lots ---");

        System.out.println("Client budget: PHP " + client.getBudget());
        System.out.println("Available lots within budget:");
        for (Property p : client.budgetFilter()) {
            System.out.println("  " + p.showPropertyDetails());
        }

        Property chosen = client.searchLot(120.0);
        if (chosen != null) {
            System.out.println("\nClient chose: " + chosen.showPropertyDetails());
        }
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 5: CLIENT SENDS INQUIRY TO AGENT
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 5] Client Sends Inquiry ---");

        Inquiry inquiry = new Inquiry(1001, client, lot1, "Is Lot 101 in Block A still available? What are the payment terms?");
        inquiry.makeInquiry();
        inquiry.viewInquiry();
        System.out.println();

        // Agent replies
        System.out.println("--- [STEP 5b] Agent Replies to Inquiry ---");
        agent.sendDetails(inquiry, "Yes, Lot 101 is available! TCP is PHP 2,500,000. We offer cash and installment options.");
        inquiry.viewResponse();
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 6: CLIENT RESERVES A LOT
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 6] Client Reserves a Lot ---");

        client.sendRequest(5001, 1, 101);
        Reservation reservation = client.getReservation();

        // Agent processes and forwards to admin
        agent.processReservation(reservation);
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 7: CLIENT PAYS RESERVATION FEE
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 7] Client Pays Reservation Fee ---");

        double resFee = lot1.getPrices().calculateReservationFee();
        ReservationPayment resPay = new ReservationPayment(resFee);
        Payment reservationPayment = new Payment(3001, client, lot1, resPay);
        client.setPayment(reservationPayment);
        client.makePayment();
        lot1.reserveLot();
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 8: ADMIN APPROVES RESERVATION
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 8] Admin Approves Reservation ---");

        admin.approveReservation(reservation);
        System.out.println("Reservation Status: " + reservation.getReservationStatus());
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 9: CLIENT MAKES INSTALLMENT PAYMENTS
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 9] Client Makes Installment Payments ---");

        double dp    = lot1.getPrices().calculateDownPayment();
        double amort = lot1.getPrices().calculateMonthlyAmortization();
        double bal   = 2500000 - (2500000 * 0.20);

        InstallmentPayment installment = new InstallmentPayment(amort, 3, bal, client, lot1);
        // Note: using 3 months here just to simulate quickly (real would be 60)

        Payment installmentPayment = new Payment(3002, client, lot1, installment);
        client.setPayment(installmentPayment);

        System.out.println("\nMonth 1 Payment:");
        client.makePayment();
        client.trackPaymentProgress();

        System.out.println("\nMonth 2 Payment:");
        client.makePayment();
        client.trackPaymentProgress();

        System.out.println("\nMonth 3 Payment (Final):");
        client.makePayment();
        client.trackPaymentProgress();
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 10: AGENT CONFIRMS PAYMENT TO ADMIN
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 10] Agent Confirms Payment ---");
        agent.confirmPayment(installmentPayment);
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 11: ADMIN APPROVES SALE + GENERATES DEED
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 11] Admin Approves Sale and Generates Deed ---");

        admin.approveSale(lot1);
        Document deed = admin.generateDeed(client, lot1);
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 12: AGENT SENDS DEED TO CLIENT
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 12] Agent Sends Deed to Client ---");
        agent.setBuyer(client);
        agent.sendDoc(deed);
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 13: CLIENT VIEWS NOTIFICATIONS
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 13] Client Views Notifications ---");
        client.viewNotifications();
        System.out.println();

        // ─────────────────────────────────────────
        // STEP 14: FINAL SUBDIVISION SUMMARY
        // ─────────────────────────────────────────
        System.out.println("--- [STEP 14] Final Subdivision Summary ---");
        subdivision.recordSale(lot1);
        subdivision.showSubdivisionSummary();

        System.out.println("\n========================================");
        System.out.println("         Simulation Complete!           ");
        System.out.println("========================================");
    }
}
