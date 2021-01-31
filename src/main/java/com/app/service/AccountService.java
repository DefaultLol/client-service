package com.app.service;

import com.app.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service",url = "https://ensaspay-account-service.herokuapp.com/api/account")
public interface AccountService {

    @PostMapping("/save")
    public Account save(@RequestBody Account account);

    @GetMapping("/{id}")
    public Account findAccount(@PathVariable String id);

    @GetMapping("/testing")
    public String test();

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id);
}
