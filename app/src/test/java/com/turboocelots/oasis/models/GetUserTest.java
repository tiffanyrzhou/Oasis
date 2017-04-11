package com.turboocelots.oasis.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests getUser method of the Model class
 */
public class GetUserTest {
    private User user1;
    private User user2;
    private User user3;
    @Before
    public void setUp() throws Exception {
        Model.getInstance().clear(); // Clear the entire Model instance
        user1 = new Reporter("username1","password1");
        user2 = new Reporter("username2","password2");
        user3 = new Reporter("username3","password3");
        Model.getInstance().addUser(user1);
        Model.getInstance().addUser(user2);
        Model.getInstance().addUser(user3);
    }

    @Test
    public void getUser() throws Exception {
        Assert.assertSame(user1, Model.getInstance().getUser("username1"));
        Assert.assertSame(user2, Model.getInstance().getUser("username2"));
        Assert.assertSame(user3, Model.getInstance().getUser("username3"));
    }

}