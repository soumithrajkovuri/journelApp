package com.learning.journelApp.Repository;

import com.learning.journelApp.entity.JournelEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournelEntryRepo extends MongoRepository<JournelEntry, ObjectId> {
}
