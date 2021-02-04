package com.app.service;

import com.app.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service",url = "https://ensaspay-zuul-gateway.herokuapp.com/api/account")
public interface AccountService {

    @PostMapping("/save")
    public Account save(@RequestHeader("Authorization") String token, @RequestBody Account account);

    @GetMapping("/{id}")
    public Account findAccount(@RequestHeader("Authorization") String token, @PathVariable String id);

    @GetMapping("/testing")
    public String test();

    @GetMapping("/alimentation/{accountID}/{amount}")
    public Account alimentationAccount(@RequestHeader("Authorization") String token, String accountID ,double amount);

    @DeleteMapping("/{id}")
    public String delete(@RequestHeader("Authorization") String token,@PathVariable String id);
}
