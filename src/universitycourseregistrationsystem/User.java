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
    private String surname;
    private String password;
    private Types type;
    private int userID;
    
    //Getters
    public String getName() { return this.name;}
    public String getSurname() {return this.surname;}
    public String getPassword() { return this.password;}
    public int getUserID() { return this.userID;}
    
    //Constructor function
    public User(String name, String surname, Types type, String password, int userID) {
        this.name = name;
        this.surname = surname;
        this.type = type; //Types.STUDENT
        this.password = password;
        this.userID = userID;
    }
    
    public User() {}
    
    public String toFileFormat() {
        return name + "|" + surname + "|" + type.toString() + userID + "|" + password + "|";
    }
    
}
