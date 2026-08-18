/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.session;

import javax.ejb.Local;
import za.ac.tut.person.Person;

/**
 *
 * @author gadeb
 */
@Local
public interface PersonService 
{
    public String addPerson(Person person);
    public Person getPerson(int personID);
}
