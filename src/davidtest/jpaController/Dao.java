/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.jpaController;

import java.util.List;

/**
 *
 * @author lenovo
 */
public interface Dao <T> {
    
    public String create(T t);
    
    public void edit(T t);
    
    public T get(String id);
    
    public void destroy(final String id);
    
    public T getByFirstNameAndLastName(String firstName,String lastName);
    
    public List<T> getAll();
    
}
