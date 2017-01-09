package com.orionhealth.contacts.services;

import com.orionhealth.contacts.models.User;

import junit.framework.TestCase;

import java.util.List;

import rx.Observable;

/**
 * Created by irineul on 06/01/17.
 */
public class UserServiceTest extends TestCase {

    public void testGetApi() throws Exception {
        UserService userService = new UserService();
        Observable<List<User>> users = userService.getApi().getUsers();
    }
}