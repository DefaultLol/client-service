package com.app.service;

import com.app.entity.Account;
import com.app.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service",url = "https://ensaspay-oauth-service.herokuapp.com/oauth/api/user")
public interface UserService {

    @PostMapping("/create")
    public User createUser(@RequestBody User user);

}
