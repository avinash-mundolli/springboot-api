package com.xerofy.journalApplication.service;

import com.xerofy.journalApplication.entity.JournalEntry;
import com.xerofy.journalApplication.entity.User;
import com.xerofy.journalApplication.repository.JournalEntryRepository;
import com.xerofy.journalApplication.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserServices {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void saveUser (User user){
        userRepository.save(user);
    }
    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Arrays.asList("User"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("exception for{}:",user.getUserName(),e);
            return false;
        }
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Use injected encoder
        user.setRole(Arrays.asList("User","ADMIN"));
        userRepository.save(user);
    }
}
