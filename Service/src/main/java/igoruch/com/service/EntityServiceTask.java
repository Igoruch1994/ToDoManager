package igoruch.com.service;

import igoruch.com.entity.Task;
import igoruch.com.entity.TaskGroup;

import java.util.List;

/**
 * Created by Igoruch on 30.12.2016.
 */
public interface EntityServiceTask {
    Task addTaskToTaskGroupId(Task task, int id);
    void deleteTask(int id);
    Task updateTask(Task task);
    Task getTaskById(int id);
    List<Task> getAllTask();
    List<Task> getAllTaskByTaskGroupId(int id);
}
