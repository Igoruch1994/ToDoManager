package igoruch.com.dao;

import igoruch.com.entity.TaskGroup;
import igoruch.com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Igoruch on 29.12.2016.
 */
public class DAOTaskGroupImpl implements DAOTaskGroup {

    public EntityManager em;

    public DAOTaskGroupImpl() {
        em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();
    }


    @Override
    public TaskGroup addTaskGroupToUserId(TaskGroup taskGroup,User user) {
        em.getTransaction().begin();
        taskGroup.setUser(user);
        TaskGroup taskGroupFromDB = em.merge(taskGroup);
        em.getTransaction().commit();
        return taskGroupFromDB;
    }

    @Override
    public void deleteTaskGroup(int id) {
        em.getTransaction().begin();
        em.remove(getTaskGroupById(id));
        em.getTransaction().commit();
    }

    @Override
    public TaskGroup updateTaskGroup(TaskGroup taskGroup) {
        em.getTransaction().begin();
        em.merge(taskGroup);
        em.getTransaction().commit();
        return taskGroup;
    }

    @Override
    public TaskGroup getTaskGroupById(int id) {
        return em.find(TaskGroup.class, id);
    }

    @Override
    public List<TaskGroup> getAllTaskGroups() {
        TypedQuery<TaskGroup> namedQuery = em.createNamedQuery("TaskGroup .getAll", TaskGroup.class);
        return namedQuery.getResultList();
    }

    @Override
    public List<TaskGroup> getAllTaskGroupsByUserId(int id) {
        TypedQuery<TaskGroup> namedQuery = em.createQuery("select tg from TaskGroup tg where tg.user.id = :idUser", TaskGroup.class).setParameter("idUser",id);
        return namedQuery.getResultList();
    }
}
