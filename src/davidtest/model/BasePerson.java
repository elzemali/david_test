/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import davidtest.util.BasePersonExtractor;
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
//pourquoi sérializable
//Rendre la classe Abstraite
public class BasePerson {
    @JsonProperty("_id")
    @Id
    @Column(name = "ID")
    private   String id;
    @Column(name = "FIRST_NAME")
    private   String firstName;
    @Column(name = "LAST_NAME")
    private   String lastName;

    public BasePerson() {
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        
        hash += (id != null ? id.hashCode() : 0);
   
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
        return this.id.equals(other.id);           // or using Objects.equals(this.id, other.id);
    }
    
     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("BasePerson{");
        sb.append("id=");
        sb.append(id);
        sb.append(", firstName=");
        sb.append(firstName);
        sb.append(", lastName=");
        sb.append(lastName);
        sb.append("}"); 
        
        return sb.toString();
    }

}
