package com.xerofy.journalApplication.service;

import com.xerofy.journalApplication.entity.JournalEntry;
import com.xerofy.journalApplication.entity.User;
import com.xerofy.journalApplication.repository.JournalEntryRepository;
import com.xerofy.journalApplication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserServices {

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void saveNewEntry(User user ){
        userRepository.save(user);
    }
    public void saveEntry(User user ){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Arrays.asList("User"));
        userRepository.save(user);
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
}
