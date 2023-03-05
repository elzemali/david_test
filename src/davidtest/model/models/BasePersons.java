/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model.models;

import davidtest.model.BasePerson;
import java.util.Iterator;
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

////    Comment for implementation with list 
//    private final List<T> basePersonList;
//    
//    public BasePersons() {
//        this.basePersonList = new ArrayList<>();
//    //    this.basePersonMap = new ConcurrentHashMap<>();
//    }
//    
//    // TODO vérifier s'il est necessaire ou pas
//    public BasePersons(List<T> baseList) {
//        this.basePersonList =  new ArrayList<>(baseList);
//    }
//
//    public void add(T obj) {
//        if (obj != null) {
//            basePersonList.add(obj);
//        }
//    }
//
//    //
//    public T get(String id) {
//        return basePersonList.stream()
//                .filter(element -> element.getId().equals(UUID.fromString(id)))
//                .findAny()
//                .orElse(null);
//        /*
//         return baseList.stream()
//         .filter(element -> element.getId().equals(UUID.fromString(id)))
//         .findAny().get();*/
//    }
//
//    public T get(String firstName, String lastName) {
//        return basePersonList.stream()
//                .filter(element -> (element.getFirstName().equalsIgnoreCase(firstName) && element.getLastName().equalsIgnoreCase(lastName)))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//        //   return new Iterator<T>
//        //return baseList.listIterator();
//        return new DeepIterator<>(basePersonList);
//    }
//
//    public List<T> removeDuplicates() {
//        
//        
//     return  new ArrayList(basePersonList.stream().filter(distinct(x-> x.getFirstName()+" "+x.getLastName())).collect(Collectors.toList()));
//       
//       
//     // return (List<T>) baseList.stream().collect(Collectors.toMap(x-> x.getFirstName()+" "+x.getLastName(), x->x)).values();
//       
//        
//    }
//
//    public static <T> Predicate<T> distinct(Function<? super T, Object> keyExtractor) {
//        Map<Object, Boolean> map = new ConcurrentHashMap<>();
//        return (T t) -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
//    }
//    
      private final Map<String,T> basePersonMap;
    
    public BasePersons() {
        this.basePersonMap = new ConcurrentHashMap<>();
   
    }
    
    // TODO vérifier s'il est necessaire ou pas
    public BasePersons(Map<String,T> baseList) {
        this.basePersonMap =  new ConcurrentHashMap<>(baseList);
    }

    public void add(T obj) {
        if (obj != null) {
            basePersonMap.put(obj.getId(), obj);
        }
    }

    //
    public T get(String id) {
        return basePersonMap.get(id);
    }

    public T get(String firstName, String lastName) {
        
        return basePersonMap.entrySet().stream()
                .filter(element -> (element.getValue().getFirstName().equalsIgnoreCase(firstName) 
                                     && element.getValue().getLastName().equalsIgnoreCase(lastName))).findFirst().get().getValue();
    }

    @Override
    public Iterator<T> iterator() {
        return basePersonMap.values().iterator();
    }

    public Map<String,T> removeDuplicates() {
       
          Map<String, T> collect = basePersonMap.entrySet().stream()
                  .filter(distinct(x-> x.getValue().getFirstName()+" "+x.getValue().getLastName()))
                  .collect(Collectors.toMap(x->x.getValue().getId(), x->x.getValue()));
        
     return collect;
   
        
    }

    public static <T> Predicate<T> distinct(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return (T t) -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
  
    
   

}
