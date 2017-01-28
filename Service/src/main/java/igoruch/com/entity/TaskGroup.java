package igoruch.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Igoruch on 27.12.2016.
 */
@Entity
@Table(name = "taskGroup")
@NamedQuery(name = "TaskGroup .getAll", query = "SELECT tg from TaskGroup  tg")
public class TaskGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", length = 16)
    private String name;

    @Column(name = "creationDate")
    private Long creationDate=System.currentTimeMillis();

    @ManyToOne(fetch = FetchType.LAZY , cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "taskGroup")
    @JsonIgnore
    private Set<Task> taskSet;

    public TaskGroup(){}

    public TaskGroup(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public void setTaskSet(Set<Task> taskSet) {
        this.taskSet = taskSet;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

}
