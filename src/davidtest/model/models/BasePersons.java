/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model.models;

import davidtest.model.BasePerson;
import davidtest.util.BaseParentIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author lenovo
 * @param <T>
 */
public class BasePersons<T extends BasePerson> implements Iterable<T> {
    private final Map<String,T> basePersons;
    
    public BasePersons() {
        this.basePersons = new HashMap<>();
    }

    public BasePersons(Map<String,T> basePersons) {
        this.basePersons =  new HashMap<>(basePersons);
    }
    
    public BasePersons(List<T> basePersons) {
        this.basePersons = basePersons.stream()
                .collect(Collectors.toMap(x->x.getId(), Function.identity()));
    }

    public void add(T obj) {
        if (obj != null) {
            basePersons.put(obj.getId(), obj);
        }
    }
    
    //First idea: convert Map to List<T> of values
    private List<T> getAll() { 
        return (List<T>) basePersons.values();           
    }

    public T get(String id) {  
        return getAll().stream()
                .filter(element -> element.getId().equals(id))
                .findAny()
                .orElse(null);     
    }
    
    public T get(String firstName, String lastName) {
        return getAll().stream()
                .filter(element -> (element.getFirstName().equalsIgnoreCase(firstName) 
                        && element.getLastName().equalsIgnoreCase(lastName)))
                .findFirst()
                .orElse(null);
        
    }
  
     public List<T> removeDuplicates() {
        return  new ArrayList(getAll().stream()
                .filter(distinct(x-> x.getFirstName()+" "+x.getLastName()))
                .collect(Collectors.toList()));    
    }

    private static <T> Predicate<T> distinct(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return (T t) -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
  
    
    @Override
    public Iterator<T> iterator() {      
        return new BaseParentIterator<>(getAll());
    }
   
    /* second idea: use interface  */
    
//    interface Gettable{
//        <T extends BasePerson> T get(String id);
//    }
//    
//    private T get(String id, Gettable gettable) { 
//        return gettable.get(id);
//    }
//    
//    public T get(String id,boolean b) {
//        Gettable g = new Gettable() {
//
//            @Override
//            public <T extends BasePerson> T get(String id) {
//              return  (T) basePersons.get(id);
//            }
//        };
//        return get(id,g);
//    }
    
     /* Third idea : change all method to handle Map     */
    
//     public T get(String firstName, String lastName) {
//        return basePersons.entrySet().stream()
//                .filter(element -> (element.getValue().getFirstName().equalsIgnoreCase(firstName) 
//                        && element.getValue().getLastName().equalsIgnoreCase(lastName)))
//                .findFirst().get().getValue();}
    
//    public Map<String,T> removeDuplicates() {
//        Map<String, T> collect = basePersons.entrySet().stream()
//                .filter(distinct(x-> x.getValue().getFirstName()+" "+x.getValue().getLastName()))
//                .collect(Collectors.toMap(x->x.getValue().getId(), x->x.getValue()));
//        
//        return collect;    
//    }
//
//    public static <T> Predicate<T> distinct(Function<? super T, Object> keyExtractor) {
//        Map<Object, Boolean> map = new ConcurrentHashMap<>();
//        
//        return (T t) -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
//    }
     
//    @Override
//    public Iterator<T> iterator() {
//        return basePersons.values().iterator();
//    }
  
      /* Comment for implementation with list */
    
//    private final List<T> basePersons;
//    
//    public BasePersons() {
//        this.basePersons = new ArrayList<>();
//    }
//    
//    public BasePersons(List<T> baseList) {
//        this.basePersons =  new ArrayList<>(baseList);
//    }
//
//    public void add(T obj) {
//        if (obj != null) {
//            basePersons.add(obj);
//        }
//    }
//
//    public T get(String id) {
//        return basePersons.stream()
//                .filter(element -> element.getId().equals(id))
//                .findAny()
//                .orElse(null);
//    }
//
//    public T get(String firstName, String lastName) {
//        return basePersons.stream()
//                .filter(element -> (element.getFirstName().equalsIgnoreCase(firstName) 
//                        && element.getLastName().equalsIgnoreCase(lastName)))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public Iterator<T> iterator() {      
//        return new BaseParentIterator<>(basePersons);
//    }
//
//    public List<T> removeDuplicates() {
//        return  new ArrayList(basePersons.stream()
//                .filter(distinct(x-> x.getFirstName()+" "+x.getLastName()))
//                .collect(Collectors.toList()));    
//    }
//
//    private static <T> Predicate<T> distinct(Function<? super T, Object> keyExtractor) {
//        Map<Object, Boolean> map = new ConcurrentHashMap<>();
//        return (T t) -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
//    }
    
    
}

 