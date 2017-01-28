package igoruch.com.dao;

import igoruch.com.entity.Task;
import igoruch.com.entity.TaskGroup;

import java.util.List;

/**
 * Created by Igoruch on 29.12.2016.
 */
public interface DAOTask {
    Task addTaskToTaskGroupId(Task task, TaskGroup taskGroup);
    void deleteTask(int id);
    Task updateTask(Task task);
    Task getTaskById(int id);
    List<Task> getAllTask();
    List<Task> getAllTaskByTaskGroupId(int id);
}
