/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.jpaController;

import davidtest.model.Child;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

/**
 *
 * @author lenovo
 */
public class ChildJpaController implements Dao<Child>{
    private final EntityManagerFactory emf;

    public ChildJpaController(final EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public String create(final Child child) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            System.out.println("to store: " + child);
            em.merge(child);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return child.getId();
    }
    
    @Override
    public void edit(Child t) {
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
    public Child get(String id) {
         EntityManager em = null;
         Child child = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            child = em.find(Child.class, id);
            } finally {
            if (em != null) {
                em.close();
            }
        }

        return child;
    }

    @Override
    public Child getByFirstNameAndLastName(String firstName, String lastName) {
        final StringBuilder queryString = new StringBuilder();
        Query query = null;
        EntityManager em = null;
        Child child = null;
        queryString.append("SELECT c FROM Child c, BasePerson b WHERE c.id = b.id and b.lastName = ");
        queryString.append("\'").append(lastName).append("\'");
        queryString.append(" and b.firstName = ");
        queryString.append("\'").append(firstName).append("\'");
        
        try {
            em = getEntityManager();
            query = em.createQuery(queryString.toString());
            child = (Child) query.getSingleResult();
        }finally {
            if (em != null) {
                em.close();
            }
        }    
        return child;
    }

    @Override
    public List<Child> getAll() {
        return getAll(-1);
    }

    public List<Child> getAll(int max) {
        final StringBuilder queryString = new StringBuilder();
        Query query = null;
        EntityManager em = null;
        List<Child> childs = null;
        queryString.append("SELECT c FROM Child c");
        
        try {
            em = getEntityManager();
            query = em.createQuery(queryString.toString());
            if (max != -1) {
                query.setMaxResults(max);
            }
            childs = (query != null) ? query.getResultList() : null;
        } finally {
            if (em != null) {
                em.close();
            } 
        }     
        return childs; 
    }

    @Override
    public void destroy(final String id) {
        EntityManager em = null;
        Child child = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            child = (Child) em.getReference((Class) Child.class, (Object) id);
            child.getId();
            em.remove(child);
            em.getTransaction().commit();
        } catch (EntityNotFoundException enfe) {
            System.out.println("child don't existe");
        }
        
    }

    
    
}
