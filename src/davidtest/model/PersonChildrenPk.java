/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author lenovo
 */

@Embeddable
public class PersonChildrenPk implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_PERSON")
    private String idPerson;
    @Basic(optional = false)
    @Column(name = "ID_CHILD")
    private String idChild;

    public PersonChildrenPk() {
    }

    public PersonChildrenPk(String idPerson, String idChild) {
        this.idPerson = idPerson;
        this.idChild = idChild;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getIdChild() {
        return idChild;
    }

    public void setIdChild(String idChild) {
        this.idChild = idChild;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        hash += (idChild != null ? idChild.hashCode() : 0);
        
        return hash;
    }

    @Override
    //utiliser la meme loguique pour equals
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }        
        if (getClass() != object.getClass()) {
            return false;
        }
        PersonChildrenPk other = (PersonChildrenPk) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        if ((this.idChild == null && other.idChild != null) || (this.idChild != null && !this.idChild.equals(other.idChild))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("PersonChildrenPk{");
        sb.append("idPerson=");
        sb.append(idPerson);
        sb.append(", idChild=");
        sb.append(idChild);
        sb.append("}");
        
        return sb.toString();
    }

}
