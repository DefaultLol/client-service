package com.app.service;

import com.app.entity.Agent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "agent-service",url = "https://ensapay-agent-service.herokuapp.com/agent")
public interface AgentService {
    @GetMapping("/get/{tel}")
    public Agent getAgentByTel(@PathVariable String tel);
}
