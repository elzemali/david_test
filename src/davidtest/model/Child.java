/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
 @Entity
@Table(name = "child")
@XmlRootElement
public class Child extends BasePerson implements Serializable {

  

    
    @Column(name = "DUMMY")
    private String dummy;

    public Child() {
    }

    public Child(String _id, String firstName, String lastName) {
        super(_id, firstName, lastName);
    }
    
      @Override
    public String toString() {
        return  super.toString() + "Child{" + "dummy=" + dummy + '}';
    }
     

}
