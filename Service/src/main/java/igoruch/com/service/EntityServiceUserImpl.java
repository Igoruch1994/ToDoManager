package igoruch.com.service;

import igoruch.com.dao.DAOUser;
import igoruch.com.entity.PersonalInfo;
import igoruch.com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Igoruch on 25.12.2016.
 */
public class EntityServiceUserImpl implements EntityServiceUser {

    @Autowired
    DAOUser userRepository;

    @Override
    public User addUser(User user) {

       return userRepository.addUser(user);
    }

    @Override
    public User updateUser(User user) {
       return userRepository.updateUser(user);
    }
    @Override
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }
    @Override
    public User getUser(int id) {
        return userRepository.getUserById(id);
    }
    @Override
    public User getUserByName(String name){
        return userRepository.getUserByName(name);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    @Override
    public PersonalInfo updatePersonalInfo(PersonalInfo pi) {
        return userRepository.updatePersonalInfo(pi);
    }
    @Override
    public PersonalInfo getPersonalInfo(int id) {
        return userRepository.getPersonalInfoById(id);
    }
    @Override
    public List<PersonalInfo> getAllPersonalInfos() {
        return userRepository.getAllPersonalInfos();
    }

}
