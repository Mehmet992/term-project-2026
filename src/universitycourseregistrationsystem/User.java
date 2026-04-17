/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitycourseregistrationsystem;

/**
 *
 * @author mehmet
 */
public abstract class User {
    private String name;
    private String password;
    private int userID;
    
    public String getName() { return this.name;}
    public String getPassword() { return this.password;}
    public int getUserID() { return this.userID;}
    
    public User(String name, String password, int userID) {
        this.name = name;
        this.password = password;
        this.userID = userID;
    }
    
}
