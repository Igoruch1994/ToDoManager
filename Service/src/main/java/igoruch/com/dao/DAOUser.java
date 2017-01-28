package igoruch.com.dao;

import igoruch.com.entity.PersonalInfo;
import igoruch.com.entity.User;

import java.util.List;

/**
 * Created by Igoruch on 25.12.2016.
 */
public interface DAOUser {
    User addUser(User user);
    void deleteUser(int id);
    User updateUser(User user);
    User getUserById(int id);
    User getUserByName(String name);
    List<User> getAllUsers();
    PersonalInfo getPersonalInfoById(int id);
    PersonalInfo updatePersonalInfo(PersonalInfo pi);
    List<PersonalInfo> getAllPersonalInfos();
}
