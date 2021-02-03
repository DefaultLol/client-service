package com.app.service;

import com.app.twilio.SmsRequest;
import com.app.twilio.TwilioConfiguration;
import com.app.twilio.TwilioSmsSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwilioSmsSenderTest {

    @Mock
    private TwilioConfiguration twilioConfiguration;

    @InjectMocks
    private TwilioSmsSender twilioSmsSender=new TwilioSmsSender(twilioConfiguration);

    @Test(expected = NullPointerException.class)
    public void testSendingSmsError(){
        SmsRequest smsRequest=new SmsRequest();
        smsRequest.setPhoneNumber("8465");
        smsRequest.setMessage("Hello");

        twilioSmsSender.sendSms(smsRequest);
    }




}
