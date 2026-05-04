/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package universitycourseregistrationsystem;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author mehmet
 */
public class adminMenuPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(adminMenuPage.class.getName());
    public adminMenuPage(Admin admin, HashMap<String, Course> allCourses, HashMap<String, Student> allStudents) {
        initComponents();
        
        this.setTitle(admin.getName() + " " + admin.getSurname() + " - ADMIN");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        
        btnLoadUsers.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               StringBuilder res = new StringBuilder(FileManager.loadUsers());
               jTextArea.setText(res.toString());
           } 
        });
        
        btnSaveUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String typeInput = typeField.getText().trim().toUpperCase();
                
                //Empty field check
                if(nameField.getText().isEmpty() || surnameField.getText().isEmpty() || idField.getText().isEmpty() || typeField.getText().isEmpty() || passwordField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Empty field(s), please fill in all fields!");
                    return;
                }
                
                try{
                    Types type = Types.valueOf(typeInput); // String -> enum
                    User newUser;
                    if(type == Types.STUDENT){
                        newUser = new Student(nameField.getText(), surnameField.getText(), type, passwordField.getText(), idField.getText());
                    }else if(type == Types.PROFESSOR){
                        newUser = new Professor(nameField.getText(), surnameField.getText(), type, passwordField.getText(), idField.getText());
                    }else{
                        throw new IllegalArgumentException(); //Throwing the exception when other types are written
                    }
                    
                    FileManager.saveUser(newUser); //Saving the new user to user file
                    JOptionPane.showMessageDialog(rootPane, "User successfully saved!");
                    nameField.setText("");
                    surnameField.setText("");
                    typeField.setText("");
                    passwordField.setText("");
                    idField.setText("");
                }catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(rootPane, "Invalid Type! Please write STUDENT or PROFESSOR!");
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(rootPane, "File Error: " + ex.getMessage());
                }catch(Exception ex){
                    ex.printStackTrace(); //Showing other exception messages in the console
                }
            } 
        });
        
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String idToDelete = idField.getText().trim();
               
               if(idToDelete.isEmpty()){
                   JOptionPane.showMessageDialog(rootPane, "Please enter the ID of the user to delete!");
                   return;
               }
               
               int confirm = JOptionPane.showConfirmDialog(rootPane, "User with " + idToDelete + " is about to be deleted, are you sure?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
               if(confirm == JOptionPane.YES_OPTION){
                   if(FileManager.deleteUser(idToDelete)){
                       JOptionPane.showMessageDialog(rootPane, "User successfully deleted!");
                       jTextArea.setText("");
                       idField.setText("");
                   }else{
                       JOptionPane.showMessageDialog(rootPane, "User not found or error occured!");
                   }
               }
            } 
        });
        
        generateReportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append("~~~~~~ Report of All Courses and its Students ~~~~~~").append("\n\n");
                for (String k : allCourses.keySet()) {
                    Course c = allCourses.get(k);

                    if (c != null) {
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append("---- ").append(c.getCourseName()).append(" ----").append("\n");

                        for (Student s : c.getStudents()) {
                            sb1.append(" ->").append(s.getName()).append(" ").append(s.getSurname()).append(" - ").append(s.getGPA()).append("\n");
                        }

                        sb.append(sb1);
                    }
                }
                
                jTextArea.setText(sb.toString());
                
            }
        }); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        btnLoadUsers = new javax.swing.JButton();
        btnSaveUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        surnameLabel = new javax.swing.JLabel();
        surnameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        typeField = new javax.swing.JTextField();
        generateReportBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        btnLoadUsers.setText("Load Users");

        btnSaveUser.setText("Save User");

        btnDeleteUser.setText("Delete User");

        idLabel.setText("User ID:");

        nameLabel.setText("Name:");

        idField.setMinimumSize(new java.awt.Dimension(70, 30));
        idField.setPreferredSize(new java.awt.Dimension(70, 30));

        nameField.setMinimumSize(new java.awt.Dimension(70, 30));
        nameField.setPreferredSize(new java.awt.Dimension(70, 30));

        surnameLabel.setText("Surname:");

        surnameField.setMinimumSize(new java.awt.Dimension(70, 30));
        surnameField.setPreferredSize(new java.awt.Dimension(70, 30));

        passwordField.setMinimumSize(new java.awt.Dimension(70, 30));
        passwordField.setPreferredSize(new java.awt.Dimension(70, 30));

        typeLabel.setText("Type:");

        passwordLabel.setText("Password:");

        typeField.setMinimumSize(new java.awt.Dimension(70, 30));
        typeField.setPreferredSize(new java.awt.Dimension(70, 30));

        generateReportBtn.setText("Report");
        generateReportBtn.addActionListener(this::generateReportBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnSaveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(btnLoadUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(generateReportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addGap(20, 20, 20)))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(surnameField, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(generateReportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateReportBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnLoadUsers;
    private javax.swing.JButton btnSaveUser;
    private javax.swing.JButton generateReportBtn;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField surnameField;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JTextField typeField;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
