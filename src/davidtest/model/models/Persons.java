/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model.models;

 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import davidtest.model.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author lenovo
 */

public class Persons extends BasePersons<Person> {
     
    private List<Person> persons;

    public Persons() {
    }
    
    public Persons(@JsonProperty("persons")List<Person> persons) {
        super(persons);
        this.persons= persons;    
    }
    
    public List<Person> getPersons() {
        return persons;
    }
 
    /**
     *  sort element by gender and then DateOfBirth
     * @return List<>
     */
    public List<Person> sortByGenderThenDateOfBirth() {
        //1- creer un comparator avec deux criteres gendre premierement puis DateOfBirth
        Comparator<Person> personComparator = Comparator.comparing(Person::getGenderName)
                .thenComparing(Person::getDateOfBirth);
        
        //2- utiliser la methode sorted 
        return persons.stream()
                .sorted(personComparator)
                .collect(Collectors.toList());
         
    }
    
    @JsonIgnore
    public Map<String, Long> getCountByCountry() {
       // grouper la liste par country puis calculer le nombre d'occurence ,
        Map<String, Long> collect = persons.stream()
                .collect(Collectors.groupingBy(Person::getCountry,Collectors.counting()));
        
        return collect;  
    }  
    
}
