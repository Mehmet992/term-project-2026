/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package universitycourseregistrationsystem;

/**
 *
 * @author mehmet
 */

//When there is a conflict of two courses, this exception is throwed
public class ScheduleConflictException extends Exception {
    public ScheduleConflictException(String msg) {
        super(msg);
    }
}
