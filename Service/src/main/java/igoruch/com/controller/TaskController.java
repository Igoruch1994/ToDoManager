package igoruch.com.controller;

import igoruch.com.entity.Task;
import igoruch.com.service.EntityServiceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Igoruch on 30.12.2016.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TaskController {

    EntityServiceTask entityServiceTask;

    @Autowired
    @Qualifier(value="entityServiceTask")
    public void setEntityServiceTask(EntityServiceTask entityServiceTask) {
        this.entityServiceTask = entityServiceTask;
    }

    @RequestMapping(value="/tasks", method = RequestMethod.GET , produces = "application/json")
    public List<Task> getListTaskGroups(){
        List<Task> taskGroups = entityServiceTask.getAllTask();
        return taskGroups;
    }

    @RequestMapping(value="/taskById/{id}", method = RequestMethod.GET, produces = "application/json")
    public Task getTaskById(@PathVariable int id){
        return entityServiceTask.getTaskById(id);
    }

    @RequestMapping(value="/tasksByTaskGroupId/{id}", method = RequestMethod.GET , produces = "application/json")
    public List<Task> getListTasksByTaskGroupId(@PathVariable int id){
        List<Task> tasks = entityServiceTask.getAllTaskByTaskGroupId(id);
        return tasks;
    }

    @RequestMapping(value = "/addTaskToTaskGroupId/{id}", method = RequestMethod.POST)
    public Task addTaskGroup(@RequestBody Task task,@PathVariable int id) {
        return entityServiceTask.addTaskToTaskGroupId(task,id);
    }

    @RequestMapping(value = "deleteTask/{id}", method = RequestMethod.DELETE)
    public void deleteTaskById(@PathVariable int id){
        entityServiceTask.deleteTask(id);
    }

    @RequestMapping(value = "/updateTask/{id}", method = RequestMethod.POST)
    public Task updateTaskGroup(@RequestBody Task task,@PathVariable int id) {
        Task taskInDB=this.getTaskById(id);
        if (task!=null) {
            if (task.getText()!=null) {
                taskInDB.setText(task.getText());
            }

                taskInDB.setStatus(task.getStatus());

        }
        return entityServiceTask.updateTask(taskInDB);
    }
}
