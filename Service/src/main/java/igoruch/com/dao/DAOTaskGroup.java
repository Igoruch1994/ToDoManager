package igoruch.com.dao;

import igoruch.com.entity.TaskGroup;
import igoruch.com.entity.User;

import java.util.List;

/**
 * Created by Igoruch on 29.12.2016.
 */
public interface DAOTaskGroup {
    TaskGroup addTaskGroupToUserId(TaskGroup taskGroup,User user);
    void deleteTaskGroup(int id);
    TaskGroup updateTaskGroup(TaskGroup taskGroup);
    TaskGroup getTaskGroupById(int id);
    List<TaskGroup> getAllTaskGroups();
    List<TaskGroup> getAllTaskGroupsByUserId(int id);
}
