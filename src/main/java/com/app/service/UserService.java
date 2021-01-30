package com.app.service;

import com.app.entity.Account;
import com.app.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service",url = "https://ensaspay-oauth-service.herokuapp.com/oauth/api/user")
public interface UserService {

    @PostMapping("/create")
    public User createUser(@RequestBody User user);

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id);
}
