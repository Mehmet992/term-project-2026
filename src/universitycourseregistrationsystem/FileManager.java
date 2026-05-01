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
    
    //Contains yerine split ile ayırarak bul + complexity + faster
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
    
    //For each User descendant, the first five information is in the form;
    //name, surname, type, id, password -> where password is in the fifth place -> index 4
    public static Boolean isPasswordCorrect(String[] line, String password) throws PasswordIsWrongException {
        int indexOfPassword = 4;
        
        //Don't need to check whether the data is correct or not for the project -> yet, it will throw the exception
        if (line[indexOfPassword].trim().equals(password)) {
            return true;
        }
        
        throw new PasswordIsWrongException("");
    }
    
    public static void initStudent(Student s1, String[] line, ArrayList<Course> allCourses) throws IOException, FileNotFoundException {
        
        s1.setName(line[0]);
        s1.setSurname(line[1]);
        s1.setType(Types.STUDENT);
        s1.setUserID(line[3]);
        s1.setPassword(line[4]);
        
        String[] currCourses = line[5].trim().split(";");
        String[] takenCourses = line[6].trim().split(";");
        
        //Search for the ids in allCourses, if match add it to the corresponding ArrayList
        for (Course c1 : allCourses) {
            for (String takenCourse : takenCourses) {
                String tempID = c1.getCourseID();
                
                if (takenCourse.equals(tempID)) {
                    s1.addTakenCourse(c1);
                }
            }
        }
        
        for (Course c1 : allCourses) {
            for (String currCourse : currCourses) {
                String tempID = c1.getCourseID();
                
                try {
                    if (currCourse.equals(tempID)) {
                        s1.addCourse(c1);
                    }
                } catch (ScheduleConflictException ex) {
                    
                } catch (CourseFullException ex) {
                    
                } catch (PrerequisiteNotMetException ex) {
                    
                }
                
            }
        }    
    }
    
    public static ArrayList<Course> initAllCourses() throws FileNotFoundException, IOException {
        //Reach to courses.txt
        File coursesFile = new File(courseFileName);
        
        if (!coursesFile.exists()) {
            throw new FileNotFoundException("");
        }
        
        //Read the file line by line, create the corresponding object for that line and add it to the ArrayList
        ArrayList<Course> allCourses = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(coursesFile));
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] ln = line.split("\\|");
            
            //String courseName, String departmentName, String courseID, int sectionNumber, int maxCapacity, int currentCapacity, int credit, String startOfTheCourse, String endOfTheCourse, Day day
            Course tempCourse = new Course(ln[1], ln[2], ln[0], Integer.parseInt(ln[3]), Integer.parseInt(ln[4]), Integer.parseInt(ln[5]), Integer.parseInt(ln[6]), ln[7], ln[8], Day.valueOf(ln[9]));
            
            //If course has no prerequisities, continue
            if (!ln[10].equalsIgnoreCase("NONE")) {
                String[] preqs = ln[10].split(";");
            
                for (String prq : preqs) {
                    tempCourse.addPrerequisities(prq);
                }
            }
            
            allCourses.add(tempCourse);
        }
        
        return allCourses;
    }
    
    public static ArrayList<Course> returnPossibleCourses(Student student, ArrayList<Course> allCourses) {
        //Show all courses except the taken courses, current courses or the courses that have prerequisite and the student haven't taken them yet
        ArrayList<Course> tempList = new ArrayList<>(); //For displaying all the available courses
        ArrayList<String> takenCourseIDs = new ArrayList<>(); //For checking prerequisities
                   
        for (Course c1 : student.getTakenCourses()) {
            takenCourseIDs.add(c1.getCourseID());
        }
                   
        for (Course c1 : allCourses) {
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
    
    public static void enrollCourse(Student student, Course courseToBeAdded, String courseID) throws CourseNotFoundException, StudentOnProbationException, MaxNumberOfCourseException, CourseFullException, PrerequisiteNotMetException, ScheduleConflictException {       
        if (courseToBeAdded == null) {
            throw new CourseNotFoundException("");
        }
                   
        if (student.getOnProbation() && student.getCourses().size() >= 4) {
            throw new StudentOnProbationException("");
        }
        
        if (student.getCourses().size() >= 6) {
            throw new MaxNumberOfCourseException("");
        }
        
        student.addCourse(courseToBeAdded);
        
        //Write on file
        saveStudentFile(student);
    }
    
    public static void saveStudentFile(Student student) {
        
    }
    
    
    //For admin and professor

    
}
