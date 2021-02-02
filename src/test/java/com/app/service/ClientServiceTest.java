package com.app.service;

import com.app.cmi.soap.api.AccountInfo;
import com.app.cmi.soap.api.AgencyInfo;
import com.app.cmi.soap.api.AgentInfo;
import com.app.cmi.soap.api.ClientInfo;
import com.app.dao.ClientRepository;
import com.app.entity.*;
import com.app.exception.ClientNotFoundException;
import com.app.twilio.SmsRequest;
import com.app.twilio.SmsService;
import com.app.utils.ClassExchanger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock private ClientRepository clientRepository;
    @Mock private SoapCmiService soapCmiService;
    @Mock private AccountService accountService;
    @Mock private SmsService smsService;
    @Mock private UserService userService;
    @Mock private AgentService agentService;
    @Mock private ClassExchanger classExchanger;
    @Mock private AuthService authService;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private Agent agent;
    private Account account;

    @Before
    public void setUp(){
        agent=new Agent("159","agentName","agentLastName", "1234567",1478,848,"root@gmail.com",null);
        account=new Account(null,"1789",159.0,0.0,"14-7-2021",new Date(),"compte 3000");
        client=new Client(null,"oussama","chamlal","marrakech","149","root@gmail.com","123456789",1L,"","",account,agent);
    }

    @Test
    public void testCreationRequest(){
        when(authService.getAccessToken()).thenReturn("token");
        when(agentService.getAgentByTel("Bearer token","123456789")).thenReturn(agent);
        //set agent info
        AgentInfo agentInfo=new AgentInfo();
        agentInfo.setId("159");
        agentInfo.setFirstName("agentName");
        agentInfo.setLastName("agentLastName");
        agentInfo.setPhoneNumber("1234567");
        agentInfo.setPatenteNumber(1478);
        agentInfo.setIdentityNumber(848);
        agentInfo.setEmail("root@gmail.com");
        agentInfo.setAgency(null);
        //set account info
        AccountInfo accountInfo=new AccountInfo();
        accountInfo.setAccountNumber("1789");
        accountInfo.setAccountType("compte 3000");
        accountInfo.setAmount(159.0);
        accountInfo.setCredit(0.0);
        accountInfo.setStrCreationDate("14-7-2021");
        //set client info
        ClientInfo clientInfo=new ClientInfo();
        clientInfo.setFirstName("oussama");
        clientInfo.setLastName("chamlal");
        clientInfo.setAddress("marrakech");
        clientInfo.setCin("149");
        clientInfo.setEmail("root@gmail.com");
        clientInfo.setTel("123456789");
        clientInfo.setAccount(accountInfo);
        clientInfo.setAgent(agentInfo);

        when(classExchanger.createAgentInfo(agent)).thenReturn(agentInfo);
        when(classExchanger.createAccountInfo(account)).thenReturn(accountInfo);
        when(classExchanger.createClientInfo(client,accountInfo,agentInfo)).thenReturn(clientInfo);
        String response="Request will ve reviewed";
        when(soapCmiService.createClientRequest(clientInfo,"Bearer token")).thenReturn(response);
        String expected=clientService.creationRequest(client,"123456789");

        assertEquals(response,expected);
    }

    @Test
    public void testCreateClient(){
        List<Role> roles=new ArrayList<>();
        roles.add(new Role(null,"ROLE_CLIENT","This is a client"));
        User user=new User(null,client.getTel(),"123",roles);
        SmsRequest request=new SmsRequest("+212"+client.getTel(),"pass");
        when(authService.getAccessToken()).thenReturn("token");
        when(accountService.save("Bearer token",client.getAccount())).thenReturn(client.getAccount());
        doNothing().when(smsService).sendSms(request);
        when(userService.createUser("Bearer token",user)).thenReturn(user);
        when(clientRepository.save(client)).thenReturn(client);
        Client response=clientService.createClient(client);
        assertEquals(client,response);
    }

    @Test
    public void getClientByTel(){
        String tel="123456789";
        when(clientRepository.findByTel(tel)).thenReturn(client);

        assertEquals(client,clientService.getClientByTel(tel));
    }

    @Test(expected = ClientNotFoundException.class)
    public void clientNotFoundByTel(){
        String tel="12345678";
        when(clientRepository.findByTel(tel)).thenThrow(ClientNotFoundException.class);

        clientService.getClientByTel(tel);
        verify(clientRepository).findByTel(tel);
    }

    @Test
    public void clientCmiFindByTel(){
        String tel="123456789";
        when(clientRepository.findByTel(tel)).thenReturn(client);
        assertEquals(client,clientService.getClientByTel(tel));
    }

    @Test
    public void testDeleteClient(){
        String id="157";
        Optional<Client> op=Optional.of(client);
        when(clientRepository.findById(id)).thenReturn(op);
        when(accountService.delete("token",client.getAccountID())).thenReturn("Success");
        doNothing().when(clientRepository).deleteById(id);

        clientService.deleteClient(id);

        verify(accountService).delete("token",client.getAccountID());
        verify(clientRepository).deleteById(id);
    }

    @Test(expected = ClientNotFoundException.class)
    public void testDeleteNotFound(){
        String id="158";
        when(clientRepository.findById(id)).thenThrow(ClientNotFoundException.class);

        clientService.deleteClient(id);

        verify(clientRepository).findById(id);
    }


}