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


//This Class includes the methods that are for saving Login information for students, professors and admins
public class FileManager {
    private static final String userFileName = "Users.txt";
    
    
    public static void saveUser(User u1) throws IOException{
        FileWriter fw = new FileWriter(userFileName, true); // true = append mode
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(u1.toFileFormat());
        bw.newLine();
        bw.close();
    }
    
    //Returns True if the given user ID does exist in the records, otherwise false
    public static Boolean searchUserByID(int id) throws IOException{
        File file = new File(userFileName);
        if (!file.exists()) {
            return false;
        }
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        
        //Int to String
        String stID = String.valueOf(id);
        
        while((line = br.readLine()) != null) {
            if (line.contains(stID)) {
                return true;
            }
        }
        
        br.close();
        return false;
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
}
