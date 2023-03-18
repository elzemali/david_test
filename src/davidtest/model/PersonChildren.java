/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "person_children")
public class PersonChildren implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonChildrenPk personChildrenPK;

    public PersonChildren() {
    }

    public PersonChildren(PersonChildrenPk personChildrenPK) {
        this.personChildrenPK = personChildrenPK;
    }

    public PersonChildren(String idPerson, String idChild) {
        this.personChildrenPK = new PersonChildrenPk(idPerson, idChild);
    }

    public PersonChildrenPk getPersonChildrenPK() {
        return personChildrenPK;
    }

    public void setPersonChildrenPK(PersonChildrenPk personChildrenPK) {
        this.personChildrenPK = personChildrenPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personChildrenPK != null ? personChildrenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonChildren)) {
            return false;
        }
        PersonChildren other = (PersonChildren) object;
        if ((this.personChildrenPK == null && other.personChildrenPK != null) || (this.personChildrenPK != null && !this.personChildrenPK.equals(other.personChildrenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "davidtest.io.PersonChildren[ personChildrenPK=" + personChildrenPK + " ]";
    }
    
}
