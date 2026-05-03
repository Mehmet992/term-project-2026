/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitycourseregistrationsystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    private ArrayList<String> prerequisites;
    private ArrayList<Student> students; 
    private LinkedList<Student> waitList;
    
    
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
    public void setPrerequisites(ArrayList<String> prerequisites) {this.prerequisites = prerequisites;}
    public void setStudents(ArrayList<Student> students) {this.students = students;}
    public void setWaitList(LinkedList<Student> waitList) {this.waitList = waitList;}
    
    
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
    public ArrayList<String> getPrerequisites() {return prerequisites;}
    public ArrayList<Student> getStudents() {return students;}
    public LinkedList<Student> getWaitList() {return waitList;}
    
    
    //Constructor
    public Course(String courseName, String departmentName, String courseID, int sectionNumber, int maxCapacity, int currentCapacity, int credit, String startOfTheCourse, String endOfTheCourse, Day day) {
        this.courseName = courseName;
        this.departmentName = departmentName;
        this.courseID = courseID;
        this.sectionNumber = sectionNumber;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity; //Initialized as 0
        this.credit = credit;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        this.startOfTheCourse = LocalTime.parse(startOfTheCourse.trim(), formatter); //In the format of "09:00" or "9:00"
        this.endOfTheCourse = LocalTime.parse(endOfTheCourse.trim(), formatter);
        
        this.day = day;
        this.prerequisites = new ArrayList<>(); //Both are initialized, methods for adding will be provided
        this.students = new ArrayList<>();
        this.waitList = new LinkedList<>();
    }
    
       
    public void addStudent(Student s1) {
        students.add(s1);
    }
    
    public void addPrerequisities(String preq) {
        prerequisites.add(preq);
    }
    
    public void addToWaitlist(Student student) {
        waitList.add(student);
    }
    
    public String toFileFormat() {
        StringBuilder sb = new StringBuilder(courseName + "|" + departmentName + "|" + courseID + "|" 
                + sectionNumber + "|" + maxCapacity + "|" + currentCapacity + "|" + credit + "|" 
                + startOfTheCourse + "|" + endOfTheCourse + "|" + String.valueOf(day) + "|");
        
        for (int i = 0; i < prerequisites.size(); i++) {
            if (i < prerequisites.size() - 1) {
                sb.append(prerequisites.get(i)).append(";");
            } else {
                sb.append(prerequisites.get(i));
            }
        }
        
        sb.append("|");
        
        for (int i = 0; i < waitList.size(); i++) {
            if (i < waitList.size() - 1) {
                sb.append(waitList.get(i).getUserID()).append(";");
            } else {
                sb.append(waitList.get(i).getUserID());
            }
        }
        
        return sb.toString();
    }
    
    
}
