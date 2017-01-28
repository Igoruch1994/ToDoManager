package igoruch.com.service;

import igoruch.com.entity.TaskGroup;

import java.util.List;

/**
 * Created by Igoruch on 29.12.2016.
 */
public interface EntityServiceTaskGroup {
    TaskGroup addTaskGroupToUserId(TaskGroup taskGroup,int id);
    void deleteTaskGroup(int id);
    TaskGroup updateTaskGroup(TaskGroup taskGroup);
    TaskGroup getTaskGroupById(int id);
    List<TaskGroup> getAllTaskGroups();
    List<TaskGroup> getAllTaskGroupsByUserId(int id);
}
