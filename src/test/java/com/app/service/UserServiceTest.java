package com.app.service;

import com.app.entity.Role;
import com.app.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Test
    public void testSaveUser(){
        /*List<Role> roles=new ArrayList<>();
        roles.add(new Role(null,"ROLE_CLIENT","this is a client"));
        User user=new User(null,"159","123",roles);
        when(userService.createUser(user)).thenReturn(user);
        User expected=userService.createUser(user);

        assertEquals(expected.getUsername(),user.getUsername());
        verify(userService).createUser(user);*/
    }

}