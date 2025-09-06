package com.xerofy.journalApplication.service;

import com.xerofy.journalApplication.entity.JournalEntry;
import com.xerofy.journalApplication.entity.User;
import com.xerofy.journalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
   //private static final Logger logger= LoggerFactory.getLogger(JournalEntryServices.class);

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserServices userServices;

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
     @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user=userServices.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userServices.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occured during saving the entry: " +e);
        }


    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id,String userName){
        boolean removed=false;
        try{
            User user= userServices.findByUserName(userName);
            removed=user.getJournalEntries().removeIf(X ->X.getId().equals(id));
            if(removed){
                userServices.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An Error Accored while deleting ID "+e);
        }
        return removed;

    }
}
