/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitycourseregistrationsystem;

public class HourOperation {
    public static void checkValidHour(String[] hourInformation) throws InvalidHourException, NumberFormatException{
        if (hourInformation.length != 2 || !(Integer.parseInt(hourInformation[0]) >= 0) || !(Integer.parseInt(hourInformation[0]) <= 23) ||
            !(Integer.parseInt(hourInformation[1]) >= 0 ) || !(Integer.parseInt(hourInformation[1]) <= 59)) {
            throw new InvalidHourException(" Invalid hour information! ");           
        }
    }
    
    public static void checkValidStartEndHours(String start, String end) throws InvalidHourException, NumberFormatException{
        if (!(Integer.parseInt(start) < Integer.parseInt(end))) {
            throw new InvalidHourException(" The End hour is before start hour! ");
        }
    }
    
    public static void checkValidDay(String day) throws NotValidDayException {
        String upperDay = day.toUpperCase();
        if (!(Day.valueOf(upperDay) instanceof Day)) {
            throw new NotValidDayException(day + " is not a valid day! ");
        }
    }
}
