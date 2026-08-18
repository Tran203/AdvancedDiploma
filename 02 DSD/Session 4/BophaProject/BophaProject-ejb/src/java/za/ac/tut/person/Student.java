/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author gadeb
 */
@Entity
@DiscriminatorValue("Student")
public class Student extends Person
{
    private int studentNo;
    private String courseCode;

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    
}
