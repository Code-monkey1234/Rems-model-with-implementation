/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserStructureMainFolder;
 
import java.util.ArrayList;
import java.util.List;
import TransactionStructureMainFolder.Notification;
 
public abstract class User {
 
    private int userID;
    private String name;
    private String email;
    private int phoneNumber;
    private String password;
    private List<Notification> notifications = new ArrayList<>();
 
    
    //User constructor kasi all agent, client and real estate are users kaya andito lahat ng common fields nila
    //need ito para ma populate ung mga common fields nila
    //need rin to kasi private fields to so yaa
    public User(int userID, String name, String email, int phoneNumber, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
 
    
    //pag nag register ung isang tao, kinukumpara lang ung registered na password and email gamit ung constructor sa taas
    //then pag equals edi log in == true
    public boolean login(String emailEntered, String passwordEntered) {
        return this.email.equals(emailEntered) && this.password.equals(passwordEntered);
        // Both email and password must match the registered values for login to succeed.
    }
 
    //pag nag logout sila (click logout button) mawawala lang ung mga frames kahit ilan man, dapat bumalik sila sa log in frame
    public void logout() {
        System.out.println(name + " has logged out.");
        // Logic side of logout — UI switching is handled in the JFrame layer separately.
    }
 
    //para makita ung mga notifications and ung status
    //naka table sya sa jframe or list component 
    public void viewNotifications() {
        System.out.println("=== Notifications for " + name + " ===");
        for (Notification n : notifications) {
            System.out.println(n.getMessage() + " [" + n.getStatus() + "]");
        }
        // Iterates all notifications and displays message + status for each entry.
    }
 
    //ginagamit lang to pag nag .send
    //so naka depend sya sa isang method para magamit
    //need muna mag send then mag add notif sa user
    public void addNotification(Notification notification) {
        notifications.add(notification);
        // Appends the notification to the list so it appears in viewNotifications().
    }
 
    // Getters and Setters
    public int getUserID() { return userID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    public List<Notification> getNotifications() { return notifications; }
 
    public void setUserID(int userID) { this.userID = userID; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setPassword(String password) { this.password = password; }
}