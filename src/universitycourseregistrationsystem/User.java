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
    private String userID;
    
    //Getters
    public String getName() { return this.name;}
    public String getSurname() {return this.surname;}
    public String getPassword() { return this.password;}
    public String getUserID() { return this.userID;}
    
    //Setters
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setPassword(String password) {this.password = password;}
    public void setType(Types type) {this.type = type;}
    public void setUserID(String userID) {this.userID = userID;}
    
    //Constructor function
    public User(String name, String surname, Types type, String password, String userID) {
        this.name = name;
        this.surname = surname;
        this.type = type; //Types.STUDENT
        this.password = password;
        this.userID = userID;
    }
    
    public User() {}
    
    public String toFileFormat() {
        return name + "|" + surname + "|" + type.toString() + "|" + userID + "|" + password + "|";
    }
    
}
