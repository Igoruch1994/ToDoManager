package igoruch.com.service;

import igoruch.com.dao.DAOTaskGroup;
import igoruch.com.dao.DAOUser;
import igoruch.com.entity.TaskGroup;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Igoruch on 29.12.2016.
 */
public class EntityServiceTaskGroupImpl implements EntityServiceTaskGroup {

    @Autowired
    DAOTaskGroup taskGroupRepository;
    @Autowired
    DAOUser userRepository;

    @Override
    public TaskGroup addTaskGroupToUserId(TaskGroup taskGroup,int id) {
        if (userRepository.getUserById(id)!=null) {
            return taskGroupRepository.addTaskGroupToUserId(taskGroup, userRepository.getUserById(id));
        }
        return null;
    }

    @Override
    public void deleteTaskGroup(int id) {
        taskGroupRepository.deleteTaskGroup(id);
    }

    @Override
    public TaskGroup updateTaskGroup(TaskGroup taskGroup) {
        return taskGroupRepository.updateTaskGroup(taskGroup);
    }

    @Override
    public TaskGroup getTaskGroupById(int id) {
        return taskGroupRepository.getTaskGroupById(id);
    }

    @Override
    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepository.getAllTaskGroups();
    }

    @Override
    public List<TaskGroup> getAllTaskGroupsByUserId(int id) {
        return taskGroupRepository.getAllTaskGroupsByUserId(id);
    }
}
