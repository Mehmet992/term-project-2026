/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package universitycourseregistrationsystem;

/**
 *
 * @author mehmet
 */

//When the student doesn't have the proper prerequisites for the course, this exception is throwed
public class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String msg) {
        super(msg);
    }
}
