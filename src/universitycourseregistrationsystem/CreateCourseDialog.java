/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package universitycourseregistrationsystem;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.HashMap;

public class CreateCourseDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CreateCourseDialog.class.getName());
    public CreateCourseDialog(java.awt.Frame parent, boolean modal, HashMap<String, Course> allCourses, Professor professor) {
        super(parent, modal);
        initComponents();
        
        createCourseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (courseNameField.getText().isEmpty() || departmentNameField.getText().isEmpty() || idField.getText().isEmpty() || 
                        sectionField.getText().isEmpty() || capacityField.getText().isEmpty() || creditField.getText().isEmpty() ||
                        startHourField.getText().isEmpty() || endHourField.getText().isEmpty() || dayField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, " Empty field or fields! ");
                    return;
                }
                
                try {
                    String courseName = courseNameField.getText();
                    String departmentName = departmentNameField.getText();
                    String id = idField.getText();
                    int section = Integer.parseInt(sectionField.getText());
                    int maxCapacity = Integer.parseInt(capacityField.getText());
                    int credit = Integer.parseInt(creditField.getText());
                    String startHour = startHourField.getText().trim();
                    String endHour = endHourField.getText().trim();
                    String day = dayField.getText();
                    String[] startHourInformation = startHour.split(":");
                    String[] endHourInformation = endHour.split(":");

                    //Check if valid start hour
                    HourOperation.checkValidHour(startHourInformation);
                    
                    //Check if valid end hour
                    HourOperation.checkValidHour(endHourInformation);
                    
                    //Check if start hour is before end hour
                    HourOperation.checkValidStartEndHours(startHourInformation[0], endHourInformation[0]);
                    
                    //Check valid day
                    HourOperation.checkValidDay(day);
                    
                    //Create Course Object
                    Course course = new Course(courseName, departmentName, id, section, maxCapacity, 0, credit, startHour, endHour, Day.valueOf(day.toUpperCase()));
                    
                    //Add Prerequisities
                    int choice;
                    boolean onAddingPrerequisiteMenu = true;
                    
                    while (onAddingPrerequisiteMenu) {
                        choice = JOptionPane.showConfirmDialog(rootPane,
                                "Do you want to add any Prerequisites?",
                                "Any Prerequisites?",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        
                        if (choice == JOptionPane.YES_OPTION) {
                            String input = JOptionPane.showInputDialog(rootPane, "Enter a valid course ID: ");
                            if (input != null && !input.trim().isEmpty()) {
                                if (input.trim().equals(id)) {
                                    JOptionPane.showMessageDialog(rootPane, "You can not add same course as a prerequisite! ");
                                } else if (allCourses.containsKey(input.trim())) {
                                course.addPrerequisities(input.trim());
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "There is no course with ID: " + input.trim());
                                }
                            }    
                        } else {
                            onAddingPrerequisiteMenu = false;
                        }
                    }
                    
                    //Add to the allCourses
                    allCourses.put(id, course);
                    
                    //Add course to the professor
                    professor.addCourse(course);
                    
                    //Save to the database
                    FileManager.saveCourseFile(course);
                    FileManager.saveProfessorFile(professor);
                    JOptionPane.showMessageDialog(rootPane, "The Course with ID: " + id + " has successfully created! ");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, " Please enter only integers to the fields: section, capacity, credit and hour fields! ");
                } catch (InvalidHourException | NotValidDayException | IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
                
                CreateCourseDialog.this.dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        courseNameField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        departmentNameField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        sectionField = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        capacityField = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        creditField = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        startHourField = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        endHourField = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        dayField = new javax.swing.JTextField();
        createCourseBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Course Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        courseNameField.setMinimumSize(new java.awt.Dimension(80, 30));
        courseNameField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 5);
        jPanel1.add(courseNameField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel6.add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Department Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        departmentNameField.setMinimumSize(new java.awt.Dimension(80, 30));
        departmentNameField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(departmentNameField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel6.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Course ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        idField.setMinimumSize(new java.awt.Dimension(80, 30));
        idField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 57, 5, 5);
        jPanel3.add(idField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel6.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Section Number:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel4, gridBagConstraints);

        sectionField.setMinimumSize(new java.awt.Dimension(80, 30));
        sectionField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(sectionField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel7.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Max Capacity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jLabel5, gridBagConstraints);

        capacityField.setMinimumSize(new java.awt.Dimension(80, 30));
        capacityField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 5);
        jPanel5.add(capacityField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel7.add(jPanel5, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Credit:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(jLabel6, gridBagConstraints);

        creditField.setMinimumSize(new java.awt.Dimension(80, 30));
        creditField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 65, 5, 5);
        jPanel8.add(creditField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel7.add(jPanel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel7, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel7.setText("Start Hour:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel10.add(jLabel7, gridBagConstraints);

        startHourField.setMinimumSize(new java.awt.Dimension(80, 30));
        startHourField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel10.add(startHourField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel9.add(jPanel10, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel8.setText("End Hour:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel11.add(jLabel8, gridBagConstraints);

        endHourField.setMinimumSize(new java.awt.Dimension(80, 30));
        endHourField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel11.add(endHourField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel9.add(jPanel11, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("Day:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(jLabel9, gridBagConstraints);

        dayField.setMinimumSize(new java.awt.Dimension(80, 30));
        dayField.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 45, 5, 5);
        jPanel12.add(dayField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        jPanel9.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel9, gridBagConstraints);

        createCourseBtn.setText("Create Course");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(createCourseBtn, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField capacityField;
    private javax.swing.JTextField courseNameField;
    private javax.swing.JButton createCourseBtn;
    private javax.swing.JTextField creditField;
    private javax.swing.JTextField dayField;
    private javax.swing.JTextField departmentNameField;
    private javax.swing.JTextField endHourField;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField sectionField;
    private javax.swing.JTextField startHourField;
    // End of variables declaration//GEN-END:variables
}
