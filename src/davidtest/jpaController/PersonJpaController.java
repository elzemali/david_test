/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.jpaController;

import davidtest.model.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author lenovo
 */
public class PersonJpaController {
  
    private EntityManagerFactory emf;

    public PersonJpaController(final EntityManagerFactory emf) {
        this.emf = null;
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }
    // TODO Enlever transaction, trouver une solution pour creer child 
    // creer Child avant, Creer Person, creer la relation entre les deux
    
    public String create(final Person person) {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
            em.getTransaction().begin();
            em.persist((Object) person);
            em.getTransaction().commit();
        }catch(javax.persistence.RollbackException v){
                
        } 
        
        finally {
            if (em != null) {
                em.close();
            }
        }
        return person.getId();
    }
    
}
