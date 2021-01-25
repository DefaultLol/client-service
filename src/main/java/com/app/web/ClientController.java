package com.app.web;

import com.app.dao.ClientRepository;
import com.app.entity.Client;
import com.app.exception.ClientAlreadyExistException;
import com.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/creationRequest")
    public String creationRequest(@RequestBody Client client){
        return clientService.creationRequest(client);
    }

    @PostMapping("/create")
    public Client createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @GetMapping("/{tel}")
    public Client findClient(@PathVariable String tel){
        return clientService.getClientByTel(tel);
    }

    @GetMapping("/cmi/{tel}")
    public Client cmiChecker(@PathVariable String tel){
        return clientService.getClientCmi(tel);
    }
}
