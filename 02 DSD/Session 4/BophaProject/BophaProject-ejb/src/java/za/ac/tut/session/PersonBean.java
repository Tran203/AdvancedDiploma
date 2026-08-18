/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.tut.person.Person;

/**
 *
 * @author gadeb
 */
@Stateless
public class PersonBean implements PersonService {

    @PersistenceContext(unitName="BophaProject-ejbPU")
    EntityManager manager;
    
    @Override
    public String addPerson(Person person) {
       manager.persist(person);
       return "Person Added";
    }

    @Override
    public Person getPerson(int personID) {
     return manager.find(Person.class,personID);
    }
    
}
