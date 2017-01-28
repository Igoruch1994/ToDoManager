package igoruch.com.dao;

import igoruch.com.entity.PersonalInfo;
import igoruch.com.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Igoruch on 24.12.2016.
 */
public class DAOUserImpl implements DAOUser {
    public EntityManager em;

    public DAOUserImpl() {
        em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();
    }

    @Override
    public User addUser(User user){
        PersonalInfo pi=new PersonalInfo();
        em.getTransaction().begin();
        PersonalInfo psDB=em.merge(pi);
        em.getTransaction().commit();
        user.setPersonalInfo(psDB);
        em.getTransaction().begin();
        User userFromDB = em.merge(user);
        em.getTransaction().commit();
        return userFromDB;
    }
    @Override
    public void deleteUser(int id){
        em.getTransaction().begin();
        em.remove(getUserById(id));
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(getPersonalInfoById(id));
        em.getTransaction().commit();
    }
    @Override
    public User getUserById(int id){
        return em.find(User.class, id);
    }
    @Override
    public User updateUser(User user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        return user;
    }
    @Override
    public List<User> getAllUsers(){
        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }
    @Override
    public PersonalInfo getPersonalInfoById(int id){
        return em.find(PersonalInfo.class, id);
    }
    @Override
    public PersonalInfo updatePersonalInfo(PersonalInfo pi){
        em.getTransaction().begin();
        em.merge(pi);
        em.getTransaction().commit();
        return pi;
    }
    @Override
    public List<PersonalInfo> getAllPersonalInfos(){
        TypedQuery<PersonalInfo> namedQuery = em.createNamedQuery("PersonalInfo.getAll", PersonalInfo.class);
        return namedQuery.getResultList();
    }

    @Override
    public User getUserByName(String name){
        TypedQuery<User> namedQuery = em.createQuery("select u from User u where u.login = :namee", User.class).setParameter("namee",name);
        if (namedQuery.getResultList().isEmpty()){
            return null;
        }else return namedQuery.getSingleResult();
    }
}
