package za.ac.tut.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import za.ac.tut.person.Address;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2026-08-15T12:58:49")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile ListAttribute<Person, Address> address;
    public static volatile SingularAttribute<Person, String> surname;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, String> dateOfbirth;
    public static volatile SingularAttribute<Person, Integer> personID;
    public static volatile SingularAttribute<Person, String> IDno;

}