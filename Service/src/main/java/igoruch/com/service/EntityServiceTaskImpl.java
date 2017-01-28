package igoruch.com.service;

import igoruch.com.dao.DAOTask;
import igoruch.com.dao.DAOTaskGroup;
import igoruch.com.entity.Task;
import igoruch.com.entity.TaskGroup;
import javassist.tools.web.BadHttpRequest;
import org.omg.PortableServer.CurrentPackage.NoContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Igoruch on 30.12.2016.
 */
public class EntityServiceTaskImpl implements EntityServiceTask {

    @Autowired
    DAOTask taskRepository;
    @Autowired
    DAOTaskGroup taskGroupRepository;

    @Override
    public Task addTaskToTaskGroupId(Task task,int id) {
        if (taskGroupRepository.getTaskGroupById(id)!=null) {
            return taskRepository.addTaskToTaskGroupId(task, taskGroupRepository.getTaskGroupById(id));
        }
        return null;
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteTask(id);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.updateTask(task);
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.getAllTask();
    }

    @Override
    public List<Task> getAllTaskByTaskGroupId(int id) {
        return taskRepository.getAllTaskByTaskGroupId(id);
    }
}
