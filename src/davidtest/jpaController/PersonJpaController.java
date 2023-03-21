/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.jpaController;

import davidtest.model.Child;
import davidtest.model.Person;
import davidtest.model.PersonChildren;
import davidtest.model.models.Persons;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

/**
 *
 * @author lenovo
 */
public class PersonJpaController implements Dao<Person> {
    private final EntityManagerFactory emf;

    public PersonJpaController(final EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public String create(final Person person) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            System.out.println("to store: " + person);
            em.merge(person);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return person.getId();
    }

    public void createPersons(final Persons persons) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            for (Person p : persons) {
                em.merge(p);
                if (p.getChildren() != null) {
                    for (final Child c : p.getChildren()) {
                        em.merge(c);
                        PersonChildren personChildren = new PersonChildren(p.getId(), c.getId());
                        System.out.println(personChildren.toString());
                        em.merge(personChildren);
                    }
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Person t) {
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
    public Person get(String id) {
         EntityManager em = null;
         Person person = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            person = em.find(Person.class, id);
            } finally {
            if (em != null) {
                em.close();
            }
        }

        return person;
    }

    @Override
    public Person getByFirstNameAndLastName(String firstName, String lastName) {
        final StringBuilder queryString = new StringBuilder();
        Query query = null;
        EntityManager em = null;
        Person person = null;
        queryString.append("SELECT p FROM Person p, BasePerson b WHERE p.id = b.id and b.lastName = ");
        queryString.append("\'").append(lastName).append("\'");
        queryString.append(" and b.firstName = ");
        queryString.append("\'").append(firstName).append("\'");
        
        try {
            em = getEntityManager();
            query = em.createQuery(queryString.toString());
            person = (Person) query.getSingleResult();
        }finally {
            if (em != null) {
                em.close();
            }
        }    
        return person;
    }

    @Override
    public List<Person> getAll() {
        return getAll(-1);
    }

    public List<Person> getAll(int max) {
        final StringBuilder queryString = new StringBuilder();
        Query query = null;
        EntityManager em = null;
        List<Person> persons = null;
        queryString.append("SELECT p FROM Person p");
        
        try {
            em = getEntityManager();
            query = em.createQuery(queryString.toString());
            if (max != -1) {
                query.setMaxResults(max);
            }
            persons = (query != null) ? query.getResultList() : null;
        } finally {
            if (em != null) {
                em.close();
            } 
        }     
        return persons; 
    }

    @Override
    public void destroy(final String id) {
        EntityManager em = null;
        Person person = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            person = (Person) em.getReference((Class) Person.class, (Object) id);
            person.getId();
            em.remove(person);
            em.getTransaction().commit();
        } catch (EntityNotFoundException enfe) {
            System.out.println("person don't existe");
        }
        
    }

}
