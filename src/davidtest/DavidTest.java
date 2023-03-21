/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest;

import com.fasterxml.jackson.core.JsonFactory;
import davidtest.jpaController.PersonJpaController;
import davidtest.model.Child;
import davidtest.model.Person;
import davidtest.model.PersonGender;
import davidtest.model.models.Persons;
import davidtest.util.ConvertJson;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author lenovo
 */
public class DavidTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*test de creation d'une liste de persons manuellement */
        Persons persons = createPersons();
        //persons.forEach(System.out::println);
        
        /* test de generation de json a partir d'une liste persons */
        // testJsonPush(persons);
        
        /* test de generation de json a partir d'une liste persons avec ObjectMapper */
        //ConvertJson.personToJson("d:/dateom.json", persons);
        
        /* test de generation de person  */
        //Persons persons2 = testJsonParser("/davidtest/ressource/data.json");
        
        /* test de sorting et grouppement en Map contry - count */
        //testSortAndCountByCountry(persons2);
        
        /*test ecriture dans la base de donnée le contenu de json*/
        //testDbCreationFromJson(persons2);
        
        /*test les operations sur la base de données */
        //testJpaController();
        
        /* test de generation de person  */
        Persons persons2 = testJsonParser("/davidtest/ressource/data2.json");
    }

    public static Persons testJsonParser(String jsonFilePath) {
        try {
//          Persons persons = ConvertJson.jsonToPerson("/davidtest/ressource/data.json");
            JsonFactory jsonFactory = new JsonFactory();
            Persons persons = ConvertJson.readJson(jsonFactory, jsonFilePath);
            persons.forEach(x->System.out.println(x.getRegistered()));

            return persons;
        } catch (IOException ex) {
            Logger.getLogger(DavidTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void testJsonPush(Persons persons) {
        JsonFactory jsonFactory = new JsonFactory();
        try {
            ConvertJson.writeJsonGenerator(jsonFactory, "d:/datar.json", persons);
        } catch (IOException ex) {
            Logger.getLogger(DavidTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Persons createPersons() {
        Person p = new Person();
        p.setId("1");
        p.setFirstName("Mediouni");
        p.setLastName("Braham");
        p.setEyeColor("blue");
        p.setGender(PersonGender.FEMALE);
        p.setDateOfBirth(LocalDateTime.parse("1931-01-29T12:25:59"));
        p.setEmail("suecleveland@homelux.com");
        p.setPhone(8515913845L);
        p.setAddress("437 Matthews Court, 587 Sussex");
        p.setCountry("Algeria");
        p.setAbout("Elit veniam dolore nostrud veniam amet deserunt enim "
                + "ipsum dolore. Voluptate quis eu exercitation amet do "
                + "aliquip. Tempor non sint eu mollit dolor incididunt "
                + "sit proident ipsum in dolor esse. "
                + "Duis aliquip velit duis consectetur voluptate nostrud "
                + "excepteur anim sit et.\r\n");
        p.setIsActive(true);
        p.setRegistered(LocalDate.parse("2019-06-26"));

        Person p2 = new Person();
        p2.setId("2");
        p2.setFirstName("Lamine");
        p2.setLastName("ZEMALI");
        p2.setEyeColor("blue");
        p2.setGender(PersonGender.FEMALE);
        p2.setDateOfBirth(LocalDateTime.parse("1931-01-29T12:25:59"));
        p2.setEmail("suecleveland@homelux.com");
        p2.setPhone(8515913845L);
        p2.setAddress("437 Matthews Court, 587 Sussex");
        p2.setCountry("Burkina Faso");
        p2.setAbout("Elit veniam dolore nostrud veniam amet deserunt enim "
                + "ipsum dolore. Voluptate quis eu exercitation amet do "
                + "aliquip. Tempor non sint eu mollit dolor incididunt "
                + "sit proident ipsum in dolor esse. "
                + "Duis aliquip velit duis consectetur voluptate nostrud "
                + "excepteur anim sit et.\r\n");
        p2.setIsActive(true);
        p2.setRegistered(LocalDate.parse("2019-06-26"));

        /*Creation children*/
        List<Child> children = new ArrayList<Child>();
        Child c = new Child();
        c.setId("5e143dea0a76442e7bd93e57");
        c.setFirstName("Cherry");
        c.setLastName("Powers");
        Child c1 = new Child();
        c1.setId("1");
        c1.setFirstName("Neal");
        c1.setLastName("Salazar");
        Child c2 = new Child();
        c2.setId("5e143deaf04df685453b34da");
        c2.setFirstName("Sandoval");
        c2.setLastName("Duffy");
        children.add(c);
        p.setChildren(children);
        p2.setChildren(children);
        Persons persons = new Persons();
        persons.add(p2);
        persons.add(p);

        return persons;

    }

    public static void testPersonEtChild(Persons persons) {
        Person p = persons.get("1");
        Person p2 = persons.get("2");
        Child c1 = null; //p.getChildren().get("1");

        //Children est affiché comme référence!
        System.out.println("Test d'égalité et hashcode");
        System.out.println(p);
        //not equal
        System.out.println(p.equals(c1));
        // the same hashcode
        System.out.println(p.hashCode());
        System.out.println(c1.hashCode());

        System.out.println("Test de modification de children");
        System.out.println("avant l'add");
        p.getChildren().forEach(System.out::println);
        Child c4 = new Child();
        c4.setId("a");
        c4.setFirstName("b");
        c4.setLastName("E");
        p.getChildren().add(c4);
        System.out.println("apres l'add");
        p.getChildren().forEach(System.out::println);

        System.out.println("Test de removeDuplicates");
        System.out.println("Afficher persons avant la suppression");
        Persons persons2 = new Persons();
        persons2.add(p2);
        persons2.add(p);
        persons2.forEach(System.out::println);

        List<Person> removeDuplicates = persons2.removeDuplicates();
        System.out.println("Afficher pesrons apres la suppression");
        removeDuplicates.forEach(System.out::println);

    }

    public static void testSortAndCountByCountry(Persons persons) {
        List<Person> sortByGenderThenDateOfBirth = persons.sortByGenderThenDateOfBirth();
        System.out.println("Afficher tri par gendre et date de naissance");
        sortByGenderThenDateOfBirth.forEach(x -> System.out.println(x.getGender() + " | " + x.getDateOfBirth()));

        Map<String, Long> countryGroupe = persons.getCountByCountry();
        countryGroupe.forEach((k, v) -> System.out.println(k + " : " + v));

    }
    
    public static void testDbCreationFromJson(Persons persons) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DavidTestPU");
        PersonJpaController personJpaController = new PersonJpaController(emf);
        persons.forEach(x -> personJpaController.create(x));
        //personJpaController.createPersons(persons);
        emf.close();
        
    }
    /* test lié a la question Lire les données depuis la base */
    public static void testJpaController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DavidTestPU");
        PersonJpaController personJpaController = new PersonJpaController(emf);
        Persons persons = createPersons();
        Person person = persons.get("1");
        personJpaController.create(person);
        person.setPhone(5555L);
        personJpaController.edit(person);
        Person person1 = personJpaController.get("1");
        List<Person> all = personJpaController.getAll();
        all.stream().forEach(System.out::println);
        Person personbyFirstNameAndLastName = personJpaController.getByFirstNameAndLastName("Mediouni", "Braham");
        System.out.println("Apres find");
        System.out.println(personbyFirstNameAndLastName);
    } 

//  
}
