/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model.models;

import davidtest.model.Child;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class Children extends BasePersons<Child> {

    public Children(List<Child> baseList) {
        super(baseList);
    }
    
}
