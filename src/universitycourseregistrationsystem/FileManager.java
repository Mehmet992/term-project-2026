/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitycourseregistrationsystem;

/**
 *
 * @author mehmet
 */
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


//This Class includes the methods that are for saving Login information for students, professors and admins
public class FileManager {
    private static final String userFileName = "Users.txt";
    private static final String courseFileName = "Courses.txt";
    
    
    public static void saveUser(User u1) throws IOException{
        FileWriter fw = new FileWriter(userFileName, true); // true = append mode
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(u1.toFileFormat());
        bw.newLine();
        bw.close();
    }
    
    public static boolean deleteUser(String targetID){
        File file = new File(userFileName);
        ArrayList<String> users = new ArrayList<>();
        boolean found = false;
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("\\|");
                if(!parts[3].equals(targetID)){
                    users.add(line);
                }else{
                    found = true;
                }
            }
        }catch(IOException e){
            return false;
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (String userLine : users) {
                pw.println(userLine);
            }
        } catch (IOException e) {
            return false;
        }
        return found;
    }
    
    public static String loadUsers(){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(userFileName))){
            String line;
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
        }catch(IOException e){
            return "Error while reading the file";
        }
        return sb.toString();
    }
    
    public static String searchUserByID1(String id) throws IOException, FileNotFoundException, UserNotFoundException {
        File file = new File(userFileName);
        if (!file.exists()) {
            throw new FileNotFoundException("");
        }
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        
        
        while((line = br.readLine()) != null) {
            if (line.contains(id)) {
                sb.append(line).append("\n");
            }
        }
        
        br.close();
        if (sb.isEmpty()) {
            throw new UserNotFoundException("");
        }
        
        return sb.toString();
    }
    
    public static Boolean isPasswordCorrect(String[] line, String password) throws PasswordIsWrongException {
        int indexOfPassword = 4;
        
        //Don't need to check whether the data is correct or not for the project -> yet, it will throw the exception
        if (line[indexOfPassword].trim().equals(password)) {
            return true;
        }
        
        throw new PasswordIsWrongException("");
    }
    
    public static HashMap<String, Course> initAllCourses(HashMap<String, Student> allStudents) throws FileNotFoundException, IOException {
        //Reach to courses.txt
        File coursesFile = new File(courseFileName);
        
        if (!coursesFile.exists()) {
            throw new FileNotFoundException("");
        }
        
        //Read the file line by line, create the corresponding object for that line and add it to the ArrayList
        HashMap<String, Course> allCourses = new HashMap<>();
        
        BufferedReader br = new BufferedReader(new FileReader(coursesFile));
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] ln = line.split("\\|", -1);
            
            //String courseName, String departmentName, String courseID, int sectionNumber, int maxCapacity, int currentCapacity, int credit, String startOfTheCourse, String endOfTheCourse, Day day
            Course tempCourse = new Course(ln[0], ln[1], ln[2], Integer.parseInt(ln[3]), Integer.parseInt(ln[4]), 0, Integer.parseInt(ln[6]), ln[7], ln[8], Day.valueOf(ln[9]));
            
            //If course has no prerequisities, continue
            if (!ln[10].equalsIgnoreCase("NONE")) {
                String[] preqs = ln[10].split(";");
            
                for (String prq : preqs) {
                    tempCourse.addPrerequisities(prq);
                }
            }
            
            //Add the students in waitlist
            if(!ln[11].equalsIgnoreCase("NONE")) {
                String[] waitlistStudents = ln[11].split(";");
                
                for (String st : waitlistStudents) {
                    tempCourse.addToWaitlist(allStudents.get(st));
                }
            }
            
            allCourses.put(ln[2], tempCourse);
        }
        
        br.close();
        return allCourses;
    }
    
    public static HashMap<String, Student> initAllStudents() throws IOException{
        File studentsFile = new File(userFileName);
        
        if (!studentsFile.exists()) {
            throw new IOException(" File not found! ");
        }
        
        HashMap<String, Student> allStudents = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(studentsFile));
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            
            if (!parts[2].toUpperCase().equals(Types.STUDENT.toString())) continue;
            
            Student tempStudent = new Student();
            
            tempStudent.setName(parts[0]);
            tempStudent.setSurname(parts[1]);
            tempStudent.setType(Types.STUDENT);
            tempStudent.setUserID(parts[3]);
            tempStudent.setPassword(parts[4]);
            tempStudent.setGPA(Double.valueOf(parts[5]));
            tempStudent.setTotalCredit(Integer.parseInt(parts[6]));
            tempStudent.setProbation(Boolean.valueOf(parts[7]));
            
            //All the courses will be initialized after initializing the courses!
            allStudents.put(parts[3], tempStudent);
        }
        
        br.close();
        return allStudents;
    }
    
    public static void initStudentCourses(HashMap<String, Student> allStudents, HashMap<String, Course> allCourses) throws IOException {
        File studentsFile = new File(userFileName);
        
        if (!studentsFile.exists()) {
            throw new IOException(" File not found! ");
        }
        
        BufferedReader br = new BufferedReader(new FileReader(studentsFile));
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|", -1);
            
            if (!parts[2].trim().equalsIgnoreCase(Types.STUDENT.toString())) continue;
            
            //Get the student
            Student tempStudent = allStudents.get(parts[3]);
            
            //Add taken courses
            if (!parts[9].isEmpty()) {
                String[] takenCourses = parts[9].split(";", -1);  
                
                for (String id : takenCourses) {
                    Course foundCourse = allCourses.get(id);
                    
                    if (foundCourse != null) {
                        tempStudent.addTakenCourse(foundCourse);
                    }
                    
                }
            }
            
            //Add current courses
            if (!parts[8].isEmpty()) {
                String[] currentCourses = parts[8].split(";", -1);
                
                for (String id : currentCourses) {
                    Course foundCourse = allCourses.get(id);
                    
                    if (foundCourse != null) {
                        
                        try {
                        tempStudent.addCourse(allCourses.get(id));
                        } catch (ScheduleConflictException ex) {
                        } catch (PrerequisiteNotMetException ex) {
                        } catch (CourseFullException ex) {
                        }
                        
                    }
                }
            }
        }
        
        br.close();
    }
    
    public static ArrayList<Course> returnPossibleCourses(Student student, HashMap<String, Course> allCourses) {
        //Show all courses except the taken courses, current courses or the courses that have prerequisite and the student haven't taken them yet
        ArrayList<Course> tempList = new ArrayList<>(); //For displaying all the available courses
        ArrayList<String> takenCourseIDs = new ArrayList<>(); //For checking prerequisities
                   
        for (Course c1 : student.getTakenCourses()) {
            takenCourseIDs.add(c1.getCourseID());
        }
                   
        for (Course c1 : allCourses.values()) {
            if (student.getCourses().contains(c1) || student.getTakenCourses().contains(c1)) {
                continue;
            }
                       
            ArrayList<String> preqs = c1.getPrerequisites();
            Boolean allPreqsMet = true;
                       
            if (!preqs.isEmpty()) {
            for (String preq : preqs) {
                if (!takenCourseIDs.contains(preq)) {
                    allPreqsMet = false;
                    }
                 }
            }
                       
            if (allPreqsMet) {
                tempList.add(c1);
            }
        }
        
        return tempList;
    }
    
    public static void enrollCourse(Student student, Course courseToBeAdded, String courseID) throws CourseNotFoundException, StudentOnProbationException, MaxNumberOfCourseException, CourseFullException, PrerequisiteNotMetException, ScheduleConflictException, IOException {       
        if (courseToBeAdded == null) {
            throw new CourseNotFoundException("There is no possible course with ID: " + courseID);
        }
        
        //When choosing a new course, check if the person onProbation or not, if onProbation max 4 courses allowed, otherwise maximum 6
        if (student.getOnProbation() && student.getCourses().size() >= 4) {
            throw new StudentOnProbationException("Student is on probation, cannot take more than 4 courses! ");
        }
        
        if (student.getCourses().size() >= 6) {
            throw new MaxNumberOfCourseException("Max number of courses! ");
        }
        
        student.addCourse(courseToBeAdded);
        student.setTotalCredit(student.getTotalCredit() + courseToBeAdded.getCredit());
        //courseToBeAdded.setCurrentCapacity(courseToBeAdded.getCurrentCapacity() + 1);
        
        //Write on file
        try { 
            saveStudentFile(student);
            saveCourseFile(courseToBeAdded);
        } catch (IOException ex) { //Send it to the UI
            throw new IOException("Something went wrong while file operation! ");
        }
    }
    
    public static void dropCourse(Student student, Course c) throws CourseNotFoundException, IOException {
        if (c != null) {
            Boolean isRemoved = student.getCourses().removeIf(course -> course.getCourseID().equals(c.getCourseID())); 
            if (isRemoved) {
                try {
                    //Decrease the student's credit by dropped course credit
                    student.setTotalCredit(student.getTotalCredit() - c.getCredit());

                    //Remove the student from Course
                    c.getStudents().removeIf(st -> st.getUserID().equals(student.getUserID()));

                    //Decrease the capacity of the Course
                    c.setCurrentCapacity(c.getCurrentCapacity() - 1);
                    
                    checkWaitlist(c);

                    //Save the file
                    saveStudentFile(student);
                    saveCourseFile(c);
                } catch (IOException ex) {
                    throw new IOException("Something went wrong while file operation! ");
                }
            } else {
                throw new CourseNotFoundException("Course with ID: " + c.getCourseID() + " is not found! ");
            }
        }    
    }
    
    public static void saveStudentFile(Student student) throws IOException {
        ArrayList<String> allLines = new ArrayList<>();

        // 1. ADIM: Dosyayı oku ve bağlantıyı hemen kapat
        try (BufferedReader br = new BufferedReader(new FileReader(userFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Boş satırları atla

                String[] parts = line.split("\\|");
                if (parts.length > 0 && parts[3].equals(student.getUserID())) {
                    allLines.add(student.toFileFormat());
                } else {
                    allLines.add(line);
                }
            }
        } 

        // 2. ADIM: Dosyayı temizle ve listeyi içine yaz
        try (PrintWriter pw = new PrintWriter(new FileWriter(userFileName, false))) {
            for (String line1 : allLines) {
                pw.println(line1);
            }
            pw.flush();
        }
    }
    
    public static void saveCourseFile(Course course) throws IOException{
        ArrayList<String> allLines = new ArrayList<>();

        // 1. ADIM: Dosyayı oku ve bağlantıyı hemen kapat
        try (BufferedReader br = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Boş satırları atla

                String[] parts = line.split("\\|");
                if (parts.length > 0 && parts[2].equals(course.getCourseID())) {
                    allLines.add(course.toFileFormat());
                } else {
                    allLines.add(line);
                }
            }
        } 

        // 2. ADIM: Dosyayı temizle ve listeyi içine yaz
        try (PrintWriter pw = new PrintWriter(new FileWriter(courseFileName, false))) {
            for (String line1 : allLines) {
                pw.println(line1);
            }
            pw.flush();
        }
    }
    
    public static void addToWaitList(Student student, Course course) throws IOException{
        if (course.getCurrentCapacity() >= course.getMaxCapacity()) {
            try {
                course.addToWaitlist(student);
            
                saveCourseFile(course);
            } catch (IOException ex) {
                throw new IOException("Something went wrong while file operation! ");
            }
            
        }
    }
    
    public static void checkWaitlist(Course c) throws IOException {
        //When a student drops the course c, check the waitlist of the course and if there any student, enroll him to the course
        if (!c.getWaitList().isEmpty()) {
            try {
                Student stToBeAdded = c.getWaitList().remove(0); //Removes the first student and returns it
                
                enrollCourse(stToBeAdded, c, c.getCourseID());
                saveCourseFile(c);
            } catch (CourseNotFoundException | PrerequisiteNotMetException | ScheduleConflictException | StudentOnProbationException | MaxNumberOfCourseException | CourseFullException ex) {
                checkWaitlist(c); //The student is not available, try to next one
            } catch (IOException ex) {
                throw new IOException("Something went wrong while file operation! ");
            }
            
        }
    }
    //For admin and professor

    
}
