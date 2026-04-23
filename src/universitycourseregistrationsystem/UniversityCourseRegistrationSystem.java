/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package universitycourseregistrationsystem;

/**
 *
 * @author mehmet
 */
public class UniversityCourseRegistrationSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Just trying adding course and schedule conflict logic
        Student s1 = new Student("Mehmet", "Murt", Types.STUDENT, "aaaBBec11" , 00332255);
        Course c1 = new Course("Logic Design", "CENG", "SE2215", 1, 50, 4, "10:40", "12:30", Day.MONDAY);
        Course c2 = new Course("Calculus 1", "CENG", "MATH1008", 1, 50, 6, "10:40", "12:30", Day.TUESDAY);
        Course c3 = new Course("Calculus 2", "CENG", "MATH2009", 1, 50, 6, "11:40", "13:30", Day.MONDAY);
        Course c4 = new Course("Data Structures", "CENG", "COMP2203", 1, 50, 4, "08:40", "11:30", Day.TUESDAY);
        Course c5 = new Course("Data Structures 2", "CENG", "COMP2203", 1, 50, 4, "12:40", "14:30", Day.MONDAY);
            
        s1.addCourse(c1);
        s1.addCourse(c2);
        s1.addCourse(c3);
        s1.addCourse(c4);
        s1.addCourse(c5);
    }
    
}
