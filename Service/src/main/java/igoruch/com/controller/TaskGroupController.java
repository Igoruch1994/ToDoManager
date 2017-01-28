package igoruch.com.controller;

import igoruch.com.entity.TaskGroup;
import igoruch.com.service.EntityServiceTaskGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Igoruch on 29.12.2016.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TaskGroupController {

    EntityServiceTaskGroup entityServiceTaskGroup;

    @Autowired
    @Qualifier(value="entityServiceTaskGroup")
    public void setEntityServiceTaskGroup(EntityServiceTaskGroup entityServiceTaskGroup) {
        this.entityServiceTaskGroup = entityServiceTaskGroup;
    }

    @RequestMapping(value="/taskGroups", method = RequestMethod.GET , produces = "application/json")
    public List<TaskGroup> getListTaskGroups(){
        List<TaskGroup> taskGroups = entityServiceTaskGroup.getAllTaskGroups();
        return taskGroups;
    }

    @RequestMapping(value="/taskGroupsByUserId/{id}", method = RequestMethod.GET , produces = "application/json")
    public List<TaskGroup> getListTaskGroupByUserId(@PathVariable int id){
        List<TaskGroup> taskGroups = entityServiceTaskGroup.getAllTaskGroupsByUserId(id);
        return taskGroups;
    }

    @RequestMapping(value = "/addTaskGroupToUserId/{id}", method = RequestMethod.POST)
    public TaskGroup addTaskGroup(@RequestBody TaskGroup taskGroup,@PathVariable int id) {
        return entityServiceTaskGroup.addTaskGroupToUserId(taskGroup,id);
    }

    @RequestMapping(value = "deleteTaskGroup/{id}", method = RequestMethod.DELETE)
    public void deleteTaskGroupById(@PathVariable int id){
        entityServiceTaskGroup.deleteTaskGroup(id);
    }

    @RequestMapping(value="/taskGroupById/{id}", method = RequestMethod.GET, produces = "application/json")
    public TaskGroup getTaskGroupById(@PathVariable int id){
        return entityServiceTaskGroup.getTaskGroupById(id);
    }

    @RequestMapping(value = "/updateTaskGroup/{id}", method = RequestMethod.POST)
    public TaskGroup updateTaskGroup(@RequestBody TaskGroup taskGroup,@PathVariable int id) {
        TaskGroup taskGroupInDB=this.getTaskGroupById(id);
        if (taskGroup!=null) {
            if (taskGroup.getName()!=null) {
                taskGroupInDB.setName(taskGroup.getName());
            }
        }
        return entityServiceTaskGroup.updateTaskGroup(taskGroupInDB);
    }
}
