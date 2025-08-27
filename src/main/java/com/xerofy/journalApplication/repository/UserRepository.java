package com.xerofy.journalApplication.repository;
import com.xerofy.journalApplication.entity.JournalEntry;
import com.xerofy.journalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUserName(String username);

    void deleteByUsername(String name);
}
