/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "person_children")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonChildren.findAll", query = "SELECT p FROM PersonChildren p"),
    @NamedQuery(name = "PersonChildren.findByIdPerson", query = "SELECT p FROM PersonChildren p WHERE p.personChildrenPK.idPerson = :idPerson"),
    @NamedQuery(name = "PersonChildren.findByIdChild", query = "SELECT p FROM PersonChildren p WHERE p.personChildrenPK.idChild = :idChild")})
public class PersonChildren implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonChildrenPK personChildrenPK;

    public PersonChildren() {
    }

    public PersonChildren(PersonChildrenPK personChildrenPK) {
        this.personChildrenPK = personChildrenPK;
    }

    public PersonChildren(String idPerson, String idChild) {
        this.personChildrenPK = new PersonChildrenPK(idPerson, idChild);
    }

    public PersonChildrenPK getPersonChildrenPK() {
        return personChildrenPK;
    }

    public void setPersonChildrenPK(PersonChildrenPK personChildrenPK) {
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
