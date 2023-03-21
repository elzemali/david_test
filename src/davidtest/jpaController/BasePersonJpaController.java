/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.jpaController;

import davidtest.model.BasePerson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

/**
 *
 * @author lenovo
 */
public class BasePersonJpaController implements Dao<BasePerson>{
    private final EntityManagerFactory emf;

    public BasePersonJpaController(final EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public String create(final BasePerson basePerson) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            System.out.println("to store: " + basePerson);
            em.merge(basePerson);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return basePerson.getId();
    }
    
    @Override
    public void edit(BasePerson t) {
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
    public BasePerson get(String id) {
         EntityManager em = null;
         BasePerson basePerson = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            basePerson = em.find(BasePerson.class, id);
            } finally {
            if (em != null) {
                em.close();
            }
        }

        return basePerson;
    }

    @Override
    public BasePerson getByFirstNameAndLastName(String firstName, String lastName) {
        final StringBuilder queryString = new StringBuilder();
        Query query = null;
        EntityManager em = null;
        BasePerson basePerson = null;
        queryString.append("SELECT b FROM BasePerson c WHERE  b.lastName = ");
        queryString.append("\'").append(lastName).append("\'");
        queryString.append(" and b.firstName = ");
        queryString.append("\'").append(firstName).append("\'");
        
        try {
            em = getEntityManager();
            query = em.createQuery(queryString.toString());
            basePerson = (BasePerson) query.getSingleResult();
        }finally {
            if (em != null) {
                em.close();
            }
        }    
        return basePerson;
    }

    @Override
    public List<BasePerson> getAll() {
        return getAll(-1);
    }

    public List<BasePerson> getAll(int max) {
        final StringBuilder queryString = new StringBuilder();
        Query query = null;
        EntityManager em = null;
        List<BasePerson> basePersons = null;
        queryString.append("SELECT b FROM BasePerson b");
        
        try {
            em = getEntityManager();
            query = em.createQuery(queryString.toString());
            if (max != -1) {
                query.setMaxResults(max);
            }
            basePersons = (query != null) ? query.getResultList() : null;
        } finally {
            if (em != null) {
                em.close();
            } 
        }     
        return basePersons; 
    }

    @Override
    public void destroy(final String id) {
        EntityManager em = null;
        BasePerson basePerson = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            basePerson = (BasePerson) em.getReference((Class) BasePerson.class, (Object) id);
            basePerson.getId();
            em.remove(basePerson);
            em.getTransaction().commit();
        } catch (EntityNotFoundException enfe) {
            System.out.println("basePerson don't existe");
        }
        
    }

    
    
}
