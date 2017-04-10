package com.turboocelots.oasis.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Shane on 4/9/17.
 */
public class GetUserTest {
    Model model;
    User user1;
    User user2;
    User user3;
    @Before
    public void setUp() throws Exception {
        model = new Model();
        user1 = new Reporter("username1","password1");
        user2 = new Reporter("username2","password2");
        user3 = new Reporter("username3","password3");
        model.addUser(user1);
        model.addUser(user2);
        model.addUser(user3);
    }

    @Test
    public void getUser() throws Exception {
        Assert.assertSame(user1, model.getUser("username1"));
        Assert.assertSame(user2, model.getUser("username2"));
        Assert.assertSame(user3, model.getUser("username3"));
    }

}