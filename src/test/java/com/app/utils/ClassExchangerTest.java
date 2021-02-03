package com.app.utils;

import com.app.cmi.soap.api.AccountInfo;
import com.app.cmi.soap.api.AgencyInfo;
import com.app.cmi.soap.api.AgentInfo;
import com.app.cmi.soap.api.ClientInfo;
import com.app.entity.Account;
import com.app.entity.Agency;
import com.app.entity.Agent;
import com.app.entity.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ClassExchangerTest {

    private ClassExchanger classExchanger;

    @Before
    public void init(){
        classExchanger=new ClassExchanger();
    }

    @Test
    public void testAgencyInfo(){
        System.out.println(classExchanger);
        Agency agency=new Agency(null,"agency 1","casablanca");
        AgencyInfo expected=new AgencyInfo();
        expected.setName("agency 1");

        assertEquals(expected.getName(),classExchanger.createAgencyInfo(agency).getName());
    }

    @Test
    public void testAgentInfo(){
        Agent agent=new Agent("123","oussama","chamlal","123456789",123,15,"root@gmail.com",new Agency());
        AgentInfo expected=new AgentInfo();
        expected.setId("123");
        expected.setFirstName("oussama");
        expected.setLastName("chamlal");
        expected.setPhoneNumber("123456789");
        expected.setPatenteNumber(123);
        expected.setIdentityNumber(15);
        expected.setEmail("root@gmail.com");
        expected.setAgency(new AgencyInfo());

        assertEquals(expected.getEmail(),classExchanger.createAgentInfo(agent).getEmail());
    }

    @Test
    public void testAccountInfo(){
        Account account=new Account(null,"7459",159.0,0.0,"14-7-2021",new Date(),"compte 3000");
        AccountInfo expected=new AccountInfo();
        expected.setStrCreationDate("14-7-2021");
        expected.setCredit(0.0);
        expected.setAmount(159.0);
        expected.setAccountType("compte 3000");
        expected.setAccountNumber("7459");

        assertEquals(expected.getAccountNumber(),classExchanger.createAccountInfo(account).getAccountNumber());
    }

    @Test
    public void testClientInfo(){
        Client client=new Client(null,"oussama","chamlal","marrakech","178","oussama@gmail.com","459",1L,"15789","78",null,null);
        ClientInfo expected=new ClientInfo();
        expected.setFirstName("oussama");
        expected.setLastName("chamlal");
        expected.setAddress("marrakech");
        expected.setCin("178");
        expected.setEmail("oussama@gmail.com");
        expected.setTel("459");
        expected.setAccount(null);
        //expected.setAgency(null);

        assertEquals(expected.getTel(),classExchanger.createClientInfo(client,null,null).getTel());

    }
}