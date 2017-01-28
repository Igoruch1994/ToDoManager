package igoruch.com.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Igoruch on 24.12.2016.
 */

@Entity
@Table(name = "user")
@NamedQuery(name = "User.getAll", query = "SELECT u from User u")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "login", length = 32)
    private String login;
    @Column(name = "password", length = 32)
    private String password;
    @Column(name = "email", length = 32)
    private String email;
    @Column(name = "registrationDate")
    private Long registrationDate=System.currentTimeMillis();


    @OneToOne(optional = false)
    @JoinColumn(name="personalInfo_id", unique = true, nullable = true)
    @JsonIgnore
    private PersonalInfo personalInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<TaskGroup> taskGroupSet;


    public User(){}

    public User( String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;

    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public Set<TaskGroup> getTaskGroupSet() {
        return taskGroupSet;
    }

    public void setTaskGroupSet(Set<TaskGroup> taskGroupSet) {
        this.taskGroupSet = taskGroupSet;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRegistrationDate() {
        return registrationDate;
    }


}
