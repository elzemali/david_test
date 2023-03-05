/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import davidtest.util.BasePersonExtractor;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.ClassExtractor;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "base_person") 
@Inheritance(strategy = InheritanceType.JOINED) 
@ClassExtractor(BasePersonExtractor.class)
public class BasePerson implements Serializable {

   

    @JsonProperty("_id")
    @Id
    @Column(name = "ID")
    protected   String _id;
   
    @Column(name = "FIRST_NAME")
    protected   String firstName;
    
    @Column(name = "LAST_NAME")
    protected   String lastName;

    public BasePerson() {
        //  this._id = UUID.randomUUID();
        this._id = "";
        this.firstName = "";
        this.lastName = "";
    }

    public BasePerson(String _id, String firstName, String lastName) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this._id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasePerson other = (BasePerson) obj;
        if (!Objects.equals(this._id, other._id)) {
            return false;
        }
        return true;
    }
    
     @Override
    public String toString() {
        return "BasePerson{" + "_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }

}
