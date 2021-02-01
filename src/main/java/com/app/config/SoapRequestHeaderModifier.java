package com.app.config;

import com.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.transport.HeadersAwareSenderWebServiceConnection;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;

import javax.xml.soap.MimeHeaders;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class SoapRequestHeaderModifier implements WebServiceMessageCallback {
    private String token;
    public SoapRequestHeaderModifier(String token){
        this.token=token;
    }
    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException {
        /*if (message instanceof SaajSoapMessage) {
            SaajSoapMessage soapMessage = (SaajSoapMessage) message;
            MimeHeaders mimeHeader = soapMessage.getSaajMessage().getMimeHeaders();
            mimeHeader.setHeader("Authorization", "Bearer "+authService.getAccessToken());
        }*/
        TransportContext context = TransportContextHolder.getTransportContext();
        HeadersAwareSenderWebServiceConnection connection = (HeadersAwareSenderWebServiceConnection) context.getConnection();
        connection.addRequestHeader("Authorization", String.format("Bearer %s", token));
    }
}
