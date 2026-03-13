/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureMainFolder;

import TransactionStructureEnumFolder.DocumentType;
import UserStructureMainFolder.Client;
import PropertyStructureMainFolder.Property;

public class Document {

    private int documentID;
    private DocumentType type;
    private String generatedAt;
    private Client client;
    private Property property;
    private String filePath;

    // Initializes the document with all required fields.
    // generatedAt and filePath are typically set by RealEstate.generateDeed().
    public Document(int documentID, DocumentType type, String generatedAt,
                    Client client, Property property, String filePath) {
        this.documentID = documentID;
        this.type = type;
        this.generatedAt = generatedAt;
        this.client = client;
        this.property = property;
        this.filePath = filePath;
        // All fields are required at creation — documents are complete records from the start.
    }

    // Simulates generating and saving the document by printing its details.
    // In a real system, this would write a PDF or file to the specified filePath.
    public void generate() {
        System.out.println("=== Document Generated ===");
        System.out.println("Type: " + type);
        System.out.println("Client: " + client.getName());
        System.out.println("Property Lot ID: " + property.getLotID());
        System.out.println("Generated At: " + generatedAt);
        System.out.println("File Path: " + filePath);
        // Outputs document details — in JFrame, will display in a document preview panel.
    }

    // Getters
    public int getDocumentID() { return documentID; }
    public DocumentType getType() { return type; }
    public String getGeneratedAt() { return generatedAt; }
    public Client getClient() { return client; }
    public Property getProperty() { return property; }
    public String getFilePath() { return filePath; }
}