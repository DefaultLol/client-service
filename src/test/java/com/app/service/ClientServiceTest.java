package com.app.service;

import com.app.cmi.soap.api.AccountInfo;
import com.app.cmi.soap.api.AgencyInfo;
import com.app.cmi.soap.api.ClientInfo;
import com.app.dao.ClientRepository;
import com.app.entity.*;
import com.app.exception.ClientNotFoundException;
import com.app.utils.ClassExchanger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
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
    @Mock private UserService userService;
    @Mock private ClassExchanger classExchanger;

    @InjectMocks private ClientService clientService=new ClientService();

    private Client client;
    private Agency agency;
    private Account account;

    @Before
    public void setUp(){
        agency=new Agency(null,"agency 1","casablanca");
        account=new Account(null,"1789",159.0,0.0,"14-7-2021",new Date(),"compte 3000");
        client=new Client(null,"oussama","chamlal","marrakech","149","root@gmail.com","123456789",1L,"","",account,null);
    }

    @Test
    public void testCreationRequest(){
        //set agency info
        AgencyInfo agencyInfo=new AgencyInfo();
        agencyInfo.setName("agency 1");
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
        //clientInfo.setAgency(agencyInfo);

        when(classExchanger.createAgencyInfo(agency)).thenReturn(agencyInfo);
        when(classExchanger.createAccountInfo(account)).thenReturn(accountInfo);
        //when(classExchanger.createClientInfo(client,accountInfo,agencyInfo)).thenReturn(clientInfo);
        String response="Request will ve reviewed";
        when(soapCmiService.createClientRequest(clientInfo)).thenReturn(response);
        //String expected=clientService.creationRequest(client);

        //assertEquals(response,expected);

        verify(soapCmiService).createClientRequest(clientInfo);
    }

    @Test
    public void testCreateClient(){
        List<Role> roles=new ArrayList<>();
        roles.add(new Role(null,"ROLE_CLIENT","This is a client"));
        User user=new User(null,client.getTel(),"123",roles);
        when(accountService.save(client.getAccount())).thenReturn(client.getAccount());
        when(userService.createUser(user)).thenReturn(user);
        when(clientRepository.save(client)).thenReturn(client);

        assertEquals(client,clientService.createClient(client));
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
        when(accountService.delete(client.getAccountID())).thenReturn("Success");
        doNothing().when(clientRepository).deleteById(id);

        clientService.deleteClient(id);

        verify(accountService).delete(client.getAccountID());
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