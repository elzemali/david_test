/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "child")
public class Child extends BasePerson {

    public Child() {
    }
    /**
     *  toString personnalisé de child avec Child{
     *  ... au lieu de BasePerson{...
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //TODO verifier si il est mieux de faire toString directement
        sb.append("Child{");
        sb.append("id=");
        sb.append(getId());
        sb.append(", firstName=");
        sb.append(getFirstName());
        sb.append(", lastName=");
        sb.append(getLastName());
        sb.append("}"); 
      
        return  sb.toString();
    }
     

}
