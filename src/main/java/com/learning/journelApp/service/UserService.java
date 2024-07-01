package com.learning.journelApp.service;

import com.learning.journelApp.Repository.UserRepo;
import com.learning.journelApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {


    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return true;
        }catch(Exception e){
            log.error("Error occured for {} :",user.getUserName(),e);
            return false;
        }
    }


    public void saveUser(User user){

        userRepo.save(user);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public List<User> getAll(){

        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){

        return userRepo.findById(id);
    }

    public boolean deleteByID(ObjectId id){
        userRepo.deleteById(id);
        return true;
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }
}
