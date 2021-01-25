package com.app.utils;

import com.app.cmi.soap.api.AccountInfo;
import com.app.cmi.soap.api.AgencyInfo;
import com.app.cmi.soap.api.ClientInfo;
import com.app.entity.Account;
import com.app.entity.Agency;
import com.app.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClassExchanger{

    public AgencyInfo createAgencyInfo(Agency agency){
        AgencyInfo agencyInfo=new AgencyInfo();
        agencyInfo.setName(agency.getName());
        return agencyInfo;
    }

    public AccountInfo createAccountInfo(Account account){
        AccountInfo accountInfo=new AccountInfo();
        accountInfo.setAccountNumber(account.getAccountNumber());
        accountInfo.setAccountType(account.getAccountType());
        accountInfo.setAmount(account.getAmount());
        accountInfo.setCredit(0.0);
        accountInfo.setStrCreationDate(account.getStrCreationDate());
        return accountInfo;
    }


    public ClientInfo createClientInfo(Client client,AccountInfo accountInfo,AgencyInfo agencyInfo){
        ClientInfo clientInfo=new ClientInfo();
        clientInfo.setFirstName(client.getFirstName());
        clientInfo.setLastName(client.getLastName());
        clientInfo.setAddress(client.getAddress());
        clientInfo.setCin(client.getCin());
        clientInfo.setEmail(client.getEmail());
        clientInfo.setTel(client.getTel());
        clientInfo.setAgency(agencyInfo);
        clientInfo.setAccount(accountInfo);
        return clientInfo;
    }
}
