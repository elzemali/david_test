/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.jpaController;

import davidtest.model.PersonChildren;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author lenovo
 */
public class PersonChildrenJpaController  implements Dao<PersonChildren> {
    private final EntityManagerFactory emf;

    public PersonChildrenJpaController(final EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public String create(final PersonChildren personChildren) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            System.out.println("to store: " + personChildren);
            em.merge(personChildren);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return "Ajouté !";
    }
    
    @Override
    public void edit(PersonChildren t) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public PersonChildren get(String id) {
        EntityManager em = null;
        PersonChildren personChildren = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personChildren = em.find(PersonChildren.class, id);
            } finally {
            if (em != null) {
                em.close();
            }
        }

        return personChildren;
    }

    @Override
    public PersonChildren getByFirstNameAndLastName(String firstName, String lastName) {        
        return null;
    }

    @Override
    public List<PersonChildren> getAll() {
        return getAll(-1);
    }

    public List<PersonChildren> getAll(int max) {
        final StringBuilder queryString = new StringBuilder();
        Query query = null;
        EntityManager em = null;
        List<PersonChildren> personChildrens = null;
        queryString.append("SELECT p FROM PersonChildren p");
        
        try {
            em = getEntityManager();
            query = em.createQuery(queryString.toString());
            if (max != -1) {
                query.setMaxResults(max);
            }
            personChildrens = (query != null) ? query.getResultList() : null;
        } finally {
            if (em != null) {
                em.close();
            } 
        }     
        return personChildrens; 
    }

    @Override
    public void destroy(final String id) {
          
    }
    
}
