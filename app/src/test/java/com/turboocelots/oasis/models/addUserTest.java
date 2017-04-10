package com.turboocelots.oasis.models;

import android.graphics.PorterDuff;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static org.junit.Assert.*;

/**
 * Created by Tiffany on 4/9/17.
 */
public class addUserTest {
    Model model;
    User user1;
    User user2;
    User user3;
    @Before
    public void setUp() throws Exception {
        model = new Model();
        user1 = new Reporter("user1","user1pass");
        Model.getInstance().addUser(user1);
    }


    @Test
    public void addUserWithSameUsername() throws Exception {
        user2 = new Reporter("user1","user2pass");
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
        user3 = new Manager("user3", "", "", "", "", UserTitle.Dr,"");
        assertTrue(Model.getInstance().addUser(user3));
    }

}