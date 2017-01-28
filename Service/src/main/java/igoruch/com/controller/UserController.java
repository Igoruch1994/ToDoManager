package igoruch.com.controller;


import igoruch.com.entity.PersonalInfo;
import igoruch.com.entity.User;
import igoruch.com.service.EntityServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Igoruch on 25.12.2016.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    EntityServiceUser entityServiceUser;

    @Autowired
    @Qualifier(value="entityServiceUser")
    public void setEntityServiceUser(EntityServiceUser entityServiceUser) {
        this.entityServiceUser = entityServiceUser;
    }

    @RequestMapping( value = "/**", method = RequestMethod.OPTIONS )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK); }

    @RequestMapping(value="/users", method = RequestMethod.GET , produces = "application/json")
    public List<User> getListUser(){
        List<User> users = entityServiceUser.getAllUsers();
        return users;
    }

    @RequestMapping(value="/userById/{id}", method = RequestMethod.GET, produces = "application/json")
    public User getUserById(@PathVariable int id){
        return entityServiceUser.getUser(id);
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user,@PathVariable int id) {
        User userInDB=this.getUserById(id);
        if (user!=null) {
            if (user.getLogin()!=null) {
                userInDB.setLogin(user.getLogin());
            }
            if (user.getEmail()!=null) {
                userInDB.setEmail(user.getEmail());
            }
            if (user.getPassword()!=null) {
                userInDB.setPassword(user.getPassword());
            }

        }
        return entityServiceUser.updateUser(userInDB);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return entityServiceUser.addUser(user);
    }

    @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable int id){
         entityServiceUser.deleteUser(id);
    }

    @RequestMapping(value="users/personalInfos", method = RequestMethod.GET , produces = "application/json")
    public List<PersonalInfo> getListPI(){
        List<PersonalInfo> personalInfos = entityServiceUser.getAllPersonalInfos();
        return personalInfos;
    }

    @RequestMapping(value="user/personalInfoById/{id}", method = RequestMethod.GET, produces = "application/json")
    public PersonalInfo getPersonalInfoById(@PathVariable int id){
        return entityServiceUser.getPersonalInfo(id);
    }

    @RequestMapping(value = "user/deletePersonalInfoById/{id}", method = RequestMethod.DELETE)
    public void deletePersonalInfoById(@PathVariable int id){
        entityServiceUser.deleteUser(id);
    }

    @RequestMapping(value = "user/updatePersonalInfo/{id}", method = RequestMethod.POST)
    public PersonalInfo update(@RequestBody PersonalInfo personalInfo, @PathVariable int id) {
        PersonalInfo personalInfoInDB=this.getPersonalInfoById(id);
        if (personalInfo!=null) {
            if (personalInfo.getFirstName()!=null) {
                personalInfoInDB.setFirstName(personalInfo.getFirstName());
            }
            if (personalInfo.getLastName()!=null) {
                personalInfoInDB.setLastName(personalInfo.getLastName());
            }
            if (personalInfo.getPhoneNumber()!=null) {
                personalInfoInDB.setPhoneNumber(personalInfo.getPhoneNumber());
            }
            if (personalInfo.getPicture()!=null) {
                personalInfoInDB.setPicture(personalInfo.getPicture());
            }
        }
        return entityServiceUser.updatePersonalInfo(personalInfoInDB);
    }

    @RequestMapping(value="user/identify", method = RequestMethod.GET, produces = "application/json")
    public User  identifyUser() throws Exception{
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String name = user.getUsername();
        return entityServiceUser.getUserByName(user.getUsername());
    }
}
