/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureMainFolder;

import UserStructureMainFolder.Client;
import PropertyStructureMainFolder.Property;

public class Inquiry {

    private int inquiryID;
    private Client client;
    private Property property;
    private String message;
    private String response;

    // Initializes the inquiry with the client, property, and their message.
    // Response starts as null — it will be set when the agent replies.
    public Inquiry(int inquiryID, Client client, Property property, String message) {
        this.inquiryID = inquiryID;
        this.client = client;
        this.property = property;
        this.message = message;
        // Response is null by default — agent must call setResponse() to answer.
    }

    // Submits the inquiry by adding it to the client's inquiry list and the agent's table.
    // Prints a confirmation showing which client inquired about which lot.
    public void makeInquiry() {
        client.getMyInquiries().add(this);
        if (client.getAgent() != null) {
            client.getAgent().receiveInquiry(this);
        }
        System.out.println("Inquiry made by " + client.getName() + " for Lot " + property.getLotID());
        // Registers the inquiry on both the client and agent side — agent will see it in viewInquiry().
    }

    // Prints the inquiry ID and the message content sent by the client.
    // In JFrame, this will display in a selected row of the inquiry table.
    public void viewInquiry() {
        System.out.println("Inquiry ID: " + inquiryID + " | Message: " + message);
        // Displays the full inquiry message — used by agent when reviewing inquiries.
    }

    // Removes this inquiry from the client's inquiry list and clears the inquiry data.
    // Called by the client or agent when an inquiry is resolved or no longer needed.
    public void deleteInquiry() {
        client.getMyInquiries().remove(this);
        System.out.println("Inquiry ID " + inquiryID + " deleted.");
        // Removes from the client's list — inquiry will no longer appear in their view.
    }

    // Prints the agent's response to this inquiry, or a default message if not yet answered.
    // In JFrame, this will populate a response field in the inquiry detail panel.
    public void viewResponse() {
        System.out.println("Response: " + (response != null ? response : "No response yet."));
        // Shows the agent's reply — client calls this to check if their inquiry was answered.
    }

    // Setters and Getters
    public void setResponse(String response) { this.response = response; }

    public int getInquiryID() { return inquiryID; }
    public Client getClient() { return client; }
    public Property getProperty() { return property; }
    public String getMessage() { return message; }
    public String getResponse() { return response; }
}