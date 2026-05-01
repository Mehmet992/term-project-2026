/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package universitycourseregistrationsystem;

import java.io.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class studentMenuPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(studentMenuPage.class.getName());
    public studentMenuPage(Student student, ArrayList<Course> allCourses) {
        initComponents();
        
        this.setSize(500, 400);
        
        this.setLocationRelativeTo(null);
        
        viewTranscriptBtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               StringBuilder res = new StringBuilder();
               
               res.append(student.getName()).append("\n");
               res.append(student.getSurname()).append("\n");
               res.append(student.getUserID()).append("\n");
               res.append(student.getGPA()).append("\n");
               res.append(student.getTotalCredit()).append("\n");
               res.append("On Probation: " + student.getOnProbation()).append("\n");
               //onProbationda olup olmadığını da yazdır
               
               //Kursları yazdır.
               ArrayList<Course> studentCourses = student.getCourses();
               ArrayList<Course> studentTakenCourses = student.getTakenCourses();
               
               res.append("\n").append("----- Current Courses -----").append("\n");
               
               for (Course c1 : studentCourses) {
                   res.append(c1.getCourseName()).append("\n");
               }
               
               res.append("\n").append("----- Taken Courses -----").append("\n");
               
               for (Course c1 : studentTakenCourses) {
                   res.append(c1.getCourseName()).append("\n");
               }
               
               textArea.setText(res.toString());
           } 
        });
        
        enrollCourseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //When it is empty and clicked, show the possible Courses that can be added
                ArrayList<Course> possibleCourses = FileManager.returnPossibleCourses(student, allCourses);
                if (enrollTextField.getText().isEmpty()) {
                     StringBuilder sb = new StringBuilder();
                     for (Course c1 : possibleCourses) {
                         sb.append(c1.getCourseID()).append(" - ").append(c1.getCourseName()).append(" - ").append(c1.getStartOfTheCourse()).append(" - ").append(c1.getEndOfTheCourse()).append(" - ").append(c1.getDay()).append("\n");
                     }

                     textArea.setText(sb.toString());
                } else {
                    String courseID = enrollTextField.getText();
                    Course courseToBeAdded = null;
                    for (Course c1 : possibleCourses) {
                        if (c1.getCourseID().equals(courseID)) {
                        courseToBeAdded = c1;
                        break;
                        }
                    }


                    try {
                        FileManager.enrollCourse(student, courseToBeAdded, courseID);
                        JOptionPane.showMessageDialog(rootPane, "The Course with ID: " + courseID + " has successfully added! ");
                    } catch (CourseNotFoundException ex){
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    } catch (StudentOnProbationException ex){
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    } catch (MaxNumberOfCourseException ex){
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    } catch (CourseFullException ex){
                        int choice = JOptionPane.showConfirmDialog(rootPane,
                                "This Course is full right now! Do you want to be on Wait List? ",
                                "Course Full!",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        
                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                               FileManager.addToWaitList(student, courseToBeAdded);
                               JOptionPane.showMessageDialog(rootPane, "The Student has successfully added to waitlist for the course with ID: " + courseID);
                            } catch (IOException ex1) {
                                JOptionPane.showMessageDialog(rootPane, ex1.getMessage());
                            }
                        }
                    } catch (ScheduleConflictException ex){
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    } catch (PrerequisiteNotMetException ex){
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    }
                }
               
                
               
            } 
        });
        
        dropCourseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Take the id of the course that will be dropped, search the line with id if doesn't exist, throw new CourseNotFoundException
                //Rearrange the line and rewrite the file that's it 
                
                String courseID = dropTextField.getText();
                if (courseID.isEmpty()) {
                    //Show the current courses
                    StringBuilder sb = new StringBuilder();
                    for (Course c1 : student.getCourses()) {
                        sb.append(c1.getCourseID()).append(" - ").append(c1.getCourseName()).append(" - ").append(c1.getStartOfTheCourse()).append(" - ").append(c1.getEndOfTheCourse()).append(" - ").append(c1.getDay()).append("\n");
                    }
                    
                    textArea.setText(sb.toString());
                } else {
                    Course c = null;
                    for (Course course : allCourses) {
                        if (course.getCourseID().equals(courseID)) {
                            c = course;
                            break;
                        }
                    }

                    if (c == null) {
                        JOptionPane.showMessageDialog(rootPane, "No Course with ID: " + courseID);
                        return;
                    }

                    try { //DROP COURSE YAPILDIĞI ZAMAN WAİTLİSTTE BİRİSİ VARSA ONU DERSE EKLE!
                        FileManager.dropCourse(student, c);
                        JOptionPane.showMessageDialog(rootPane, "The Course with ID: " + courseID + " has successfully dropped! ");
                    } catch (CourseNotFoundException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    }
                }
                
                
                 
            }
        });
    }
    
    public studentMenuPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        enrollCourseBtn = new javax.swing.JButton();
        enrollTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        dropCourseBtn = new javax.swing.JButton();
        dropTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        viewTranscriptBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        enrollCourseBtn.setText("Enroll Course");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(enrollCourseBtn, gridBagConstraints);

        enrollTextField.setMinimumSize(new java.awt.Dimension(120, 80));
        enrollTextField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(enrollTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 20, 5);
        jPanel4.add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        dropCourseBtn.setText("Drop Course");
        dropCourseBtn.addActionListener(this::dropCourseBtnActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(dropCourseBtn, gridBagConstraints);

        dropTextField.setMinimumSize(new java.awt.Dimension(120, 80));
        dropTextField.setPreferredSize(new java.awt.Dimension(80, 30));
        dropTextField.addActionListener(this::dropTextFieldActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(dropTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 20, 5);
        jPanel4.add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        viewTranscriptBtn.setText("View Transcript");
        viewTranscriptBtn.addActionListener(this::viewTranscriptBtnActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(viewTranscriptBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 68, 5);
        jPanel4.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dropCourseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropCourseBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dropCourseBtnActionPerformed

    private void dropTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dropTextFieldActionPerformed

    private void viewTranscriptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewTranscriptBtnActionPerformed
    
    }//GEN-LAST:event_viewTranscriptBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dropCourseBtn;
    private javax.swing.JTextField dropTextField;
    private javax.swing.JButton enrollCourseBtn;
    private javax.swing.JTextField enrollTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    private javax.swing.JButton viewTranscriptBtn;
    // End of variables declaration//GEN-END:variables
}
