package com.app.service;

import com.app.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service",url = "https://ensaspay-account-service.herokuapp.com/api/account")
public interface AccountService {

    @PostMapping("/save")
    public Account save(@RequestBody Account account);

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id);
}
