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
    public void setName(String name) {super.setName(name);}
    public void setSurname(String surname) {super.setSurname(surname);}
    public void setType(Types type) {super.setType(type);}
    public void setPassword(String password) {super.setPassword(password);}
    public void setUserID(String userID) {super.setUserID(userID);}
    public void setTotalCredit(int totalCredit) {this.totalCredit = totalCredit;}
    
    //Getters
    public double getGPA() {return this.gpa;}
    public int getTotalCredit() {return this.totalCredit;}
    public Boolean getOnProbation() {return this.onProbation;}
    public ArrayList<Course> getCourses() {return this.courses;}
    public ArrayList<Course> getTakenCourses() {return this.takenCourses;}
    
    //Constructor function
    public Student(String name, String surname, Types type, String password, String userID) {
        super(name, surname, type, password, userID);
        this.gpa = 0; //Initialized as 0
        this.totalCredit = 0; //Initialized as 0
        this.onProbation = false; //Initialized as false
        courses = new ArrayList<>();
        takenCourses = new ArrayList<>();
    }
    
    public Student() {
        super();
        this.gpa = 0.0; //Initialized as 0
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
            
            /*
            Logic Explanation: Either the first course's ending time is before second's start
            or the first's start is after second's ending time
            
            Think about the examples:
            10:40 - 12:30 (First Course)
            11:40 - 13:30 (Second Course)
            
            ->First ending 12:30 is before second's start 11:40 -> FALSE
            ->First start 10:40 is after second's ending 13:30 -> FALSE
            results in ScheduleConflictException
            
            10:40 - 12:30 (First Course)
            13:40 - 15:30 (Second Course)
            
            ->First ending 12:30 is before second's start 13:40 -> TRUE
            ->First start 10:40 is after second's ending 15:30 -> FALSE
            results in no Exception
            
            10:40 - 12:30 (Second Course)
            13:40 - 15:30 (First Course)
            
            ->First ending 15:30 is before second's start 10:40 -> FALSE
            ->First start 13:40 is after second's ending 12:30 -> TRUE
            results in no Exception
            */
            
            
            if (!temp.getEndOfTheCourse().isBefore(c1.getStartOfTheCourse()) && !temp.getStartOfTheCourse().isAfter(c1.getEndOfTheCourse()) && temp.getDay() == c1.getDay()) {
                //Means that both false and we need to throw ScheduleConflict Exception
                throw new ScheduleConflictException("There is a Schedule conflict with the course:" + temp.getCourseName() + " and " + c1.getCourseName() + ", double check your Schedule!");
            }
        }
    }
    
    private void checkPrerequisites(Course c1) throws PrerequisiteNotMetException {
        ArrayList<String> preqs = c1.getPrerequisites();
        if (!preqs.isEmpty()) {
            //If at least one of the prerequisite is not included in the student's takenCourses array throw PrerequisiteNotMetException
            
            
            //Collect all the ID's of takenCourses
            ArrayList<String> allCourseIDs = new ArrayList<>();
            for (Course cr : takenCourses) {
                allCourseIDs.add(cr.getCourseID());
            }
            
            for (String preq : preqs) {
                if (!allCourseIDs.contains(preq)) {
                    throw new PrerequisiteNotMetException("The Student has not taken the necessary courses for the course: " + c1.getCourseID());
                }
            }
        }   
    }
    
    //If added successfully, return true otherwise false
    public void addCourse(Course c1) throws ScheduleConflictException, PrerequisiteNotMetException, CourseFullException{
        
        //Try to add course
        //If capacity is full, catch CourseFullException
        //If Prerequisities are not met, catch PrerequisiteNotMetException
        //If there is another lesson within that hour, catch ScheduleConflictException
            
        checkCapacity(c1);
        checkSchedule(c1);
        checkPrerequisites(c1);
            
        //Capacity, Schedule and Prerequisites are checked, add the course.
        courses.add(c1);
        totalCredit += c1.getCredit();
        c1.addStudent(this);
        
    }
    
    public static void calculateGPA() {
        
    }
    
    public void addTakenCourse(Course c1) {
        takenCourses.add(c1);
        //calculateGPA(); //Calculate the new GPA
    }
    
    @Override
    public String toFileFormat() {
        StringBuilder result = new StringBuilder(super.toFileFormat() + gpa + "|" + totalCredit + "|" + onProbation + "|");
        
        for (int i = 0; i < courses.size(); i++) {
            if(i < courses.size()-1){
                result.append(courses.get(i).getCourseID() + ";");
            }else{
                result.append(courses.get(i).getCourseID());
            }
        }
        
        result.append("|");
        
        for (int i = 0; i < takenCourses.size(); i++) {
            if(i < takenCourses.size()-1){
                result.append(takenCourses.get(i).getCourseID() + ";");
            }else{
                result.append(takenCourses.get(i).getCourseID());
            }
        } 
        
        
        return result.toString();
    }
}
