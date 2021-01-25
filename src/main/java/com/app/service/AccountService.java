package com.app.service;

import com.app.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service",url = "http://localhost:8082/api/account")
public interface AccountService {

    @PostMapping("/save")
    public Account save(@RequestBody Account account);
}
