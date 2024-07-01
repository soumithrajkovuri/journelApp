package com.learning.journelApp.service;

import com.learning.journelApp.Repository.JournelEntryRepo;
import com.learning.journelApp.entity.JournelEntry;
import com.learning.journelApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournelEntryService {


    @Autowired
    private JournelEntryRepo journelEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournelEntry journelEntry, String userName){
        try{
            User user=userService.findByUserName(userName);
            JournelEntry saved = journelEntryRepo.save(journelEntry);
            user.getJournelEntries().add(saved);
            userService.saveUser(user);

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while saving",e);
        }

    }

    public void saveEntry(JournelEntry journelEntry){

        journelEntryRepo.save(journelEntry);
    }

    public List<JournelEntry> getAll(){
        return journelEntryRepo.findAll();
    }

    public Optional<JournelEntry> findById(ObjectId id){
        return journelEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteByID(ObjectId id, String userName){
        boolean removed =false;
        try{
            User user=userService.findByUserName(userName);
            removed=user.getJournelEntries().removeIf(x->x.getId().equals(id));

            if(removed){
                userService.saveUser(user);
                journelEntryRepo.deleteById(id);
            }

        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occured",e);
        }
        return removed;
    }
}
