package com.app.web;

import com.app.dao.ClientRepository;
import com.app.entity.Agent;
import com.app.entity.Client;
import com.app.exception.ClientAlreadyExistException;
import com.app.service.AccountService;
import com.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String getMessage(){
        return accountService.test();
    }

    @GetMapping("/getByAgent/{tel}")
    public List<Client> getClientByAgent(@PathVariable String tel){
        return clientService.getClientByAgent(tel);
    }

    @GetMapping("/getAgent/{tel}")
    public Agent getAgent(@PathVariable String tel){
        return clientService.getAgent(tel);
    }

    @PostMapping("/creationRequest/{tel}")
    public String creationRequest(@RequestBody Client client,@PathVariable String tel){
        return clientService.creationRequest(client,tel);
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

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id){
        clientService.deleteClient(id);
    }
}
