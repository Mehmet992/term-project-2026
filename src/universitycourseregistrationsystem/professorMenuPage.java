package universitycourseregistrationsystem;

import java.util.HashMap;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JOptionPane;

public class professorMenuPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(professorMenuPage.class.getName());
    public professorMenuPage(Professor professor, HashMap<String, Course> allCourses, HashMap<String, Student> allStudents) {
        initComponents();
        
        this.setTitle(professor.getName() + " " + professor.getSurname() + " - PROFESSOR");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        
        //Create course 
        createCourseBtn.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                CreateCourseDialog dialog = new CreateCourseDialog(professorMenuPage.this, true, allCourses, professor);
                dialog.setVisible(true);
            }
        });
        
        //Assign grade
        assignGradeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idTextField.getText();
                    String grade = gradeTextField.getText();
                    String courseID = courseIdTextField.getText();
                    
                    if (id.isEmpty() || grade.isEmpty() || courseID.isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, " Empty field or fields! ");
                        return;
                    }
                    
                    //find the student object
                    Student student = allStudents.get(id);
                    
                    if (student == null) {
                        JOptionPane.showMessageDialog(rootPane, " No student with the id: " + id);
                        return;
                    } else {
                        //The student is found, find the course and its index
                        int indexOfCourse = -1;
                        for (int i = 0; i < student.getCourses().size(); i++) {
                            if (student.getCourses().get(i).getCourseID().equals(courseID)) {
                                indexOfCourse = i;
                                break;
                            }
                        }
                        
                        if (indexOfCourse == -1) {
                            JOptionPane.showMessageDialog(rootPane, "The student: " + student.getName() + " has no course with id: " + courseID);
                            return;
                        }
                        
                        if (!professor.getGivenCourses().contains(allCourses.get(courseID))) {
                            JOptionPane.showMessageDialog(rootPane, " Professor doesn't have a course with ID: " + courseID);
                            return;
                        }
                        
                        if (Integer.parseInt(grade) > 100 || Integer.parseInt(grade) < 0) {
                            JOptionPane.showMessageDialog(rootPane, "Enter a valid grade between 0 - 100");
                            return;
                        }
                        
                        //Assign the grade, recalculate gpa and probation
                        student.changeGrade(indexOfCourse, grade);
                        student.calculateGPA();
                        student.checkProbation();
                        
                        //Save the student to database
                        FileManager.saveStudentFile(student);
                        JOptionPane.showMessageDialog(rootPane, "The grade of the course: " + courseID + " of the student " + student.getName() + " has changed successfully! ");
                    }
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, " Enter a valid grade! ");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
                
            }
        });
        
        //View enrolled students
        viewStudentsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Find all the students that takes any courses from the professor
                StringBuilder sb = new StringBuilder();
                for (Course c : professor.getGivenCourses()) {
                    sb.append("---- ").append(c.getCourseName()).append(" ----").append("\n\n");
                        
                    StringBuilder sb1 = new StringBuilder();
                    for (Student st : c.getStudents()) {
                        sb1.append(st.getName()).append(" ").append(st.getSurname()).append(" - ").append(st.getGradeOfTheCourse(c)).append("\n");
                    }
                        
                    sb.append(sb1);
                }
                    
                displayArea.setText(sb.toString());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        createCourseBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        assignGradeBtn = new javax.swing.JButton();
        idTextField = new javax.swing.JTextField();
        gradeTextField = new javax.swing.JTextField();
        courseIdTextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        viewStudentsBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        displayArea.setColumns(20);
        displayArea.setRows(5);
        jScrollPane1.setViewportView(displayArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(130, 150));
        jPanel1.setPreferredSize(new java.awt.Dimension(130, 150));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        createCourseBtn.setText("Create Course");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 120, 10);
        jPanel1.add(createCourseBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 47;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jPanel1, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(130, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(130, 150));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        assignGradeBtn.setText("Assign Grade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanel2.add(assignGradeBtn, gridBagConstraints);

        idTextField.setText("Student ID");
        idTextField.setMinimumSize(new java.awt.Dimension(80, 30));
        idTextField.setPreferredSize(new java.awt.Dimension(80, 30));
        idTextField.addActionListener(this::idTextFieldActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanel2.add(idTextField, gridBagConstraints);

        gradeTextField.setText("Grade");
        gradeTextField.setMinimumSize(new java.awt.Dimension(80, 30));
        gradeTextField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanel2.add(gradeTextField, gridBagConstraints);

        courseIdTextField.setText("Course ID");
        courseIdTextField.setMinimumSize(new java.awt.Dimension(80, 30));
        courseIdTextField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanel2.add(courseIdTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jPanel2, gridBagConstraints);

        jPanel3.setMinimumSize(new java.awt.Dimension(130, 150));
        jPanel3.setPreferredSize(new java.awt.Dimension(130, 150));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        viewStudentsBtn.setText("View Students");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 35, 130, 8);
        jPanel3.add(viewStudentsBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 30, 0);
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignGradeBtn;
    private javax.swing.JTextField courseIdTextField;
    private javax.swing.JButton createCourseBtn;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JTextField gradeTextField;
    private javax.swing.JTextField idTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton viewStudentsBtn;
    // End of variables declaration//GEN-END:variables
}
