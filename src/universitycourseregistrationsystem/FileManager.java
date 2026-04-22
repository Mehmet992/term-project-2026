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
    private static final String userFileName = "Users";
    
    
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
}
