package com.app.service;

import com.app.cmi.soap.api.ClientCreationRequest;
import com.app.cmi.soap.api.ClientCreationResponse;
import com.app.cmi.soap.api.ClientInfo;
import com.app.config.SoapRequestHeaderModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapCmiService {
    @Autowired
    private Jaxb2Marshaller marshaller;

    @Value("${gatewayUrl}")
    private String url;

    private WebServiceTemplate template;


    public String createClientRequest(ClientInfo clientInfo,String token){
        ClientCreationRequest request=new ClientCreationRequest();
        request.setClientInfo(clientInfo);
        template=new WebServiceTemplate(marshaller);
        ClientCreationResponse response= (ClientCreationResponse) template.marshalSendAndReceive(url+"soapWS",request,new SoapRequestHeaderModifier(token));
        return response.getResponse();
    }
}
