/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package universitycourseregistrationsystem;

/**
 *
 * @author mehmet
 */

//When the course is full this exception is throwed
public class CourseFullException extends Exception {
    public CourseFullException(String msg) {
        super(msg);
    }
}
