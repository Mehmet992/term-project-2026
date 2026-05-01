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
                
                if (currCourse.equals(tempID)) {
                    s1.addCourse(c1);
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
    
    
    //For admin and professor

    
}
