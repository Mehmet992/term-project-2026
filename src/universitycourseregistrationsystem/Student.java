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
public class Student extends User{
    private double gpa;
    private int totalCredit; //will be calculated with added courses
    private Boolean onProbation; //check when setting probation whether they have more than 4 lessons or not
    private ArrayList<Course> courses;
    private ArrayList<Course> takenCourses;
    
    //Setters
    public void setProbation(Boolean onProbation) {this.onProbation = onProbation;}
    public void setGPA(double gpa) {this.gpa = gpa;}
    
    //Getters
    public double getGPA() {return this.gpa;}
    public int getTotalCredit() {return this.totalCredit;}
    public Boolean getOnProbation() {return this.onProbation;}
    
    //Constructor function
    public Student(String name, String password, int userID, double gpa) {
        super(name, password, userID);
        this.gpa = gpa;
        this.totalCredit = 0; //Initialized as 0
        this.onProbation = false; //Initialized as false
        courses = new ArrayList<>();
        takenCourses = new ArrayList<>();
    }
    
    //Construct the methods of adding Courses to the Student's 'courses' ArrayList
    private void checkCapacity(Course c1) throws CourseFullException {
        if (c1.getCurrentCapacity() >= c1.getMaxCapacity()) {
            throw new CourseFullException("The Course: " + c1.getCourseName() + " is full!");
        }
    }
    
    private void checkSchedule(Course c1) throws ScheduleConflictException {
        for (Course temp : courses) {
            
        }
    }
    
    private void checkPrerequisites(Course c1) throws PrerequisiteNotMetException {
        
    }
    
    //If added successfully, return true otherwise false
    public Boolean addCourse(Course c1) {
        try {
            //Try to add course
            //If capacity is full, catch CourseFullException
            //If Prerequisities are not met, catch PrerequisiteNotMetException
            //If there is another lesson within that hour, catch ScheduleConflictException
            
            checkCapacity(c1);
            checkSchedule(c1);
            checkPrerequisites(c1);
            
        } catch(CourseFullException ex) {
            System.out.println(ex.getMessage());
        } catch(ScheduleConflictException) {
            
        } catch (PrerequisiteNotMetException) {
            
        }
    }
}
