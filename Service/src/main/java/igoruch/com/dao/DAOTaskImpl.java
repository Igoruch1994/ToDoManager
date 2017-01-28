package igoruch.com.dao;

import igoruch.com.entity.Task;
import igoruch.com.entity.TaskGroup;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Igoruch on 29.12.2016.
 */
public class DAOTaskImpl implements DAOTask {

    public EntityManager em;

    public DAOTaskImpl() {
        em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();
    }

    @Override
    public Task addTaskToTaskGroupId(Task task, TaskGroup taskGroup) {
        em.getTransaction().begin();
        task.setTaskGroup(taskGroup);
        Task taskGroupFromDB = em.merge(task);
        em.getTransaction().commit();
        return taskGroupFromDB;
    }

    @Override
    public void deleteTask(int id) {
        em.getTransaction().begin();
        em.remove(getTaskById(id));
        em.getTransaction().commit();
    }

    @Override
    public Task updateTask(Task task) {
        em.getTransaction().begin();
        em.merge(task);
        em.getTransaction().commit();
        return task;
    }

    @Override
    public Task getTaskById(int id) {
        return em.find(Task.class, id);
    }

    @Override
    public List<Task> getAllTask() {
        TypedQuery<Task> namedQuery = em.createNamedQuery("Task.getAll", Task.class);
        return namedQuery.getResultList();
    }

    @Override
    public List<Task> getAllTaskByTaskGroupId(int id) {
        TypedQuery<Task> namedQuery = em.createQuery("select t from Task t where t.taskGroup.id = :idTask", Task.class).setParameter("idTask",id);
        return namedQuery.getResultList();
    }
}
