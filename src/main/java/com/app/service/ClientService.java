package com.app.service;

import com.app.cmi.soap.api.AccountInfo;
import com.app.cmi.soap.api.AgentInfo;
import com.app.cmi.soap.api.ClientInfo;
import com.app.dao.ClientRepository;
import com.app.entity.*;
import com.app.exception.*;
import com.app.twilio.SmsRequest;
import com.app.twilio.SmsService;
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
    @Autowired private AuthService authService;
    @Autowired private SmsService smsService;


    public String creationRequest(Client client,String tel){
        String token;
        try{
            token="Bearer "+authService.getAccessToken();
        }catch(Exception e){
            throw new InternalAuthenticationException("Error while authenticating");
        }
        System.out.println("token : "+token);
        checkTelExist(client.getTel());
        Agent agent=agentService.getAgentByTel(token,tel);

        AgentInfo agentInfo=exchanger.createAgentInfo(agent);
        AccountInfo accountInfo=exchanger.createAccountInfo(client.getAccount());
        ClientInfo clientInfo=exchanger.createClientInfo(client,accountInfo,agentInfo);
        try{
            return soapCmiService.createClientRequest(clientInfo,token);
        }
        catch(Exception e){
            throw new SoapConnectionServiceException("Can't connect to cmi service");
        }
    }

    public Agent getAgent(String tel){
        try{
            String token="Bearer "+authService.getAccessToken();
            Agent agent=agentService.getAgentByTel(token,tel);
            return agent;
        }
        catch(Exception exception){
            throw new ClientNotFoundException("Agent not found");
        }
    }

    public List<Client> getClientByAgent(String tel){
        String token="Bearer "+authService.getAccessToken();
        Agent agent=getAgent(tel);
        List<Client> clients=clientRepository.findByAgentID(agent.getId());
        for(Client client:clients){
            Account account=accountService.findAccount(token,client.getAccountID());
            client.setAgent(agent);
            client.setAccount(account);
        }
        return clients;
    }

    public Client createClient(Client client){
        String token="Bearer "+authService.getAccessToken();
        checkTelExist(client.getTel());
        //generate fields for the account and setting them
        SimpleDateFormat format=new SimpleDateFormat();
        String accountNumber = UUID.randomUUID().toString().replace("-","");
        client.getAccount().setAccountNumber(accountNumber);
        Date creationDate=new Date();
        client.getAccount().setCreationDate(creationDate);
        client.getAccount().setStrCreationDate(format.format(creationDate));
        //save account in account-service
        Account account=accountService.save(token,client.getAccount());
        //setting account info in client
        client.setAccount(account);
        client.setAccountID(account.getId());
        //setting agent info in client
        client.setAgent(client.getAgent());
        client.setAgentID(client.getAgent().getId());
        //create user
        String randomPassword = UUID.randomUUID().toString().replace("-","").substring(0,8);
        SmsRequest request=new SmsRequest("+212"+client.getTel(),randomPassword);
        smsService.sendSms(request);
        List<Role> roles=new ArrayList<>();
        roles.add(new Role(null,"ROLE_CLIENT","This is a client"));

        User user=new User(null,client.getTel(),"123",roles);
        User createdUser=userService.createUser(token,user);
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

    public Client getClient(String tel){
        String token="Bearer "+authService.getAccessToken();
        Client client=clientRepository.findByTel(tel);
        if(client==null) throw new ClientNotFoundException("Client not found");
        Account account=accountService.findAccount(token,client.getAccountID());
        client.setAccount(account);
        return client;
    }

    public void deleteClient(String id){
        Client client=clientRepository.findById(id).orElseThrow(()->new ClientNotFoundException("Client with id : "+id+" not found"));
        try{
            String token="Bearer "+authService.getAccessToken();
            userService.deleteUser(token,client.getUserID());
            accountService.delete(token,client.getAccountID());
            clientRepository.deleteById(id);
        }catch(Exception e){
            throw new ErrorWhileDeletingException("Error while deleting client");
        }
    }
}
