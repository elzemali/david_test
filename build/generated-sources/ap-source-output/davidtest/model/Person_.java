package davidtest.model;

import davidtest.model.Child;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-04T20:23:22")
@StaticMetamodel(Person.class)
public class Person_ extends BasePerson_ {

    public static volatile SingularAttribute<Person, String> country;
    public static volatile SingularAttribute<Person, String> address;
    public static volatile SingularAttribute<Person, String> eyeColor;
    public static volatile SingularAttribute<Person, String> gender;
    public static volatile SingularAttribute<Person, Long> phone;
    public static volatile ListAttribute<Person, Child> children;
    public static volatile SingularAttribute<Person, String> about;
    public static volatile SingularAttribute<Person, LocalDate> registered;
    public static volatile SingularAttribute<Person, LocalDateTime> dateOfBirth;
    public static volatile SingularAttribute<Person, Boolean> isActive;
    public static volatile SingularAttribute<Person, String> email;

}