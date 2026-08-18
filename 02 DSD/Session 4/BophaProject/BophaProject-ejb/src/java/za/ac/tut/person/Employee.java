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
@DiscriminatorValue("Employee")
public class Employee extends Person
{
    private int staffNo;
    private double salary;

    public int getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(int staffNo) {
        this.staffNo = staffNo;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
            
}
