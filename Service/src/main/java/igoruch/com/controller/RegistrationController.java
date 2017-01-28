package igoruch.com.controller;

import igoruch.com.entity.User;
import igoruch.com.service.EntityServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Igoruch on 10.01.2017.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RegistrationController {

    EntityServiceUser entityServiceUser;

    /*^                 # start-of-string
    (?=.*[0-9])       # a digit must occur at least once
    (?=.*[a-z])       # a lower case letter must occur at least once
    (?=.*[A-Z])       # an upper case letter must occur at least once
    (?=.*[@#$%^&+=])  # a special character must occur at least once
    (?=\S+$)          # no whitespace allowed in the entire string
    .{8,}             # anything, at least eight places though
    $                 # end-of-string*/
    String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Autowired
    @Qualifier(value="entityServiceUser")
    public void setEntityServiceUser(EntityServiceUser entityServiceUser) {
        this.entityServiceUser = entityServiceUser;
    }

    @RequestMapping(value = "/registrateUser", method = RequestMethod.POST, produces = "text/plain")
    public String  addUser(@RequestBody User user) {
        if (user==null){
            return "Empty field";
        }
        if (user.getLogin()==null){
            return "Empty login";
        }
        if (entityServiceUser.getUserByName(user.getLogin())!=null){
            return "User with this login already exist!";
        }
        if (user.getLogin().length()<6){
            return "Invalid login. Less then 6 characters";
        }
        if (user.getPassword()==null){
            return "Empty password";
        }
        if (!user.getPassword().matches(passwordRegex)){
            return "Invalid password!";
        }
        if (user.getEmail()==null){
            return "Empty email";
        }
        if (!user.getEmail().matches(emailRegex)){
            return "Invalid email!";
        }
        entityServiceUser.addUser(user);
        return  "Success registration "+user.getLogin()+" !";
    }
}
