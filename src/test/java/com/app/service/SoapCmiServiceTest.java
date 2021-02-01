package com.app.service;

import com.app.cmi.soap.api.ClientCreationRequest;
import com.app.cmi.soap.api.ClientCreationResponse;
import com.app.cmi.soap.api.ClientInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class SoapCmiServiceTest {
    @Mock
    private SoapCmiService soapCmiService;

    @Test
    public void testCreateClientRequest(){
        ClientInfo clientInfo=new ClientInfo();
        //clientInfo.setAgency(null);
        clientInfo.setAccount(null);
        clientInfo.setTel("1789");
        clientInfo.setEmail("root@gmail.com");
        clientInfo.setCin("1478");
        clientInfo.setAddress("casablanca");
        clientInfo.setFirstName("oussama");
        clientInfo.setLastName("chamlal");

        ClientCreationResponse response=new ClientCreationResponse();
        response.setResponse("Request will be processed");

        when(soapCmiService.createClientRequest(clientInfo,"")).thenReturn(response.getResponse());

        String finalResponse=soapCmiService.createClientRequest(clientInfo,"");

        assertEquals(response.getResponse(),finalResponse);
    }
}