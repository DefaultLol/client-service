package com.app.service;

import com.app.cmi.soap.api.AccountInfo;
import com.app.cmi.soap.api.AgencyInfo;
import com.app.cmi.soap.api.ClientInfo;
import com.app.dao.ClientRepository;
import com.app.entity.Account;
import com.app.entity.Client;
import com.app.exception.ClientNotFoundException;
import com.app.utils.ClassExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class ClientService {
    private static final String DATE_FORMAT="dd/MM/yy-HH:mm";
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SoapCmiService soapCmiService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClassExchanger exchanger;


    public String creationRequest(Client client){
        AgencyInfo agencyInfo=exchanger.createAgencyInfo(client.getAgency());
        AccountInfo accountInfo=exchanger.createAccountInfo(client.getAccount());
        ClientInfo clientInfo=exchanger.createClientInfo(client,accountInfo,agencyInfo);
        return soapCmiService.createClientRequest(clientInfo);
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
        return clientRepository.save(client);
    }

    public Client getClientByTel(String tel){
        Client client=clientRepository.findByTel(tel);
        if(client ==null) throw new ClientNotFoundException("Client with tel : "+tel+" doesn't exist");
        return client;
    }

    public Client getClientCmi(String tel){
        return clientRepository.findByTel(tel);
    }
}
