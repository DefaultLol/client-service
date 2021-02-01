package com.app.service;

import com.app.entity.Agent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "agent-service",url = "https://ensaspay-zuul-gateway.herokuapp.com/agent")
public interface AgentService {
    @GetMapping("/get/{tel}")
    public Agent getAgentByTel(@RequestHeader("Authorization") String token, @PathVariable String tel);
}
