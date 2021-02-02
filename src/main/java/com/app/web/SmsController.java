package com.app.web;

import com.app.twilio.Service;
import com.app.twilio.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/sms")
public class SmsController {

    private final Service service;

    @Autowired
    public SmsController(Service service) {
        this.service = service;
    }

    @GetMapping
    public void sendSms() {
        SmsRequest request=new SmsRequest("+212708042169","Hello from app");
        service.sendSms(request);
    }
}
