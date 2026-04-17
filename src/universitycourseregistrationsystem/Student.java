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
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Course> takenCourses = new ArrayList<>();
    
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
    }
    
    //Construct the methods of adding Courses to the Student's 'courses' ArrayList
}
