package com.app.config;

import com.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.soap.MimeHeaders;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class SoapRequestHeaderModifier implements WebServiceMessageCallback {
    @Autowired
    private AuthService authService;

    @Override
    public void doWithMessage(WebServiceMessage message) {
        if (message instanceof SaajSoapMessage) {
            SaajSoapMessage soapMessage = (SaajSoapMessage) message;
            MimeHeaders mimeHeader = soapMessage.getSaajMessage().getMimeHeaders();
            mimeHeader.setHeader("Authorization", "Bearer "+authService.getAccessToken());
        }
    }
}
