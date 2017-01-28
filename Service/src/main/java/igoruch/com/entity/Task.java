package igoruch.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Igoruch on 27.12.2016.
 */
@Entity
@Table(name = "task")
@NamedQuery(name = "Task.getAll", query = "SELECT t from Task t")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "text", length = 70)
    private String text;
    @Column(name = "status")
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY , cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "taskGroup_id", nullable = false)
    @JsonIgnore
    private TaskGroup taskGroup;


    public Task(){}

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }

    public Task(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
