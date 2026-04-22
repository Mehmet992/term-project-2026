/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitycourseregistrationsystem;

import java.util.ArrayList;

/**
 *
 * @author mehmet
 */

public class Professor extends User {
    private ArrayList<Course> givenCourses;
    
    public Professor(String name, String surname, String password, int userID) {
        super(name, surname, password, userID);
    }
      
    //Creates Courses and can see the information about the courses, not girişi
}
