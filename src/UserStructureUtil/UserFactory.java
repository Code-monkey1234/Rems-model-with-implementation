/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserStructureUtil;

import UserStructureMainFolder.User;
import UserStructureMainFolder.Client;
import UserStructureMainFolder.Agent;
import UserStructureMainFolder.RealEstate;
import UserStructureEnum.Role;

public class UserFactory {

    // Creates and returns the correct User subtype based on the given role.
    // The password passed here is the "passwordSetted" during registration —
    // it gets stored in User's private password field and later used by login() to verify.
    public static User register(
        int userID,
        String name,
        String email,
        int phoneNumber,
        String password,
        Role role
    ) {
        switch (role) {
            case CLIENT:
                return new Client(userID, name, email, phoneNumber, password);
                // Creates a Client — a buyer who can browse lots, make reservations, and payments.

            case AGENT:
                return new Agent(userID, name, email, phoneNumber, password);
                // Creates an Agent — handles inquiries, forwards reservations, confirms payments.

            case ADMIN:
                return new RealEstate(userID, name, email, phoneNumber, password);
                // Creates a RealEstate admin — approves reservations, confirms sales, generates deeds.

            default:
                throw new IllegalArgumentException("Invalid role: " + role);
                // Throws an exception if an unrecognized role is passed — prevents bad user creation.
        }
    }
}