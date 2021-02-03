package com.app.web;

import com.app.entity.Account;
import com.app.entity.Agency;
import com.app.entity.Client;
import com.app.exception.ClientNotFoundException;
import com.app.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ClientControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private ClientService clientService;
    private Client client;
    private Account account;
    private Agency agency;
    @Before
    public void init(){
        agency=new Agency();
        agency.setName("agency 1");

        account=new Account();
        account.setStrCreationDate("14-7-2021");
        account.setCreationDate(new Date());
        account.setAmount(159.0);
        account.setCredit(0.0);
        account.setAccountType("compte 3000");

        client=new Client();
        client.setFirstName("oussama");
        client.setLastName("chamlal");
        client.setTel("123456789");
        client.setEmail("root@gmail.com");
        client.setAccount(account);
    }

    @Test
    public void testCreationRequest() throws Exception {
        when(clientService.creationRequest(client,"123456789")).thenReturn("Request will be checked");
        String url="/api/client/creationRequest/123456789";
        MvcResult result=mockMvc.perform(post(url).contentType("application/json").content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk()).andReturn();

        String response=result.getResponse().getContentAsString();

        assertEquals("Request will be checked",response);
    }

    @Test
    public void testClientCreation() throws Exception {
        when(clientService.createClient(client)).thenReturn(client);
        String url="/api/client/create";
        MvcResult result=mockMvc.perform(post(url).contentType("application/json").content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk()).andReturn();

        String response=result.getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(client),response);
    }

    @Test
    public void testFindByTel() throws Exception{
        String tel="123456789";

        when(clientService.getClientByTel(tel)).thenReturn(client);

        String url="/api/client/"+tel;
        MvcResult result=mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String response=result.getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(client),response);

        verify(clientService).getClientByTel(tel);
    }

    @Test
    public void testNotFindByTel() throws Exception{
        String tel="123456789";

        when(clientService.getClientByTel(tel)).thenThrow(ClientNotFoundException.class);

        String url="/api/client/"+tel;
        mockMvc.perform(get(url)).andExpect(status().isNotFound());
    }

    @Test
    public void testFindCmiByTel() throws Exception{
        String tel="123456789";

        when(clientService.getClientCmi(tel)).thenReturn(client);

        String url="/api/client/cmi/"+tel;
        MvcResult result=mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String response=result.getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(client),response);

        verify(clientService).getClientCmi(tel);
    }




}