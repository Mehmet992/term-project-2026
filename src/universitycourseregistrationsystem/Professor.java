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
        givenCourses = new ArrayList<>();
    }
    
    //Getter(s)
    public ArrayList<Course> getGivenCourses() {return this.givenCourses;}
    
    public Professor() {
        super();
        givenCourses = new ArrayList<>();
    }
    
    public void addCourse(Course c) {
        givenCourses.add(c);
    }
      
    
    @Override
    public String toFileFormat() {
        StringBuilder result = new StringBuilder(super.toFileFormat());
        if (!givenCourses.isEmpty()) {
            for (int i = 0; i < givenCourses.size(); i++) {
                if (i < givenCourses.size() - 1) {
                    result.append(givenCourses.get(i).getCourseID() + ";");
                } else {
                    result.append(givenCourses.get(i).getCourseID());
                }
            }
        }
        
        return result.toString();
    }
    
    //Creates Courses and can see the information about the courses, not girişi
}
