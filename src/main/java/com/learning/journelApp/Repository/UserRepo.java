package com.learning.journelApp.Repository;


import com.learning.journelApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);
    void deleteByUserName(String username);
}
