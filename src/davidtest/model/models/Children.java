/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import davidtest.model.Child;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class Children extends BasePersons<Child> {
    
    private List<Child> children;
    
    public Children() {
    }
    
    public Children(List<Child> children) {
        super(children);
        this.children = new ArrayList<>(children);
    }

    public List<Child> getChildren() {
        return children;
    }
    
    
    
    
}
