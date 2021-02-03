package com.app.service;

import com.app.entity.LoginResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock private RestTemplate restTemplate;

    @InjectMocks private AuthService authService=new AuthService();

    @Value("${gatewayUrl}")
    private String url;

    @Test
    public void testInternalAuthentication(){
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setAccess_token("123456789");
        String url1=url+"oauth/token?grant_type=password&username=service&password=root";

        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization","Basic Y2xpZW50OnNlY3JldA==");
        HttpEntity<String> entity=new HttpEntity<>("body",headers);
        when(restTemplate.postForObject(url1,entity,LoginResponse.class)).thenReturn(loginResponse);
        String response = authService.getAccessToken();

        assertEquals(loginResponse.getAccess_token(),response);
    }

}