package com.app.service;

import com.app.entity.Account;
import com.app.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service",url = "https://ensaspay-zuul-gateway.herokuapp.com/oauth/api/user")
public interface UserService {

    @PostMapping("/create")
    public User createUser(@RequestHeader("Authorization") String token,@RequestBody User user);

    @DeleteMapping("/{id}")
    public String deleteUser(@RequestHeader("Authorization") String token,@PathVariable Long id);
}
