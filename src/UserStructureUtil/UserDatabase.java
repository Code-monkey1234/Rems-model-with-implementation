/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserStructureUtil;

import UserStructureMainFolder.User;
import UserStructureMainFolder.Client;
import UserStructureMainFolder.Agent;
import UserStructureMainFolder.RealEstate;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    // In-memory storage lists for each user type.
    // Acts as a simple database for the application's registered users.
    private List<User> allUsers = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Agent> agents = new ArrayList<>();
    private List<RealEstate> admins = new ArrayList<>();

    // Adds a registered user to the master list and their specific type list.
    // Called after UserFactory.register() creates the user object.
    public void addUser(User user) {
        allUsers.add(user);
        if (user instanceof Client) clients.add((Client) user);
        else if (user instanceof Agent) agents.add((Agent) user);
        else if (user instanceof RealEstate) admins.add((RealEstate) user);
        System.out.println("User registered: " + user.getName() + " [" + user.getClass().getSimpleName() + "]");
        // Stores user in both the master list and the appropriate typed sublist.
    }

    // Searches all users by email and returns the matching User, or null if not found.
    // Used during login to find the user account before calling login() to verify password.
    public User findByEmail(String email) {
        for (User u : allUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
        // Linear search through all users — returns first match or null if no user found.
    }

    // Searches all users by their userID and returns the matching User, or null if not found.
    // Used when referencing a user by ID across transactions or notifications.
    public User findByID(int userID) {
        for (User u : allUsers) {
            if (u.getUserID() == userID) {
                return u;
            }
        }
        return null;
        // Linear search through all users — returns first match or null if not found.
    }

    // Removes a user from all lists by their userID.
    // Used for account deletion or admin-level user management.
    public void removeUser(int userID) {
        User target = findByID(userID);
        if (target != null) {
            allUsers.remove(target);
            clients.remove(target);
            agents.remove(target);
            admins.remove(target);
            System.out.println("User removed: " + target.getName());
        }
        // Removes from master list and all typed sublists to keep data consistent.
    }

    // Getters
    public List<User> getAllUsers() { return allUsers; }
    public List<Client> getClients() { return clients; }
    public List<Agent> getAgents() { return agents; }
    public List<RealEstate> getAdmins() { return admins; }
}