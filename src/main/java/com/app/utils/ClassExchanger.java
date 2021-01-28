package com.app.utils;

import com.app.cmi.soap.api.AccountInfo;
import com.app.cmi.soap.api.AgencyInfo;
import com.app.cmi.soap.api.AgentInfo;
import com.app.cmi.soap.api.ClientInfo;
import com.app.entity.Account;
import com.app.entity.Agency;
import com.app.entity.Agent;
import com.app.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClassExchanger{

    public AgencyInfo createAgencyInfo(Agency agency){
        AgencyInfo agencyInfo=new AgencyInfo();
        agencyInfo.setId(agency.getId());
        agencyInfo.setName(agency.getName());
        agencyInfo.setAddress(agency.getAddress());
        return agencyInfo;
    }

    public AgentInfo createAgentInfo(Agent agent){
        AgentInfo agentInfo=new AgentInfo();
        agentInfo.setId(agent.getId());
        agentInfo.setFirstName(agent.getFirst_name());
        agentInfo.setLastName(agent.getLast_name());
        agentInfo.setEmail(agent.getEmail());
        agentInfo.setIdentityNumber(agent.getIdentity_number());
        agentInfo.setPatenteNumber(agent.getPatente_number());
        agentInfo.setPhoneNumber(agent.getPhone_number());
        agentInfo.setAgency(createAgencyInfo(agent.getAgency()));

        return agentInfo;

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


    public ClientInfo createClientInfo(Client client,AccountInfo accountInfo,AgentInfo agentInfo){
        ClientInfo clientInfo=new ClientInfo();
        clientInfo.setFirstName(client.getFirstName());
        clientInfo.setLastName(client.getLastName());
        clientInfo.setAddress(client.getAddress());
        clientInfo.setCin(client.getCin());
        clientInfo.setEmail(client.getEmail());
        clientInfo.setTel(client.getTel());
        clientInfo.setAgent(agentInfo);
        clientInfo.setAccount(accountInfo);
        return clientInfo;
    }
}
