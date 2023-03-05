/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model.models;

 
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("persons")
   public List<Person> personList;
    
    //// for list implementation
//    public Persons(@JsonProperty("persons")List<Person> personList) {
//        super(personList);
//        this.personList= new ArrayList(personList);
//        
//    }
    public Persons(@JsonProperty("persons")List<Person> personList) {
        super(personList.stream().collect(Collectors.toMap(x->x.getId(), x->x)));
        this.personList= new ArrayList(personList);
        
    }
    
    /**
     *  sort element by gender and DateOfBirth
     */
    public void sortByGenderThenDateOfBirth (){
        // 1- creer un comparator avec deux criteres gendre premierement puis DateOfBirth
        Comparator<Person> personComparator = Comparator.comparing(Person::getGender).thenComparing(Person::getDateOfBirth);
        //2- utiliser la methode sort de collections
        Collections.sort(personList, personComparator); 
        //// to show result
      //  personList.forEach(x-> System.out.println(x.getGender()+" "+ x.getDateOfBirth()));
    }
    
    public Map<String, Long> getCountByCountry(){
        // grouper la liste par country puis calculer le nombre d'occurence ,
       Map<String, Long> collect = personList.stream().collect(Collectors.groupingBy(Person::getCountry,Collectors.counting()));
     return collect;
    
    }
    
}
