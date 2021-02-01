package com.app.service;

import com.app.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountService accountService;

    @Test
    public void testSaveAccount(){
        /*Account account=new Account(null,"1578",159.0,0.0,"14-7-2021",new Date(),"compte 3000");
        when(accountService.save(account)).thenReturn(account);
        Account expected=accountService.save(account);

        assertEquals(expected.getAccountNumber(),account.getAccountNumber());*/
    }

    @Test
    public void testDeleteAccount(){
        /*String id="159";
        when(accountService.delete(id)).thenReturn("Account with id : "+id+" deleted successfully");

        String expected="Account with id : 159 deleted successfully";
        String response=accountService.delete(id);
        
        assertEquals(expected,response);*/
    }
}