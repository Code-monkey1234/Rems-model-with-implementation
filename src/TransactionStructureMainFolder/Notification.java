/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransactionStructureMainFolder;

import TransactionStructureEnumFolder.NotificationStatus;
import TransactionStructureEnumFolder.NotificationType;
import UserStructureMainFolder.User;

public class Notification {

    private int notificationID;
    private String message;
    private NotificationStatus status;
    private User sender;
    private User receiver;
    private NotificationType type;
    private int referenceID;
    private String createdAt;

    // Initializes the notification with sender, receiver, message, and type.
    // Status defaults to UNREAD and createdAt is set to today's date automatically.
    public Notification(int notificationID, String message, User sender,
                        User receiver, NotificationType type, int referenceID) {
        this.notificationID = notificationID;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.referenceID = referenceID;
        this.status = NotificationStatus.UNREAD;
        this.createdAt = java.time.LocalDate.now().toString();
        // Auto-sets status to UNREAD and timestamps the notification on creation.
    }

    // Delivers the notification to the receiver by adding it to their notification list.
    // The receiver can then view it via User.viewNotifications().
    public void send() {
        receiver.addNotification(this);
        System.out.println("Notification sent to " + receiver.getName() + ": " + message);
        // Pushes this notification into the receiver's list — triggers their viewNotifications().
    }

    // Changes the notification status from UNREAD to READ.
    // Called when the user opens or acknowledges the notification.
    public void markAsRead() {
        this.status = NotificationStatus.READ;
        System.out.println("Notification " + notificationID + " marked as read.");
        // Updates status to READ — prevents it from showing as new in viewNotifications().
    }

    // Getters
    public int getNotificationID() { return notificationID; }
    public String getMessage() { return message; }
    public NotificationStatus getStatus() { return status; }
    public User getSender() { return sender; }
    public User getReceiver() { return receiver; }
    public NotificationType getType() { return type; }
    public int getReferenceID() { return referenceID; }
    public String getCreatedAt() { return createdAt; }
}