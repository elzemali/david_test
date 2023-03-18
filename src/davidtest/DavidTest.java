/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest;

import davidtest.model.BasePerson;
import davidtest.model.Child;
import davidtest.model.Person;
import davidtest.model.PersonGender;
import davidtest.model.models.Children;
import davidtest.model.models.Persons;
import davidtest.util.ConvertJson;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class DavidTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testPersonEtChild();
        //testJsonFetch ();
    }
    
    public static void testJsonFetch () {
        try {
           //Persons persons = ConvertJson.jsonToPerson("/davidtest/ressource/data.json");
            Persons persons =  ConvertJson.readJsonGenerator("/davidtest/ressource/data.json");
            persons.forEach(x-> x.getChildren().forEach(System.out::println));
        } catch (IOException ex) {
            Logger.getLogger(DavidTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void testPersonEtChild() {
        Person p = new Person();
        String id = UUID.randomUUID().toString();
        p.setId(id);
        p.setFirstName("Sue");
        p.setLastName("Cleveland");
        p.setEyeColor("blue");
        p.setGender(PersonGender.FEMALE);
        p.setDateOfBirth(LocalDateTime.parse("1931-01-29T12:25:59"));
        p.setEmail("suecleveland@homelux.com");
        p.setPhone(8515913845L);
        p.setAddress("437 Matthews Court, 587 Sussex");
        p.setCountry("Burkina Faso");
        p.setAbout("Elit veniam dolore nostrud veniam amet deserunt enim "
                + "ipsum dolore. Voluptate quis eu exercitation amet do "
                + "aliquip. Tempor non sint eu mollit dolor incididunt "
                + "sit proident ipsum in dolor esse. "
                + "Duis aliquip velit duis consectetur voluptate nostrud "
                + "excepteur anim sit et.\r\n");
        p.setIsActive(true);
        p.setRegistered(LocalDate.parse("2019-06-26"));
        
        Person p2 = new Person();
        String id2 = UUID.randomUUID().toString();
        p2.setId(id2);
        p2.setFirstName("Sue");
        p2.setLastName("Cleveland");
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
        c1.setId(id);
        c1.setFirstName("Neal");
        c1.setLastName("Salazar");
        Child c2 = new Child();
        c2.setId("5e143deaf04df685453b34da");
        c2.setFirstName("Sandoval");
        c2.setLastName("Duffy");
        children.add(c);
        System.out.println(c);
        children.add(c1);
        System.out.println(c1);
        children.add(c2);
        System.out.println(c2);
        
        p.setChildren(children);
        p2.setChildren(children);
        
        
        //Children est affiché comme référence!
        System.out.println(p);
        System.out.println(p.equals(c1));
        System.out.println(p.hashCode());
        System.out.println(c1.hashCode());
        
        children.forEach(System.out::println);
        
        System.out.println(p);
        System.out.println("avant l'add");
        p.getChildren().forEach(System.out::println);
        Child c4 = new Child();
        c4.setId("a");
        c4.setFirstName("b");
        c4.setLastName("E");
        p.getChildren().add(c4);
        System.out.println("apres l'add");
        p.getChildren().forEach(System.out::println);
        
        System.out.println("Afficher pesrons avant la suppression");
        Persons persons = new Persons();
        persons.add(p2);
        persons.add(p);
        persons.forEach(System.out::println);
        
        List<Person> removeDuplicates = persons.removeDuplicates();
        System.out.println("Afficher pesrons apres la suppression");
        removeDuplicates.forEach(System.out::println);
        
        
    }

    public void testBasePerson() {
        BasePerson baseParent = new BasePerson();
        baseParent.setId(UUID.randomUUID().toString());
        baseParent.setFirstName("ZEMALI");
        baseParent.setLastName("Lamine");
        BasePerson baseParent2 = new BasePerson();
        String id = UUID.randomUUID().toString();
        baseParent2.setId(id);
        baseParent2.setFirstName("RAHMANI");
        baseParent2.setLastName("Amine");
        BasePerson baseParent3 = new BasePerson();
        baseParent3.setId(id);
        baseParent3.setFirstName("MEDIOUNI");
        baseParent3.setLastName("Rayan");

        System.out.println(baseParent);
        System.out.println(baseParent.hashCode());
        System.out.println(baseParent.equals(baseParent2));
        System.out.println(baseParent2.equals(baseParent3));
        System.out.println(baseParent2.hashCode());
        System.out.println(baseParent3.hashCode());
    }

//  
}
