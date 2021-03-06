package com.turboocelots.oasis.models;

import com.turboocelots.oasis.models.constants.UserTitle;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Case for Adding Users
 */
public class AddUserTest {
    @Before
    public void setUp() throws Exception {
        UserRepository.clear(); // Clear the entire UserRepository instance
        User user1 = new Reporter("user1","user1pass");
        UserRepository.addUser(user1);
    }


    @Test
    public void addUserWithSameUsername() throws Exception {
        User user2 = new Reporter("user1","user2pass");
        assertFalse(UserRepository.addUser(user2));
        user2 = new Manager("user1", "", "", "", "", UserTitle.Dr,"");
        assertFalse(UserRepository.addUser(user2));
    }

    @Test
    public void addingNullUser() throws Exception {
        assertFalse(UserRepository.addUser(null));
    }

    @Test
    public void addUser() {
        User user3 = new Manager("user3", "", "", "", "", UserTitle.Dr,"");
        assertTrue(UserRepository.addUser(user3));
    }

}