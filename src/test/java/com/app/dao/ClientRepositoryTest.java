package com.app.dao;

import com.app.entity.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Before
    public void setUp() {
        Client client;
        for(int i=0;i<5;i++){
            client=new Client(String.valueOf(i),"abdelkarim","guessouss","address","cin","email","tel",1L,"465","486",null,null);
            clientRepository.save(client);
        }
    }

    @Test
    public void test_findAll(){
        List<Client> accounts=clientRepository.findAll();
        assertTrue(accounts.size() > 0);
        assertEquals(accounts.get(0).getFirstName(),"abdelkarim");
    }

    @Test
    public void test_save(){
        Client client=new Client();
        client.setFirstName("firstName");
        client.setFirstName("lastName");
        client.setEmail("test@gmail.com");
        client.setTel("123456789");
        Client expected=clientRepository.save(client);
        assertNotNull(expected);
        assertEquals(client.getEmail(),expected.getEmail());
    }

    @Test
    public void testFindClientById(){
        String id="1";
        Client client=new Client("1","abdelkarim","guessouss","address","cin","email","tel",1L,"465","486",null,null);
        Optional<Client> optional = Optional.of(client);

        assertEquals(optional,clientRepository.findById(id));
    }

    @Test
    public void testDeleteClient(){
        String id="1";
        clientRepository.deleteById(id);
        List<Client> clients=clientRepository.findAll();
        assertTrue(clients.size() == 5);
    }



}