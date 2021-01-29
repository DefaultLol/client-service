package com.app.service;

import com.app.cmi.soap.api.AccountInfo;
import com.app.cmi.soap.api.AgentInfo;
import com.app.cmi.soap.api.ClientInfo;
import com.app.dao.ClientRepository;
import com.app.entity.*;
import com.app.exception.ClientNotFoundException;
import com.app.exception.ClientAlreadyExistException;
import com.app.utils.ClassExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private static final String DATE_FORMAT="dd/MM/yy-HH:mm";
    @Autowired private ClientRepository clientRepository;
    @Autowired private SoapCmiService soapCmiService;
    @Autowired private AccountService accountService;
    @Autowired private AgentService agentService;
    @Autowired private UserService userService;
    @Autowired private ClassExchanger exchanger;


    public String creationRequest(Client client,String tel){
        getClientByTel(client.getTel());
        Agent agent=agentService.getAgentByTel(tel);

        AgentInfo agentInfo=exchanger.createAgentInfo(agent);
        AccountInfo accountInfo=exchanger.createAccountInfo(client.getAccount());
        ClientInfo clientInfo=exchanger.createClientInfo(client,accountInfo,agentInfo);
        return soapCmiService.createClientRequest(clientInfo);
    }

    public List<Client> getClientByAgent(String tel){
        Agent agent;
        try{
            agent=agentService.getAgentByTel(tel);
        }
        catch(Exception exception){
            throw new ClientNotFoundException("Agent not found");
        }
        List<Client> clients=clientRepository.findByAgentID(agent.getId());
        for(Client client:clients){
            Account account=accountService.findAccount(client.getAccountID());
            client.setAgent(agent);
            client.setAccount(account);
        }
        return clients;
    }

    public Client createClient(Client client){
        //generate fields for the account and setting them
        SimpleDateFormat format=new SimpleDateFormat();
        String accountNumber = UUID.randomUUID().toString().replace("-","");
        client.getAccount().setAccountNumber(accountNumber);
        Date creationDate=new Date();
        client.getAccount().setCreationDate(creationDate);
        client.getAccount().setStrCreationDate(format.format(creationDate));
        //save account in account-service
        Account account=accountService.save(client.getAccount());
        //setting account info in client
        client.setAccount(account);
        client.setAccountID(account.getId());
        //setting agent info in client
        client.setAgent(client.getAgent());
        client.setAgentID(client.getAgent().getId());
        //create user
        //String randomPassword = UUID.randomUUID().toString().replace("-","").substring(0,8);
        List<Role> roles=new ArrayList<>();
        roles.add(new Role(null,"ROLE_CLIENT","This is a client"));
        User user=new User(null,client.getTel(),"123",roles);
        User createdUser=userService.createUser(user);
        client.setUserID(createdUser.getId());
        //save client
        return clientRepository.save(client);
    }

    public Client getClientByTel(String tel){
        Client client=clientRepository.findByTel(tel);
        if(client ==null) throw new ClientNotFoundException("Client with tel : "+tel+" doesn't exist");
        return client;
    }

    public void checkTelExist(String tel){
        Client client=clientRepository.findByTel(tel);
        if(client!=null) throw new ClientAlreadyExistException("Client with this tel already exist");
    }

    public Client getClientCmi(String tel){
        return clientRepository.findByTel(tel);
    }

    public void deleteClient(String id){
        Client client=clientRepository.findById(id).orElseThrow(()->new ClientNotFoundException("Client with id : "+id+" not found"));
        accountService.delete(client.getAccountID());
        clientRepository.deleteById(id);
    }
}
