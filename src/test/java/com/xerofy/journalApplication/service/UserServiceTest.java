package com.xerofy.journalApplication.service;

import com.xerofy.journalApplication.entity.User;
import com.xerofy.journalApplication.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser (User user){
        assertTrue(userServices.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1 , 2, 3",
            "5 , 3 , 8",
            "10 , 5 , 4"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
