package com.app.service;

import com.app.entity.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gatewayUrl}")
    private String url;

    public String getAccessToken(){
        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization","Basic Y2xpZW50OnNlY3JldA==");
        HttpEntity<String> entity=new HttpEntity<>("body",headers);
        LoginResponse response=restTemplate.postForObject(url+"oauth/token?grant_type=password&username=service&password=root",entity,LoginResponse.class);
        System.out.println(response.getAccess_token());
        return response.getAccess_token();
    }
}
