package igoruch.com.service;

import igoruch.com.entity.PersonalInfo;
import igoruch.com.entity.User;

import java.util.List;

/**
 * Created by Igoruch on 25.12.2016.
 */
public interface EntityServiceUser {
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    User getUser(int id);
    User getUserByName(String name);
    List<User> getAllUsers();
    PersonalInfo updatePersonalInfo(PersonalInfo pi);
    PersonalInfo getPersonalInfo(int id);
    List<PersonalInfo> getAllPersonalInfos();
}
