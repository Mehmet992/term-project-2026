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
    
    public Professor(String name, String surname, Types type, String password, String userID) {
        super(name, surname, type, password, userID);
    }
      
    
    @Override
    public String toFileFormat() {
        StringBuilder result = new StringBuilder(super.toFileFormat() + "|");
        if (!givenCourses.isEmpty()) {
            for (Course c1: givenCourses) {
                result.append(c1.getCourseName() + "|");
            }
        }
        
        return result.toString();
    }
    
    //Creates Courses and can see the information about the courses, not girişi
}
