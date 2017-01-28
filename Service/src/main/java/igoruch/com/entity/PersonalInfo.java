package igoruch.com.entity;

import javax.persistence.*;

/**
 * Created by Igoruch on 27.12.2016.
 */
@Entity
@Table(name = "personalInfo")
@NamedQuery(name = "PersonalInfo.getAll", query = "SELECT pi from PersonalInfo pi")
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "picture", length = 32)
    private String picture;
    @Column(name = "firstName", length = 32)
    private String firstName;
    @Column(name = "lastName", length = 32)
    private String lastName;
    @Column(name = "phoneNumber", length = 32)
    private String phoneNumber;




    public PersonalInfo(){}

    public PersonalInfo(String picture, String firstName, String lastName, String phoneNumber) {
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
