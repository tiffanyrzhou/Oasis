package com.turboocelots.oasis.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Case for Adding Users
 */
public class AddUserTest {
    @Before
    public void setUp() throws Exception {
        Model model = Model.getInstance();
        model.clear(); // Clear the entire Model instance
        User user1 = new Reporter("user1","user1pass");
        model.addUser(user1);
    }


    @Test
    public void addUserWithSameUsername() throws Exception {
        User user2 = new Reporter("user1","user2pass");
        assertFalse(Model.getInstance().addUser(user2));
        user2 = new Manager("user1", "", "", "", "", UserTitle.Dr,"");
        assertFalse(Model.getInstance().addUser(user2));
    }

    @Test
    public void addingNullUser() throws Exception {
        assertFalse(Model.getInstance().addUser(null));
    }

    @Test
    public void addUser() {
        User user3 = new Manager("user3", "", "", "", "", UserTitle.Dr,"");
        assertTrue(Model.getInstance().addUser(user3));
    }

}