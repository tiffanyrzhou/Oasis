package com.turboocelots.oasis.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests getUser method of the UserRepository class
 */
public class GetUserTest {
    private User user1;
    private User user2;
    private User user3;
    @Before
    public void setUp() throws Exception {
        UserRepository.clear(); // Clear the entire UserRepository instance
        user1 = new Reporter("username1","password1");
        user2 = new Reporter("username2","password2");
        user3 = new Reporter("username3","password3");
        UserRepository.addUser(user1);
        UserRepository.addUser(user2);
        UserRepository.addUser(user3);
    }

    @Test
    public void getUser() throws Exception {
        Assert.assertSame(user1, UserRepository.getUser("username1"));
        Assert.assertSame(user2, UserRepository.getUser("username2"));
        Assert.assertSame(user3, UserRepository.getUser("username3"));
    }

}