/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitycourseregistrationsystem;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author mehmet
 */
public class Course {
    private String courseName;
    private String departmentName;
    private String courseID;
    private int sectionNumber; //Enumeration can be used
    private int maxCapacity; 
    private int currentCapacity; //initialize this as 0
    private int credit;
    private LocalTime startOfTheCourse;
    private LocalTime endOfTheCourse;
    private Day day;
    private ArrayList<Course> prerequisities;
    private ArrayList<Student> students; 
    
    
    //Setters
    public void setCourseName(String courseName) {this.courseName = courseName;}
    public void setDepartmentName(String departmentName) {this.departmentName = departmentName;}
    public void setCourseID(String courseID) {this.courseID = courseID;}
    public void setSectionNumber(int sectionNumber) {this.sectionNumber = sectionNumber;}
    public void setMaxCapacity(int maxCapacity) {this.maxCapacity = maxCapacity;}
    public void setCurrentCapacity(int currentCapacity) {this.currentCapacity = currentCapacity;}
    public void setCredit(int credit) {this.credit = credit;}
    public void setStartOfTheCourse(LocalTime startOfTheCourse) {this.startOfTheCourse = startOfTheCourse;}
    public void setEndOfTheCourse(LocalTime endOfTheCourse) {this.endOfTheCourse = endOfTheCourse;}
    public void setDay(Day day) {this.day = day;}
    public void setPrerequisities(ArrayList<Course> prerequisities) {this.prerequisities = prerequisities;}
    public void setStudents(ArrayList<Student> students) {this.students = students;}
    
    
    //Getters
    public String getCourseName() {return courseName;}
    public String getDepartmentName() {return departmentName;}
    public String getCourseID() {return courseID;}
    public int getSectionNumber() {return sectionNumber;}
    public int getMaxCapacity() {return maxCapacity;}
    public int getCurrentCapacity() {return currentCapacity;}
    public int getCredit() {return credit;}
    public LocalTime getStartOfTheCourse() {return startOfTheCourse;}
    public LocalTime getEndOfTheCourse() {return endOfTheCourse;}
    public Day getDay() {return day;}
    public ArrayList<Course> getPrerequisities() {return prerequisities;}
    public ArrayList<Student> getStudents() {return students;}
    
    
    //Constructor
    public Course(String courseName, String departmentName, String courseID, int sectionNumber, int maxCapacity, int credit, String startOfTheCourse, String endOfTheCourse, Day day) {
        this.courseName = courseName;
        this.departmentName = departmentName;
        this.courseID = courseID;
        this.sectionNumber = sectionNumber;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0; //Initialized as 0
        this.credit = credit;
        this.startOfTheCourse = LocalTime.parse(startOfTheCourse); //In the format of "09:00"
        this.endOfTheCourse = LocalTime.parse(endOfTheCourse);
        this.day = day;
        this.prerequisities = new ArrayList<>(); //Both are initialized, methods for adding will be provided
        this.students = new ArrayList<>();
    }
}
